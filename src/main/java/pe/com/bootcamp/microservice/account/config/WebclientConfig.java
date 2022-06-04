package pe.com.bootcamp.microservice.account.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;

import pe.com.bootcamp.microservice.account.model.CreditCard;
import pe.com.bootcamp.microservice.account.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class WebclientConfig {
	private final WebClient.Builder builder = WebClient.builder();

	public Flux<Customer> getCustomers() {
		return builder.build().get().uri("http://localhost:8080/customers").retrieve()
				.bodyToFlux(Customer.class);
	}

	public Mono<Customer> getCustomerById(@PathVariable String id) {
		return builder.build().get().uri("http://localhost:8080/customers/" + id).retrieve()
				.bodyToMono(Customer.class);
	}

	public Mono<CreditCard> getCreditCardByNum(@PathVariable String numCreditCard) {
		return builder.build().get().uri("http://localhost:8080/Credit/numberDocument/" + numCreditCard).retrieve()
				.bodyToMono(CreditCard.class);
	}

	public Mono<CreditCard> getDebtCustomerById(@PathVariable String idCustomer) {
		return builder.build().get().uri("http://localhost:8080/CreditLine/xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx /" + idCustomer).retrieve()
				.bodyToMono(CreditCard.class);
	}

}
