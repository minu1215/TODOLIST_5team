package com.example.team5_project.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team5_project.model.Reply;
import com.example.team5_project.model.ReplyDTO;
import com.example.team5_project.model.ToDoList;
import com.example.team5_project.model.User;
import com.example.team5_project.repository.ReplyRepository;
import com.example.team5_project.repository.ToDoListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {
	
	private final ReplyRepository replyRepository;
	private final ToDoListRepository toDoListRepository;
	
    @Transactional
    public Reply createReply(ReplyDTO replyDTO, Optional<User> user) {
    	
    	Optional<ToDoList> todoList = toDoListRepository.findById(replyDTO.getListId());
    	
		if (todoList.get().getProject() != null) {
			Set<User> users = todoList.get().getProject().getUsers();
			
			if (!users.contains(user)) {
				throw new RuntimeException("해당 이름의 사용자가 프로젝트에 속해있지 않습니다.");
			}
		} else {
			if(todoList.get().getUser() != user.get()) {
	    		throw new RuntimeException("일치하지 않는 유저입니다.");
	    	}	    	
		}
		
    	Reply reply = Reply.builder()
				.content(replyDTO.getContent())
				.createDate(new Date())
				.user(user.get())
				.list(todoList.get())
				.build();
    	
  	
    	return replyRepository.save(reply);
    }

}
