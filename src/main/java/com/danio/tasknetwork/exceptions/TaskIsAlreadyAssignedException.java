package com.danio.tasknetwork.exceptions;

import java.text.MessageFormat;

public class TaskIsAlreadyAssignedException extends RuntimeException {
    public TaskIsAlreadyAssignedException(Long taskId, String name) {
       super(MessageFormat.format("La tarea con id: {0} ya fue asinganda al developer: {1}", taskId, name));
    }
}