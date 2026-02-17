package components;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import pages.AbsBasePage;

@SuppressWarnings("unchecked")
public class AbsComponents <T extends AbsComponents<T>> extends AbsBasePage {

   protected SelenideElement root;

   @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "SelenideElement must be stored to interact with elements")
   public AbsComponents(SelenideElement root) {
      this.root = root;
   }

   public T shouldBe(WebElementCondition... conditions) {
       for (WebElementCondition condition : conditions) {
           root.should(condition);
       }
       return (T) this;
   }



}
