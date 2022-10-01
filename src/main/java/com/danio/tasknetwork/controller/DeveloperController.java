package com.danio.tasknetwork.controller;

import com.danio.tasknetwork.exceptions.TaskNotFoundException;
import com.danio.tasknetwork.persistence.entity.Developer;
import com.danio.tasknetwork.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    private final DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @PostMapping
    public Developer createDeveloper(Developer developer){
        return this.developerService.addDeveloper(developer);
    }

    @GetMapping
    public List<Developer> getDevelopers(){
        return this.developerService.getDevelopers();
    }

    @PostMapping("{developerId}/tasks/{taskId}/add")
    public Developer addTaskToDeveloper(@PathVariable Long developerId, @PathVariable Long taskId) throws TaskNotFoundException {
        Developer developer = this.developerService.addTaskToDeveloper(developerId, taskId);
        return developer;
    }
}
