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
import br.com.RollTickets.api.service.PagamentoService;
import br.com.RollTickets.api.enums.status;
import br.com.RollTickets.api.enums.metodoPagamento;

@RestController
@RequestMapping("/api/mercadopago")
@CrossOrigin("*")
public class PagamentoMercadoPagoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private CompraRepository compraRepository;

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
                .email(pagamentoDTO.email())
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

        // Executa o pagamento
        Payment payment = paymentClient.create(paymentRequest);

        // Retorna resposta
        return ResponseEntity.ok(Map.of(
                "status", payment.getStatus(),
                "id", payment.getId()));

    }

    @PostMapping("/confirmar")
    public ResponseEntity<?> confirmarPagamento(@RequestBody Map<String, String> body)
            throws MPException, MPApiException {
        MercadoPagoConfig.setAccessToken(accessToken);
        String paymentIdStr = body.get("paymentId"); // id Mercado Pago
        String compraIdStr = body.get("compraId"); // id Compra no seu sistema

        Long compraId = Long.parseLong(compraIdStr);

        PaymentClient paymentClient = new PaymentClient();
        Payment payment = paymentClient.get(Long.parseLong(paymentIdStr));

        String statusMP = payment.getStatus();

        if ("approved".equalsIgnoreCase(statusMP)) {
            Compra compra = compraRepository.findById(compraId)
                    .orElseThrow(() -> new RuntimeException("Compra não encontrada"));

            // Cria ou atualiza o pagamento no seu sistema
            pagamentoService.storeOrUpdatePagamento(compra, status.PAGO, metodoPagamento.CREDITO, LocalDateTime.now());

            return ResponseEntity.ok(Map.of("status", statusMP, "message", "Pagamento aprovado! Compra confirmada."));
        } else {
            return ResponseEntity.ok(Map.of("status", statusMP, "message", "Pagamento não aprovado ainda."));
        }
    }

}
