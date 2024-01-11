package com.example.team5_project.Service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team5_project.model.Emotion;
import com.example.team5_project.model.EmotionDTO;
import com.example.team5_project.model.EmotionUser;
import com.example.team5_project.model.ListIdDTO;
import com.example.team5_project.model.ToDoList;
import com.example.team5_project.model.User;
import com.example.team5_project.repository.EmotionRepository;
import com.example.team5_project.repository.EmotionUserRepository;
import com.example.team5_project.repository.ToDoListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmotionService {

	private final EmotionRepository emotionRepository;
	private final EmotionUserRepository emotionUserRepository;
	
	private final ToDoListRepository toDoListRepository;

	@Transactional
	public Optional<EmotionUser> checkEmotion(EmotionDTO emotionDTO, Optional<User> user) {

		Emotion emotion = emotionRepository.findByName(emotionDTO.getName());
		if (emotion == null) {
			throw new RuntimeException("없는 감정표현입니다.");
		}
		
		
		Optional<ToDoList> todoList = toDoListRepository.findById(emotionDTO.getListId());

		EmotionUser emotionUser = EmotionUser.builder()
				.user(user.get())
				.list(todoList.get())
				.emotion(emotion)
				.build();

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
		
		
		System.out.println("test1");
		Optional<EmotionUser> findEmotionUser = emotionUserRepository.findByListIdAndUserId(emotionDTO.getListId(), user.get().getId());
		
		System.out.println("test2");
		if(findEmotionUser.isEmpty()) {
			emotionUserRepository.save(emotionUser);			
		} else if(findEmotionUser.get().getEmotion().getId() != emotion.getId()){
			findEmotionUser.get().setEmotion(emotion);
			emotionUserRepository.save(findEmotionUser.get());
		} else {
			emotionUserRepository.delete(findEmotionUser.get());
		}

		Optional<EmotionUser> result = emotionUserRepository.findByListIdAndUserId(emotionDTO.getListId(), user.get().getId());
		return result.isPresent() ? result : null;
	}
	
	@Transactional
	public Optional<EmotionUser> readEmotion(Long listId, Optional<User> user){
		Optional<ToDoList> todoList = toDoListRepository.findById(listId);

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
		Optional<EmotionUser> result = emotionUserRepository.findByListId(listId);
		return result.isPresent() ? result : null;
	}

}
