package components.wishlist;

import static com.codeborne.selenide.CollectionCondition.size;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import components.AbsComponents;

public class WishListContent extends AbsComponents<WishListContent> {

   private final ElementsCollection items = root().$$(id("wishlist_item"));

   public WishListContent(SelenideElement root) {
      super(root);
   }

   public WishListItem getItem(int index) {
      return new WishListItem(items.get(index - 1));
   }

   public void assertSizeEquals(int expectedSize) {
      items.shouldHave(size(expectedSize));
   }
}
