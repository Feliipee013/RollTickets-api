package br.com.RollTickets.api.entity;

import java.time.LocalDateTime;

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
    @JoinColumn(name = "compra_id")
    private Compra compra;

    @Enumerated(EnumType.STRING)
    private metodoPagamento metodoPagamento;

    @Enumerated(EnumType.STRING)
    private status status;
    

    LocalDateTime dataHoraPagamento;

    public Pagamento(Compra compra, metodoPagamento metodoPagamento, status status,
            LocalDateTime dataHoraPagamento) {
        this.compra = compra;
        this.metodoPagamento = metodoPagamento;
        this.status = status;
        this.dataHoraPagamento = dataHoraPagamento;
    }

    // Construtor vazio (necessário para JPA)
    public Pagamento() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setIngresso(Compra compra) {
        this.compra = compra;
    }

    public metodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(metodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public status getStatus() {
        return status;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    public LocalDateTime getDataHoraPagamento() {
        return dataHoraPagamento;
    }

    public void setDataHoraPagamento(LocalDateTime dataHoraPagamento) {
        this.dataHoraPagamento = dataHoraPagamento;
    }
}
