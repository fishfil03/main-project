package org.selenide.pageObjects;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Represents the index page of the booking.com website
 */
public class MobileIndexPage extends LoadableComponent<MobileIndexPage> {

    private final SelenideElement logo = $(".sc-kLgnNl.xmVNP, .sc-ha-DqFk.ikvSXm, .sc-fIYNhG.duaBbh");
    private final SelenideElement mobileMenu = $(".sc-btdhtl.cduirw, .sc-gqdwHF.ikGRIR");
    private final SelenideElement installMobileApp = $(".sc-hxqEdz.iEOyEl, .sc-btdhtl.iDxvix");
    private final ElementsCollection blocksHeaders = $$(".sc-lgqlnP.hZKtWy h2, .sc-jfJyPD.gDOKcd h2");
    private final SelenideElement signInLink = $(".LinkWrapper-sc-a7l7fm-0.ktbVkT.sc-cSaENr.fevDBw");

    public MobileIndexPage(){
        Configuration.headless = true;

        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Nexus 5");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        chromeOptions.addArguments("--headless");

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

        WebDriver webDriver = new ChromeDriver(chromeOptions);
        setWebDriver(webDriver);
    }

    @Override
    protected void load() {
        open("https://www.mtsbank.ru/");
    }

    @Override
    protected void isLoaded() throws Error {
        try{
            logo.should(visible);
        } catch (Exception e){
            fail();
        }
    }

    public SelenideElement getMobileMenu() {
        return mobileMenu;
    }

    public SelenideElement getInstallMobileApp() {
        return installMobileApp;
    }

    public ElementsCollection getBlocksHeaders() {
        return blocksHeaders;
    }

    public SelenideElement getSignInLink() {
        return signInLink;
    }
}
