package br.com.RollTickets.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
=======
>>>>>>> 15f5abb3004d0c8d51fdb439919b7bd865265997
import jakarta.persistence.Table;


@Entity
@Table(name = "salas")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long numero;

<<<<<<< HEAD
    @OneToOne
    @JoinColumn(name = "id_assento")
    private Assento assento;

=======
>>>>>>> 15f5abb3004d0c8d51fdb439919b7bd865265997
    private long capacidade;

    public Sala() {
    }

<<<<<<< HEAD
    public Sala(long id, long numero, Assento assento, long capacidade) {
        super();
        this.id = id;
        this.numero = numero;
        this.assento = assento;
=======
    public Sala(long id, long numero, long capacidade) {
        this.id = id;
        this.numero = numero;
>>>>>>> 15f5abb3004d0c8d51fdb439919b7bd865265997
        this.capacidade = capacidade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

<<<<<<< HEAD
    public Assento getAssento() {
        return assento;
    }

    public void setAssento(Assento assento) {
        this.assento = assento;
    }
=======
>>>>>>> 15f5abb3004d0c8d51fdb439919b7bd865265997

    public long getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(long capacidade) {
        this.capacidade = capacidade;
    }
}
