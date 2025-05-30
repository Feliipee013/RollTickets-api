package br.com.RollTickets.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.RollTickets.api.dto.AssentoCreateDTO;
import br.com.RollTickets.api.dto.AssentoResponseDTO;
import br.com.RollTickets.api.dto.AssentoUpdateDTO;
import br.com.RollTickets.api.entity.Assento;
import br.com.RollTickets.api.mapper.AssentoMapper;
import br.com.RollTickets.api.repository.AssentoRepository;

@Service
public class AssentoService {

    @Autowired
    private AssentoRepository assentoRepository;

    public AssentoResponseDTO store(AssentoCreateDTO assentoCreateDTO) {
        Assento assento = AssentoMapper.toEntity(assentoCreateDTO);
        assentoRepository.save(assento);
        return AssentoMapper.toDTO(assento);
    }

    public List<AssentoResponseDTO> list() {
        return assentoRepository.findAll()
                .stream()
                .map(AssentoMapper::toDTO)
                .toList();
    }

    public AssentoResponseDTO show(long id) {
        Assento assento = assentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assento com id " + id + " não encontrado"));
        return AssentoMapper.toDTO(assento);
    }

    public AssentoResponseDTO update(AssentoUpdateDTO assentoUpdateDTO) {
        Assento assento = assentoRepository.findById(assentoUpdateDTO.id())
                .orElseThrow(() -> new RuntimeException("Assento não encontrado para alteração"));

        assento.setFileira(assentoUpdateDTO.fileira());
        assento.setNumero(assentoUpdateDTO.numero());
        assento.setSala(assentoUpdateDTO.sala());

        return AssentoMapper.toDTO(assentoRepository.save(assento));
    }

    public void destroy(long id) {
        Assento assento = assentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assento não encontrado para deleção"));
        assentoRepository.delete(assento);
    }
}
