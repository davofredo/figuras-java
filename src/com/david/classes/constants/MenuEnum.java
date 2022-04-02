package com.david.classes.constants;

public enum MenuEnum {
    REGISTER(Message.REGISTER_MESSAGE,1),
    OPEN(Message.OPEN_MESSAGE, 2),
    EXIT(Message.EXIT_MESSAGE, 3);

    private final String message;
    private final int option;

    MenuEnum(String message, int option) {
        this.message = message;
        this.option = option;
    }

    public String getMessage() {
        return message;
    }

    public int getOption() {
        return option;
    }
}