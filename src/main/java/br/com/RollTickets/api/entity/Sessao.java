package br.com.RollTickets.api.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "sessoes")
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "filme_id")
    private Filme filme;

    @OneToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

    LocalDateTime horario;

    int quantidade_ingressos_disponiveis;

    public Sessao() {
    }

    public Sessao(Filme filme, Sala sala, LocalDateTime horario, int quantidade_ingressos_disponiveis) {
        this.filme = filme;
        this.sala = sala;
        this.horario = horario;
        this.quantidade_ingressos_disponiveis = quantidade_ingressos_disponiveis;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public int getQuantidade_ingressos_disponiveis() {
        return quantidade_ingressos_disponiveis;
    }

    public void setQuantidade_ingressos_disponiveis(int quantidade_ingressos_disponiveis) {
        this.quantidade_ingressos_disponiveis = quantidade_ingressos_disponiveis;
    }

}
