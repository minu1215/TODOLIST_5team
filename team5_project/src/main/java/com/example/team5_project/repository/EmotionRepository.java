package com.example.team5_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.team5_project.model.Emotion;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {

}
