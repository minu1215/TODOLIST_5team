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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PROJECT")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Project {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "project_id")
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "projectName")
    private String projectName;

//    @NotBlank
//    @Size(max = 100)
//    @Column(name = "email")
//    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PROJECT_USER",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

}
