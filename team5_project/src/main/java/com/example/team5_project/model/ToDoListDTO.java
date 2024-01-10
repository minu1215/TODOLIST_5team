package com.example.team5_project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToDoListDTO {
	
	private Long parentId;
	
    private String listName;

    private String listDesc;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    
    private Boolean isCommpleted;
    
    private Integer level; 
    
    private Integer likeCount;
    
    private String category;
}
