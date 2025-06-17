package br.com.RollTickets.api.dto;


public record AssentoCreateDTO (
    String fileira,
    String numero,
    Long sessaoId, //Troquei pelo long para ficar mais fácil de manipular, pois antes era passado o objeto então ficaria muito extenso
    Long salaId
){}
