package br.com.RollTickets.api.dto;


public record AssentoSessaoCreateDTO(
    Long assento, //Tive que trocar porque era mais simples só pegar um id do que o objeto inteiro
    Long sessao//Tive que trocar porque era mais simples só pegar um id do que o objeto inteiro
) {}
