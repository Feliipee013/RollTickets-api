package br.com.RollTickets.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "assentos")
public class Assento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String fileira;
    private String numero;
    
    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;


    @ManyToOne
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;

    public Assento() {

    }

    public Assento(long id, String fileira, String numero, Sala sala,Sessao sessao) {
        this.id = id;
        this.fileira = fileira;
        this.numero = numero;
        this.sala = sala;
        this.sessao = sessao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileira() {
        return fileira;
    }

    public void setFileira(String fileira) {
        this.fileira = fileira;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    

    public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	@Override
    public String toString() {
        return "Assento [id=" + id + ",fileira=" + fileira + ", numero=" + numero + ", sala =" + sala + "]";
    }

    
    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
}

