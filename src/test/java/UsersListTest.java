import com.google.inject.Inject;
import extensions.AndroidExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.UsersPage;

@ExtendWith(AndroidExtension.class)
public class UsersListTest {

   @Inject
   private UsersPage usersPage;

   @Test
   public void checkFilterIsVisible(){
      usersPage.clickUsersTab();
      usersPage.assertFilterIsVisible();
   }

}
