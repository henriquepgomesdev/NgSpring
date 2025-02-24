package org.example.user.repository;

import org.example.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<Object> findByLogin(String username);
}
