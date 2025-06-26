package br.com.RollTickets.api.dto;

import java.time.LocalDateTime;

import br.com.RollTickets.api.entity.Cliente;
import java.util.*;

public record CompraUpdateDTO(Long id,LocalDateTime dataHora, Cliente cliente, List<Long> ingresso) {
} 
