package edu.uneti.stem.payloads.responses;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
    UUID id;
    String email;
    String telephone;
    String firstName;
    String lastName;
    LocalDate birthday;
    String gender;
}
