package com.maklersoft.springbe.models.enums.offer;

public enum TypeCode {
    PLACE("Койко-место"),
    SHARE("Доля"),
    ROOM("Комната"),
    DORMITORY("Гостинка"),
    SMALL_FAMILY("Малосемейка"),
    APARTMENT("Квартира"),
    HOUSE("Дом"),
    COTTAGE("Коттедж"),
    DACHA("Дача"),
    TOWNHOUSE("Таунхаус"),
    DUPLEX("Дуплекс"),
    BARRACK("Барак")
//    GARDEN_LAND("Садовый земельный участок"),
//    DACHA_LAND("Дачный земельный участок"),
//    CULTIVATE_LAND("Огородный земельный участок"),
//    HOTEL("Отель"),
//    RESTAURANT("Ресторан"),
//    СAFE("Кафе"),
//    SPORT_BUILDING("Спортивное сооружение"),
//    SHOP("Магазин"),
//    SHOPS_CENTER("Торговый центр"),
//    SHOP_ENTERTAINMENT("ТРК"),
//    CABINET("Кабинет"),
//    OFFICE_SPACE("Офисное помещение"),
//    OFFICE_BUILDING("Офисное здание"),
//    BUSINESS_CENTER("Бизнес центр"),
//    MANUFACTURE_BUILDING("Производственное здание"),
//    WAREHOUSE_SPACE("Складское помещение"),
//    INDUSTRIAL_ENTERPRICE("Промышленное предприятие"),
//    OTHER("Другое")
    ;

    private String rusName;

    TypeCode(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
