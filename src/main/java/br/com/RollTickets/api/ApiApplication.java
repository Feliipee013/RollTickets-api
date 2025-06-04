package br.com.RollTickets.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //Funcionalidade de tarefas agendadas
public class ApiApplication {
//teste
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
