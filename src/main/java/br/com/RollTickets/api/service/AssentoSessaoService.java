package br.com.RollTickets.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.RollTickets.api.dto.AssentoSessaoCreateDTO;
import br.com.RollTickets.api.dto.AssentoSessaoResponseDTO;
import br.com.RollTickets.api.dto.AssentoSessaoUpdateDTO;
import br.com.RollTickets.api.entity.AssentoSessao;
import br.com.RollTickets.api.mapper.AssentoSessaoMapper;
import br.com.RollTickets.api.repository.AssentoSessaoRepository;

@Service
public class AssentoSessaoService {

    @Autowired
    private AssentoSessaoRepository assentoSessaoRepository;

    public AssentoSessaoResponseDTO store(AssentoSessaoCreateDTO assentoSessaoCreateDTO) {
        Long sessaoId = assentoSessaoCreateDTO.sessao().getId();
        Long assentoId = assentoSessaoCreateDTO.assento().getId();

        boolean jaReservado = assentoSessaoRepository
                .findBySessaoIdAndAssentoId(sessaoId, assentoId)
                .isPresent();

        if (jaReservado) {
            throw new RuntimeException("Assento já reservado para esta sessão.");
        }

        AssentoSessao entity = AssentoSessaoMapper.toEntity(assentoSessaoCreateDTO);
        assentoSessaoRepository.save(entity);
        return AssentoSessaoMapper.toDTO(entity);
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
