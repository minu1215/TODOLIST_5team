package com.example.team5_project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team5_project.Service.ProjectService;
import com.example.team5_project.Service.UserService;
import com.example.team5_project.model.Project;
import com.example.team5_project.model.ProjectDTO;
import com.example.team5_project.model.ToDoList;
import com.example.team5_project.model.ToDoListDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProjectController {
	private final UserService userService;
	private final ProjectService projectService;
		
	@PostMapping("/project/create")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<Project> createProject(@RequestBody ProjectDTO projectDTO) {
				
		return ResponseEntity.ok(projectService.createProject(projectDTO, userService.getMyUserWithAuthorities()));
	}
}
