package com.maklersoft.springbe.models.enums.offer;

public enum CategoryCode {
    RESIDENTIAL("Жилая недвижимость"),
    COMMERCIAL("Коммерческая недвижимость"),
    LAND("Земельный участок");

    private String rusName;

    CategoryCode(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
