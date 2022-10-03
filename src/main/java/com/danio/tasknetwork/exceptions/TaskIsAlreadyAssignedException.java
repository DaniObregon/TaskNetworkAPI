package com.danio.tasknetwork.exceptions;

import java.text.MessageFormat;

public class TaskIsAlreadyAssignedException extends Throwable {
    public TaskIsAlreadyAssignedException(Long taskId, String name) {
       super(MessageFormat.format("La tarea con id: {0} ya fue asinganda al developer: {1}", taskId, name));
    }
}
//    public PetIsAlreadyAssignedException(final Long petId, final Long ownerId){
//        super(MessageFormat.format("Pet: {0} is already assigned to Owner: {1} ", petId, ownerId));
//    }
