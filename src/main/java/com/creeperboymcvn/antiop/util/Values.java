package com.creeperboymcvn.antiop.util;

import com.creeperboymcvn.antiop.Main;

public class Values {
    public static String NOT_IN_PLAYER_LIST = Main.inst.getMessageConf().getConfig().getString("notInPlayerList");
    public static String WARN_TITLE = Main.inst.getMessageConf().getConfig().getString("warnTitle");
    public static String WARN_MESSAGE = Main.inst.getMessageConf().getConfig().getString("warnMsg");
    public static String WARN_SUBTITLE = Main.inst.getMessageConf().getConfig().getString("warnSubtitle");
    public static String KICK_MESSAGE = Main.inst.getMessageConf().getConfig().getString("kickMsg");
    public static String INPUTING_PASSWORD = Main.inst.getMessageConf().getConfig().getString("inputingPassword");
    public static String WRONG_PASS = Main.inst.getMessageConf().getConfig().getString("wrongPass");
    public static String PASSED = Main.inst.getMessageConf().getConfig().getString("passed");
    public static String DISCORD_RESET_PASSWORD_CONTENT = Main.inst.getMessageConf().getConfig().getString("discord.reset.content");
    public static String DISCORD_RESET_PASSWORD_TITLE = Main.inst.getMessageConf().getConfig().getString("discord.reset.title");
    public static String DISCORD_RESET_PASSWORD_MESSAGE = Main.inst.getMessageConf().getConfig().getString("discord.reset.message");
    
    public static int __INPUT_PASSWORD_COUNTDOWN = Main.inst.getConfig().getInt("inputPasswordCountdown");
    public static int __PASSWORD_LENGTH = Main.inst.getConfig().getInt("passwordLength");
    public static String __DISCORD_WEBHOOK_URL = Main.inst.getConfig().getString("discordWebhookUrl");
}
