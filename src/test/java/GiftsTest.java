import com.google.inject.Inject;
import utils.dbutils.TestDataManager;
import extensions.AndroidExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.FilterPage;
import pages.gifts.EditGiftPage;
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
   private MyWishListPage wishListPage;

   @Inject
   private MyGiftsPage giftPage;

   @Inject
   private EditGiftPage editGiftPage;

   @Inject
   private TestDataManager testDataManager;

   @Inject
   private FilterPage filterPage;


   @Test
   public void editGiftStatusTest() {
      testDataManager.updateGiftStatus("Ura16", "gift_743665", false);
      loginPage.login("test1", "Test123456.");
      wishListPage.clickUsersTab()
          .openFilter()
          .searchUser("Ura16")
          .clickUserItem(0)
          .clickWishList(1)
          .clickReserveButton()
            .isReserveButtonChecked();
   }

   @Test
   public void addGiftToWishListTest(){
      testDataManager.deleteAllGifts("test3");
      loginPage.login("test3", "Test123456.");
      wishListPage.clickWishList(1)
          .clickAddGiftButton()
          .createGift("test","test","200")
            .assertGiftTitleEquals("test", 1);}
}
