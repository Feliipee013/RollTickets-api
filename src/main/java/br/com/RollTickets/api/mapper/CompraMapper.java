package br.com.RollTickets.api.mapper;

import br.com.RollTickets.api.dto.ClienteCreateDTO;
import br.com.RollTickets.api.dto.CompraCreateDTO;
import br.com.RollTickets.api.dto.CompraResponseDTO;
import br.com.RollTickets.api.dto.IngressoResponseDTO;
import br.com.RollTickets.api.entity.Cliente;
import br.com.RollTickets.api.entity.Compra;
import java.util.*;

public class CompraMapper {
    
	public static CompraResponseDTO toDTO(Compra compra) {

		List<IngressoResponseDTO> ingressosDTO = compra.getIngressos() //Preciso converter a lista de ingressos em um objeto ingresso
        .stream()
        .map(IngressoMapper::toDTO)
        .toList();

		CompraResponseDTO compraReponse = new CompraResponseDTO(compra.getId(),compra.getDataHora(),compra.getCliente(),ingressosDTO,compra.getPagamento());
		return compraReponse;
	}
	
	
}
