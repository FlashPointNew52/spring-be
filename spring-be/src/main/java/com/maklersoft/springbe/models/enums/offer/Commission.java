package com.maklersoft.springbe.models.enums.offer;

public enum Commission {
    PERCENT("%"),
    FIX("Р");

    private String rusName;

    Commission(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
