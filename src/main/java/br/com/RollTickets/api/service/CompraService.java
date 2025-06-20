package br.com.RollTickets.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.RollTickets.api.dto.ClienteCreateDTO;
import br.com.RollTickets.api.dto.ClienteLoginDTO;
import br.com.RollTickets.api.dto.ClienteResponseDTO;
import br.com.RollTickets.api.dto.ClienteUpdateDTO;
import br.com.RollTickets.api.dto.CompraCreateDTO;
import br.com.RollTickets.api.dto.CompraResponseDTO;
import br.com.RollTickets.api.dto.CompraUpdateDTO;
import br.com.RollTickets.api.entity.Cliente;
import br.com.RollTickets.api.entity.Compra;
import br.com.RollTickets.api.entity.Ingresso;
import br.com.RollTickets.api.entity.Pagamento;
import br.com.RollTickets.api.mapper.CompraMapper;
import br.com.RollTickets.api.repository.ClienteRepository;
import br.com.RollTickets.api.repository.CompraRepository;
import br.com.RollTickets.api.repository.IngressoRepository;
import br.com.RollTickets.api.repository.PagamentoRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private IngressoRepository ingressoRepository;



	public CompraResponseDTO store(CompraCreateDTO compraCreateDTO) {
		Compra compra = new Compra();

		Cliente cliente = clienteRepository.findById(compraCreateDTO.clienteId())
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
		compra.setCliente(cliente);

		List<Ingresso> ingressos = compraCreateDTO.ingressosIds().stream()
				.map(id -> ingressoRepository.findById(id)
						.orElseThrow(() -> new RuntimeException("Ingresso não encontrado: " + id)))
				.toList();
		compra.setIngressos(ingressos);

		for (Ingresso ingresso : ingressos) {
			ingresso.setCompra(compra);
		}
		compra.setIngressos(ingressos);

		compra.setDataHora(LocalDateTime.now());

		compraRepository.save(compra);

		return CompraMapper.toDTO(compra);
	}

	public List<CompraResponseDTO> list() {
		return compraRepository.findAll().stream().map(CompraMapper::toDTO).toList();
	}

	public CompraResponseDTO show(long id) {
		Compra compra = compraRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Compra com id: " + id + " não encontrado"));
		return CompraMapper.toDTO(compra);
	}

	public CompraResponseDTO update(CompraUpdateDTO compraUpdateDTO) {
		Compra compra = compraRepository.findById(compraUpdateDTO.id())
				.orElseThrow(() -> new RuntimeException("Compra não encontrada para alteração"));

		List<Ingresso> ingressos = compraUpdateDTO.ingresso().stream()
				.map(id -> ingressoRepository.findById(id)
						.orElseThrow(() -> new RuntimeException("Ingresso não encontrado: " + id)))
				.toList();
		compra.setIngressos(ingressos);

		return CompraMapper.toDTO(compraRepository.save(compra));
	}

	public void destroy(long id) {
		Compra compra = compraRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Compra não encontrado para deleção"));
		compraRepository.delete(compra);
	}

	
}
