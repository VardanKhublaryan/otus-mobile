import com.google.inject.Inject;
import dbUtils.TestDataManager;
import dbUtils.TestDataManager.TestUser;
import extensions.AndroidExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.EditWishListPage;
import pages.LoginPage;
import pages.MyWishListPage;

@ExtendWith(AndroidExtension.class)
public class WishListEditTest {

   @Inject
   private LoginPage loginPage;

   @Inject
   private MyWishListPage wishListPage;

   @Inject
   private EditWishListPage editWishListPage;

   @Test
   public void editWishListTest() {
      loginPage.login("vardan", "vardan.1999");
      String wishListTitle = "Новый год";
      String newWishListDescription = "К нам мчится, скоро всё.";

      wishListPage
          .assertsNumberOfWishLists(1)
          .assertTitleEquals(wishListTitle, 1)
          .assertSubTitleEquals("К нам уже не мчится", 1)
          .tabEditWishList(1);
      editWishListPage
          .assertEditWishListTitle("Изменить список желаний")
          .editWishListTitle(newWishListDescription);
      wishListPage
          .assertsNumberOfWishLists(1)
          .assertTitleEquals(wishListTitle, 1)
          .assertSubTitleEquals(newWishListDescription, 1)
          .tabEditWishList(1);
      editWishListPage
          .editWishListTitle("К нам уже не мчится");
   }

   @Test
   public void addNewWishListTest() {
      TestDataManager.TestUser user = TestDataManager.createRandomUserWithWishlists();
      loginPage.login(user.username(), user.password());
      wishListPage.clickAddButton();
      editWishListPage.addWishList("Новый список", "test");
      wishListPage.assertTitleEquals("Новый список", 1);
      TestDataManager.deleteUser(user);

   }

   @Test
   public void test(){
      TestUser user = TestDataManager.findUserWithGifts();
      System.out.println(user.username());
      System.out.println(user.password());

   }
}
