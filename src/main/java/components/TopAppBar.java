package components;

import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;

public class TopAppBar extends AbsComponents<TopAppBar>{

   private final SelenideElement filterButton = root.$(id("filter"));

   public TopAppBar(SelenideElement root) {
      super(root);
   }

   public void assertFilterIsVisible() {
      filterButton.shouldBe(visible);
   }

   public void openFilter() {
      filterButton.click();
   }
}
