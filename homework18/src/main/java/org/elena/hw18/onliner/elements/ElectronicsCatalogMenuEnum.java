package org.elena.hw18.onliner.elements;

public enum ElectronicsCatalogMenuEnum {
    MOBILE("Мобильные телефоны и аксессуары"),
    TV("Телевидение и видео"),
    TABLETS("Планшеты, электронные книги"),
    AUDIO("Наушники и аудиотехника"),
    HIFIAUDIO("Hi-Fi аудио"),
    PHOTO("Фото- и видеотехника"),
    GAMES("Видеоигры"),
    SMARTHOME("Умный дом и видеонаблюдение"),
    TRANSPORT("Электрический транспорт"),
    COMMUNICATION("Телефония и связь"),
    MUSIC("Музыкальное оборудование");

    private String value;

    ElectronicsCatalogMenuEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
