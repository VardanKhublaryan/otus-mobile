package pages;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import components.NavigationBar;

public class HomePage extends AbsBasePage{

   private final NavigationBar navigationBar = new NavigationBar($(id("bottom_navigation")));

   public UsersPage clickUsersTab(){
      navigationBar.clickUsersTab();
      return new UsersPage();
   }

}
