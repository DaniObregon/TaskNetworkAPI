package com.danio.tasknetwork.persistence.entity;

import com.danio.tasknetwork.dto.TaskDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdDay;
    private LocalDateTime eta;
    private boolean finished;
    private TaskStatus taskStatus;

    @ManyToOne
    private Developer developer;
}
