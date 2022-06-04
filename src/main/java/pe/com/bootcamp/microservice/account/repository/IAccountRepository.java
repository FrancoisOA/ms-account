package pe.com.bootcamp.microservice.account.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.com.bootcamp.microservice.account.entity.Account;
import reactor.core.publisher.Mono;
 
@Repository //Cada clase repository indica una base de datos. Aqui estan todos los registros de cuentas 
public interface IAccountRepository extends ReactiveMongoRepository<Account, String>{
//	findBy + seguido del nombre del campo X a buscar. JPA entenderá la operacion indicada
	Mono<Account> findByIdCustomer(String idCustomer);	
	
	Mono<Account> findByTypeAccount(String TypeAccount); 
	Mono<Account> findByMinOpeningAmount(String MinOpeningAmount); 
    Mono<Account> findByNumAccount(String NumAccount); 
	Mono<Account> findByOpeningDate(String OpeningDate);
	Mono<Account> findByStatus(String Status);	
	Mono<Account> findByCurrentAvailableBalance(String CurrentAvailableBalance);
	Mono<Account> findByCurrency(String Currency);
	Mono<Account> findByNumCreditCard(String NumCreditCard);
}
