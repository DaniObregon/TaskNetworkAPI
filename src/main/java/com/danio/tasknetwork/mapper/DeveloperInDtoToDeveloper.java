package com.danio.tasknetwork.mapper;

import com.danio.tasknetwork.persistence.entity.Developer;
import com.danio.tasknetwork.dto.DeveloperDTO;
import org.springframework.stereotype.Component;

@Component
public class DeveloperInDtoToDeveloper implements IMapper<DeveloperDTO, Developer>{

    @Override
    public Developer TaskDtoToTask(DeveloperDTO developerDTO) {
        Developer developer = new Developer();
        developer.setName(developerDTO.getName());
        return developer;
    }
}
