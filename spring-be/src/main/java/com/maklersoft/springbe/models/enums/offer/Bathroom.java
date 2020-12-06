package com.maklersoft.springbe.models.enums.offer;

public enum Bathroom {
    NO("Нет"),
    SPLITTED("Раздельный"),
    COMBINED("Совмещенный"),
    OTHER("Другое");

    private String rusName;

    Bathroom(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
