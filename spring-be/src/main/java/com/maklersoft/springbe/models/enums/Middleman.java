package com.maklersoft.springbe.models.enums;

public enum Middleman {
    MIDDLEMAN("Посредник"),
    OWNER("Принципал");

    private String rusName;

    Middleman(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
