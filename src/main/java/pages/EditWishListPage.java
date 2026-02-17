package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;

@Singleton
public class EditWishListPage extends AbsBasePage {

   private final SelenideElement title = $(id("wishlist_edit_title"));
   private final SelenideElement wishListDescriptionInputField = $(
       id("description_input"));
   private final SelenideElement titleInputField = $(
       id("title_input"));
   private final SelenideElement saveButton = $(
       id("save_button"));

   public EditWishListPage assertEditWishListTitle(String expectedTitle) {
      title.shouldBe(visible).shouldHave(text(expectedTitle));
      return this;
   }

   public EditWishListPage editWishListTitle(String newTitle) {
      wishListDescriptionInputField.shouldBe(visible).clear();
      sendKey(wishListDescriptionInputField,newTitle);
      click(saveButton);
      return this;
   }

   public EditWishListPage addWishList(String newTitle,String newDescription) {
      sendKey(titleInputField,newTitle);
      sendKey(wishListDescriptionInputField,newDescription);
      click(saveButton);
      return this;
   }

}
