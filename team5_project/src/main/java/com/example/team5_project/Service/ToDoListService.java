package com.example.team5_project.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team5_project.model.CategoryName;
import com.example.team5_project.model.ERole;
import com.example.team5_project.model.Emotion;
import com.example.team5_project.model.Role;
import com.example.team5_project.model.ToDoList;
import com.example.team5_project.model.ToDoListDTO;
import com.example.team5_project.model.User;
import com.example.team5_project.repository.CategoryNameRepository;
import com.example.team5_project.repository.ToDoListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToDoListService {
    private final ToDoListRepository toDoListRepository;
    private final CategoryNameRepository categoryNameRepository;

    @Transactional
    public ToDoList createList(ToDoListDTO toDoListDTO, Optional<User> user) {

    	CategoryName categoryName = categoryNameRepository.findByCategoryName(toDoListDTO.getCategory()).get();
		if (categoryName == null) {
			throw new RuntimeException("없는 카테고리입니다.");
		}
		
    	ToDoList toDoList = ToDoList.builder()
    							.parentId(toDoListDTO.getParentId())
    							.listName(toDoListDTO.getListName())
    							.listDesc(toDoListDTO.getListDesc())
    							.createDate(new Date())
    							.endDate(toDoListDTO.getEndDate())
    							.isCommpleted(false)
    							.level(toDoListDTO.getLevel())
    							.user(user.get())
    							.project(null)
    							.categoryName(categoryName)
    							.likeUsers(null)
    							.build();
    							

        return toDoListRepository.save(toDoList);
    }
    
    @Transactional
    public void deleteList(Long listId, Optional<User> user) {
    	
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
		
        toDoListRepository.deleteById(listId);
    }
    
    @Transactional
    public ToDoList updateList(Long listId, ToDoListDTO toDoListDTO, Optional<User> user) {
    	Optional<ToDoList> toDoList = toDoListRepository.findById(listId);

    	if(toDoList.isEmpty()) {
    		throw new RuntimeException("리스트가 존재하지 않습니다.");
    	}
		if (toDoList.get().getProject() != null) {
			Set<User> users = toDoList.get().getProject().getUsers();
			
			if (!users.contains(user)) {
				throw new RuntimeException("해당 이름의 사용자가 프로젝트에 속해있지 않습니다.");
			}
		} else {
			if(toDoList.get().getUser() != user.get()) {
	    		throw new RuntimeException("일치하지 않는 유저입니다.");
	    	}	    	
		}
	
    	CategoryName categoryName = CategoryName.builder()
				.categoryName(toDoListDTO.getCategory())
				.id(categoryNameRepository.findByCategoryName(toDoListDTO.getCategory()).get().getId())
				.build();
    	
        // listName, listDesc, endDate, iscompleted, level 수정
        toDoList.get().setListName(toDoListDTO.getListName());
        toDoList.get().setListDesc(toDoListDTO.getListDesc());
        toDoList.get().setEndDate(toDoListDTO.getEndDate());
        toDoList.get().setIsCommpleted(toDoListDTO.getIsCommpleted());
        toDoList.get().setCategoryName(categoryName);
        
        
        // project, categoryName, emotions 수정은 못함

        // Save the updated ToDoList
        return toDoListRepository.save(toDoList.get());
    }

    @Transactional(readOnly = true)
    public ToDoList getList(Long listId, Optional<User> user) {
       	Optional<ToDoList> toDoList = toDoListRepository.findById(listId);

    	if(toDoList.isEmpty()) {
    		throw new RuntimeException("리스트가 존재하지 않습니다.");
    	}
		if (toDoList.get().getProject() != null) {
			Set<User> users = toDoList.get().getProject().getUsers();
			
			if (!users.contains(user)) {
				throw new RuntimeException("해당 이름의 사용자가 프로젝트에 속해있지 않습니다.");
			}
		} else {
			if(toDoList.get().getUser() != user.get()) {
	    		throw new RuntimeException("일치하지 않는 유저입니다.");
	    	}	    	
		}
        return toDoList.get();
    }
}
