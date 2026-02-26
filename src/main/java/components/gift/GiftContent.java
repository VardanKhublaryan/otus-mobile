package components.gift;

import static com.codeborne.selenide.CollectionCondition.size;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import components.AbsComponents;

public class GiftContent extends AbsComponents<GiftContent> {

   private final ElementsCollection items = root().$$(id("gift_item"));

   public GiftContent(SelenideElement root) {
      super(root);
   }

   public GiftsItem getItem(int index) {
      return new GiftsItem(items.get(index - 1));
   }

   public void assertSizeEquals(int expectedSize) {
      items.shouldHave(size(expectedSize));
   }

}
