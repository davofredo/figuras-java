package com.david.classes.excepciones;


import com.david.classes.constants.ProcessesEnum;

public class InterruptedProcessException extends Exception {

    private ProcessesEnum process;

    public InterruptedProcessException(ProcessesEnum process) {
        this.process = process;
    }

    public InterruptedProcessException(String message, ProcessesEnum process) {
        super(message);
        this.process = process;
    }

    public ProcessesEnum getProcess() {
        return process;
    }
}