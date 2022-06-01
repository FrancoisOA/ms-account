package pe.com.bootcamp.microservice.account.service;

import pe.com.bootcamp.microservice.account.entity.Account;
import pe.com.bootcamp.microservice.account.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService  extends CrudService<Account, String>{

    Mono<Account> saveSavingAccount(Account account, String numCreditCard);
    Mono<Account> saveCurrentAccount(Account account, String numCreditCard);
    Mono<Account> saveFixedTerm(Account account);
//    Flux<Customer> getCustomers();
//    Mono<Customer> getCustomerById(String id);
}
