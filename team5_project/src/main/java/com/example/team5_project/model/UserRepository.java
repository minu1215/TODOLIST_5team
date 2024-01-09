package com.example.team5_project.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findOneWithAuthoritiesByUsername(String username);

	Optional<User> findOneWithAuthoritiesByEmail(String email);
}
