package com.danio.tasknetwork.controller;

import com.danio.tasknetwork.persistence.entity.Task;
import com.danio.tasknetwork.persistence.entity.TaskStatus;
import com.danio.tasknetwork.service.TaskService;
import com.danio.tasknetwork.service.dto.TaskInDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody TaskInDTO taskInDTO) {
        return this.taskService.createTask(taskInDTO);
    }

    @GetMapping
    public List<Task> findAll(){
        return this.taskService.findAll();
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
