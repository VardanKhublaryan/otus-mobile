package extensions;

import static java.sql.DriverManager.getDriver;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import factory.AndroidDriverFactory;
import factory.AndroidDriverModule;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import utils.LogcatUtil;

public class AndroidExtension implements TestInstancePostProcessor, BeforeEachCallback, AfterEachCallback,AfterTestExecutionCallback {

   private final Injector injector = Guice.createInjector(new AndroidDriverModule());

   @Override
   public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
      injector.injectMembers(testInstance);
   }

   @Override
   public void beforeEach(ExtensionContext context) {
      WebDriver driver = injector.getInstance(WebDriver.class);
      WebDriverRunner.setWebDriver(driver);
      Selenide.open();
      String deviceId = ((AndroidDriver) driver)
            .getCapabilities()
            .getCapability("udid")
            .toString();

      LogcatUtil.clearLogcat(deviceId);

   }

   @Override
   public void afterTestExecution(ExtensionContext context) {

      if (context.getExecutionException().isPresent()) {

         WebDriver driver = injector.getInstance(WebDriver.class);
         String udid = ((AndroidDriver)driver).getCapabilities()
               .getCapability("udid")
               .toString();

         String testName = context.getRequiredTestMethod().getName();

         LogcatUtil.saveLogcat(udid, testName);
      }
   }

   @Override
   public void afterEach(ExtensionContext context) {
      WebDriver driver = WebDriverRunner.getWebDriver();
      if (driver != null) {
         injector.getInstance(AndroidDriverFactory.class).quit(driver);
      }
   }
}
