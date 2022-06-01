package pe.com.bootcamp.microservice.account.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.bootcamp.microservice.account.entity.Account;
import reactor.core.publisher.Mono;
 
@Repository
public interface IAccountRepository extends ReactiveMongoRepository<Account, String>{
	 Mono<Account> findByIdCustomer(String idCustomer);
}
