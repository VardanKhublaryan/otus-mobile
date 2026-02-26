package components.gift;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import components.AbsComponents;

public class GiftsItem extends AbsComponents<GiftsItem> {

   private final SelenideElement title = root().$(id("title"));
   private final SelenideElement deleteButton = root().$(id("delete_button"));
   private final SelenideElement item = root().$(id("wishlist_item"));


   public void assertTitleEquals(String expectedTitle) {
      title.shouldHave(text(expectedTitle));
   }

   public GiftsItem(SelenideElement root) {
      super(root);
   }

   public void tapGiftItem(){
      click(item);
   }

   public void tapDeleteButton(){
      deleteButton.shouldBe(visible).click();
   }

}
