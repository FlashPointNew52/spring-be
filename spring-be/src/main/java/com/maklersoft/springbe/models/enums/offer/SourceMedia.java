package com.maklersoft.springbe.models.enums.offer;

public enum SourceMedia {
    PRESENT_SITE("Презент сайт"),
    PRESENT_ARCHIVE("Презент архив"),
    AVITO("Авито"),
    FARPOST("Фарпост"),
    CIAN("Циан"),
    IRR("Из рук в руки"),
    MKV("Мир квартир");

    private String rusName;

    SourceMedia(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
