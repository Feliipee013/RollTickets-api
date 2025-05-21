package br.com.RollTickets.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.RollTickets.api.dto.SalaCreateDTO;
import br.com.RollTickets.api.dto.SalaResponseDTO;
import br.com.RollTickets.api.dto.SalaUpdateDTO;
import br.com.RollTickets.api.entity.Sala;
import br.com.RollTickets.api.mapper.SalaMapper;
import br.com.RollTickets.api.repository.SalaRepository;

@Service
public class SalaService {
	
	@Autowired
	SalaRepository salaRepository;
	
	public SalaResponseDTO store(SalaCreateDTO salaCreateDTO) {
		Sala sala = SalaMapper.toEntity(salaCreateDTO);
		salaRepository.save(sala);
		return SalaMapper.toDTO(sala);
	}
	
	public List<SalaResponseDTO> list() {
		return salaRepository.findAll().stream().map(SalaMapper::toDTO).toList();
	}
	
	public SalaResponseDTO show(long id) {
			Sala sala = salaRepository.findById(id)
					.orElseThrow(()->new RuntimeException("Sala com id" + id + " não encontrada"));
			return SalaMapper.toDTO(sala);
	}
	
	public SalaResponseDTO update(SalaUpdateDTO salaUpdateDTO) {
		Sala sala = salaRepository.findById(salaUpdateDTO.id()).orElseThrow(()->new RuntimeException("Sala não encontrada para alteração"));
<<<<<<< HEAD
		sala.setAssento(salaUpdateDTO.assento());
=======
>>>>>>> 15f5abb3004d0c8d51fdb439919b7bd865265997
		sala.setCapacidade(salaUpdateDTO.capacidade());
		sala.setNumero(salaUpdateDTO.numero());
		return SalaMapper.toDTO(salaRepository.save(sala));
	}
	
	public void destroy(long id) {
		Sala sala = salaRepository.findById(id).orElseThrow(()->new RuntimeException("Sala não encontrado para deleção"));
		salaRepository.delete(sala);
	}
}
