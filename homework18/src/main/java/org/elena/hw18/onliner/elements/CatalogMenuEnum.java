package org.elena.hw18.onliner.elements;

public enum CatalogMenuEnum {
    PRIME("Onlíner Prime"),
    ELECTRONICS("Электроника"),
    COMPUTERS("Компьютеры и сети"),
    APPLIANCE("Бытовая техника"),
    FMCG("На каждый день"),
    BUILDING("Стройка и ремонт"),
    HOUSEHOLD("Дом и сад"),
    AUTO("Авто и мото"),
    LIFESTYLE("Красота и спорт"),
    KIDS("Детям и мамам");

    private String value;

    CatalogMenuEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
