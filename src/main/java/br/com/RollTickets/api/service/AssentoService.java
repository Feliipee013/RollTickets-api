package br.com.RollTickets.api.service;

import java.util.ArrayList;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.RollTickets.api.dto.AssentoCreateDTO;
import br.com.RollTickets.api.dto.AssentoResponseDTO;
import br.com.RollTickets.api.dto.AssentoStatusResponseDTO;
import br.com.RollTickets.api.dto.AssentoUpdateDTO;
import br.com.RollTickets.api.entity.Assento;
import br.com.RollTickets.api.entity.Ingresso;
import br.com.RollTickets.api.entity.Pagamento;
import br.com.RollTickets.api.entity.Sessao;
import br.com.RollTickets.api.mapper.AssentoMapper;
import br.com.RollTickets.api.repository.AssentoRepository;
import br.com.RollTickets.api.repository.IngressoRepository;

@Service
public class AssentoService {

    @Autowired
    private AssentoRepository assentoRepository;

    @Autowired
    private AssentoMapper assentoMapper;

    @Autowired
    private IngressoRepository ingressoRepository;

    public AssentoResponseDTO store(AssentoCreateDTO assentoCreateDTO) {

        boolean existe = assentoRepository.existsByNumeroAndFileiraAndSessaoId(
                assentoCreateDTO.numero(),
                assentoCreateDTO.fileira(),
                assentoCreateDTO.sessaoId());

        if (existe) {
            throw new RuntimeException("Assento já reservado para essa sessão.");
        }

        Assento assento = assentoMapper.toEntity(assentoCreateDTO);
        assentoRepository.save(assento);
        return assentoMapper.toDTO(assento);
    }

    public List<AssentoResponseDTO> list() {
        return assentoRepository.findAll()
                .stream()
                .map(assentoMapper::toDTO)
                .toList();
    }

    public AssentoResponseDTO show(long id) {
        Assento assento = assentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assento com id " + id + " não encontrado"));
        return assentoMapper.toDTO(assento);
    }

    public List<AssentoResponseDTO> storeAll(List<AssentoCreateDTO> assentoCreateDTO) {
        List<AssentoResponseDTO> reservados = new ArrayList<>();
        for (AssentoCreateDTO dto : assentoCreateDTO) {
            reservados.add(this.store(dto));
        }
        return reservados;
    }

    public AssentoResponseDTO update(AssentoUpdateDTO assentoUpdateDTO) {
        Assento assento = assentoRepository.findById(assentoUpdateDTO.id())
                .orElseThrow(() -> new RuntimeException("Assento não encontrado para alteração"));

        assento.setFileira(assentoUpdateDTO.fileira());
        assento.setNumero(assentoUpdateDTO.numero());
        assento.setSala(assentoUpdateDTO.sala());
        assento.setSessao(assentoUpdateDTO.sessao());

        return assentoMapper.toDTO(assentoRepository.save(assento));
    }

    public List<AssentoResponseDTO> listBySessao(Long id) {
        List<Assento> assentos = assentoRepository.findBySessaoId(id);
        return assentos.stream()
                .map(assento -> new AssentoResponseDTO(
                        assento.getId(),
                        assento.getFileira(),
                        assento.getNumero(),
                        assento.getSala().getId(),
                        assento.getSessao().getId()))
                .toList();
    }

    public void destroy(long id) {
        Assento assento = assentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assento não encontrado para deleção"));
        assentoRepository.delete(assento);
    }

    public List<AssentoStatusResponseDTO> listBySessaoComStatus(Long sessaoId) {
    List<Assento> assentos = assentoRepository.findBySessaoId(sessaoId);
    
    // Aqui você deve buscar a sessão para passar nos métodos do repositório ingresso
    Sessao sessao = new Sessao();
    sessao.setId(sessaoId);

    List<AssentoStatusResponseDTO> resultado = new ArrayList<>();

    for (Assento assento : assentos) {
        Optional<Ingresso> ingressoOpt = ingressoRepository.findBySessaoAndAssento(sessao, assento);
        
        String statusPagamento = "LIVRE"; // Default: assento livre (sem ingresso)
        
        if (ingressoOpt.isPresent()) {
            Ingresso ingresso = ingressoOpt.get();
            Pagamento pagamento = ingresso.getCompra() != null ? ingresso.getCompra().getPagamento() : null;
            if (pagamento != null) {
                statusPagamento = pagamento.getStatus().name(); // converte enum para string
            }
        }

        resultado.add(new AssentoStatusResponseDTO(
            assento.getId(),
            assento.getFileira(),
            assento.getNumero(),
            assento.getSala().getId(),
            assento.getSessao().getId(),
            statusPagamento
        ));
    }
    return resultado;
}
}
