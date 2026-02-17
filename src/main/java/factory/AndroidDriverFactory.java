package factory;

import com.google.inject.Singleton;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URL;
import org.openqa.selenium.WebDriver;

@Singleton
public class AndroidDriverFactory {

   public WebDriver create() {
      UiAutomator2Options options = new UiAutomator2Options();
      options.setPlatformName("Android");
      options.setAutomationName("UiAutomator2");
      options.setDeviceName("emulator-5554");
      options.setUdid("emulator-5554");
      options.setApp("/Users/gevorg.kocharyan/IdeaProjects/otus-mobile/src/test/java/resources/wishlist.apk");
      options.setAppPackage("ru.otus.wishlist");

      try {
         return new AndroidDriver(
             new URL("http://127.0.0.1:4723"),
             options
         );
      } catch (Exception e) {
         throw new RuntimeException("Cannot create AndroidDriver", e);
      }
   }

   public void quit(WebDriver driver) {
      if (driver != null) {
         driver.quit();
      }
   }

}
