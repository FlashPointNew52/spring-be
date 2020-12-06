package com.maklersoft.springbe.models.enums;

public enum Position {
    TRAINEE("Стажер"),
    SPECIALIST("Специалист"),
    SALE_AGENT("Агент по продажам"),
    SALE_MANAGER("Менеджер по продажам"),
    SALE_DIRECTOR("Директор отдела продаж"),
    APPRAISER("Оценщик"),
    EVAL_MANAGER("Менеджер отдела оценки"),
    EVAL_DIRECTOR("Директор отдела оценки"),
    PR_MANAGER("PR-менеджер"),
    ADV_MANAGER("Менеджер по рекламе"),
    ADV_WEB_MANAGER("Менеджер по интернет-рекламе"),
    ADV_DIRECTOR("Директор отдела рекламы"),
    MARKETER("Маркетолог"),
    MARKET_MANAGER("Менеджер отдела маркетинга"),
    MARKET_DIRECTOR("Директор отдела маркетинга"),
    LEGAL_ASSISTANT("Помощник юриста"),
    LAWYER("Юрист"),
    LAWYER_DIRECTOR("Директор юридического отдела"),
    BROKER("Брокер"),
    MORTGAGE_MANAGER("Менеджер отдела ипотеки"),
    MORTGAGE_DIRECTOR("Директор отдела ипотеки"),
    HR_MANAGER("Менеджер по персоналу"),
    HR_DIRECTOR("Менеджер HR-отдела"),
    SYSADMIN("Системный администратор"),
    DEVELOPER("Программист"),
    IT_MANAGER("Менеджер проектов"),
    IT_DIRECTOR("Директор IT-отдела"),
    TOP_MANAGER("Топ-менеджер"),
    DIRECTOR("Директор"),
    COMMERCIAL_DIRECTOR("Коммерческий директор"),
    GENERAL_DIRECTOR("Генеральный директор");

    private String rusName;

    Position(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return this.rusName;
    }
}
