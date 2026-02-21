package dbUtils;

import java.math.BigDecimal;
import java.sql.*;
import java.util.UUID;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class TestDataManager {

   private static final String URL = System.getenv("DB_URL");
   private static final String DB_USER = System.getenv("DB_USER");
   private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

   /**
    * @param password plain password for login
    */
   public record TestUser(String username, String email, String password, UUID userId) {

   }

   public static TestUser createRandomUserWithWishlists() {
      try (Connection connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD)) {
         connection.setAutoCommit(false);
         UUID userId = UUID.randomUUID();
         String username = "user_" + UUID.randomUUID().toString().substring(0, 8);
         String email = username + "@example.com";
         String password = "pass_" + UUID.randomUUID().toString().substring(0, 6);
         String hashedPassword = BCrypt.withDefaults().hashToString(10, password.toCharArray());
         try (PreparedStatement ps = connection.prepareStatement(
             "INSERT INTO users(id, username, email, password) VALUES (?, ?, ?, ?)"
         )) {
            ps.setObject(1, userId);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, hashedPassword);
            ps.executeUpdate();
         }
         connection.commit();
         return new TestUser(username, email, password, userId);
      } catch (SQLException e) {
         System.err.println("Error creating random user: " + e.getMessage());
         return null;
      }
   }

   public static TestUser createRandomUserWithWishlistsAndGifts(String wishlistTitle,
       String wishlistDescription, String giftName, String giftDescription, String giftPrice) {
      try (Connection connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD)) {
         connection.setAutoCommit(false);
         UUID userId = UUID.randomUUID();
         String username = "user_" + UUID.randomUUID().toString().substring(0, 8);
         String email = username + "@example.com";
         String password = "pass_" + UUID.randomUUID().toString().substring(0, 6);
         String hashedPassword = BCrypt.withDefaults()
             .hashToString(10, password.toCharArray());

         try (PreparedStatement ps = connection.prepareStatement(
             "INSERT INTO users(id, username, email, password) VALUES (?, ?, ?, ?)"
         )) {
            ps.setObject(1, userId);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, hashedPassword);
            ps.executeUpdate();
         }

         UUID wishlistId = UUID.randomUUID();
         try (PreparedStatement ps = connection.prepareStatement(
             "INSERT INTO wishlists(id, title, description, user_id) VALUES (?, ?, ?, ?)"
         )) {
            ps.setObject(1, wishlistId);
            ps.setString(2, wishlistTitle);
            ps.setString(3, wishlistDescription);
            ps.setObject(4, userId);
            ps.executeUpdate();
         }

         try (PreparedStatement ps = connection.prepareStatement(
             "INSERT INTO gifts(id, name, description, price, wish_id, is_reserved) " +
                 "VALUES (?, ?, ?, ?, ?, ?)"
         )) {
            ps.setObject(1, UUID.randomUUID());
            ps.setString(2, giftName);
            ps.setString(3, giftDescription);
            ps.setBigDecimal(4, new java.math.BigDecimal(giftPrice));
            ps.setObject(5, wishlistId);
            ps.setBoolean(6, false);
            ps.executeUpdate();
         }
         connection.commit();
         return new TestUser(username, email, password, userId);
      } catch (SQLException e) {
         throw new RuntimeException("Failed to create test data", e);
      }
   }


   public static void deleteUser(TestUser user) {
      if (user == null) {
         return;
      }
      try (Connection connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD)) {
         connection.setAutoCommit(false);
         try (PreparedStatement ps = connection.prepareStatement(
             "DELETE FROM wishlists WHERE user_id = ?"
         )) {
            ps.setObject(1, user.userId);
            ps.executeUpdate();
         }
         try (PreparedStatement ps = connection.prepareStatement(
             "DELETE FROM users WHERE id = ?"
         )) {
            ps.setObject(1, user.userId);
            ps.executeUpdate();
         }
         connection.commit();

         try (PreparedStatement ps = connection.prepareStatement(
             "SELECT * FROM users WHERE id = ?"
         )) {
            ps.setObject(1, user.userId);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
               System.out.println("User successfully deleted: " + user.username);
            } else {
               System.out.println("User STILL EXISTS in DB: " + user.username);
            }
         }
      } catch (SQLException e) {
         System.err.println("Error deleting user data: " + e.getMessage());
      }
   }

   public static TestUser findUserWithGifts() {

      String sql = """
        SELECT u.id, u.username, u.email, u.password
        FROM users u
        JOIN wishlists w ON w.user_id = u.id
        JOIN gifts g ON g.wish_id = w.id
        LIMIT 1
        """;

      try (Connection connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
          PreparedStatement ps = connection.prepareStatement(sql);
          ResultSet rs = ps.executeQuery()) {

         if (!rs.next()) {
            throw new RuntimeException("No user with gifts found");
         }

         UUID userId = rs.getObject("id", UUID.class);
         String username = rs.getString("username");
         String email = rs.getString("email");
         String password = rs.getString("password");

         return new TestUser(username, email, password, userId);

      } catch (SQLException e) {
         e.printStackTrace();
         throw new RuntimeException("Failed to find user with gifts", e);
      }
   }


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

         int updated = ps.executeUpdate();
         System.out.println("Updated wishlists: " + updated);

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

         int updated = ps.executeUpdate();
         System.out.println("Updated wishlists: " + updated);

      } catch (SQLException e) {
         throw new RuntimeException("Failed to update wishlist title", e);
      }
   }


   public static void updateGift(String login, String newName, BigDecimal newPrice) {

      String sql = """
          UPDATE gifts
          SET name = ?, price = ?
          WHERE wish_id IN (
              SELECT w.id
              FROM wishlists w
              JOIN users u ON w.user_id = u.id
              WHERE u.username = ?
          )
          """;

      try (Connection connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
          PreparedStatement ps = connection.prepareStatement(sql)) {

         ps.setString(1, newName);
         ps.setBigDecimal(2, newPrice);
         ps.setString(3, login);

         int updated = ps.executeUpdate();
         System.out.println("Updated gifts: " + updated);

      } catch (SQLException e) {
         throw new RuntimeException("Failed to update gift", e);
      }
   }



}
