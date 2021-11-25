package com.creeperboymcvn.antiop.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

public class Utils {
    public static void apiRequestDiscord(URL url, JSONObject json) throws IOException {
      HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
      connection.addRequestProperty("Content-Type", "application/json");
      connection.addRequestProperty("User-Agent", "Java-DiscordWebhook-by-CreeperboyMCVN");
      connection.setDoOutput(true);
      connection.setRequestMethod("POST");
      OutputStream stream = connection.getOutputStream();
      stream.write(json.toString().getBytes());
      stream.flush();
      stream.close();
      connection.getInputStream().close();
      connection.disconnect();
   }
}
