package util;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class TelegramNotifier {
    private String botToken;
    private String chatId;

    public TelegramNotifier() {
        loadConfigurations();
    }

    private void loadConfigurations() {
        try {
            Properties properties = new Properties();
            FileInputStream inStream = new FileInputStream("/Users/oudomphoem/repos/UserManagementSystem/src/resources/config.properties");
            properties.load(inStream);
            this.botToken = properties.getProperty("telegram.botToken");
            this.chatId = properties.getProperty("telegram.chatId");
        } catch (Exception e) {
            System.err.println("Failed to load Telegram configurations: " + e.getMessage());
        }
    }


    public void sendNotification(String message) {
        if (botToken == null || chatId == null) {
            System.err.println("TelegramNotifier is not properly configured.");
            return;
        }

        try {
            String apiUrl = String.format("https://api.telegram.org/bot%s/sendMessage", botToken);
            String payload = String.format("chat_id=%s&text=%s", chatId, message);

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (OutputStream os = connection.getOutputStream()) {
                os.write(payload.getBytes(StandardCharsets.UTF_8));
                os.flush();
            }

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("Notification sent to Telegram Successfully.");
            } else {
                System.err.println("Failed to send Telegram notification: HTTP " + connection.getResponseCode());
            }

            connection.disconnect();
        } catch (Exception e) {
            System.err.println("Error sending Telegram notification: " + e.getMessage());
        }

    }
}
