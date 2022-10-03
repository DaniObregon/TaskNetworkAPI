package com.danio.tasknetwork.dto;

import com.danio.tasknetwork.persistence.entity.Task;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime eta;

    public static TaskDTO from(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setEta(task.getEta());
        return taskDTO;
    }
}