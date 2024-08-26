package edu.uneti.stem.services;

import edu.uneti.stem.entities.User;
import edu.uneti.stem.payloads.requests.CreateUserRequest;
import edu.uneti.stem.payloads.responses.UserResponse;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {

    public Flux<UserResponse> getAllUser(Pageable pageable);

    public Mono<UserResponse> getUserById(UUID id);

    public Mono<UserResponse> createUser(CreateUserRequest request);

    public Mono<Void> updateUser(UUID id, User user);

    public Mono<Void> deleteUser(UUID id);
}
