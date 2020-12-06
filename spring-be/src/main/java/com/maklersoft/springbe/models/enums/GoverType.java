package com.maklersoft.springbe.models.enums;

public enum GoverType {
    MAIN("Основной филиал"),
    FILIAL("Филиал"),
    SUBSIDIARY("Дочернее предприятие"),
    FRANCHISE("Франшиза");

    private String rusName;

    GoverType(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
