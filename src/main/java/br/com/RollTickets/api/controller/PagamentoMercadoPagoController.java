package br.com.RollTickets.api.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import br.com.RollTickets.api.dto.PagamentoCartaoDTO;
import br.com.RollTickets.api.entity.Compra;
import br.com.RollTickets.api.repository.CompraRepository;
import br.com.RollTickets.api.service.IngressoService;
import br.com.RollTickets.api.service.PagamentoService;
import br.com.RollTickets.api.enums.status;
import br.com.RollTickets.api.enums.metodoPagamento;

@RestController
@RequestMapping("/api/mercadopago")
public class PagamentoMercadoPagoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private IngressoService ingressoService;

    @Value("${mercadopago.token}")
    private String accessToken;

    @PostMapping("/cartao")
    public ResponseEntity<?> pagarCartao(@RequestBody PagamentoCartaoDTO pagamentoDTO)
            throws MPException, MPApiException {
        // Configura token global (opcional)
        MercadoPagoConfig.setAccessToken(accessToken);

        // Cria client de pagamento
        PaymentClient paymentClient = new PaymentClient();

        PaymentPayerRequest payerRequest = PaymentPayerRequest.builder()
                .email(pagamentoDTO.payer().email())
                .build();

        // Cria o objeto de requisição de pagamento
        PaymentCreateRequest paymentRequest = PaymentCreateRequest.builder()
                .transactionAmount(BigDecimal.valueOf(pagamentoDTO.valor()))
                .token(pagamentoDTO.token())
                .installments(pagamentoDTO.installments())
                .paymentMethodId(pagamentoDTO.paymentMethodId())
                .issuerId(pagamentoDTO.issuerId())
                .payer(payerRequest)
                .build();

        try {
            // Executa o pagamento
            Payment payment = paymentClient.create(paymentRequest);
            System.out.println("Status do pagamento: " + payment.getStatus());
            System.out.println("Status detail: " + payment.getStatusDetail());
            System.out.println("ID do pagamento: " + payment.getId());
            if ("approved".equalsIgnoreCase(payment.getStatus())) {
                Long compraId = pagamentoDTO.compraId(); // Pega o id da compra
                Long clienteId = pagamentoDTO.clienteId(); //Pega o id do cliente

                Compra compra = compraRepository.findById(compraId) //Extrai os dados da compra e do cliente do DTO
                        .orElseThrow(() -> new RuntimeException("Compra não encontrada"));

                pagamentoService.storeOrUpdatePagamento(compra, status.PAGO, metodoPagamento.CREDITO,
                        LocalDateTime.now()); //Busca a compra no BD
                ingressoService.vincularIngressosPendentesACompra(clienteId, compra.getId()); //Atualiza ou cria o pagamento com o status "PAGO"
                return ResponseEntity.ok(Map.of( //Isso serve para que todos os ingressos que estejam com pendentes, no final fiquem associados a compra confirmada
                        "status", payment.getStatus(),
                        "id", payment.getId(),
                        "message", "Pagamento aprovado e confirmado!"));
            }

            // Retorna resposta
            return ResponseEntity.ok(Map.of(
                    "status", payment.getStatus(),
                    "status_detail", payment.getStatusDetail(),
                    "id", payment.getId(),
                    "message", "Pagamento pendente ou recusado."));

        } catch (MPApiException ex) {
            System.err.println("Erro da API do Mercado Pago:");
            System.err.println("Status Code: " + ex.getStatusCode());
            System.err.println("Content: " + ex.getApiResponse().getContent());
            ex.printStackTrace();
            return ResponseEntity.status(500).body(Map.of(
                    "message", "Erro ao processar pagamento",
                    "statusCode", ex.getStatusCode(),
                    "error", ex.getApiResponse().getContent()));
        }

    }

    @PostMapping("/confirmar")
    public ResponseEntity<?> confirmarPagamento(@RequestBody Map<String, String> body)
            throws MPException, MPApiException {
        MercadoPagoConfig.setAccessToken(accessToken);
        String paymentIdStr = body.get("paymentId"); // id Mercado Pago
        String compraIdStr = body.get("compraId"); // id Compra no seu sistema
        String clienteIdStr = body.get("clienteId");//id Cliente no sistema

        Long clienteId = Long.parseLong(clienteIdStr); //Lê os dados do corpo da requisição
        Long compraId = Long.parseLong(compraIdStr); //Lê os dados do corpo da requisição
                
        PaymentClient paymentClient = new PaymentClient();
        Payment payment = paymentClient.get(Long.parseLong(paymentIdStr));

        String statusMP = payment.getStatus();

        if ("approved".equalsIgnoreCase(statusMP)) {
            Compra compra = compraRepository.findById(compraId)
                    .orElseThrow(() -> new RuntimeException("Compra não encontrada"));

            // Cria ou atualiza o pagamento no seu sistema
            pagamentoService.storeOrUpdatePagamento(compra, status.PAGO, metodoPagamento.CREDITO, LocalDateTime.now()); //Serve para atualizar as compras
            ingressoService.vincularIngressosPendentesACompra(clienteId, compra.getId()); //E isso para vincular aos ingressos pendentes
            return ResponseEntity.ok(Map.of("status", statusMP, "message", "Pagamento aprovado! Compra confirmada."));
        } else {
            return ResponseEntity.ok(Map.of("status", statusMP, "message", "Pagamento não aprovado ainda."));
        }
    }

}
