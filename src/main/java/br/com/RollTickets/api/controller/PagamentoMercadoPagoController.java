package br.com.RollTickets.api.controller;

import java.math.BigDecimal;
import java.util.Map;

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


@RestController
@RequestMapping("/api/mercadopago")
@CrossOrigin("*")
public class PagamentoMercadoPagoController {

    @Value("${mercadopago.token}")
    private String accessToken;

    @PostMapping("/cartao")
    public ResponseEntity<?> pagarCartao(@RequestBody PagamentoCartaoDTO pagamentoDTO) throws MPException, MPApiException {
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
            "id", payment.getId()
        ));
    }
}
