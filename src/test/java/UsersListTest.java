import com.google.inject.Inject;
import extensions.AndroidExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.HomePage;
import pages.LoginPage;
import pages.UsersPage;

@ExtendWith(AndroidExtension.class)
public class UsersListTest {

   @Inject
   private UsersPage usersPage;

   @Inject
   private LoginPage loginPage;

   @Inject
   private HomePage homePage;

   @Test
   public void checkFilterIsVisible(){
      loginPage.login("vardan", "vardan.1999");
      homePage.clickUsersTab();
      usersPage.assertFilterIsVisible();
   }

}
