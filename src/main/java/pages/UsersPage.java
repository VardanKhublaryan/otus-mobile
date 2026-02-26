package pages;

import static com.codeborne.selenide.appium.SelenideAppium.$$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.appium.SelenideAppiumCollection;
import com.google.inject.Singleton;
import pages.wishlists.MyWishListPage;


@Singleton
public class UsersPage extends AbsBasePage{

   private final SelenideAppiumCollection usersList = $$(id("user_item"));

   public UsersPage assertFilterIsVisible() {
      topAppBar.assertFilterIsVisible();
      return this;
   }

   public FilterPage openFilter(){
      topAppBar.openFilter();
      return new FilterPage();
   }


   public MyWishListPage clickUserItem(int index){
      click(usersList.get(index));
      return new MyWishListPage();
   }

}
