package br.com.RollTickets.api.dto;



public record AssentoResponseDTO (
    long id,
    String fileira,
    String numero,
    Long sala,  //Troquei pelo long para ficar mais fácil de manipular, pois antes era passado o objeto então ficaria muito extenso
    Long sessao
){}
    

