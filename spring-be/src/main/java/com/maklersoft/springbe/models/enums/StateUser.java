package com.maklersoft.springbe.models.enums;

public enum StateUser {
    ACTIVE("Активно"),
    VACATION("Отпуск"),
    SICK_LEAVE("Больничный"),
    OTHER("Другое"),
    ARCHIVE("Архив");

    private String rusName;

    StateUser(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
