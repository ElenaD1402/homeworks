package org.elena.hw18.onliner.elements;

public enum TopMenuEnum {
    CATALOG("Каталог"),
    NEWS("Новости"),
    AUTOSALES("Автобарахолка"),
    FLATSALES("Дома и квартиры"),
    SERVICES("Услуги"),
    FLEAMARKET("Барахолка"),
    FORUM("Форум");

    private String value;

    TopMenuEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
