import com.google.inject.Inject;
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
      String wishListTitle = "Новый год";
      String newWishListDescription = "К нам мчится, скоро всё.";

      wishListPage
          .assertsNumberOfWishLists(1)
          .assertTitleEquals(wishListTitle,1)
          .assertSubTitleEquals("К нам уже не мчится",1)
          .tabEditWishList(1);
      editWishListPage
          .assertEditWishListTitle("Изменить список желаний")
          .editWishListTitle(newWishListDescription);
      wishListPage
          .assertsNumberOfWishLists(1)
          .assertTitleEquals(wishListTitle,1)
          .assertSubTitleEquals(newWishListDescription,1)
          .tabEditWishList(1);
      editWishListPage
          .editWishListTitle("К нам уже не мчится");
   }

   @Test
   public void addNewWishListTest() {
      wishListPage.clickAddButton();
      editWishListPage.addWishList("Новый список","test");
      wishListPage.assertTitleEquals("Новый список",2)
          .deleteWishList(2);
   }
}
