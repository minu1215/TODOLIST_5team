package com.example.team5_project.model;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REPLY")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Reply {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "reply_id")
    private Long id;
	
    @NotBlank
    @Column(name = "content")
	private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate")
    private Date createDate;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "list_id")
    private ToDoList list;
  
}
