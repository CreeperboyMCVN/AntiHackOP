package com.creeperboymcvn.antiop.util.hook.discord;

import com.creeperboymcvn.antiop.Main;
import com.creeperboymcvn.antiop.util.Utils;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class Webhook {
    
   private URL webhook;
   private String content;
   private List<JSONObject> embeds;

   public Webhook(String WEBHOOK) {
      try {
         webhook = new URL(WEBHOOK);
      } catch (MalformedURLException e) {
         e.printStackTrace();
      }
      
      embeds = new ArrayList<>();
   }
   
   public void setContent(String content) {
       this.content = content;
   }
   
   public void addEmbed(JSONObject embed) {
       embeds.add(embed);
   }



   public void sendMessage() {
                                                                                                                                                         
                                                                                
      JSONObject json = new JSONObject();
      
      if (this.content == null && embeds.isEmpty()) {
          Main.inst.getLogger().severe("Cannot send discord webhook, nothing to send!");
          return;
      }
      
      if (!content.equalsIgnoreCase("")) {
          json.put("content", this.content);
      }
      if (!embeds.isEmpty()) {
          json.put("embeds", embeds.toArray());
      }
      Main.inst.getLogger().info("Webhook Debugger: "+json.toString());
      try {
         Utils.apiRequestDiscord(webhook, json);
      } catch (IOException e) {
         e.printStackTrace();
      }

   }
}