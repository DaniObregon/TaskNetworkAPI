package com.danio.tasknetwork.controller;

import com.danio.tasknetwork.exceptions.TaskIsAlreadyAssignedException;
import com.danio.tasknetwork.exceptions.TaskNotFoundException;
import com.danio.tasknetwork.persistence.entity.Developer;
import com.danio.tasknetwork.service.DeveloperService;
import com.danio.tasknetwork.dto.DeveloperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    private final DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @PostMapping
    public Developer createDeveloper(@RequestBody DeveloperDTO developerDTO){
        return this.developerService.addDeveloper(developerDTO);
    }

    @GetMapping
    public ResponseEntity<List<DeveloperDTO>> getDevelopers(){
        List<Developer> developers = developerService.getDevelopers();
        List<DeveloperDTO> developerDTOS = developers.stream().map(DeveloperDTO::from).collect(Collectors.toList());
        return new ResponseEntity<>(developerDTOS, HttpStatus.OK);
    }

    @PostMapping("{developerId}/tasks/{taskId}/add")
    public ResponseEntity<DeveloperDTO> addTaskToDeveloper(@PathVariable final Long developerId,
                                                           @PathVariable final Long taskId)
            throws TaskNotFoundException, TaskIsAlreadyAssignedException {
        Developer developer = developerService.addTaskToDeveloper(developerId, taskId);
        return new ResponseEntity<>(DeveloperDTO.from(developer), HttpStatus.OK);
    }
}
