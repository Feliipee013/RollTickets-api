package br.com.RollTickets.api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.RollTickets.api.entity.Filme;
import br.com.RollTickets.api.entity.Sala;


public record SessaoCreateDTO(
    Long filmeId,
    Long salaId,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")LocalDateTime horario) {
} 
