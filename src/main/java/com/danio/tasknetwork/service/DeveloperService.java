package com.danio.tasknetwork.service;

import com.danio.tasknetwork.exceptions.DeveloperNotFoundException;
import com.danio.tasknetwork.exceptions.TaskIsAlreadyAssignedException;
import com.danio.tasknetwork.exceptions.TaskNotFoundException;
import com.danio.tasknetwork.mapper.DeveloperInDtoToDeveloper;
import com.danio.tasknetwork.persistence.entity.Developer;
import com.danio.tasknetwork.persistence.entity.Task;
import com.danio.tasknetwork.persistence.repository.DeveloperRepository;
import com.danio.tasknetwork.dto.DeveloperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DeveloperService {
    private final DeveloperRepository developerRepository;
    private final TaskService taskService;
    private final DeveloperInDtoToDeveloper mapper;

    @Autowired
    public DeveloperService(DeveloperRepository developerRepository, TaskService taskService, DeveloperInDtoToDeveloper mapper) {
        this.developerRepository = developerRepository;
        this.taskService = taskService;
        this.mapper = mapper;
    }

    public Developer addDeveloper(DeveloperDTO developerDTO) {
        Developer developer = mapper.TaskDtoToTask(developerDTO);
        return developerRepository.save(developer);
    }

    public List<Developer> getDevelopers() {
        return StreamSupport
                .stream(developerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Developer getDeveloperById(Long id) {
        return developerRepository.findById(id).orElseThrow(() -> new DeveloperNotFoundException(id));
    }

    @Transactional
    public Developer addTaskToDeveloper(Long developerId, Long taskId) throws TaskNotFoundException, TaskIsAlreadyAssignedException {
        Developer developer = this.getDeveloperById(developerId);
        Task task = taskService.getTaskById(taskId);
        if (Objects.nonNull(task.getDeveloper())) {
            throw new TaskIsAlreadyAssignedException(taskId, task.getDeveloper().getName());
        }
        developer.addTask(task);
        return developer;
    }
}
