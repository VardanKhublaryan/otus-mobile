package factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.openqa.selenium.WebDriver;

@SuppressWarnings("unused")
public class AndroidDriverModule extends AbstractModule {

   @Provides
   private WebDriver webdriver(AndroidDriverFactory factory) {
      return factory.create();
   }
}
