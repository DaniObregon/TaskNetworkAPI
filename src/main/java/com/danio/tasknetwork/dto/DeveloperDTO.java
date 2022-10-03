package com.danio.tasknetwork.dto;

import com.danio.tasknetwork.persistence.entity.Developer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DeveloperDTO {
    private Long id;
    private String name;
    private List<TaskDTO> taskDTOS = new ArrayList<>();

    public static DeveloperDTO from(Developer developer) {
        DeveloperDTO developerDTO = new DeveloperDTO();
        developerDTO.setId(developer.getId());
        developerDTO.setName(developer.getName());
        developerDTO.setTaskDTOS(developer.getTasks().stream().map(TaskDTO::from).collect(Collectors.toList()));
        return developerDTO;
    }
}
