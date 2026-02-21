package pages;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pageobject.AbsPageObject;

@Singleton
public abstract class AbsBasePage extends AbsPageObject {

   public static void main(String[] args) throws Exception {

      Class.forName("org.postgresql.Driver");

      Connection connection = DriverManager.getConnection(
          "jdbc:postgresql://sql.otus.kartushin.su:5432/wishlist",
          "student",
          "student"
      );

      Statement stmt = connection.createStatement();

      ResultSet rs = stmt.executeQuery(
          "SELECT column_name, data_type " +
              "FROM information_schema.columns " +
              "WHERE table_name = 'wishlists';"
      );

      while (rs.next()) {
         System.out.println(rs.getString("column_name") + " | " + rs.getString("data_type"));
      }


      while (rs.next()) {
         System.out.println(rs.getString("table_schema") + " | " + rs.getString("table_name"));
      }
   }


}
