package com.danio.tasknetwork.service;

import com.danio.tasknetwork.exceptions.DeveloperNotFoundException;
import com.danio.tasknetwork.exceptions.TaskNotFoundException;
import com.danio.tasknetwork.persistence.entity.Developer;
import com.danio.tasknetwork.persistence.entity.Task;
import com.danio.tasknetwork.persistence.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DeveloperService {
    private final DeveloperRepository developerRepository;
    private final TaskService taskService;

    @Autowired
    public DeveloperService(DeveloperRepository developerRepository, TaskService taskService) {
        this.developerRepository = developerRepository;
        this.taskService = taskService;
    }

    public Developer addDeveloper(Developer developer) {
        return developerRepository.save(developer);
    }

    public List<Developer> getDevelopers() {
        return developerRepository.findAll();
    }

    public Developer getDeveloperById(Long id) {
        return developerRepository.findById(id).orElseThrow(() -> new DeveloperNotFoundException(id));
    }

    @Transactional
    public Developer addTaskToDeveloper(Long developerId, Long taskId) throws TaskNotFoundException {
        Developer developer = this.getDeveloperById(developerId);
        Task task = taskService.getTaskById(taskId);
        developer.addTask(task);
        return developer;
    }
}
