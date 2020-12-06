package com.maklersoft.springbe.models.enums;

public enum Priority {
    LOW("Низкий"),
    MIDDLE("Средний"),
    HIGH("Высокий");

    private String rusName;

    Priority(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
