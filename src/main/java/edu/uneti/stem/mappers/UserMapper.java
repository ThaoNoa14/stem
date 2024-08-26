package edu.uneti.stem.mappers;

import edu.uneti.stem.entities.User;
import edu.uneti.stem.payloads.requests.CreateUserRequest;
import edu.uneti.stem.payloads.requests.UpdateUserRequest;
import edu.uneti.stem.payloads.responses.UserResponse;
import edu.uneti.stem.repositories.UserRepository;

public interface UserMapper {
    User toUser(CreateUserRequest request);
    User toUser(UpdateUserRequest request);
    UserResponse toUserResponse(User user);
}
