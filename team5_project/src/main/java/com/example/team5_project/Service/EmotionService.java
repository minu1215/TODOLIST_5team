package com.example.team5_project.Service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team5_project.model.Emotion;
import com.example.team5_project.model.EmotionDTO;
import com.example.team5_project.model.Reply;
import com.example.team5_project.model.ToDoList;
import com.example.team5_project.model.User;
import com.example.team5_project.repository.EmotionRepository;
import com.example.team5_project.repository.ToDoListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmotionService {

	private final EmotionRepository emotionRepository;
	private final ToDoListRepository toDoListRepository;

	@Transactional
	public Emotion createEmotion(EmotionDTO emotionDTO, Optional<User> user) {

		if (emotionRepository.findByName(emotionDTO.getName()) == null) {
			throw new RuntimeException("없는 감정표현입니다.");
		}
		Emotion emotion = Emotion.builder().name(emotionDTO.getName()).build();

		Optional<ToDoList> todoList = toDoListRepository.findById(emotionDTO.getListId());

		if (todoList.get().getProject() != null) {
			Set<User> users = todoList.get().getProject().getUsers();
			boolean userNameExists = users.stream().anyMatch(u -> u.getId().equals(user.get().getId()));

			if (!userNameExists) {
				throw new RuntimeException("해당 이름의 사용자가 프로젝트에 속해있지 않습니다.");
			}
		}
    	emotionRepository.save(emotion);


    	Set<Emotion> emotions = todoList.get().getEmotions();
    	emotions.add(emotion);
    	
    	todoList.get().setEmotions(emotions);

    	toDoListRepository.save(todoList.get());
  	
		return emotion;
	}

}
