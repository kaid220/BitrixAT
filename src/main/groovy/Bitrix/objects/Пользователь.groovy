package Bitrix.objects

class Пользователь {
    private String логин
    private String пароль

    String getЛогин() {
        return логин
    }

    String getПароль() {
        return пароль
    }

    Пользователь(String логин, String пароль) {
        this.логин = логин
        this.пароль = пароль
    }
}
