package br.com.RollTickets.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import br.com.RollTickets.api.entity.Assento;

@Entity
@Table(name = "salas")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long numero;

    @OneToOne
    @JoinColumn(name = "id_assento")
    private Assento assento;

    private long capacidade;

    public Sala() {
    }

    public Sala(long id, long numero, Assento assento, long capacidade) {
        super();
        this.id = id;
        this.numero = numero;
        this.assento = assento;
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

    public Assento getAssento() {
        return assento;
    }

    public void setAssento(Assento assento) {
        this.assento = assento;
    }

    public long getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(long capacidade) {
        this.capacidade = capacidade;
    }
}
