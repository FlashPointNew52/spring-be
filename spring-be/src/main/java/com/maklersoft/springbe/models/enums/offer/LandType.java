package com.maklersoft.springbe.models.enums.offer;

public enum LandType {
    GA("Сотки"),
    AR("Гектары");

    private String rusName;

    LandType(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
