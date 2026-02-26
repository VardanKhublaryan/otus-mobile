package factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public class AndroidDriverModule extends AbstractModule {

   private final String deviceName = System.getProperty("deviceName", "emulator-5554");
   private final String udid = System.getProperty("udid", "emulator-5554");
   private final URL resource = Objects.requireNonNull(
         getClass().getClassLoader().getResource("wishlist.apk"),
         "APK file not found in test/resources"
   );

   @Provides
   @Singleton
   public Capabilities capabilities() throws URISyntaxException {
      String appPath = Paths.get(resource.toURI()).toString();
      UiAutomator2Options options = new UiAutomator2Options();
      options.setPlatformName("Android");
      options.setAutomationName("UiAutomator2");
      options.setDeviceName(deviceName);
      options.setUdid(udid);
      options.setApp(appPath);
      options.setAppPackage("ru.otus.wishlist");

      return options;
   }

   @Provides
   public WebDriver webdriver(AndroidDriverFactory factory) {
      return factory.create();
   }
}
