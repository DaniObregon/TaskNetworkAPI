package com.danio.tasknetwork.exceptions;

import java.text.MessageFormat;

public class DeveloperNotFoundException extends RuntimeException{
    public DeveloperNotFoundException(Long id) {
        super(MessageFormat.format("No se ha encontrado el developer con id: {0}", id));
    }
}
