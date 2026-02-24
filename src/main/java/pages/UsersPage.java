package pages;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.ElementsCollection;
import com.google.inject.Singleton;
import components.FilterComponent;
import components.TopAppBar;

@Singleton
public class UsersPage extends AbsBasePage{

   private final TopAppBar topAppBar = new TopAppBar($(id("top_app_bar_layout")));
   private final FilterComponent filterComponent = new FilterComponent($(id("users_filter_bottom_sheet")));
   private final ElementsCollection usersList = $$(id("user_item"));

   public UsersPage assertFilterIsVisible() {
      topAppBar.assertFilterIsVisible();
      return this;
   }

   public UsersPage openFilter(){
      topAppBar.openFilter();
      return this;
   }
   public UsersPage searchUser(String username){
      filterComponent.searchUser(username);
      return  this;
   }

   public UsersPage clickUserItem(int index){
      click(usersList.get(index));
      return this;
   }

}
