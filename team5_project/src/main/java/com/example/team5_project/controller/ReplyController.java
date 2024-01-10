package com.example.team5_project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.team5_project.Service.ReplyService;
import com.example.team5_project.Service.UserService;
import com.example.team5_project.model.Reply;
import com.example.team5_project.model.ReplyDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReplyController {
	private final UserService userService;
	private final ReplyService replyService;
		
	@PostMapping("/reply/create")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<Reply> createProject(@RequestBody ReplyDTO replyDTO) {
				
		return ResponseEntity.ok(replyService.createReply(replyDTO, userService.getMyUserWithAuthorities()));
	}
}
