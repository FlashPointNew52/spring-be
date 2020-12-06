package com.maklersoft.springbe.models.enums.offer;

public enum BuildingType {
    MULTISECTION_HOUSE("Многосекционный"),
    SINGLESECTION_HOUSE("Односекционный"),
    CORRIDOR_HOUSE("Коридорный"),
    GALARY_HOUSE("Галерейный"),
    LOWRISE_HOUSE("Малоэтажное жильё"),
    AGRICULTURAL_LAND("Земли сельхоз назначения"),
    SETTLEMENTS_LAND("Земли населенных пунктов"),
    PURPOSE_PLACE("Свободного назначения"),
    MARKET_PLACE("Розничная торговля"),
    OFFICE("Офисная"),
    PRODUCTION_PLACE("Индустриальная");

    private String rusName;

    BuildingType(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
