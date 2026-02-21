package pages;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import components.Alert;
import components.WishListContent;
import components.WishListItem;

@Singleton
public class MyWishListPage extends AbsBasePage {

   private final WishListContent wishListContent = new WishListContent(
       $(id("wishlists")));
   private final SelenideElement addButton = $(id("add_button"));
   private final Alert alert = new Alert($(id("parentPanel")));

   public MyWishListPage assertsNumberOfWishLists(int expectedSize) {
      wishListContent
          .shouldBe(visible)
          .assertSizeEquals(expectedSize);
      return this;
   }

   public MyWishListPage assertSubTitleEquals(String expectedSubTitle, int index) {
      getItem(index).assertSubTitleEquals(expectedSubTitle);
      return this;
   }

   public MyWishListPage assertTitleEquals(String expectedTitle, int index) {
      getItem(index).assertTitleEquals(expectedTitle);
      return this;
   }

   public MyWishListPage tabEditWishList(int index) {
      getItem(index).tabEdit();
      return this;
   }

   private WishListItem getItem(int index) {
      return wishListContent.getItem(index).shouldBe(visible);
   }

   public MyWishListPage clickAddButton(){
      click(addButton);
      return this;
   }

   public MyWishListPage deleteWishList(int index){
      getItem(index).tabDeleteButton();
      alert
          .shouldBeVisible()
          .confirm()
          .shouldNotBeVisible();
      return this;
   }


}
