package com.example.team5_project.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(ERole roleUser);

}
