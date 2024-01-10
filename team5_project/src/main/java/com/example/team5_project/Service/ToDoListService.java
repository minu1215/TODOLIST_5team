package com.example.team5_project.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team5_project.model.CategoryName;
import com.example.team5_project.model.ERole;
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

        System.out.println(toDoListDTO.getCategory());
    	CategoryName categoryName = CategoryName.builder()
    									.categoryName(toDoListDTO.getCategory())
    									.id(categoryNameRepository.findByCategoryName(toDoListDTO.getCategory()).get().getId())
    									.build();
    	Long parentId = toDoListRepository.getById(toDoListDTO.getParentId()).getId();
    	ToDoList toDoList = ToDoList.builder()
    							.parentId(toDoListDTO.getParentId() != null ? parentId : null)
    							.listName(toDoListDTO.getListName())
    							.listDesc(toDoListDTO.getListDesc())
    							.createDate(new Date())
    							.endDate(toDoListDTO.getEndDate())
    							.isCommpleted(false)
    							.level(toDoListDTO.getLevel())
    							.likeCount(new Integer(0))
    							.user(user.get())
    							.project(null)
    							.categoryName(categoryName)
    							.likeUser(null)
    							.replys(null)
    							.emotions(null)
    							.build();
    							

        return toDoListRepository.save(toDoList);
    }

}
