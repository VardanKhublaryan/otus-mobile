package components;

import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;


public class NavigationBar extends AbsComponents<NavigationBar> {

   private final SelenideElement usersTab = root().$(id("navigation_bar_item_small_label_view"));

   public NavigationBar(SelenideElement root) {
      super(root);
   }

   public void clickUsersTab(){
      click(usersTab);
   }

}
