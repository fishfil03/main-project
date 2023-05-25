package org.selenide.pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.ui.LoadableComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Represents the index page of the booking.com website
 */
public class IndexPage extends LoadableComponent<IndexPage> {
    private final SelenideElement logo = $(".sc-kLgnNl.xmVNP, .sc-ha-DqFk.ikvSXm, .sc-fIYNhG.duaBbh");
    private final SelenideElement exchangeRateBlock = $(".sc-bdfBQB.dTuzuK");
    private final ElementsCollection navigationButtons = $$(".NavControl-sc-naaiqk-0.dWsJoo");
    private final SelenideElement telegramLink = $("a[aria-label=\"Telegram\"]");
    private final SelenideElement phoneNumberInput = $("input[name=\"phoneNumber\"]");
    private final SelenideElement passportInput = $("input[name=\"passportNumberWithSerial\"]");
    private final SelenideElement enterCodeInput = $(".Wrapper-sc-j59vvk-0.hwwaev");
    private final SelenideElement headingTitle = $("h1");


    @Override
    protected void load() {
        //driver.get("https://www.mtsbank.ru/");
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

    public void fillTheFindOutYourPersonalOfferForm(String phoneWithoutCountryCode, String passportSeries, String passportId){
        phoneNumberInput.should(interactable);
        passportInput.should(interactable);

        phoneNumberInput.sendKeys(phoneWithoutCountryCode);
        passportInput.sendKeys(passportSeries+passportId);

        passportInput.submit();


    }

    public SelenideElement getExchangeRateBlock() {
        return exchangeRateBlock;
    }

    public ElementsCollection getNavigationButtons() {
        return navigationButtons;
    }

    public SelenideElement getTelegramLink() {
        return telegramLink;
    }

    public SelenideElement getEnterCodeInput() {
        return enterCodeInput;
    }

    public SelenideElement getHeadingTitle() {
        return headingTitle;
    }
}
