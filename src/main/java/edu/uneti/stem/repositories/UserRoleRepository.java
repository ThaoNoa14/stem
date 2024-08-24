package edu.uneti.stem.repositories;

import edu.uneti.stem.entities.UserRole;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRoleRepository extends ReactiveCrudRepository<UserRole, UUID> {

}
