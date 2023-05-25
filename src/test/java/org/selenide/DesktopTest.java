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

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TextReportExtension.class)
class DesktopTest {
  private static IndexPage indexPage;

  @BeforeAll
  public static void init() {

    Configuration.headless = true;
    indexPage = new IndexPage();
    indexPage.get();

    SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
  }

  @Test
  public void appearsCodeInputAfterFillingTheFindOutYourPersonalOfferForm(){
    indexPage.fillTheFindOutYourPersonalOfferForm("9954442233", "1234", "123123");

    try {
      indexPage.getEnterCodeInput().should(Condition.visible);
    }
    catch (Exception e){
      System.out.println(e.getMessage());
      fail();
    }
  }

  @ParameterizedTest(name = "{index} - {0} is a link")
  @ValueSource(strings = {"https://t.me/mts_bank_official", "https://t.me/mts_bank"})
  public void telegramLeadsToTheOfficialPage(String link) {
    try {
      indexPage.getTelegramLink().should(Condition.visible);
      String href = indexPage.getTelegramLink().getAttribute("href");

      assertEquals(link, href);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      fail("Ссылка не совпадает");
    }
  }

  @Test
  public void isNavigationDisplayedBothWays() {
    try {
      indexPage.getNavigationButtons().should(CollectionCondition.size(2));
    } catch (Exception e) {
      System.out.println(e.getMessage());
      fail();
    }
  }

  @Test
  public void isExchangeRateDisplayed() {
    try {
      indexPage.getExchangeRateBlock().should(Condition.visible);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void isHeadingTitleExists(){
    try{
      indexPage.getHeadingTitle().should(Condition.exist);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}
