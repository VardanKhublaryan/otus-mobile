package pages.gifts;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import pages.AbsBasePage;

@Singleton
public class EditGiftPage extends AbsBasePage {

   private final SelenideElement nameInputField = $(id("name_input"));
   private final SelenideElement descriptionInputField = $(id("description_input"));
   private final SelenideElement priceInputField = $(id("price_input"));
   private final SelenideElement saveButton = $(id("save_button"));

   public MyGiftsPage createGift(String name, String description, String price) {
      sendKey(nameInputField,name);
      sendKey(descriptionInputField,description);
      sendKey(priceInputField,price);
      click(saveButton);
      return new MyGiftsPage();
   }


}
