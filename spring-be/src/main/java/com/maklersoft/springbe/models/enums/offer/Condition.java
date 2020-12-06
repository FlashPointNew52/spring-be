package com.maklersoft.springbe.models.enums.offer;

public enum Condition {
    ROUGH("После строителей"),
    SOCIAL("Социальный ремонт"),
    REPAIRED("Сделан ремонт"),
    EURO("Евроремонт"),
    DESIGNER("Дизайнерский ремонт"),
    NEED("Требуется ремонт"),
    OTHER("Другое");

    private String rusName;

    Condition(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
