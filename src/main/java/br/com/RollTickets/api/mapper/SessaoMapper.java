package br.com.RollTickets.api.mapper;

import br.com.RollTickets.api.dto.SessaoCreateDTO;
import br.com.RollTickets.api.dto.SessaoResponseDTO;
import br.com.RollTickets.api.entity.Sessao;

public class SessaoMapper {
    public static SessaoResponseDTO toDTO(Sessao sessao){
        SessaoResponseDTO sessaoResponse = new SessaoResponseDTO(sessao.getId(), sessao.getFilme(), sessao.getSala(), sessao.getQuantidade_ingressos_disponiveis(), sessao.getHorario());
        return sessaoResponse;
    }

    public static Sessao toEntity(SessaoCreateDTO sessaoCreateDTO){
        Sessao sessao = new Sessao();
        sessao.setFilme(sessaoCreateDTO.filme());
        sessao.setQuantidade_ingressos_disponiveis(sessaoCreateDTO.quantidade_ingressos_disponiveis());
        sessao.setSala(sessaoCreateDTO.sala());
        return sessao;
    }
}
