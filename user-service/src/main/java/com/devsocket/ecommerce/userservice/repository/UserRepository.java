// repository/UserRepository.java
package com.devsocket.ecommerce.userservice.repository;

import com.devsocket.ecommerce.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
