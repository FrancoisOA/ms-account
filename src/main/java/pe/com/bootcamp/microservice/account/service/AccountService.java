package pe.com.bootcamp.microservice.account.service;

import pe.com.bootcamp.microservice.account.entity.Account;
import pe.com.bootcamp.microservice.account.model.CreditCard;
import pe.com.bootcamp.microservice.account.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService  extends CrudService<Account, String>{

    Mono<Account> saveSavingAccount(Account account, String numCreditCard);
    Mono<Account> saveCurrentAccount(Account account, String numCreditCard);
    Mono<Account> saveFixedTerm(Account account);

    //Customers
    Flux<Customer> getCustomers();
    Mono<Customer> getCustomerById(String id);
    
    //Credit card buscar por numero de tarjeta / verificar deuda del cliente
    Mono<CreditCard> getCreditCardByNum(String numCreditCard);
    Mono<CreditCard> getDebtCustomerById(String idCustomer);
    
    
}
