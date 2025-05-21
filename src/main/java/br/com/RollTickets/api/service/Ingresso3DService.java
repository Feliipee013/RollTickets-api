package br.com.RollTickets.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.RollTickets.api.dto.Ingresso3DCreateDTO;
import br.com.RollTickets.api.dto.Ingresso3DResponseDTO;
import br.com.RollTickets.api.dto.Ingresso3DUpdateDTO;
import br.com.RollTickets.api.entity.Ingresso3D;
import br.com.RollTickets.api.mapper.Ingresso3DMapper;
import br.com.RollTickets.api.repository.Ingresso3DRepository;

@Service
public class Ingresso3DService {
	
	@Autowired
	Ingresso3DRepository ingresso3DRepostory;
	
	public Ingresso3DResponseDTO store(Ingresso3DCreateDTO ingresso3DCreateDTO) {
		Ingresso3D ingresso3D = Ingresso3DMapper.toEntity(ingresso3DCreateDTO);
		ingresso3DRepostory.save(ingresso3D);
		return Ingresso3DMapper.toDTO(ingresso3D);
	}
	
	public List<Ingresso3DResponseDTO> list() {
		return ingresso3DRepostory.findAll().stream().map(Ingresso3DMapper::toDTO).toList();
	}
	
	public Ingresso3DResponseDTO show(long id) {
			Ingresso3D ingresso3D = ingresso3DRepostory.findById(id)
					.orElseThrow(()->new RuntimeException("Ingresso3D com id" + id + " não encontrada"));
			return Ingresso3DMapper.toDTO(ingresso3D);
	}
	
	public Ingresso3DResponseDTO update(Ingresso3DUpdateDTO ingresso3DUpdateDTO) {
		Ingresso3D ingresso3D = ingresso3DRepostory.findById(ingresso3DUpdateDTO.id()).orElseThrow(()->new RuntimeException("Ingresso3D não encontrada para alteração"));
		ingresso3D.setPreco(ingresso3DUpdateDTO.preco());
		ingresso3D.setAssento(ingresso3DUpdateDTO.assento());
		ingresso3D.setSessao(ingresso3DUpdateDTO.sessao());
		ingresso3D.setCliente(ingresso3DUpdateDTO.cliente());
		ingresso3D.setIncluiOculos(ingresso3DUpdateDTO.incluiOculos());
		ingresso3D.setTaxaExtra3D(ingresso3DUpdateDTO.taxaExtra3D());
		return Ingresso3DMapper.toDTO(ingresso3DRepostory.save(ingresso3D));
	}
	
	public void destroy(long id) {
		Ingresso3D ingresso3D = ingresso3DRepostory.findById(id).orElseThrow(()->new RuntimeException("Ingresso3D não encontrado para deleção"));
		ingresso3DRepostory.delete(ingresso3D);
	}
}
