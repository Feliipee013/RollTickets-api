package br.com.RollTickets.api.mapper;

import br.com.RollTickets.api.dto.PagamentoCreateDTO;
import br.com.RollTickets.api.dto.PagamentoResponseDTO;
import br.com.RollTickets.api.entity.Pagamento;

public class PagamentoMapper {
    
    public static PagamentoResponseDTO toDTO(Pagamento pagamento) {
		PagamentoResponseDTO pagamentoResponseDTO = new PagamentoResponseDTO(pagamento.getId(),pagamento.getIngresso(),pagamento.getMetodoPagamento(),pagamento.getStatus(),pagamento.getDataHoraPagamento());
		return pagamentoResponseDTO;
	}

    public static Pagamento toEntity(PagamentoCreateDTO pagamentoCreateDTO){
        Pagamento pagamento = new Pagamento();
        pagamento.setIngresso(pagamentoCreateDTO.ingresso());
        pagamento.setMetodoPagamento(pagamentoCreateDTO.metodoPagamento());
        pagamento.setStatus(pagamentoCreateDTO.status());

        return pagamento;
    }
}
