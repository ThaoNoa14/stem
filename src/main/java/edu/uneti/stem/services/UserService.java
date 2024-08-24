package edu.uneti.stem.services;

import edu.uneti.stem.entities.User;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {

    public Flux<User> getAllUser(Pageable pageable);

    public Mono<User> getUserById(UUID id);

    public Mono<User> createUser(User user);

    public Mono<Void> updateUser(UUID id, User user);

    public Mono<Boolean> deleteUser(UUID id);
}
