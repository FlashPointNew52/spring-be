package com.maklersoft.springbe.models.enums;

public enum Repeate {
    NEVER("Никогда"),
    EVERYDAY("Каждый день"),
    EVERYWEEK("Каждую неделю");

    private String rusName;

    Repeate(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
