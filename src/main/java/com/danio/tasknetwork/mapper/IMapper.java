package com.danio.tasknetwork.mapper;

public interface IMapper <In, Out>{
    public Out TaskDtoToTask(In in);
}
