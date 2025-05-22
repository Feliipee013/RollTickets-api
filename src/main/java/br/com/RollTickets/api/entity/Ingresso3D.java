package br.com.RollTickets.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingressos_3d")
public class Ingresso3D extends Ingresso{
	
	private	boolean incluiOculos;
	@Column(name = "taxa_extra_3d")
	private double taxaExtra3D;
	
	public Ingresso3D() {
		
	}
	public Ingresso3D(boolean incluiOculos, double taxaExtra3D) {
		super();
		this.incluiOculos = incluiOculos;
		this.taxaExtra3D = taxaExtra3D;
	}
	public boolean isIncluiOculos() {
		return incluiOculos;
	}
	public void setIncluiOculos(boolean incluiOculos) {
		this.incluiOculos = incluiOculos;
	}
	public double getTaxaExtra3D() {
		return taxaExtra3D;
	}
	public void setTaxaExtra3D(double taxaExtra3D) {
		this.taxaExtra3D = taxaExtra3D;
	}
	
	
}
