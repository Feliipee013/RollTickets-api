package br.com.RollTickets.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.RollTickets.api.dto.ClienteCreateDTO;
import br.com.RollTickets.api.dto.ClienteResponseDTO;
import br.com.RollTickets.api.dto.ClienteUpdateDTO;
import br.com.RollTickets.api.dto.PagamentoCreateDTO;
import br.com.RollTickets.api.dto.PagamentoResponseDTO;
import br.com.RollTickets.api.dto.PagamentoUpdateDTO;
import br.com.RollTickets.api.entity.Pagamento;
import br.com.RollTickets.api.mapper.ClienteMapper;
import br.com.RollTickets.api.mapper.PagamentoMapper;
import br.com.RollTickets.api.repository.ClienteRepository;
import br.com.RollTickets.api.repository.PagamentoRepository;

@Service
public class PagamentoService {
    	
	@Autowired
	PagamentoRepository pagamentoRepository;
	
	public PagamentoResponseDTO store(PagamentoCreateDTO pagamentoCreateDTO) {
		Pagamento pagamento = PagamentoMapper.toEntity(pagamentoCreateDTO);
		pagamentoRepository.save(pagamento);
		return PagamentoMapper.toDTO(pagamento);
	}
	
	public List<PagamentoResponseDTO> list() {
		return pagamentoRepository.findAll().stream().map(PagamentoMapper::toDTO).toList();
	}
	
	public PagamentoResponseDTO show(long id) {
			Pagamento pagamento = pagamentoRepository.findById(id)
					.orElseThrow(()->new RuntimeException("Pagamento com id" + id + " não encontrado"));
			return PagamentoMapper.toDTO(pagamento);
	}
	
	public PagamentoResponseDTO update(PagamentoUpdateDTO pagamentoUpdateDTO) {
		Pagamento pagamento = pagamentoRepository.findById(pagamentoUpdateDTO.id()).orElseThrow(()->new RuntimeException("Pagamento não encontrado para alteração"));
		pagamento.setIngresso(pagamentoUpdateDTO.ingresso());
        pagamento.setMetodoPagamento(pagamentoUpdateDTO.metodoPagamento());
        pagamento.setStatus(pagamentoUpdateDTO.status());
        pagamento.setDataHoraPagamento(pagamentoUpdateDTO.dataHoraPagamento());


		return PagamentoMapper.toDTO(pagamentoRepository.save(pagamento));
	}
	
	public void destroy(long id) {
		Pagamento pagamento= pagamentoRepository.findById(id).orElseThrow(()->new RuntimeException("Pagamento não encontrado para deleção"));
		pagamentoRepository.delete(pagamento);
	}
}
