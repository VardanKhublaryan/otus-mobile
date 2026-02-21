package factory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

@Singleton
public class AndroidDriverFactory {

   private final Capabilities capabilities;

   @Inject
   public AndroidDriverFactory(Capabilities capabilities) {
      this.capabilities = capabilities;
   }

   public WebDriver create() {
      try {
         return new AndroidDriver(
             new URL("http://127.0.0.1:4723"),
             capabilities
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
