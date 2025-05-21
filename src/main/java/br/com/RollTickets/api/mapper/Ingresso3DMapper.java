package br.com.RollTickets.api.mapper;

import br.com.RollTickets.api.dto.Ingresso3DCreateDTO;
import br.com.RollTickets.api.dto.Ingresso3DResponseDTO;
import br.com.RollTickets.api.entity.Ingresso3D;

public class Ingresso3DMapper {
	public static Ingresso3DResponseDTO toDTO(Ingresso3D ingresso) {
		Ingresso3DResponseDTO ingressoResponse = new Ingresso3DResponseDTO(ingresso.getId(), ingresso.getPreco(), ingresso.getSessao(), ingresso.getAssento(), ingresso.getCliente(), ingresso.isIncluiOculos(), ingresso.getTaxaExtra3D());
		return ingressoResponse;
	}
	
	public static Ingresso3D toEntity(Ingresso3DCreateDTO ingresso3DCreateDTO) {
        Ingresso3D ingresso = new Ingresso3D();
        ingresso.setPreco(ingresso3DCreateDTO.preco());
        ingresso.setAssento(ingresso3DCreateDTO.assento());
        ingresso.setSessao(ingresso3DCreateDTO.sessao());
        ingresso.setCliente(ingresso3DCreateDTO.cliente());
        ingresso.setIncluiOculos(ingresso3DCreateDTO.incluiOculos());
        ingresso.setTaxaExtra3D(ingresso3DCreateDTO.taxaExtra3D());
        return ingresso;
    }
}
