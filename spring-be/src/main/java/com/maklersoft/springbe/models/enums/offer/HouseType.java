package com.maklersoft.springbe.models.enums.offer;

public enum HouseType {
    BRICK("Кирпичный"),
    PANEL("Панель"),
    MONOLITHIC("Монолит"),
    MONOLITHIC_BRICK("Кирпично-монолитный"),
    WOOD("Деревянный"),
    CINDER_BLOCK("Шлакоблочный"),
    OTHER("Другое");

    private String rusName;

    HouseType(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
