package edu.uneti.stem.mappers.impls;

import edu.uneti.stem.entities.User;
import edu.uneti.stem.mappers.UserMapper;
import edu.uneti.stem.payloads.requests.CreateUserRequest;
import edu.uneti.stem.payloads.requests.UpdateUserRequest;
import edu.uneti.stem.payloads.responses.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(CreateUserRequest request) {
        if (request == null) {
            return null;
        }
        return User.builder()
                .email(request.getEmail())
                .telephone(request.getTelephone())
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .gender(request.getGender())
                .birthday(request.getBirthday())
                .password(request.getPassword())
                .build();
    }

    @Override
    public User toUser(UpdateUserRequest request) {
        return null;
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if(user == null){
            return null;
        }
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .telephone(user.getTelephone())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .gender(user.getGender())
                .birthday(user.getBirthday())
                .build();
    }
}
