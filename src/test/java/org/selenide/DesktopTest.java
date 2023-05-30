package org.selenide;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.selenide.pageObjects.IndexPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TextReportExtension.class)
class DesktopTest {
    private static IndexPage indexPage;

    @BeforeAll
    public static void init() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

        indexPage = new IndexPage();
        indexPage.get();
    }

    @Test
    public void appearsCodeInputAfterFillingTheFindOutYourPersonalOfferForm() {
        indexPage.fillTheFindOutYourPersonalOfferForm("9954442233", "1234", "123123");
        indexPage.getEnterCodeInput().should(Condition.visible);
    }

    @Test
    public void telegramLeadsToTheOfficialPage() {
        indexPage.getTelegramLink().should(Condition.visible);
        String href = indexPage.getTelegramLink().getAttribute("href");
        assertEquals("https://t.me/mts_bank_official", href);
    }

    @Test
    public void isNavigationDisplayedBothWays() {
        indexPage.getNavigationButtons().should(CollectionCondition.size(2));
    }

    @Test
    public void isExchangeRateDisplayed() {
        indexPage.getExchangeRateBlock().should(Condition.visible);
    }

    @Test
    public void isHeadingTitleExists() {
        indexPage.getHeadingTitle().should(Condition.exist);
    }
}
