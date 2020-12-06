package com.maklersoft.springbe.models.enums;

public enum Route {
    NO("Не указано"),
    CAR("На автомобиле"),
    WALK("Пешком"),
    BUS("Общественный транспорт");

    private String rusName;

    Route(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
