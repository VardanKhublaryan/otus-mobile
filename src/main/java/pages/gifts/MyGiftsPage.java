package pages.gifts;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import components.Alert;
import components.gift.GiftContent;
import components.gift.GiftsItem;
import pages.AbsBasePage;
import pages.wishlists.MyWishListPage;

public class MyGiftsPage extends AbsBasePage {

   private final GiftContent giftContent = new GiftContent($(id("gifts")));
   private SelenideElement reserveButton = Selenide.$(id("reserved"));
   private final Alert alert = new Alert($(id("parentPanel")));

   public void clickReserveButton(){
      click(reserveButton);
   }

   public void isReserveButtonChecked(){
      reserveButton.shouldHave(attribute("checked", "true"));

   }

   public MyGiftsPage deleteWishList(int index){
      getGiftsItem(index).tabDeleteButton();
      alert
          .shouldBeVisible()
          .confirm()
          .shouldNotBeVisible();
      return this;
   }

   private GiftsItem getGiftsItem(int index) {
      return giftContent.getItem(index).shouldBe(visible);
   }

   public MyGiftsPage assertGiftTitleEquals(String expectedTitle, int index) {
      getGiftsItem(index).assertTitleEquals(expectedTitle);
      return this;
   }

}
