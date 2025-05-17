package br.com.RollTickets.api.mapper;

import br.com.RollTickets.api.dto.Ingresso2DCreateDTO;
import br.com.RollTickets.api.dto.Ingresso2DResponseDTO;
import br.com.RollTickets.api.entity.Ingresso2D;

public class Ingresso2DMapper{
	
	public static Ingresso2DResponseDTO toDTO(Ingresso2D ingresso) {
		Ingresso2DResponseDTO ingressoResponse = new Ingresso2DResponseDTO(ingresso.getId(), ingresso.getPreco(), ingresso.getSessao(), ingresso.getAssento(), ingresso.getCliente());
		return ingressoResponse;
	}
	
	public static Ingresso2D toEntity(Ingresso2DCreateDTO ingresso2DCreateDTO) {
        Ingresso2D ingresso = new Ingresso2D();
        ingresso.setPreco(ingresso2DCreateDTO.preco());
        ingresso.setAssento(ingresso2DCreateDTO.assento());
        ingresso.setSessao(ingresso2DCreateDTO.sessao());
        ingresso.setCliente(ingresso2DCreateDTO.cliente());
        return ingresso;
    }
}
