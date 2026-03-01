package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogcatUtil {

   public static void clearLogcat(WebDriver driver) {
      driver.manage().logs().get("logcat");
   }

   public static void saveLogcat(WebDriver driver, String testName) {
      try {
         LogEntries logs = driver.manage().logs().get("logcat");

         File logDir = new File(System.getProperty("user.dir"), "logs");
         if (!logDir.exists()) logDir.mkdirs();

         File logFile = new File(logDir, testName + ".log");

         try (FileWriter writer = new FileWriter(logFile)) {
            for (LogEntry entry : logs) {
               writer.write(entry.getMessage());
               writer.write(System.lineSeparator());
            }
         }

      } catch (IOException e) {
         throw new RuntimeException("Failed to save logcat", e);
      }
   }
}
