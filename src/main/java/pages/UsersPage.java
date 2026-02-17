package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;

public class UsersPage extends AbsBasePage{

   private SelenideElement filter = $(id("filter"));


   public UsersPage assertFilterIsVisible() {
      filter.shouldBe(visible);
      return this;
   }

}
