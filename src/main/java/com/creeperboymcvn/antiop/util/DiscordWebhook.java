package com.creeperboymcvn.antiop.util;

import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class DiscordWebhook {
   private static int color;
   private static String title;
   private static String description;
   private static String message;
   private static URL webhook;

   public DiscordWebhook(String WEBHOOK, String TITLE, String DESCRIPTION, String MESSAGE, String COLOR) {
      try {
         webhook = new URL(WEBHOOK);
      } catch (MalformedURLException var7) {
         var7.printStackTrace();
      }

      title = TITLE;
      description = DESCRIPTION.replace("%empity%", "\u200b");
      message = MESSAGE;
      color = Color.decode(COLOR).getRed();
      color = (color << 8) + Color.decode(COLOR).getGreen();
      color = (color << 8) + Color.decode(COLOR).getBlue();
   }



   public static void sendMessage(String text) {
      DateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        String format = df.format(new java.util.Date(System.currentTimeMillis()));
      List<JSONObject> jsonArray = new ArrayList();
      
      JSONObject json = new JSONObject();
      json.put("title", title);
      json.put("description", description);
      json.put("color", color);
      
      JSONObject JsonX = new JSONObject();
      JsonX.put("name", text);
      JsonX.put("value", "\u200b");
      JsonX.put("inline", true);
      jsonArray.add(JsonX);
      json.put("fields", jsonArray.toArray());
      
      JsonX = new JSONObject();
      JsonX.put("text", format+" - Plugin được làm bởi CreeperboyMCVN");
      json.put("footer", JsonX);
      
      jsonArray = new ArrayList();
      jsonArray.add(json);
      
      json = new JSONObject();
      json.put("embeds", jsonArray);

      try {
         Utils.apiRequestDiscord(webhook, json);
      } catch (IOException var9) {
         var9.printStackTrace();
      }

   }
}