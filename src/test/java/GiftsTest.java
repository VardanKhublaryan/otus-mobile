import com.google.inject.Inject;
import extensions.AndroidExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.gifts.EditGiftPage;
import pages.HomePage;
import pages.LoginPage;
import pages.gifts.MyGiftsPage;
import pages.wishlists.MyWishListPage;
import pages.UsersPage;

@ExtendWith(AndroidExtension.class)
public class GiftsTest {

   @Inject
   private UsersPage usersPage;

   @Inject
   private LoginPage loginPage;

   @Inject
   private HomePage homePage;

   @Inject
   private MyWishListPage wishListPage;

   @Inject
   private MyGiftsPage giftPage;

   @Inject
   private EditGiftPage editGiftPage;

   @Test
   public void editGiftStatusTest() {
      loginPage.login("test1", "Test123456.");
      homePage.clickUsersTab();
      usersPage.openFilter();
      usersPage.searchUser("Ura16");
      usersPage.clickUserItem(0);
      wishListPage.clickWishList(1);
      giftPage.clickReserveButton();
      giftPage.isReserveButtonChecked();
      giftPage.clickReserveButton();
   }

   @Test
   public void addGiftToWishListTest(){
      loginPage.login("test3", "Test123456.");
      wishListPage.clickWishList(1);
      wishListPage.clickAddButton();
      editGiftPage.createGift("test","test","200");
      giftPage.assertGiftTitleEquals("test", 1);
      giftPage.deleteWishList(1);
   }
}
