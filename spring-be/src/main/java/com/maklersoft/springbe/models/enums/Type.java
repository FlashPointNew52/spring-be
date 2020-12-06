package com.maklersoft.springbe.models.enums;

public enum Type {
    UNKNOWN("Не определено"),
    CLIENT("Клиент"),
    PARTNER("Партнер"),
    COMPETITOR("Конкурент");

    private String rusName;

    Type(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
