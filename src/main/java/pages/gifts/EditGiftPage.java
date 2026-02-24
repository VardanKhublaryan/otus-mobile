package pages.gifts;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import pages.AbsBasePage;

public class EditGiftPage extends AbsBasePage {

   private final SelenideElement nameInputField = $(id("name_input"));
   private final SelenideElement descriptionInputField = $(id("description_input"));
   private final SelenideElement priceInputField = $(id("price_input"));
   private final SelenideElement saveButton = $(id("save_button"));

   public EditGiftPage createGift(String name, String description, String price) {
      sendKey(nameInputField,name);
      sendKey(descriptionInputField,description);
      sendKey(priceInputField,price);
      click(saveButton);
      return this;
   }


}
