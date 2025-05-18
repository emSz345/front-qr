package main.java.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;

/**
 *
 * @author emmso
 */
@Named("qrBean")
@RequestScoped
public class QRBean implements Serializable {
    private String content;
    private String qrImage;

    public String generate() {
        try {
            URL url = new URL("http://localhost:5000/generate");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"content\":\"" + content + "\"}";
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() == 200) {
                this.qrImage = "data:image/png;base64," + java.util.Base64.getEncoder().encodeToString(conn.getInputStream().readAllBytes());
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String clear() {
        this.content = null;
        this.qrImage = null;
        return null;
    }

    // Getters & Setters
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getQrImage() { return qrImage; }
}
