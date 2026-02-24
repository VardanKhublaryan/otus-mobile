package components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class FilterComponent extends AbsComponents<FilterComponent> {

   private SelenideElement userNameInput = root.$(By.id("username_input"));
   private SelenideElement applyButton = root.$(By.id("apply_button"));

   public FilterComponent(SelenideElement root) {
      super(root);
   }

   public void searchUser(String username){
      sendKey(userNameInput,username);
      click(applyButton);
   }
}
