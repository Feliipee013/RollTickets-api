package br.com.RollTickets.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.RollTickets.api.dto.IngressoCreateDTO;
import br.com.RollTickets.api.dto.IngressoResponseDTO;
import br.com.RollTickets.api.dto.IngressoUpdateDTO;
import br.com.RollTickets.api.entity.Ingresso;
import br.com.RollTickets.api.repository.IngressoRepository;
import br.com.RollTickets.api.mapper.IngressoMapper;

@Service
public class IngressoService {

    @Autowired
    private IngressoRepository ingressoRepository;

    public IngressoResponseDTO store(IngressoCreateDTO dto) {
        Ingresso ingresso = IngressoMapper.toEntity(dto);
        ingressoRepository.save(ingresso);
        return IngressoMapper.toDTO(ingresso);
    }

    public List<IngressoResponseDTO> list() {
        return ingressoRepository.findAll()
                .stream()
                .map(IngressoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public IngressoResponseDTO show(Long id) {
        Ingresso ingresso = ingressoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingresso não encontrado: " + id));
        return IngressoMapper.toDTO(ingresso);
    }

    public IngressoResponseDTO update(IngressoUpdateDTO dto) {
        Ingresso ingressoExistente = ingressoRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Ingresso não encontrado para alteração: " + dto.id()));

        IngressoMapper.updateEntity(dto, ingressoExistente);
        ingressoRepository.save(ingressoExistente);
        return IngressoMapper.toDTO(ingressoExistente);
    }

    public void destroy(Long id) {
        if (!ingressoRepository.existsById(id)) {
            throw new RuntimeException("Ingresso não encontrado para exclusão: " + id);
        }
        ingressoRepository.deleteById(id);
    }
}
