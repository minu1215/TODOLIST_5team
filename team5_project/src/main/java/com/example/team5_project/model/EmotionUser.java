package com.example.team5_project.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EMOTION_USER")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmotionUser {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "emotion_user_id")
    private Long id;
	
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "list_id")
    private ToDoList list;
    
    @ManyToOne
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;
 
}
