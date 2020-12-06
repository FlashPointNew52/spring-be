package com.maklersoft.springbe.models.enums.offer;

public enum PaymentType {
    ALL("Все"),
    CASHLESS("Безналичный"),
    CASH("Наличный");

    private String rusName;

    PaymentType(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
