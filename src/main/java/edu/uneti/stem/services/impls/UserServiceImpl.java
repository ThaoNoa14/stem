package edu.uneti.stem.services.impls;

import edu.uneti.stem.entities.User;
import edu.uneti.stem.exceptions.BadRequestException;
import edu.uneti.stem.exceptions.ResourceConflictException;
import edu.uneti.stem.exceptions.ResourceNotFoundException;
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


    @Override
    public Flux<User> getAllUser(Pageable pageable) {
        return userRepository.findAllBy(pageable);
    }

    @Override
    public Mono<User> getUserById(UUID id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("User not found!")));
    }

    @Override
    public Mono<User> createUser(User user) {
        if (user == null) {
            return Mono.error(new BadRequestException("User cannot be null"));
        }
        return userRepository.findByEmail(user.getEmail())
                .flatMap(existingUser -> Mono.<User>error(new ResourceConflictException("email")))
                .switchIfEmpty(
                        userRepository.findByTelephone(user.getTelephone())
                                .flatMap(existingUser -> Mono.<User>error(new ResourceConflictException("telephone")))
                                .switchIfEmpty(userRepository.save(user))
                );
    }

    @Override
    public Mono<Void> updateUser(UUID id, User updateUser) {
        Mono<User> oldUser = getUserById(id);
        userRepository.save(updateUser);
        return null;
    }

    @Override
    public Mono<Boolean> deleteUser(UUID id) {
        return userRepository.findById(id)
                .flatMap(existingUser -> userRepository.deleteById(id).then(Mono.just(true))
                        .switchIfEmpty(Mono.just(false))
                );
    }
}
