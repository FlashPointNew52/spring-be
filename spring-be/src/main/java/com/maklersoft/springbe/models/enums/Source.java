package com.maklersoft.springbe.models.enums;

public enum Source {
    INTERNET("Не определено"),
    PRINT("Неприемлемо"),
    SOCIAL("Социальные сети"),
    MESSENGERS("Мессенджеры"),
    EMAIL("E-Mail-рассылка"),
    RECOMMENDATIONS("'Рекомендации'"),
    OTHER("'Другое'");

    private String rusName;

    Source(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
