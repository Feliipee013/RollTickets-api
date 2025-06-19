package br.com.RollTickets.api.mapper;

import br.com.RollTickets.api.dto.PagamentoCreateDTO;
import br.com.RollTickets.api.dto.PagamentoResponseDTO;
import br.com.RollTickets.api.entity.Pagamento;
import br.com.RollTickets.api.entity.Compra;

public class PagamentoMapper {
    
    public static PagamentoResponseDTO toDTO(Pagamento pagamento) {
		PagamentoResponseDTO pagamentoResponseDTO = new PagamentoResponseDTO(pagamento.getId(),pagamento.getCompra(),pagamento.getMetodoPagamento(),pagamento.getStatus(),pagamento.getDataHoraPagamento());
		return pagamentoResponseDTO;
	}

   public static Pagamento toEntity(PagamentoCreateDTO pagamentoCreateDTO,Compra compra) {
    Pagamento pagamento = new Pagamento();
    pagamento.setCompra(compra);
    pagamento.setMetodoPagamento(pagamentoCreateDTO.metodoPagamento());
    pagamento.setStatus(pagamentoCreateDTO.status());
    pagamento.setDataHoraPagamento(pagamentoCreateDTO.dataHoraPagamento());
    return pagamento;
}
}
