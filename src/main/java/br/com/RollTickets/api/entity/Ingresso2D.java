package br.com.RollTickets.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingressos_2D")
public class Ingresso2D extends Ingresso {

	public Ingresso2D() {
		super();
	}
	
}
