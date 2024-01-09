package com.exam.todo.springtodo.domain;
import com.exam.todo.springtodo.util.ParseDate;
import com.exam.todo.springtodo.util.PriorityType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private PriorityType priority;

    @Column(name = "created_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdDate;

    @Column(name = "deadline_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date deadlineDate;

    @Column
    private boolean complete;

    @Column
    private boolean timeOver;

    // 마감 기한 정하지 않은 경우.
    public Task(){
        createdDate = new Date();
        deadlineDate = new Date();
        complete = false;
        timeOver = false;
    }
    // 마감 기한 정해진 경우.
    public Task(Date deadlineDate){
        createdDate = new Date();
        deadlineDate = deadlineDate;
        complete = false;
        timeOver = false;
    }
}
