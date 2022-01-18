/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import com.google.gson.Gson;

public class NameHistory extends Command implements Runnable
{
    public String name;
    public Gson gson;
    
    public NameHistory() {
        super("namehistory");
        this.name = "";
        this.gson = new Gson();
    }
    
    @Override
    public void runCommand(final String s, final String[] array) {
        this.name = array[0];
        new Thread(this).start();
    }
    
    @Override
    public String getSyntax() {
        return "namehistory <Player>";
    }
    
    @Override
    public void run() {
        ChatUtils.message("Collecting Data, this may take some time");
        Exception ex;
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL("https://api.mojang.com/users/profiles/minecraft/" + this.name).openConnection().getInputStream()));
            final StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            if (sb.toString().equalsIgnoreCase("")) {
                ChatUtils.error("Player Not Found");
                return;
            }
            final String s = sb.toString().split(",")[1].split("\":\"")[1].split("\"")[0];
            final StringBuilder append = new StringBuilder().append("Fetching ").append(this.name);
            while (true) {
                switch (-50525590 + 1639296896 + 1) {
                    case -1045002928: {
                        continue;
                    }
                    default: {
                        ChatUtils.message(append.append("'s UUID: ").append(s).toString());
                        final BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new URL("https://api.mojang.com/user/profiles/" + s + "/names").openConnection().getInputStream()));
                        final StringBuilder sb2 = new StringBuilder();
                        String line2;
                        while ((line2 = bufferedReader2.readLine()) != null) {
                            sb2.append(line2);
                        }
                        final StringBuilder sb3 = new StringBuilder();
                        final JsonArray asJsonArray = new JsonParser().parse(sb2.toString()).getAsJsonArray();
                        sb3.append("Player " + this.name + " used the following names in the past: ");
                        for (int i = 0; i < asJsonArray.size(); ++i) {
                            sb3.append(asJsonArray.get(i).getAsJsonObject().get("name").toString() + ", ");
                        }
                        ChatUtils.message(sb3.toString());
                        return;
                    }
                    case -1518884653: {
                        throw null;
                    }
                }
            }
        }
        catch (Exception ex2) {
            ChatUtils.error("Something went wrong");
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    @Override
    public String getDescription() {
        return "Shows you the former names of a Player";
    }
}
