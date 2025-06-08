package br.com.RollTickets.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.RollTickets.api.dto.AssentoSessaoCreateDTO;
import br.com.RollTickets.api.dto.AssentoSessaoResponseDTO;
import br.com.RollTickets.api.dto.AssentoSessaoUpdateDTO;
import br.com.RollTickets.api.entity.Assento;
import br.com.RollTickets.api.entity.AssentoSessao;
import br.com.RollTickets.api.entity.Sessao;
import br.com.RollTickets.api.mapper.AssentoSessaoMapper;
import br.com.RollTickets.api.repository.AssentoRepository;
import br.com.RollTickets.api.repository.AssentoSessaoRepository;
import br.com.RollTickets.api.repository.SessaoRepository;

@Service
public class AssentoSessaoService {

    @Autowired
    private AssentoSessaoRepository assentoSessaoRepository;

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private AssentoRepository assentoRepository;

    public AssentoSessaoResponseDTO store(AssentoSessaoCreateDTO assentoSessaoCreateDTO) {
        Assento assento = assentoRepository.findById(assentoSessaoCreateDTO.assento()) // Pega o id que o assentoSessaoCreateDTO recebeu e buscar o objeto referente a esse id
                .orElseThrow(() -> new RuntimeException("Assento não encontrado"));

        Sessao sessao = sessaoRepository.findById(assentoSessaoCreateDTO.sessao())
                .orElseThrow(() -> new RuntimeException("Sessao não encontrada"));

        boolean jaReservado = assentoSessaoRepository
                .findBySessaoIdAndAssentoId(assento.getId(), sessao.getId())
                .isPresent();

        if (jaReservado) {
            throw new RuntimeException("Assento já reservado para esta sessão.");
        }

        AssentoSessao assentoSessao = AssentoSessaoMapper.toEntity(assentoSessaoCreateDTO, assento,  sessao);
        assentoSessao.setReservado(true);
        
        assentoSessaoRepository.save(assentoSessao);
        return AssentoSessaoMapper.toDTO(assentoSessao);
    }

    public List<AssentoSessaoResponseDTO> listBySessao(Long id) {
        return assentoSessaoRepository.findBySessaoId(id)
                .stream()
                .map(AssentoSessaoMapper::toDTO)
                .toList();
    }

    public List<AssentoSessaoResponseDTO> list() {
        return assentoSessaoRepository.findAll()
                .stream()
                .map(AssentoSessaoMapper::toDTO)
                .toList();
    }

    public AssentoSessaoResponseDTO show(long id) {
        AssentoSessao assentoSessao = assentoSessaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assento com id " + id + " não encontrado"));
        return AssentoSessaoMapper.toDTO(assentoSessao);
    }

    public AssentoSessaoResponseDTO update(AssentoSessaoUpdateDTO assentoSessaoUpdateDTO) {
        AssentoSessao assentoSessao = assentoSessaoRepository.findById(assentoSessaoUpdateDTO.id())
                .orElseThrow(() -> new RuntimeException("Assento não encontrado para alteração"));

        assentoSessao.setAssento(assentoSessaoUpdateDTO.assento());
        assentoSessao.setSessao(assentoSessaoUpdateDTO.sessao());
        assentoSessao.setReservado(assentoSessaoUpdateDTO.reservado());

        return AssentoSessaoMapper.toDTO(assentoSessaoRepository.save(assentoSessao));
    }

    public void destroy(long id) {
        AssentoSessao assentoSessao = assentoSessaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assento não encontrado para deleção"));
        assentoSessaoRepository.delete(assentoSessao);
    }
}
