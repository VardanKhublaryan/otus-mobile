package pages;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;

@Singleton
public class LoginPage extends AbsBasePage {

   private final SelenideElement usernameInputField = $(
       id("username_text_input"));
   private final SelenideElement passwordInputField = $(
        id("password_text_input"));
   private final SelenideElement loginButton = $(
       id("log_in_button"));

   public void login(String username, String password) {
      sendKey(usernameInputField,username);
      sendKey(passwordInputField,password);
      click(loginButton);
   }

}
