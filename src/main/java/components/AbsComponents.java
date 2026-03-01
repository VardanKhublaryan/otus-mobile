package components;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import pageobject.AbsPageObject;

@SuppressWarnings("unchecked")
public abstract class AbsComponents<T extends AbsComponents<T>> extends AbsPageObject {

   protected final SelenideElement root;

   public AbsComponents(SelenideElement root) {
      this.root = root;
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
