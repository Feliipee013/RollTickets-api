package br.com.RollTickets.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.RollTickets.api.dto.Ingresso2DCreateDTO;
import br.com.RollTickets.api.dto.Ingresso2DResponseDTO;
import br.com.RollTickets.api.dto.Ingresso2DUpdateDTO;
import br.com.RollTickets.api.entity.Ingresso2D;
import br.com.RollTickets.api.mapper.Ingresso2DMapper;
import br.com.RollTickets.api.repository.Ingresso2DRepository;

@Service
public class Ingresso2DService {
	
	@Autowired
	Ingresso2DRepository ingresso2DRepostory;
	
	public Ingresso2DResponseDTO store(Ingresso2DCreateDTO ingresso2DCreateDTO) {
		Ingresso2D ingresso2D = Ingresso2DMapper.toEntity(ingresso2DCreateDTO);
		ingresso2DRepostory.save(ingresso2D);
		return Ingresso2DMapper.toDTO(ingresso2D);
	}
	
	public List<Ingresso2DResponseDTO> list() {
		return ingresso2DRepostory.findAll().stream().map(Ingresso2DMapper::toDTO).toList();
	}
	
	public Ingresso2DResponseDTO show(long id) {
			Ingresso2D ingresso2D = ingresso2DRepostory.findById(id)
					.orElseThrow(()->new RuntimeException("Ingresso2D com id" + id + " não encontrada"));
			return Ingresso2DMapper.toDTO(ingresso2D);
	}
	
	public Ingresso2DResponseDTO update(Ingresso2DUpdateDTO ingresso2DUpdateDTO) {
		Ingresso2D ingresso2D = ingresso2DRepostory.findById(ingresso2DUpdateDTO.id()).orElseThrow(()->new RuntimeException("Ingresso2D não encontrada para alteração"));
		ingresso2D.setPreco(ingresso2DUpdateDTO.preco());
		ingresso2D.setAssento(ingresso2DUpdateDTO.assento());
		ingresso2D.setSessao(ingresso2DUpdateDTO.sessao());
		ingresso2D.setCliente(ingresso2DUpdateDTO.cliente());
		return Ingresso2DMapper.toDTO(ingresso2DRepostory.save(ingresso2D));
	}
	
	public void destroy(long id) {
		Ingresso2D ingresso2D = ingresso2DRepostory.findById(id).orElseThrow(()->new RuntimeException("Ingresso2D não encontrado para deleção"));
		ingresso2DRepostory.delete(ingresso2D);
	}
}
