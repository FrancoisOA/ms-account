package pe.com.bootcamp.microservice.account.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.com.bootcamp.microservice.account.entity.Account;
import pe.com.bootcamp.microservice.account.model.Customer;
import pe.com.bootcamp.microservice.account.service.AccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path="/account")
public class AccountController {

	@Autowired
	private final AccountService accountService;
	
 
    @PostMapping("/create/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Account> saveAccount(@RequestBody Account account, @PathVariable("id") String numCreditCard){
    	log.info("Crear cuenta bancaria");
        return account.getTypeAccount().equals("ahorros")
                ?  accountService.saveSavingAccount(account, numCreditCard)
            : account.getTypeAccount().equals("cuenta corriente")
                ? accountService.saveCurrentAccount(account, numCreditCard)
                : accountService.saveFixedTerm(account);
    }
    
    @GetMapping("/find-account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  Mono<Account> findAccountId(@PathVariable String id){
    	log.info("Buscar una cuenta bancaria");
        return accountService.findById(id);
    }
 
 
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Account> updateAccount(@RequestBody Account account){
    	log.info("Actualizar cuenta bancaria");
        return accountService.update(account);
    }
 
    
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
    public Mono<Account> deleteAccount(@PathVariable String id){
		log.info("Eliminar cuenta bancaria");
        return accountService.deleteById(id);
    }
 

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Customer> getCustomers(){
    	log.info("Listar clientes");
        return accountService.getCustomers();
    }
    
    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Customer> getCustomerById(@PathVariable String id){
    	log.info("Listar cliente por id: "+ id);
        return accountService.getCustomerById(id);
    }
}