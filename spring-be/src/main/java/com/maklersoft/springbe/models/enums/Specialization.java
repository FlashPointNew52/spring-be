package com.maklersoft.springbe.models.enums;

public enum Specialization {
    ALL("Всё"),
    RENT("Аренда"),
    SALE("Продажа"),
    TRAINEE("Жилая недвижимость"),
    SPECIALIST("Коммерческая недвижимость"),
    LAND("Земля"),
    CREATION("Творчество"),
    PRODUCTION("Прозводство"),
    MEDIA_PLANNING("Медиапланирование"),
    RESEARCH("Исследования"),
    ANALYSIS("Анализ и планирование"),
    PROMOTION("Продвижение"),
    MAIN("Правовое сопровождение основной деятельности"),
    CORPORATE("Корпоративно-правовая"),
    CLAIM("Судебно-претензионная"),
    ECONOMIC("Хозяйственная деятельность"),
    RECRUITING("Служба рекрутинга"),
    STAFF("Служба кадров"),
    GROWTH("Служба развития"),
    EVALUATION("Служба оценки");

    private String rusName;

    Specialization(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
