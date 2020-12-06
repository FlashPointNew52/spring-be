package com.maklersoft.springbe.models.enums;

public enum Stage {
    NEW_CONTACT("Новый клиент"),
    QUALIFICATION("Квалификация контакта"),
    POTENTIAL_CLIENT("Потенциальный клиент"),
    NEW_CLIENT("Новый клиент"),
    CONST_CLIENT("Постоянный клиент"),
    LOSE_CLIENT("Потерянный клиент"),
    REFURBISH_CLIENT("Востановленный клиент"),
    SUSPECTED("Приостановлено"),
    ARCHIVE("Архив");

    private String rusName;

    Stage(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
