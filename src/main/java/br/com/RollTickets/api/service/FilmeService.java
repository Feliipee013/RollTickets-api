package br.com.RollTickets.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.RollTickets.api.dto.FilmeCreateDTO;
import br.com.RollTickets.api.dto.FilmeResponseDTO;
import br.com.RollTickets.api.dto.FilmeUpdateDTO;
import br.com.RollTickets.api.entity.Filme;
import br.com.RollTickets.api.mapper.FilmeMapper;
import br.com.RollTickets.api.repository.FilmeRepository;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public FilmeResponseDTO store(FilmeCreateDTO filmeCreateDTO) {
        Filme filme = FilmeMapper.toEntity(filmeCreateDTO);
        filmeRepository.save(filme);
        return FilmeMapper.toDTO(filme);
    }

    public List<FilmeResponseDTO> list() {
        return filmeRepository.findAll()
                .stream()
                .map(FilmeMapper::toDTO)
                .toList();
    }

    public FilmeResponseDTO show(long id) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme com id " + id + " não encontrado"));
        return FilmeMapper.toDTO(filme);
    }

    public FilmeResponseDTO update(FilmeUpdateDTO dto) {
        Filme filme = filmeRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Filme não encontrado para alteração"));

        filme.setTitulo(dto.titulo());
        filme.setSinopse(dto.sinopse());
        filme.setDuracao(dto.duracao());
        filme.setClassificacao(dto.classificacao());
        filme.setImageUrl(dto.imageUrl());
        filme.setFormato(dto.formato());
        filme.setAvaliacao(dto.avalicao());

        return FilmeMapper.toDTO(filmeRepository.save(filme));
    }

    public void destroy(long id) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado para deleção"));
        filmeRepository.delete(filme);
    }
}
