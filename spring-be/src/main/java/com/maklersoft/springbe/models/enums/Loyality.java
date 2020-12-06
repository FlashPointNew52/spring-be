package com.maklersoft.springbe.models.enums;

public enum Loyality {
    UNDEFINED("Не определено"),
    UNACCEPTABLE("Неприемлемо"),
    NEUTRAL("Нейтрально"),
    REASONABLY("Разумно"),
    STANDARD("Стандартно");

    private String rusName;

    Loyality(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
