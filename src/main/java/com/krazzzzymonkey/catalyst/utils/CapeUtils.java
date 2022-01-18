/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import com.krazzzzymonkey.catalyst.Main;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.UUID;
import java.util.HashMap;
import net.minecraft.util.ResourceLocation;
import java.util.List;

public class CapeUtils
{
    public static List<ResourceLocation> capeResourceLocation;
    public static int uuidAmount;
    public static HashMap<String, String> capeUsers;
    
    public static void lambda$getUsersCape$0(final String key) {
        CapeUtils.capeUsers.put(key, "User");
    }
    
    public static String removeDash(final UUID uuid) {
        return removeDash(uuid.toString());
    }
    
    public static void lambda$getUsersCape$2(final String key) {
        CapeUtils.capeUsers.put(key, "Dev");
    }
    
    public void getUsersCape() {
        this.getFromCatalystDatabase("https://raw.githubusercontent.com/Krazzzzymonkey/catalyst-website/master/capes/users.html", "User").forEach(CapeUtils::lambda$getUsersCape$0);
        this.getFromCatalystDatabase("https://raw.githubusercontent.com/Krazzzzymonkey/catalyst-website/master/capes/ogusers.html", "OG User").forEach(CapeUtils::lambda$getUsersCape$1);
        this.getFromCatalystDatabase("https://raw.githubusercontent.com/Krazzzzymonkey/catalyst-website/master/capes/developers.html", "Developer").forEach(CapeUtils::lambda$getUsersCape$2);
    }
    
    public static boolean isDeveloper(final UUID uuid) {
        return isCapeUser(uuid) && CapeUtils.capeUsers.get(removeDash(uuid)).equals("Dev");
    }
    
    public static void lambda$getUsersCape$1(final String key) {
        CapeUtils.capeUsers.put(key, "OG");
    }
    
    public CapeUtils() {
        CapeUtils.capeUsers = new HashMap<String, String>();
    }
    
    public static boolean isOGCapeUser(final UUID uuid) {
        return isCapeUser(uuid) && CapeUtils.capeUsers.get(removeDash(uuid)).equals("OG");
    }
    
    public static boolean isCapeUser(final UUID uuid) {
        return CapeUtils.capeUsers.containsKey(removeDash(uuid));
    }
    
    public static String removeDash(final String s) {
        return s.replaceAll("-", "").toLowerCase();
    }
    
    public static boolean isUser(final UUID uuid) {
        if (isCapeUser(uuid)) {
            if (CapeUtils.capeUsers.get(removeDash(uuid)).equals("User")) {
                return true;
            }
        }
        return false;
    }
    
    static {
        CapeUtils.uuidAmount = 0;
        CapeUtils.capeResourceLocation = new ArrayList<ResourceLocation>();
        try {
            CapeUtils.capeResourceLocation.add(new ResourceLocation("catalyst/capes/cape0.png"));
            CapeUtils.capeResourceLocation.add(new ResourceLocation("catalyst/capes/cape1.png"));
            CapeUtils.capeResourceLocation.add(new ResourceLocation("catalyst/capes/cape2.png"));
            Main.logger.info("Successfully fetched and loaded " + CapeUtils.capeResourceLocation.size() + " Catalyst Cape textures!");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public List getFromCatalystDatabase(final String spec, final String str) {
        URL url;
        try {
            url = new URL(spec);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
            return new ArrayList();
        }
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
            return new ArrayList();
        }
        final ArrayList<String> list = new ArrayList<String>();
        while (true) {
            String line;
            try {
                if ((line = bufferedReader.readLine()) == null) {
                    break;
                }
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
                return new ArrayList();
            }
            if (!line.equals("<html><head></head><body><pre style=\"word-wrap: break-word; white-space: pre-wrap;\">") && !line.equals("</pre></body></html>")) {
                list.add(removeDash(line));
                ++CapeUtils.uuidAmount;
            }
        }
        try {
            bufferedReader.close();
            Main.logger.info("Successfully fetched " + CapeUtils.uuidAmount + " Catalyst " + str + " UUID's!");
            CapeUtils.uuidAmount = 0;
        }
        catch (IOException ex4) {
            ex4.printStackTrace();
            return new ArrayList();
        }
        return list;
    }
}
