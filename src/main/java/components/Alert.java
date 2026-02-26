package components;

import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class Alert extends AbsComponents<Alert> implements PopUp<Alert> {

   private final SelenideElement confirmButton = root().$(id("android:id/button1"));

   public Alert(SelenideElement root) {
      super(root);
   }

   public Alert confirm() {
      confirmButton.click();
      return this;
   }

   @Override
   public Alert shouldBeVisible() {
      shouldBe(Condition.visible);
      return this;
   }

   @Override
   public Alert shouldNotBeVisible() {
      shouldNotBe(Condition.visible);
      return this;
   }
}
