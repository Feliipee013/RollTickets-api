package br.com.RollTickets.api.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.RollTickets.api.dto.AssentoCreateDTO;
import br.com.RollTickets.api.dto.AssentoResponseDTO;
import br.com.RollTickets.api.entity.Assento;
import br.com.RollTickets.api.entity.Sala;
import br.com.RollTickets.api.entity.Sessao;
import br.com.RollTickets.api.repository.SalaRepository;
import br.com.RollTickets.api.repository.SessaoRepository;

@Component
public class AssentoMapper {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private SalaRepository salaRepository;

    public AssentoResponseDTO toDTO(Assento assento) {
        AssentoResponseDTO assentoResponse = new AssentoResponseDTO(assento.getId(), assento.getFileira(),
                assento.getNumero(), assento.getSala().getId(), assento.getSessao().getId());
        return assentoResponse;
    }

  
    public Assento toEntity(AssentoCreateDTO assentoCreateDTO) { //Como agora eu tô recebendo um id, não podia mais tratar sessão e sala como objetos, então eu pego o id e faço uma busca e armazeno na entidade assento
        Assento assento = new Assento();
        assento.setFileira(assentoCreateDTO.fileira());
        assento.setNumero(assentoCreateDTO.numero());

        Sessao sessao = sessaoRepository.findById(assentoCreateDTO.sessaoId())
                .orElseThrow(() -> new RuntimeException("Sessao não encontrada"));
        assento.setSessao(sessao);

        Sala sala = salaRepository.findById(assentoCreateDTO.salaId())
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));
        assento.setSala(sala);
        return assento;
    }
}
