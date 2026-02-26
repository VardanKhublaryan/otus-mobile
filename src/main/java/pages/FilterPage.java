package pages;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;

@Singleton
public class FilterPage extends AbsBasePage {

   private SelenideElement userNameInput = $(id("username_input"));
   private SelenideElement applyButton = $(id("apply_button"));


   public UsersPage searchUser(String username){
      sendKey(userNameInput,username);
      click(applyButton);
      return new UsersPage();
   }
}
