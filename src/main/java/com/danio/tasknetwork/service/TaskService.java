package com.danio.tasknetwork.service;

import com.danio.tasknetwork.exceptions.TaskNotFoundException;
import com.danio.tasknetwork.exceptions.ToDoExceptions;
import com.danio.tasknetwork.mapper.TaskInDTOToTask;
import com.danio.tasknetwork.persistence.entity.Task;
import com.danio.tasknetwork.persistence.entity.TaskStatus;
import com.danio.tasknetwork.persistence.repository.TaskRepository;
import com.danio.tasknetwork.dto.TaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private TaskInDTOToTask mapper;

    public TaskService(TaskRepository taskRepository, TaskInDTOToTask mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public Task createTask(TaskDTO taskDTO) {
        Task task = mapper.TaskDtoToTask(taskDTO);
        return this.taskRepository.save(task);
    }

    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    public Task getTaskById(Long id) throws TaskNotFoundException {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public List<Task> findAllByTaskStatus(TaskStatus taskStatus) {
        return this.taskRepository.findAllByTaskStatus(taskStatus);
    }

    @Transactional
    public void updateTaskAsFinished(Long id) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if(optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.maskTaskAsFinished(id);
    }

    @Transactional
    public void deleteTaskById(Long id) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if(optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.deleteById(id);
    }
}
