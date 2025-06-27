package br.com.RollTickets.api.mapper;

import br.com.RollTickets.api.dto.SessaoCreateDTO;
import br.com.RollTickets.api.dto.SessaoResponseDTO;
import br.com.RollTickets.api.entity.Filme;
import br.com.RollTickets.api.entity.Sala;
import br.com.RollTickets.api.entity.Sessao;

public class SessaoMapper {
    public static SessaoResponseDTO toDTO(Sessao sessao){
        SessaoResponseDTO sessaoResponse = new SessaoResponseDTO(sessao.getId(), sessao.getFilme(),sessao.getSala(),sessao.getHorario());
        return sessaoResponse;
    }

    public static Sessao toEntity(SessaoCreateDTO sessaoCreateDTO, Filme filme, Sala sala){
        Sessao sessao = new Sessao();
        sessao.setFilme(filme);
        sessao.setSala(sala);
        sessao.setHorario(sessaoCreateDTO.horario());
        return sessao;
    }
}
