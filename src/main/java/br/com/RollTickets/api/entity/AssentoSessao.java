package br.com.RollTickets.api.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "assentos_sessao")
public class AssentoSessao {
    
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assento_id")
    private Assento assento;

    @ManyToOne
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;

    private boolean reservado;

    public AssentoSessao(){
        
    }

    public AssentoSessao(Long id, Assento assento, Sessao sessao, boolean reservado) {
        this.id = id;
        this.assento = assento;
        this.sessao = sessao;
        this.reservado = reservado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Assento getAssento() {
        return assento;
    }

    public void setAssento(Assento assento) {
        this.assento = assento;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public boolean getReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    
}
