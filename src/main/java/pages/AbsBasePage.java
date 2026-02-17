package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;

public class AbsBasePage {

   private final SelenideElement usersTab = $(id("navigation_bar_item_small_label_view"));

   protected void sendKey(SelenideElement element, String text) {
      element.shouldBe(visible).sendKeys(text);
   }

   protected void click(SelenideElement element) {
      element.shouldBe(visible).click();
   }

   public void clickUsersTab(){
      click(usersTab);
   }

}
