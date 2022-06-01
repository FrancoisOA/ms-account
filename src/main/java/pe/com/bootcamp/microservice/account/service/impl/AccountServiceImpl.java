package pe.com.bootcamp.microservice.account.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.com.bootcamp.microservice.account.config.WebclientConfig;
import pe.com.bootcamp.microservice.account.entity.Account;
import pe.com.bootcamp.microservice.account.model.Customer;
import pe.com.bootcamp.microservice.account.repository.IAccountRepository;
import pe.com.bootcamp.microservice.account.service.AccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	@Autowired
	IAccountRepository accountRepo;

	private final WebclientConfig webclient = new WebclientConfig();

	@Override
	public Flux<Account> findAll() {
		log.info("Listar cuentas");
		return accountRepo.findAll();
	}

	@Override
	public Mono<Account> findById(String id) {
		log.info("Buscar cuenta");
		return accountRepo.findById(id);
	}

	@Override
	public Mono<Account> saveSavingAccount(Account account, String numCreditCard) {
		log.info("Opcion cuenta ahorro");
		Mono<Customer> customer = webclient.getCustomerById(account.getIdCustomer());
		return customer
				.filter(c -> (c.getProfile().equals("VIP") && accountRepo.findByIdCustomer(c.getId()) == null
						&& webclient.getCreditCardByNum(numCreditCard) != null))
				.flatMap(o -> this.save(account))
				.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT)));
	}

	@Override
	public Mono<Account> saveCurrentAccount(Account account, String numCreditCard) {
		log.info("Opcion cuenta corriente");
		Mono<Customer> customer = webclient.getCustomerById(account.getIdCustomer());
		return customer.filter(c -> ((c.getTypeCustomer().equals("PERSONAL")
				&& accountRepo.findByIdCustomer(account.getIdCustomer()).equals(""))
				|| (c.getTypeCustomer().equals("EMPRESARIAL") && webclient.getCreditCardByNum(numCreditCard) != null)))
				.flatMap(o -> this.save(account)
						.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT))));
	}

	@Override
	public Mono<Account> saveFixedTerm(Account account) {
		log.info("Opcion cuenta plazo fijo");
		Mono<Customer> customer = webclient.getCustomerById(account.getIdCustomer());
		return customer.filter(c -> c.getProfile().equals("personal")).flatMap(o -> this.save(account))
				.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT)));
	}

	@Override
	public Mono<Account> save(Account a) {
		log.info("Cuenta creada");
		a.setOpeningDate(new Date());
		a.setStatus(true);
		return accountRepo.save(a);
	}

	@Override
	public Mono<Account> update(Account account) {
		log.info("Actualizar cuenta");
		return accountRepo.findById(account.getId()).switchIfEmpty(Mono.empty()).flatMap(o -> {
			if (account.getTypeAccount() != null) {
				o.setTypeAccount(account.getTypeAccount());
			}
			if (account.getMinOpeningAmount() != 0.0d) {
				o.setMinOpeningAmount(account.getMinOpeningAmount());
			}
			if (account.getNumAccount() != null) {
				o.setNumAccount(account.getNumAccount());
			}
			if (account.getOpeningDate() != null) {
				o.setOpeningDate(account.getOpeningDate());
			}
			if (account.getIdCustomer() != null) {
				o.setIdCustomer(account.getIdCustomer());
			}
			if (account.getStatus() != null) {
				o.setStatus(account.getStatus());
			}
			if (account.getCurrentAvailableBalance() != 0.0d) {
				o.setCurrentAvailableBalance(account.getCurrentAvailableBalance());
			}
			if (account.getCurrency() != null) {
				o.setCurrency(account.getCurrency());
			}
			return accountRepo.save(o);
		});
	}

	@Override
	public Mono<Account> deleteById(String id) {
		log.info("Eliminar cuenta");
		return accountRepo.findById(id).switchIfEmpty(Mono.empty()).flatMap(o -> {
			o.setStatus(false);
			return accountRepo.save(o);
		});
	}

}
