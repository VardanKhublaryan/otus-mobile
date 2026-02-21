package pageobject;

import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

public abstract class AbsPageObject {

   protected void sendKey(SelenideElement element, String text) {
      element.shouldBe(visible).sendKeys(text);
   }

   protected void click(SelenideElement element) {
      element.shouldBe(visible, Duration.ofSeconds(10)).click();
   }

}
