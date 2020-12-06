package com.maklersoft.springbe.models.enums.offer;

public enum BuildingClass {
    ELITE("Элит класс"),
    BUSINESS("Бизнес класс"),
    ECONOMY("Эконом класс"),
    IMPROVED("Улучшенная"),
    BREZHNEV("Брежневка"),
    KHRUSHCHEV("Хрущевка"),
    STALIN("Сталинка"),
    OLD_FUND("Старый фонд"),
    SMALL_APARTM("Малосемейка"),
    DORMITORY("Общежитие"),
    GOSTINKA("Гостинка"),
    INDIVIDUAL("Индивидуальная"),
    SINGLE_HOUSE("Дом"),
    COTTAGE("Коттедж"),
    DACHA("Дача"),
    TOWNHOUSE("Таунхаус"),
    DUPLEX("Дуплекс"),
    NEW("Новая планировка"),
    A("А"),
    AP("А+"),
    B("Б"),
    BP("Б+"),
    C("С"),
    CP("С+");

    private String rusName;

    BuildingClass(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
