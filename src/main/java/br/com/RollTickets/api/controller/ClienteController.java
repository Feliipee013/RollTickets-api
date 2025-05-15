package br.com.RollTickets.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.RollTickets.api.service.ClienteService;

@RestController
@RequestMapping
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
}
