package com.example.team5_project.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team5_project.model.Project;
import com.example.team5_project.model.ProjectDTO;
import com.example.team5_project.model.ToDoList;
import com.example.team5_project.model.User;
import com.example.team5_project.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {
	
	private final ProjectRepository projectRepository;
	
    @Transactional
    public Project createProject(ProjectDTO projectDTO, Optional<User> user) {

    	Project project = Project.builder()
    						.projectName(projectDTO.getProjectName())
    						.users(Collections.singleton(user.get()))
    						.build();
    	
    	return projectRepository.save(project);
    }
    
    @Transactional
    public void deleteProject(Long projectId, Optional<User> user) {
    	
    	Optional<Project> project = projectRepository.findById(projectId);
    	
    	if(project.isEmpty()) {
    		throw new RuntimeException("해당 프로젝트를 찾을 수 없습니다.");
    	}
    	
    	if(project.get().getUsers().contains(user.get())) {
    		project.get().getUsers().remove(user.get());
    		projectRepository.save(project.get());
    	} else {
    		throw new RuntimeException("해당 이름의 사용자가 프로젝트에 속해있지 않습니다.");
    	}
    	
    	if(project.get().getUsers().size() == 0) {
    		projectRepository.deleteById(projectId);
    	}
    }
    
    @Transactional
    public Project updateProject(Long projectId, ProjectDTO projectDTO, Optional<User> user) {
    	
    	Optional<Project> project = projectRepository.findById(projectId);
    	
    	if(project.isEmpty()) {
    		throw new RuntimeException("해당 프로젝트를 찾을 수 없습니다.");
    	}
    	
    	if(!project.get().getUsers().contains(user.get())) {
    		throw new RuntimeException("해당 이름의 사용자가 프로젝트에 속해있지 않습니다.");
    	}
    	
    	project.get().setProjectName(projectDTO.getProjectName());
    	
		return projectRepository.save(project.get());
   	
    }
    
    @Transactional
    public Project readProject(Long projectId, Optional<User> user) {
    	Optional<Project> project = projectRepository.findById(projectId);
    	
    	if(project.isEmpty()) {
    		throw new RuntimeException("해당 프로젝트를 찾을 수 없습니다.");
    	}
    	if(!project.get().getUsers().contains(user.get())) {
    		throw new RuntimeException("해당 이름의 사용자가 프로젝트에 속해있지 않습니다.");
    	}
    	
    	return projectRepository.findById(projectId).get();
    }

}
