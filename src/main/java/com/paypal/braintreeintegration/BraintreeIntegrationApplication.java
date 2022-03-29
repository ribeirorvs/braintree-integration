package com.paypal.braintreeintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.braintreegateway.*;
import java.math.BigDecimal;

@SpringBootApplication
@RestController
public class BraintreeIntegrationApplication {

	private static BraintreeGateway gateway = new BraintreeGateway(
			Environment.SANDBOX,
			"w8b33jczk3ys342p",
			"799mw428s9hv5zxx",
			"8c6ca7db051b6ee29ee7db31905dc5a0");

	public static void main(String[] args) {
		SpringApplication.run(BraintreeIntegrationApplication.class, args);
	}

	@PostMapping("/checkout")
	public Result<Transaction> Checkout(@RequestParam(value = "paymentMethodNonce") String paymentMethodNonce) {
		String nonceFromTheClient = paymentMethodNonce;
		System.out.println("Test");
		TransactionRequest request = new TransactionRequest()
				.amount(new BigDecimal("10.00"))
				.paymentMethodNonce(nonceFromTheClient)
				.options()
				.submitForSettlement(true)
				.done();

		Result<Transaction> result = gateway.transaction().sale(request);
		System.out.println(result);
		return result;
	}

	@GetMapping("/client-token")
	public String ClientToken() {

		final String clientToken = gateway.clientToken().generate();

		System.out.println(clientToken);

		return clientToken;
	}

}
