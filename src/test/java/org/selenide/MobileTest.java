package org.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.selenide.pageObjects.MobileIndexPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TextReportExtension.class)
class MobileTest {
    private static MobileIndexPage indexPage;

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

        indexPage = new MobileIndexPage();
        indexPage.get();
    }

    @Test
    public void isSignInExists() {
        indexPage.getSignInLink().should(Condition.exist, Duration.ofSeconds(10));
    }

    @Test
    public void signInLeadsToTheRightPage() {
        indexPage.getSignInLink().should(Condition.visible, Duration.ofSeconds(10));
        String href = indexPage.getSignInLink().getAttribute("href");

        assertEquals("https://payment.mts.ru/Auth/SignIn/", href);
    }

    @Test
    public void isOrderPayTagExists() {
        indexPage.getBlocksHeaders().find(Condition.text("Заказать Pay Tag"));
    }

    @Test
    public void isMobileMenuExists() {
        indexPage.getMobileMenu().should(Condition.exist);
    }

    @Test
    public void isInstallAppExists() {
        indexPage.getInstallMobileApp().should(Condition.exist);
    }


}
