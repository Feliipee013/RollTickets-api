package br.com.RollTickets.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
=======
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
>>>>>>> 15f5abb3004d0c8d51fdb439919b7bd865265997
import jakarta.persistence.Table;

@Entity
@Table(name = "Assento")
public class Assento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String fileira;
    private String numero;
<<<<<<< HEAD
=======
    
    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;
>>>>>>> 15f5abb3004d0c8d51fdb439919b7bd865265997

    public Assento() {

    }

<<<<<<< HEAD
    public Assento(long id, String fileira, String numero) {
        super();
        this.id = id;
        this.fileira = fileira;
        this.numero = numero;
=======
    public Assento(long id, String fileira, String numero, Sala sala) {
        this.id = id;
        this.fileira = fileira;
        this.numero = numero;
        this.sala = sala;
>>>>>>> 15f5abb3004d0c8d51fdb439919b7bd865265997
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
}

