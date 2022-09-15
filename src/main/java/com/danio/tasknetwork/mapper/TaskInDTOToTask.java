package com.danio.tasknetwork.mapper;

import com.danio.tasknetwork.persistence.entity.Task;
import com.danio.tasknetwork.persistence.entity.TaskStatus;
import com.danio.tasknetwork.service.dto.TaskInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskInDTOToTask implements IMapper<TaskInDTO, Task>{
    @Override
    public Task map(TaskInDTO taskInDTO) {
        Task task = new Task();
        task.setTitle(taskInDTO.getTitle());
        task.setDescription(taskInDTO.getDescription());
        task.setEta(taskInDTO.getEta());
        task.setCreatedDay(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ON_TIME);
        return task;
    }
}
