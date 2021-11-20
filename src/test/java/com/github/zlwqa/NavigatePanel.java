package com.github.zlwqa;

public enum NavigatePanel {
    ORDER_STATUS("Статус заказа"),
    SIGN_IN("Войти"),
    COMPARISON("Сравнение"),
    FAVORITES("Избранное"),
    CART("Корзина");

    private final String navigatePanel;

    NavigatePanel(String navigatePanel) {
        this.navigatePanel = navigatePanel;
    }

    public String getProfileMenu() {
        return navigatePanel;
    }

    @Override
    public String toString() {
        return navigatePanel;
    }
}
