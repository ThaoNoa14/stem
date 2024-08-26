package edu.uneti.stem.controllers;

import edu.uneti.stem.payloads.requests.CreateUserRequest;
import edu.uneti.stem.payloads.responses.UserResponse;
import edu.uneti.stem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public Mono<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }
}
