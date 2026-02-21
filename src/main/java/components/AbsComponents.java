package components;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import pageobject.AbsPageObject;

@SuppressWarnings("unchecked")
public abstract class AbsComponents <T extends AbsPageObject> extends AbsPageObject {

   protected SelenideElement root;

   public AbsComponents(SelenideElement root) {
      this.root = root;
   }

   public T shouldBe(WebElementCondition... conditions) {
      root.should(conditions);
      return (T) this;
   }




}
