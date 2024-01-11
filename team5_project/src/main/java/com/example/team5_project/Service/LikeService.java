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
public class LikeService {

	private final ReplyRepository replyRepository;
	private final ToDoListRepository toDoListRepository;

	@Transactional
	public ToDoList checkLike(Long listId, Optional<User> user) {

		Optional<ToDoList> todoList = toDoListRepository.findById(listId);

		if (todoList.get().getProject() != null) {
			Set<User> users = todoList.get().getProject().getUsers();

			if (!users.contains(user)) {
				throw new RuntimeException("해당 이름의 사용자가 프로젝트에 속해있지 않습니다.");
			}
		} else {
			if (todoList.get().getUser() != user.get()) {
				throw new RuntimeException("일치하지 않는 유저입니다.");
			}
		}

		Set<User> likeUsers = todoList.get().getLikeUsers();
		if (likeUsers.contains(user.get())) {
			likeUsers.remove(user.get());
		} else {
			likeUsers.add(user.get());
		}
		todoList.get().setLikeUsers(likeUsers);

		toDoListRepository.save(todoList.get());

		return toDoListRepository.save(todoList.get());
	}

	@Transactional
	public Set<User> readLike(Long listId, Optional<User> user) {
		Optional<ToDoList> todoList = toDoListRepository.findById(listId);

		if (todoList.get().getProject() != null) {
			Set<User> users = todoList.get().getProject().getUsers();

			if (!users.contains(user)) {
				throw new RuntimeException("해당 이름의 사용자가 프로젝트에 속해있지 않습니다.");
			}
		} else {
			if (todoList.get().getUser() != user.get()) {
				throw new RuntimeException("일치하지 않는 유저입니다.");
			}
		}
		
		return toDoListRepository.findById(listId).get().getLikeUsers();

	}
}
