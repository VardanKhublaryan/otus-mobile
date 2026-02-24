package pages.wishlists;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import components.Alert;
import components.wishlist.WishListContent;
import components.wishlist.WishListItem;
import pages.AbsBasePage;

@Singleton
public class MyWishListPage extends AbsBasePage {

   private final WishListContent wishListContent = new WishListContent($(id("wishlists")));
   private final SelenideElement addButton = $(id("add_button"));
   private final Alert alert = new Alert($(id("parentPanel")));

   public MyWishListPage assertsNumberOfWishLists(int expectedSize) {
      wishListContent
          .shouldBe(visible)
          .assertSizeEquals(expectedSize);
      return this;
   }

   public MyWishListPage assertSubTitleEquals(String expectedSubTitle, int index) {
      getWishListItem(index).assertSubTitleEquals(expectedSubTitle);
      return this;
   }

   public MyWishListPage assertWishListTitleEquals(String expectedTitle, int index) {
      getWishListItem(index).assertTitleEquals(expectedTitle);
      return this;
   }

   public MyWishListPage tabEditWishList(int index) {
      getWishListItem(index).tabEdit();
      return this;
   }

   public MyWishListPage clickWishList(int index){
      getWishListItem(index).tabWishList();
      return this;
   }

   private WishListItem getWishListItem(int index) {
      return wishListContent.getItem(index).shouldBe(visible);
   }

   public MyWishListPage clickAddButton(){
      click(addButton);
      return this;
   }

   public MyWishListPage deleteWishList(int index){
      getWishListItem(index).tabDeleteButton();
      alert
          .shouldBeVisible()
          .confirm()
          .shouldNotBeVisible();
      return this;
   }


}
