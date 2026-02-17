package extensions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.AndroidDriverFactory;
import factory.AndroidDriverModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class AndroidExtension implements TestInstancePostProcessor, BeforeEachCallback,
    AfterEachCallback, AfterTestExecutionCallback {

   private final Injector injector = Guice.createInjector(new AndroidDriverModule());

   @Override
   public void afterEach(ExtensionContext context) {

   }

   @Override
   public void afterTestExecution(ExtensionContext context) {
      WebDriver driver = WebDriverRunner.getWebDriver();
      injector.getInstance(AndroidDriverFactory.class).quit(driver);
   }

   @Override
   public void beforeEach(ExtensionContext context) {
      WebDriver driver = injector.getInstance(WebDriver.class);
      WebDriverRunner.setWebDriver(driver);
      Selenide.open();
      LoginPage loginPage = injector.getInstance(LoginPage.class);
      loginPage.login("vardan","vardan.1999");
   }

   @Override
   public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
      injector.injectMembers(testInstance);
   }
}
