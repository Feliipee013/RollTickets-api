package br.com.RollTickets.api.dto;

import java.time.LocalDateTime;
import java.util.*;
import br.com.RollTickets.api.entity.Cliente;

public record CompraResponseDTO(Long id,LocalDateTime dataHora, Cliente cliente, List<IngressoResponseDTO> ingresso) {
} 
