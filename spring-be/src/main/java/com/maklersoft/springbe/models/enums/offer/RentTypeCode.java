package com.maklersoft.springbe.models.enums.offer;

public enum RentTypeCode {
    SHORT("Краткосрочная"),
    LONG("Долгосрочная");

    private String rusName;

    RentTypeCode(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
