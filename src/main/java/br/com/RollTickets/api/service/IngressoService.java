package br.com.RollTickets.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.RollTickets.api.dto.IngressoCreateDTO;
import br.com.RollTickets.api.dto.IngressoResponseDTO;
import br.com.RollTickets.api.dto.IngressoUpdateDTO;
import br.com.RollTickets.api.entity.Assento;
import br.com.RollTickets.api.entity.Cliente;
import br.com.RollTickets.api.entity.Compra;
import br.com.RollTickets.api.entity.Ingresso;
import br.com.RollTickets.api.entity.Sessao;
import br.com.RollTickets.api.enums.status;
import br.com.RollTickets.api.repository.AssentoRepository;
import br.com.RollTickets.api.repository.ClienteRepository;
import br.com.RollTickets.api.repository.CompraRepository;
import br.com.RollTickets.api.repository.IngressoRepository;
import br.com.RollTickets.api.repository.PagamentoRepository;
import br.com.RollTickets.api.repository.SessaoRepository;
import jakarta.transaction.Transactional;
import br.com.RollTickets.api.mapper.IngressoMapper;

@Service
public class IngressoService {

    @Autowired
    private IngressoRepository ingressoRepository;

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private AssentoRepository assentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public IngressoResponseDTO store(IngressoCreateDTO ingressoCreateDTO) { //Como eu só tô com os IDs agr, preciso realizar uma busca das sessões, assentos e cliente específicos

        Sessao sessao = sessaoRepository.findById(ingressoCreateDTO.sessaoid())
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        Assento assento = assentoRepository.findById(ingressoCreateDTO.assentoid())
                .orElseThrow(() -> new RuntimeException("Assento não encontrado"));

        Cliente cliente = clienteRepository.findById(ingressoCreateDTO.clienteid())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        boolean assentoOcupado = ingressoRepository.existsBySessaoAndAssento(sessao, assento);
        if (assentoOcupado) {
            throw new RuntimeException("Assento já vendido para essa sessão");
        }

        Ingresso ingresso = IngressoMapper.toEntity(ingressoCreateDTO, sessao, assento, cliente);
        ingressoRepository.save(ingresso);
        return IngressoMapper.toDTO(ingresso);
    }

    public List<IngressoResponseDTO> list() {
        return ingressoRepository.findAll()
                .stream()
                .map(IngressoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<IngressoResponseDTO> buscarIngressosPorCliente(Long id) {
        List<Ingresso> ingressos = ingressoRepository.findByClienteId(id);

        // Converter lista de entidades para lista de DTOs
        return ingressos.stream()
                .map(IngressoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public IngressoResponseDTO show(Long id) {
        Ingresso ingresso = ingressoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingresso não encontrado: " + id));
        return IngressoMapper.toDTO(ingresso);
    }

    public IngressoResponseDTO update(IngressoUpdateDTO dto) {
        Ingresso ingressoExistente = ingressoRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Ingresso não encontrado para alteração: " + dto.id()));

        IngressoMapper.updateEntity(dto, ingressoExistente);
        ingressoRepository.save(ingressoExistente);
        return IngressoMapper.toDTO(ingressoExistente);
    }

    

    public void destroy(Long id) {
        if (!ingressoRepository.existsById(id)) {
            throw new RuntimeException("Ingresso não encontrado para exclusão: " + id);
        }
        ingressoRepository.deleteById(id);
    }
    
    //lista os ingressos pendentes
    public List<Ingresso> listarIngressosPendentesPorCliente(Long clienteId) {
        return ingressoRepository.findByClienteIdAndPagamentoStatus(clienteId, status.PENDENTE);
    }

    // remove o ingresso da compra e libera o assento
    @Transactional
    public void removerIngressoEPossivelmenteCompra(Long ingressoId) {
        Ingresso ingresso = ingressoRepository.findById(ingressoId)
                .orElseThrow(() -> new RuntimeException("Ingresso não encontrado"));

        Assento assento = ingresso.getAssento();
        Compra compra = ingresso.getCompra();

        // Atualiza a lista de ingressos da compra removendo o ingresso a ser deletado
        if (compra != null) {
            compra.getIngressos().removeIf(i -> i.getId() == ingressoId);
        }

        ingressoRepository.delete(ingresso);
        assentoRepository.delete(assento);

        // Se a compra ficar sem ingressos, delete ela também
        if (compra != null && compra.getIngressos().isEmpty()) {
            pagamentoRepository.findByCompraId(compra.getId()).ifPresent(pagamentoRepository::delete);
            compraRepository.delete(compra);
        }
    }
}
