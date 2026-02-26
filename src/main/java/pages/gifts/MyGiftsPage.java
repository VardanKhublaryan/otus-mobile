package pages.gifts;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import components.gift.GiftContent;
import components.gift.GiftsItem;
import pages.AbsBasePage;

@Singleton
public class MyGiftsPage extends AbsBasePage {

   private final GiftContent giftContent = new GiftContent($(id("gifts")));
   private final SelenideElement reserveButton = $(id("reserved"));
   private final SelenideElement addButton = $(id("add_button"));


   public MyGiftsPage clickReserveButton(){
      click(reserveButton);
      return this;
   }

   public MyGiftsPage isReserveButtonChecked(){
      reserveButton.shouldHave(attribute("checked", "true"));
      return this;
   }

   public EditGiftPage clickAddGiftButton(){
      click(addButton);
      return new EditGiftPage();
   }

   private GiftsItem getGiftsItem(int index) {
      return giftContent.getItem(index).shouldBe(visible);
   }

   public MyGiftsPage assertGiftTitleEquals(String expectedTitle, int index) {
      getGiftsItem(index).assertTitleEquals(expectedTitle);
      return this;
   }

}
