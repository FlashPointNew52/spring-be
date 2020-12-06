package com.maklersoft.springbe.models.enums.offer;

public enum OfferTypeCode {
    RENT("Аренда"),
    SALE("Продажа"),
    ALTERNATIVE("Альтернатива");

    private String rusName;

    OfferTypeCode(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
