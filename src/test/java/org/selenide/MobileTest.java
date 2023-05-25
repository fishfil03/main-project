package org.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.selenide.pageObjects.MobileIndexPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(TextReportExtension.class)
class MobileTest {
    private static MobileIndexPage indexPage;

    @BeforeAll
    public static void setUp() {
        Configuration.headless = true;
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Nexus 5");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        WebDriver webDriver = new ChromeDriver(chromeOptions);
        setWebDriver(webDriver);

        indexPage = new MobileIndexPage(webDriver);
        indexPage.get();
    }

    @Test
    public void isOrderPayTagExists() {
        try {
            indexPage.getBlocksHeaders().find(Condition.text("Заказать Pay Tag"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void isMobileMenuExists() {
        try {
            indexPage.getMobileMenu().should(Condition.exist);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void isInstallAppExists() {
        try {
            indexPage.getInstallMobileApp().should(Condition.exist);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void isSignInExists() {
        try {
            indexPage.getSignInLink().should(Condition.exist);
        } catch (Exception e) {
            fail(e);
        }
    }

    @ParameterizedTest(name = "{index} - {0} is a link")
    @ValueSource(strings = {"https://payment.mts.ru/Auth/SignIn/", "https://t.me/mts_bank"})
    public void signInLeadsToTheRightPage(String link) {
        try {
            indexPage.getSignInLink().should(Condition.visible);
            String href = indexPage.getSignInLink().getAttribute("href");

            assertEquals(link, href);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Ссылка не совпадает");
        }
    }
}
