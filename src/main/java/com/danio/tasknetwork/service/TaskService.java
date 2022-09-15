package com.danio.tasknetwork.service;

import com.danio.tasknetwork.mapper.TaskInDTOToTask;
import com.danio.tasknetwork.persistence.entity.Task;
import com.danio.tasknetwork.persistence.repository.TaskRepository;
import com.danio.tasknetwork.service.dto.TaskInDTO;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    //TODO: probar campos con final.. en el video usa final, tambien usa constructon en vez de @Autowired
    //@Autowired
    private TaskRepository taskRepository;
    private TaskInDTOToTask mapper;

    public TaskService(TaskRepository taskRepository, TaskInDTOToTask mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDTO taskInDTO){
        Task task = mapper.map(taskInDTO);
        return this.taskRepository.save(task);
    }
}
