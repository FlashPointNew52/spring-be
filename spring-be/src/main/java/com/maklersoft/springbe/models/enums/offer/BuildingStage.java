package com.maklersoft.springbe.models.enums.offer;

public enum BuildingStage {
    PROJECT("Проект"),
    BUILDING("Строящийся"),
    READY("Сдан");

    private String rusName;

    BuildingStage(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
