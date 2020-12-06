package com.maklersoft.springbe.models.enums.offer;

public enum RoomScheme {
    STANDARD("Стандартный"),
    REPLAN("Перепланированный"),
    STUDIO("Студия"),
    SEPARATE("Раздельный"),
    ADJOINING("Смежный"),
    ADJOIN_SEPARATE("Смежно-раздельный"),
    FREE("Свободный"),
    OTHER("Другое");

    private String rusName;

    RoomScheme(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
