package com.danio.tasknetwork.controller;

import com.danio.tasknetwork.persistence.entity.Task;
import com.danio.tasknetwork.persistence.entity.TaskStatus;
import com.danio.tasknetwork.service.TaskService;
import com.danio.tasknetwork.dto.TaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody TaskDTO taskDTO) {
        return this.taskService.createTask(taskDTO);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findAll(){
        List<Task> tasks = taskService.findAll();
        List<TaskDTO> taskDTOS = tasks.stream().map(TaskDTO::from).collect(Collectors.toList());
        return new ResponseEntity<>(taskDTOS, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public List<Task> findAllByTaskStatus(@PathVariable("status") TaskStatus taskStatus){
        return this.taskService.findAllByTaskStatus(taskStatus);
    }

    @PatchMapping("/mask_as_finished/{id}")
    public ResponseEntity<Void> maskAsFinished(@PathVariable("id") Long id){
        this.taskService.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable("id") Long id){
        this.taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}