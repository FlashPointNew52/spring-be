package com.maklersoft.springbe.models.enums.offer;

public enum Source {
    INTERNET("Интернет площадки"),
    PRINT("Печатные издания"),
    SOCIAL("Социальные сети"),
    MESSENGERS("Мессенджеры"),
    EMAIL("E-mail-рассылка"),
    RECOMMENDATIONS("Рекомендации");

    private String rusName;

    Source(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
