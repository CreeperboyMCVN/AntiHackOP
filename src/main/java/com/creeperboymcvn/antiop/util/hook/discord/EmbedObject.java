package com.creeperboymcvn.antiop.util.hook.discord;

import com.creeperboymcvn.antiop.Main;
import com.creeperboymcvn.antiop.util.Values;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

public class EmbedObject {
    private JSONObject embed;
    private String title;
    private String description;
    private String url;
    private int color;
    private String timestamp;
    //add to embed on call
    private List<JSONObject> fields;
    private JSONObject author;
    private JSONObject footer;
    private JSONObject image;
    private JSONObject thumbnail;

    
    public EmbedObject() {
        embed = new JSONObject();
        author = new JSONObject();
        footer = new JSONObject();
        thumbnail = new JSONObject();
        image = new JSONObject();
        fields = new ArrayList();
    }
    
    public void setTitle(String title) {
        if (title == null || title.equalsIgnoreCase("")) return;
        this.title = title;
        embed.put("title", title);
    }
    
    public void setDescription(String desc) {
        if (desc == null || desc.equalsIgnoreCase("")) return;
        this.description = desc;
        embed.put("description", desc);
    }
    
    public void setUrlDescription(String url) {
        if (url == null || url.equalsIgnoreCase("")) return;
        this.url = url;
        embed.put("url", url);
    }
    
    public void setColor(String color) {        
        if (color == null||color.equalsIgnoreCase("")) {
            embed.put("color", JSONObject.NULL);
            return;
        }
        this.color = Color.decode(color).getRed();
        this.color = (this.color << 8) + Color.decode(color).getGreen();
        this.color = (this.color << 8) + Color.decode(color).getBlue();
        embed.put("color", this.color);
    }
    
    public void setTimeStamp(Date date) {
        if (date == null) {
            date = new Date(0);
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone(Values.__TIME_ZONE));
        this.timestamp = df.format(date);
        embed.put("timestamp", timestamp);
    }
    
    public void addField(String name, String value, boolean inline) {
        if (name == null || name.equalsIgnoreCase("")||value == null||value.equalsIgnoreCase("")) {
            return;
        }
            
        JSONObject fieldsContent = new JSONObject();
        fieldsContent.put("name", name);
        fieldsContent.put("value", value);
        fieldsContent.put("inline", inline);
        
        fields.add(fieldsContent);
    }
    
    public void setAuthor(String name, String url, String icon_url) {
        if (name == null|| name.equalsIgnoreCase("")||url == null || url.equalsIgnoreCase("")||icon_url==null||icon_url.equalsIgnoreCase("")) {
            return;
        }
            
        author.put("name", name);
        author.put("url", url);
        author.put("icon_url", icon_url);
    }
    
    public void setThumbnail(String url) {
        if (url == null||url.equalsIgnoreCase("")) return;
        thumbnail.put("url", url);
    }
    
    public void setImage(String url) {
        if (url == null||url.equalsIgnoreCase("")) return;
        image.put("url", url);
    }
    
    public void setFooter(String text, String icon_url) {
        
        
        footer.put("text", text);
        
        footer.put("icon_url", icon_url);
    }
    
    public JSONObject getEmbed() {
        if (!fields.isEmpty()) {
            embed.put("fields", fields.toArray());
        }
        if (!author.isEmpty()) {
            embed.put("author", author);
        }
        if (!footer.isEmpty()) {
            embed.put("footer", footer);
        }
        if (!image.isEmpty()) {
            embed.put("image", image);
        }
        if (!thumbnail.isEmpty()) {
            embed.put("thumbnail", thumbnail);
        }
        Main.inst.getLogger().info("Embed Debugger: "+embed.toString());
        return embed;
        
    }
}
