package br.com.RollTickets.api.service;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.RollTickets.api.dto.PagamentoCreateDTO;
import br.com.RollTickets.api.dto.PagamentoResponseDTO;
import br.com.RollTickets.api.dto.PagamentoUpdateDTO;
import br.com.RollTickets.api.entity.Compra;
import br.com.RollTickets.api.entity.Pagamento;
import br.com.RollTickets.api.enums.metodoPagamento;
import br.com.RollTickets.api.enums.status;
import br.com.RollTickets.api.mapper.PagamentoMapper;
import br.com.RollTickets.api.repository.CompraRepository;
import br.com.RollTickets.api.repository.PagamentoRepository;

@Service
public class PagamentoService {
    	
	@Autowired
	PagamentoRepository pagamentoRepository;

	@Autowired
	CompraRepository compraRepository;
	
	public PagamentoResponseDTO store(PagamentoCreateDTO pagamentoCreateDTO) {
		Compra compra = compraRepository.findById(pagamentoCreateDTO.compra_id()).orElseThrow(() -> new RuntimeException("Compra não encontrada"));
		Pagamento pagamento = PagamentoMapper.toEntity(pagamentoCreateDTO,compra);
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
		pagamento.setCompra(pagamentoUpdateDTO.compra());
        pagamento.setMetodoPagamento(pagamentoUpdateDTO.metodoPagamento());
        pagamento.setStatus(pagamentoUpdateDTO.status());
        pagamento.setDataHoraPagamento(pagamentoUpdateDTO.dataHoraPagamento());


		return PagamentoMapper.toDTO(pagamentoRepository.save(pagamento));
	}
	
	public void destroy(long id) {
		Pagamento pagamento= pagamentoRepository.findById(id).orElseThrow(()->new RuntimeException("Pagamento não encontrado para deleção"));
		pagamentoRepository.delete(pagamento);
	}

	public Pagamento storeOrUpdatePagamento(Compra compra, status status, metodoPagamento metodo, LocalDateTime dataHora) {
    Optional<Pagamento> optPagamento = pagamentoRepository.findByCompra(compra);
    Pagamento pagamento;
    if (optPagamento.isPresent()) {
        pagamento = optPagamento.get();
        System.out.println("Atualizando pagamento existente id: " + pagamento.getId());
    } else {
        pagamento = new Pagamento();
        System.out.println("Criando novo pagamento para compraId: " + compra.getId());
    }

    pagamento.setCompra(compra);
    pagamento.setStatus(status);
    pagamento.setMetodoPagamento(metodo);
    pagamento.setDataHoraPagamento(dataHora);

    Pagamento salvo = pagamentoRepository.save(pagamento);
    System.out.println("Pagamento salvo com id: " + salvo.getId() + " e status: " + salvo.getStatus());

    return salvo;
}

	
	
}
