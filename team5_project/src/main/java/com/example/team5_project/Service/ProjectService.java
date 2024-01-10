package com.example.team5_project.Service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.team5_project.model.Project;
import com.example.team5_project.model.ProjectDTO;
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
    						.email(user.get().getEmail())
    						.users(Collections.singleton(user.get()))
    						.build();
    	
    	return projectRepository.save(project);
    }

}
