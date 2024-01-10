package com.example.team5_project.Service;

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
    	
    	Reply reply = Reply.builder()
				.content(replyDTO.getContent())
				.user(user.get())
				.build();
    	
    	replyRepository.save(reply);
    	
    	Optional<ToDoList> todoList = toDoListRepository.findById(replyDTO.getListId());
    	
    	if(todoList.get().getUser().getId() != user.get().getId()) {
    		throw new RuntimeException("일치하지 않는 유저입니다.");
    	}
    	
    	Set<Reply> replys = todoList.get().getReplys();
    	replys.add(reply);
    	
    	todoList.get().setReplys(replys);

    	toDoListRepository.save(todoList.get());
  	
    	return reply;
    }

}
