package pages;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import components.WishListContent;
import components.WishListItem;

public class MyWishListPage extends AbsBasePage {

   private final WishListContent wishListContent = new WishListContent(
       $(id("wishlists")));
   private final SelenideElement addButton = $(id("add_button"));
   private final SelenideElement confirmButton = Selenide.$(
       id("android:id/button1"));


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
      click(confirmButton);
      return this;
   }

}
