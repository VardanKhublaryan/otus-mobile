import com.google.inject.Inject;
import utils.dbutils.TestDataManager;
import extensions.AndroidExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.wishlists.EditWishListPage;
import pages.LoginPage;
import pages.wishlists.MyWishListPage;
import pages.UsersPage;

@ExtendWith(AndroidExtension.class)
public class WishListTest {

   @Inject
   private LoginPage loginPage;

   @Inject
   private MyWishListPage wishListPage;

   @Inject
   private EditWishListPage editWishListPage;

   @Inject
   private UsersPage usersPage;

   @Inject
   private TestDataManager testDataManager;

   @Test
   public void editWishListTest() {
      testDataManager.updateWishlistTitle("test2","Новый год");
      testDataManager.updateWishlistDescription("test2","К нам уже не мчится");
      loginPage.login("test2", "Test123456");
      String wishListTitle = "Новый год";
      String newWishListDescription = "К нам мчится, скоро всё.";

      wishListPage
          .assertsNumberOfWishLists(1)
          .assertWishListTitleEquals(wishListTitle, 1)
          .assertSubTitleEquals("К нам уже не мчится", 1)
            .tapEditWishList(1);
      editWishListPage
          .assertEditWishListTitle("Изменить список желаний")
            .editWishListTitle(newWishListDescription);
      wishListPage
          .assertsNumberOfWishLists(1)
          .assertWishListTitleEquals(wishListTitle, 1)
          .assertSubTitleEquals(newWishListDescription, 1)
            .tapEditWishList(1);
   }

   @Test
   public void addNewWishListTest() {
      testDataManager.deleteWishlist("vardan1");
      loginPage.login("vardan1", "Vardan.1999");
      wishListPage.clickAddWishlistButton()
          .addWishList("Новый список", "test")
            .assertWishListTitleEquals("Новый список", 1);
   }

}
