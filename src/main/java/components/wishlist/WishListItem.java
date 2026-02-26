package components.wishlist;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import components.AbsComponents;


public class WishListItem extends AbsComponents<WishListItem> {

   private final SelenideElement title = root().$(id("title"));
   private final SelenideElement subTitle = root().$(id("subtitle"));
   private final SelenideElement editButton = root().$(id("edit_button"));
   private final SelenideElement deleteButton = root().$(id("delete_button"));
   private final SelenideElement item = root().$(id("wishlist_item"));

   public WishListItem(SelenideElement root) {
      super(root);
   }

   public void assertTitleEquals(String expectedTitle) {
      title.shouldHave(text(expectedTitle));
   }

   public void assertSubTitleEquals(String expectedSubTitle) {
      subTitle.shouldHave(text(expectedSubTitle));
   }

   public void tapDeleteButton(){
      deleteButton.shouldBe(visible).click();
   }

   public void tapEdit(){
      click(editButton);
   }

   public void tapWishList(){
      click(item);
   }

}
