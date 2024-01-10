package com.example.team5_project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team5_project.Service.ToDoListService;
import com.example.team5_project.Service.UserService;
import com.example.team5_project.model.ToDoList;
import com.example.team5_project.model.ToDoListDTO;
import com.example.team5_project.model.User;
import com.example.team5_project.model.UserDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ListController {
	private final UserService userService;
	private final ToDoListService toDoListService;
		
	@PostMapping("/list/create")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<ToDoList> createList(@RequestBody ToDoListDTO toDoListDTO) {
		
		toDoListDTO.setParentId(-1L);
		toDoListDTO.setLevel(0);
		
		return ResponseEntity.ok(toDoListService.createList(toDoListDTO, userService.getMyUserWithAuthorities()));
	}
	
	@PostMapping("/list/create/{parentId}/{level}")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<ToDoList> createChildList(@RequestBody ToDoListDTO toDoListDTO, @PathVariable Long parentId, @PathVariable Integer level) {
		
		toDoListDTO.setParentId(parentId);
		toDoListDTO.setLevel(level);
		
		return ResponseEntity.ok(toDoListService.createList(toDoListDTO, userService.getMyUserWithAuthorities()));
	}
}
