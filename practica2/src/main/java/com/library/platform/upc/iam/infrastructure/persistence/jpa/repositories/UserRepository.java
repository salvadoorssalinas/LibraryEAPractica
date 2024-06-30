package com.library.platform.upc.iam.infrastructure.persistence.jpa.repositories;

import com.library.platform.upc.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User repository
 * <p>
 *     This interface is used to interact with the database to manage the users.
 * </p>
 * @author Salvador Salinas
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
