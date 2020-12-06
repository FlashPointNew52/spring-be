package com.maklersoft.springbe.models.enums;

public enum Department {
    MANAGEMENT("Управление"),
    SALE("Отдел продаж"),
    EVALUATION("Отдел оценки"),
    ADVERTISING("Отдел рекламы"),
    MARKETING("Отдел маркетинга"),
    LEGAL("Юридический отдел"),
    MORTGAGE("Отдел ипотеки"),
    QUALITY("Отдел контроля качества"),
    IT("Отдел IT"),
    HR("HR-отдел");

    private String rusName;

    Department(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
