package edu.uneti.stem.services.impls;

import edu.uneti.stem.entities.User;
import edu.uneti.stem.exceptions.BadRequestException;
import edu.uneti.stem.exceptions.ResourceConflictException;
import edu.uneti.stem.exceptions.ResourceNotFoundException;
import edu.uneti.stem.mappers.UserMapper;
import edu.uneti.stem.payloads.requests.CreateUserRequest;
import edu.uneti.stem.payloads.responses.UserResponse;
import edu.uneti.stem.repositories.UserRepository;
import edu.uneti.stem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public Flux<UserResponse> getAllUser(Pageable pageable) {

        return userRepository.findAllBy(pageable)
                .flatMap(savedUser -> Flux.just(userMapper.toUserResponse(savedUser)));
    }

    @Override
    public Mono<UserResponse> getUserById(UUID id) {
        return userRepository.findById(id)
                .flatMap(savedUser -> Mono.just(userMapper.toUserResponse(savedUser)))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("User not found!")));
    }

    @Override
    public Mono<UserResponse> createUser(CreateUserRequest request) {
        if (request == null) {
            return Mono.error(new BadRequestException("User cannot be null"));
        }

        return userRepository.findByEmail(request.getEmail())
                .flatMap(existingUser -> Mono.<UserResponse>error(new ResourceConflictException("Email already exists")))
                .switchIfEmpty(
                        userRepository.findByTelephone(request.getTelephone())
                                .flatMap(existingUser -> Mono.<UserResponse>error(new ResourceConflictException("Telephone already exists")))
                                .switchIfEmpty(
                                        userRepository.save(userMapper.toUser(request))
                                                .flatMap(savedUser -> Mono.just(userMapper.toUserResponse(savedUser)))
                                )
                );
    }

    @Override
    public Mono<Void> updateUser(UUID id, User updateUser) {
        return null;
    }

    @Override
    public Mono<Void> deleteUser(UUID id) {
        return userRepository.findById(id)
                .flatMap(existingUser -> userRepository.deleteById(id)
                );
    }
}
