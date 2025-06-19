package br.com.RollTickets.api.dto;



import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;


public record CompraCreateDTO( 
		@JsonProperty("cliente_id") Long clienteId,
	    @JsonProperty("ingressos_ids") List<Long> ingressosIds) {
}
