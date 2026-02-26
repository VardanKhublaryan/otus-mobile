package components;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import pageobject.AbsPageObject;

@SuppressWarnings("unchecked")
public abstract class AbsComponents<T extends AbsPageObject> extends AbsPageObject {

   private final SelenideElement root;

   public AbsComponents(SelenideElement root) {
      this.root = root;
   }

   protected SelenideElement root() {
      return root;
   }

   public T shouldBe(WebElementCondition... conditions) {
      root.should(conditions);
      return (T) this;
   }

   public T shouldNotBe(WebElementCondition... conditions) {
      root.shouldNot(conditions);
      return (T) this;
   }




}
