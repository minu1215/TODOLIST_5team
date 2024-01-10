package com.example.team5_project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team5_project.Service.EmotionService;
import com.example.team5_project.Service.UserService;
import com.example.team5_project.model.Emotion;
import com.example.team5_project.model.EmotionDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmotionController {
	private final UserService userService;
	private final EmotionService emotionService;
		
	@PostMapping("/emotion/create")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<Emotion> createProject(@RequestBody EmotionDTO emotionDTO) {
				
		return ResponseEntity.ok(emotionService.createEmotion(emotionDTO, userService.getMyUserWithAuthorities()));
	}
}
