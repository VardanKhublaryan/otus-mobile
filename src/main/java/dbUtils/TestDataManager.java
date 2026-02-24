package dbUtils;

import java.sql.*;

public class TestDataManager {

   private static final String URL = System.getenv("DB_URL");
   private static final String DB_USER = System.getenv("DB_USER");
   private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

   public static void updateWishlistDescription(String login, String description) {

      String sql = """
          UPDATE wishlists
          SET description = ?
          WHERE user_id = (
              SELECT id FROM users WHERE username = ?
          )
          """;

      try (Connection conn = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
          PreparedStatement ps = conn.prepareStatement(sql)) {

         ps.setString(1, description);
         ps.setString(2, login);
         ps.executeUpdate();

      } catch (SQLException e) {
         throw new RuntimeException("Failed to update wishlist description", e);
      }
   }

   public static void updateWishlistTitle(String login, String newTitle) {

      String sql = """
          UPDATE wishlists
          SET title = ?
          WHERE user_id = (
              SELECT id FROM users WHERE username = ?
          )
          """;

      try (Connection connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
          PreparedStatement ps = connection.prepareStatement(sql)) {

         ps.setString(1, newTitle);
         ps.setString(2, login);
         ps.executeUpdate();

      } catch (SQLException e) {
         throw new RuntimeException("Failed to update wishlist title", e);
      }
   }

}
