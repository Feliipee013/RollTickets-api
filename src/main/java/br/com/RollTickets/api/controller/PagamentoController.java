package br.com.RollTickets.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.RollTickets.api.dto.PagamentoCreateDTO;
import br.com.RollTickets.api.dto.PagamentoResponseDTO;
import br.com.RollTickets.api.dto.PagamentoUpdateDTO;
import br.com.RollTickets.api.entity.Compra;
import br.com.RollTickets.api.entity.Pagamento;
import br.com.RollTickets.api.enums.status;
import br.com.RollTickets.api.mapper.PagamentoMapper;
import br.com.RollTickets.api.repository.CompraRepository;
import br.com.RollTickets.api.service.PagamentoService;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {
    @Autowired
	PagamentoService pagamentoService;
	
	@PostMapping("/realizar")
	public ResponseEntity<PagamentoResponseDTO> store(@RequestBody PagamentoCreateDTO pagamentoCreateDTO) {
		return new ResponseEntity<>(pagamentoService.store(pagamentoCreateDTO), HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<PagamentoResponseDTO>> list()
	{
		return new ResponseEntity<>(pagamentoService.list(), HttpStatus.OK);
	}
	
	@GetMapping("/{pagamento_id}")
	public ResponseEntity<PagamentoResponseDTO> show(@PathVariable long pagamento_id) {
		try {
			return new ResponseEntity<>(pagamentoService.show(pagamento_id), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PatchMapping
	public ResponseEntity<PagamentoResponseDTO> update(@RequestBody PagamentoUpdateDTO pagamentoUpdateDTO) {
		try {
			return new ResponseEntity<>(pagamentoService.update(pagamentoUpdateDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{pagamento_id}")
	public ResponseEntity<String> destroy(@PathVariable long pagamento_id) {
		try {
			pagamentoService.destroy(pagamento_id);
			return new ResponseEntity<>("Pagamento deletado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	
    @PutMapping("/{id}/aprovar")
    public ResponseEntity<String> aprovarCompra(@PathVariable Long id) {
    	PagamentoResponseDTO pagamentoResponse = pagamentoService.show(id);
        
        if (pagamentoResponse != null) {
            PagamentoUpdateDTO atualizado = new PagamentoUpdateDTO(
                pagamentoResponse.id(),
                pagamentoResponse.compra(),
                pagamentoResponse.metodoPagamento(),
                status.PAGO,
                pagamentoResponse.dataHoraPagamento()
            );

            pagamentoService.update(atualizado);
        }

        return ResponseEntity.ok("Compra aprovada com sucesso");
    }
}
