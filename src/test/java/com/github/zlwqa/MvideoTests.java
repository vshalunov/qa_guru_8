package com.github.zlwqa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.github.zlwqa.TestData.MVIDEO_URL;

public class MvideoTests extends TestBase {

    @ValueSource(strings = {"беспроводной контроллер playstation 5 dualsense rainbo ice banana", "воздухоувлажнитель vitek vt-2338"})
    @Tag("Critical")
    @Tag("High")
    @Tag("Web")
    @DisplayName("Результаты поиска")
    @ParameterizedTest(name = "Отображение товара {0} в результатах поиска")
    void displayProductAfterSearchTests(String searchQuery) {
        open(MVIDEO_URL);
        $(".input__field").setValue(searchQuery).pressEnter();
        $$("a.product-title__text").shouldHave(texts(searchQuery));
    }

    @CsvSource(value = {
            "беспроводной контроллер playstation 5 dualsense rainbo ice banana | 4007 5615",
            "воздухоувлажнитель vitek vt-2338 | 2006 1485"
    }, delimiter = '|')
    @Tag("Minor")
    @Tag("Medium")
    @Tag("Web")
    @DisplayName("Карточка товара")
    @ParameterizedTest(name = "Отображение кода {1} у товара {0}")
    void displayProductCodeInProductCardTests(String searchQuery, String productCode) {
        open(MVIDEO_URL);
        $(".input__field").setValue(searchQuery).pressEnter();
        $$("a.product-title__text").find(text(searchQuery)).click();
        $(".product-code-container").shouldHave(text(productCode));
    }

    @MethodSource("com.github.zlwqa.ReviewsByCategory#displayValuesOfReviewsByCategoryOnMainCategoryPageTests")
    @Tag("Minor")
    @Tag("Medium")
    @Tag("Web")
    @DisplayName("Обзор по категориям на странице основной категории")
    @ParameterizedTest(name = "Отображение категорий {1} в обзоре по категориям на странице {0}")
    void displayValuesOfReviewsByCategoryOnMainCategoryPageTests(String itemSideMenuInCatalog, List<String> valuesOfCategory) {
        open(MVIDEO_URL);
        $("[class='button button--with-icon ng-star-inserted']").click();
        $$(".left-menu__item-text").find(text(itemSideMenuInCatalog)).click();
        $$("a.c-list-of-links__item").shouldHave(texts(valuesOfCategory));
    }

    @EnumSource(NavigatePanel.class)
    @Tag("Blocker")
    @Tag("High")
    @Tag("Web")
    @DisplayName("Пункты меню неавторизованного пользователя")
    @ParameterizedTest(name = "Отображение пукнта меню {0}")
    void displayAnonymousNavigatePanelItemsTests(NavigatePanel navigatePanel) {
        open(MVIDEO_URL);
        $(".nav-tabs").shouldHave(text(navigatePanel.getProfileMenu()));
    }
}    
