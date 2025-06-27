package br.com.RollTickets.api.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.RollTickets.api.dto.SessaoCreateDTO;
import br.com.RollTickets.api.dto.SessaoResponseDTO;
import br.com.RollTickets.api.dto.SessaoUpdateDTO;
import br.com.RollTickets.api.entity.Filme;
import br.com.RollTickets.api.entity.Sala;
import br.com.RollTickets.api.entity.Sessao;

import br.com.RollTickets.api.mapper.SessaoMapper;
import br.com.RollTickets.api.repository.FilmeRepository;
import br.com.RollTickets.api.repository.SalaRepository;
import br.com.RollTickets.api.repository.SessaoRepository;
import jakarta.transaction.Transactional;


@Service
public class SessaoService {
    @Autowired
	SessaoRepository sessaoRepository;
    
    @Autowired
    FilmeRepository filmeRepository;

    @Autowired
    SalaRepository salaRepository;
	
    
	public SessaoResponseDTO store(SessaoCreateDTO sessaoCreateDTO) {
		Filme filme = filmeRepository.findById(sessaoCreateDTO.filmeId())
				.orElseThrow(() -> new RuntimeException("Filme não encontrado"));
		Sala sala = salaRepository.findById(sessaoCreateDTO.salaId())
				.orElseThrow(() -> new RuntimeException("Sala não encontrada"));
		Sessao sessao = SessaoMapper.toEntity(sessaoCreateDTO, filme, sala);
		sessaoRepository.save(sessao);
        return SessaoMapper.toDTO(sessao);
	}
	
	public List<SessaoResponseDTO> list() {
		return sessaoRepository.findAll().stream().map(SessaoMapper::toDTO).toList();
	}
	
	public SessaoResponseDTO show(long id) {
			Sessao sessao = sessaoRepository.findById(id)
					.orElseThrow(()->new RuntimeException("Sessao com id" + id + " não encontrado"));
			return SessaoMapper.toDTO(sessao);
	}
	
	public SessaoResponseDTO update(SessaoUpdateDTO sessaoUpdateDTO) {
		Sessao sessao = sessaoRepository.findById(sessaoUpdateDTO.id()).orElseThrow(()->new RuntimeException("Sessao não encontrado para alteração"));
        sessao.setFilme(sessaoUpdateDTO.filme());
        sessao.setSala(sessaoUpdateDTO.sala());
        sessao.setHorario(sessaoUpdateDTO.horario());

		return SessaoMapper.toDTO(sessaoRepository.save(sessao));
	}
	
	public void destroy(long id) {
		Sessao sessao= sessaoRepository.findById(id).orElseThrow(()->new RuntimeException("Sessao não encontrado para deleção"));
		sessaoRepository.delete(sessao);
	}
	
	public List<SessaoResponseDTO> listByFilme(Long id) { 
		return sessaoRepository.findByFilmeId(id)
                .stream()
                .map(SessaoMapper::toDTO)
                .toList();
	}
}
