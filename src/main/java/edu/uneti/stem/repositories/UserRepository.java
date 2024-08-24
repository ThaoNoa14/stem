package edu.uneti.stem.repositories;

import edu.uneti.stem.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, UUID> {

    Flux<User> findAllBy(Pageable pageable);
    Mono<User> findByEmail(String email);
    Mono<User> findByTelephone(String telephone);
}
