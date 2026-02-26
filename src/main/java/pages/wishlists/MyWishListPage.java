package pages.wishlists;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import components.wishlist.WishListContent;
import components.wishlist.WishListItem;
import pages.AbsBasePage;
import pages.UsersPage;
import pages.gifts.MyGiftsPage;

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

   public MyGiftsPage clickWishList(int index){
      getWishListItem(index).tapWishList();
      return new MyGiftsPage();
   }

   private WishListItem getWishListItem(int index) {
      return wishListContent.getItem(index).shouldBe(visible);
   }

   public EditWishListPage clickAddWishlistButton(){
      click(addButton);
      return new EditWishListPage();
   }

   public UsersPage clickUsersTab(){
      navigationBar.clickUsersTab();
      return new UsersPage();
   }

}
