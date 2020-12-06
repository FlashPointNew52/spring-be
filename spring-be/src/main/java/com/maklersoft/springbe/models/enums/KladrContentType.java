package com.maklersoft.springbe.models.enums;

public enum KladrContentType {
    REGION(null),
    DISTRICT(null),
    CITY(KladrContentType.REGION),
    STREET(KladrContentType.CITY),
    BUILDING(KladrContentType.STREET);

    private KladrContentType parent;

    KladrContentType(KladrContentType parent) {
        this.parent = parent;
    }

    public KladrContentType getParent() {
        return this.parent;
    }
}
