package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import components.TopAppBar;

@Singleton
public class UsersPage extends AbsBasePage{

   private final TopAppBar topAppBar = new TopAppBar($(id("top_app_bar_layout")));

   public UsersPage assertFilterIsVisible() {
      topAppBar.assertFilterIsVisible();
      return this;
   }

}
