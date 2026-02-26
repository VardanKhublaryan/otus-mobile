package utils;

import java.io.File;
import java.io.IOException;

public class LogcatUtil {

   public static void clearLogcat(String deviceId) {
      try {
         new ProcessBuilder("adb", "-s", deviceId, "logcat", "-c")
             .start()
               .waitFor();
      } catch (Exception e) {
         throw new RuntimeException("Failed to clear logcat", e);
      }
   }

   public static void saveLogcat(String deviceId, String testName) {
      try {
         File logFile = new File(
               System.getProperty("user.dir"),
               testName + ".log"
         );

         ProcessBuilder processBuilder = new ProcessBuilder(
               "adb", "-s", deviceId, "logcat", "-d"
         );

         processBuilder.redirectOutput(logFile);
         Process process = processBuilder.start();
         process.waitFor();

         System.out.println("Logcat saved: " + logFile.getAbsolutePath());

      } catch (IOException | InterruptedException e) {
         throw new RuntimeException("Failed to save logcat", e);
      }
   }
}
