package pe.com.bootcamp.microservice.account.service;

import reactor.core.publisher.Mono;

public interface CrudService<T, ID> {
    
    Mono<T> save(T t);
    Mono<T> update(T t);
    Mono<T> deleteById(String id);
    Mono<T> findById(String id);
}
