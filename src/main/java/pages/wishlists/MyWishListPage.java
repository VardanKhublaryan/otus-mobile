package pages.wishlists;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import components.wishlist.WishListContent;
import components.wishlist.WishListItem;
import pages.AbsBasePage;

@Singleton
public class MyWishListPage extends AbsBasePage {

   private final WishListContent wishListContent = new WishListContent($(id("wishlists")));
   private final SelenideElement addButton = $(id("add_button"));

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

   public MyWishListPage tapEditWishList(int index) {
      getWishListItem(index).tapEdit();
      return this;
   }

   public void clickWishList(int index){
      getWishListItem(index).tapWishList();
   }

   private WishListItem getWishListItem(int index) {
      return wishListContent.getItem(index).shouldBe(visible);
   }

   public void clickAddWishlistButton(){
      click(addButton);
   }

}
