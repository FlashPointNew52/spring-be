package com.maklersoft.springbe.models.enums;

public enum Action {
    CALL("Звонок"),
    MEET("Встреча"),
    MESSAGE("Сообщение"),
    OTHER("Другое");

    private String rusName;

    Action(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
