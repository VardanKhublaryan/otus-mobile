package pages;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.appium.SelenideAppium;
import components.Alert;
import components.NavigationBar;
import components.TopAppBar;
import pageobject.AbsPageObject;

public abstract class AbsBasePage extends AbsPageObject {

   protected final NavigationBar navigationBar = new NavigationBar($(id("bottom_navigation")));
   protected final TopAppBar topAppBar = new TopAppBar(SelenideAppium.$(id("top_app_bar_layout")));
   protected final Alert alert = new Alert(SelenideAppium.$(id("parentPanel")));

}
