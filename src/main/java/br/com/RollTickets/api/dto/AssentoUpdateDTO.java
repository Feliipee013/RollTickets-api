package br.com.RollTickets.api.dto;

<<<<<<< HEAD
public record AssentoUpdateDTO(
        long id,
        String fileira,
        String numero
=======
import br.com.RollTickets.api.entity.Sala;

public record AssentoUpdateDTO(
        long id,
        String fileira,
        String numero,
        Sala sala
>>>>>>> 15f5abb3004d0c8d51fdb439919b7bd865265997
) {}
