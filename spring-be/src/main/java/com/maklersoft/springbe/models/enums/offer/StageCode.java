package com.maklersoft.springbe.models.enums.offer;

public enum StageCode {
    RAW("Не активно"),
    ACTIVE("Активно"),
    LISTING("Листинг"),
    MULTILISTING("Мультилистинг"),
    DEAL("Сделка"),
    SUSPENDED("Приостановлено"),
    ARCHIVE("Архив");

    private String rusName;

    StageCode(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
