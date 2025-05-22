package br.com.RollTickets.api.mapper;

import br.com.RollTickets.api.dto.IngressoCreateDTO;
import br.com.RollTickets.api.dto.IngressoResponseDTO;
import br.com.RollTickets.api.dto.IngressoUpdateDTO;
import br.com.RollTickets.api.entity.Ingresso;
import br.com.RollTickets.api.entity.Ingresso2D;
import br.com.RollTickets.api.entity.Ingresso3D;

public class IngressoMapper{
	
	public static IngressoResponseDTO toDTO(Ingresso ingresso) {
		if(ingresso instanceof Ingresso2D ingresso2D) {
			return new IngressoResponseDTO(ingresso2D.getId(), "DOIS_D", ingresso.getPreco(), ingresso.getSessao(), ingresso.getAssento(), ingresso.getCliente(), null, null);
		} else if (ingresso instanceof Ingresso3D ingresso3D) {
            return new IngressoResponseDTO(ingresso3D.getId(),"TRES_D",ingresso3D.getPreco(),ingresso3D.getSessao(),ingresso3D.getAssento(),ingresso3D.getCliente(),ingresso3D.isIncluiOculos(),ingresso3D.getTaxaExtra3D());
		} else {
			throw new IllegalArgumentException("Tipo de ingresso desconhecido");
		}
	}
	
	public static Ingresso toEntity(IngressoCreateDTO dto) {
        if (dto.tipo().equalsIgnoreCase("DOIS_D")) {
            Ingresso2D ingresso2D = new Ingresso2D();
            ingresso2D.setPreco(dto.preco());
            ingresso2D.setSessao(dto.sessao());
            ingresso2D.setAssento(dto.assento());
            ingresso2D.setCliente(dto.cliente());
            return ingresso2D;
        } else if (dto.tipo().equalsIgnoreCase("TRES_D")) {
            Ingresso3D ingresso3D = new Ingresso3D();
            ingresso3D.setPreco(dto.preco());
            ingresso3D.setSessao(dto.sessao());
            ingresso3D.setAssento(dto.assento());
            ingresso3D.setCliente(dto.cliente());
            ingresso3D.setIncluiOculos(dto.incluiOculos() != null ? dto.incluiOculos() : false);
            ingresso3D.setTaxaExtra3D(dto.taxaExtra3D() != null ? dto.taxaExtra3D() : 0.0);
            return ingresso3D;
        }
        throw new IllegalArgumentException("Tipo de ingresso inválido: " + dto.tipo());
    }
	
	public static Ingresso updateEntity(IngressoUpdateDTO dto, Ingresso existente) {
        existente.setPreco(dto.preco());
        existente.setSessao(dto.sessao());
        existente.setAssento(dto.assento());
        existente.setCliente(dto.cliente());

        if (existente instanceof Ingresso3D ingresso3D) {
            ingresso3D.setIncluiOculos(dto.incluiOculos() != null ? dto.incluiOculos() : ingresso3D.isIncluiOculos());
            ingresso3D.setTaxaExtra3D(dto.taxaExtra3D() != null ? dto.taxaExtra3D() : ingresso3D.getTaxaExtra3D());
        }
        // Ingresso2D não tem campos extras a atualizar

        return existente;
    }
}
