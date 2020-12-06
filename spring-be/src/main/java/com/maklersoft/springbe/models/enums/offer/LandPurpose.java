package com.maklersoft.springbe.models.enums.offer;

public enum LandPurpose {
    IGS("Земли сельскохозяйственного назначения"),
    GARDEN("Земли населённых пунктов"),
    PROM_LAND("Земли промышленного назначения"),
    FARM_COMN("Земли охраняемых территорий"),
    GARDEN_COMN("Земли лесного фонда"),
    WH_KAND("Земли водного фонда"),
    SH_LAND("Земли запаса");

    private String rusName;

    LandPurpose(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
