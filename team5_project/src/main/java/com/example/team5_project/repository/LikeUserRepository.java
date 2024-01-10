package com.example.team5_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.team5_project.model.LikeUser;

public interface LikeUserRepository extends JpaRepository<LikeUser, Long> {

}
