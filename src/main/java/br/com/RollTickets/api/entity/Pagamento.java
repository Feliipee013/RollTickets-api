package br.com.RollTickets.api.entity;

import br.com.RollTickets.api.enums.metodoPagamento;
import br.com.RollTickets.api.enums.status;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;


@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    @OneToOne
	@JoinColumn(name="id_ingresso")
	private Ingresso ingresso;

    @Enumerated(EnumType.STRING)
	private metodoPagamento metodoPagamento;

    @Enumerated(EnumType.STRING)
	private status status;




}
