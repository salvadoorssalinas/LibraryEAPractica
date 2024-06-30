package com.library.platform.upc.iam.infrastructure.persistence.jpa.repositories;

import com.library.platform.upc.iam.domain.model.entities.Role;
import com.library.platform.upc.iam.domain.model.valueobjects.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Role repository
 * <p>
 *     This interface is used to interact with the database to manage the roles.
 * </p>
 * @author Salvador Salinas
 * @version 1.0
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);

    boolean existsByName(Roles name);
}
