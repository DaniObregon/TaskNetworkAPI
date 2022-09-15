package com.danio.tasknetwork.mapper;

public interface IMapper <In, Out>{
    public Out map(In in);
}
