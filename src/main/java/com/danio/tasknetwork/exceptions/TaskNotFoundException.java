package com.danio.tasknetwork.exceptions;

import java.text.MessageFormat;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super(MessageFormat.format("La tarea con id: {0} no fue encontrada", id));
    }
}
