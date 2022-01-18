/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers;

import java.io.BufferedWriter;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.gui.click.elements.Frame;
import com.krazzzzymonkey.catalyst.gui.click.ClickGuiScreen;
import java.util.List;
import com.krazzzzymonkey.catalyst.value.Mode;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import com.google.gson.JsonElement;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.Modules;
import java.util.Iterator;
import java.io.IOException;
import com.krazzzzymonkey.catalyst.xray.XRayData;
import java.util.Map;
import com.google.gson.JsonObject;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import com.google.gson.JsonParser;
import java.io.File;
import com.google.gson.Gson;

public class FileManager
{
    public static Gson gsonPretty;
    public static File FRIENDS;
    public static File HACKS;
    public static JsonParser jsonParser;
    public static File CHATMENTION;
    public static File XRAYDATA;
    public static File CATALYST_DIR;
    public static File PREFIX;
    public static File ENEMYS;
    public static File CLICKGUI;
    public static File ALT_DIR;
    
    public static void loadXRayData() {
        IOException ex;
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(FileManager.XRAYDATA));
            final JsonObject jsonObject = (JsonObject)FileManager.jsonParser.parse((Reader)bufferedReader);
            bufferedReader.close();
            for (final Map.Entry<K, JsonObject> entry : jsonObject.entrySet()) {
                final JsonObject jsonObject2 = entry.getValue();
                final String[] split = ((String)entry.getKey()).split(":");
                XRayManager.addData(new XRayData(Integer.parseInt(split[0]), Integer.parseInt(split[1]), jsonObject2.get("red").getAsInt(), jsonObject2.get("green").getAsInt(), jsonObject2.get("blue").getAsInt()));
            }
            return;
        }
        catch (IOException ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    public static void saveHacks() {
        IOException ex;
        try {
            final JsonObject jsonObject = new JsonObject();
            for (final Modules modules : ModuleManager.getModules()) {
                final JsonObject jsonObject2 = new JsonObject();
                jsonObject2.addProperty("toggled", Boolean.valueOf(modules.isToggled()));
                jsonObject2.addProperty("key", (Number)modules.getKey());
                if (!modules.getValues().isEmpty()) {
                    for (final Value value : modules.getValues()) {
                        if (value instanceof BooleanValue) {
                            jsonObject2.addProperty(value.getName(), (Boolean)value.getValue());
                        }
                        if (value instanceof IntegerValue) {
                            jsonObject2.addProperty(value.getName(), (Number)value.getValue());
                        }
                        if (value instanceof DoubleValue) {
                            jsonObject2.addProperty(value.getName(), (Number)value.getValue());
                        }
                        if (value instanceof ColorValue) {
                            jsonObject2.addProperty(value.getName(), (Number)((ColorValue)value).getColorInt());
                        }
                        if (value instanceof com.krazzzzymonkey.catalyst.value.types.Number) {
                            jsonObject2.addProperty(value.getName(), (Number)value.getValue());
                        }
                        if (value instanceof ModeValue) {
                            for (final Mode mode : ((ModeValue)value).getModes()) {
                                jsonObject2.addProperty(mode.getName(), Boolean.valueOf(mode.isToggled()));
                            }
                        }
                    }
                }
                jsonObject.add(modules.getName(), (JsonElement)jsonObject2);
            }
            final PrintWriter printWriter = new PrintWriter(new FileWriter(FileManager.HACKS));
            printWriter.println(FileManager.gsonPretty.toJson((JsonElement)jsonObject));
            printWriter.close();
            return;
        }
        catch (IOException ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    public static void saveChatMention() {
        write(FileManager.CHATMENTION, ChatMentionManager.mentionList, true, true);
    }
    
    public static void loadChatMentions() {
        final Iterator<String> iterator = read(FileManager.CHATMENTION).iterator();
        while (iterator.hasNext()) {
            ChatMentionManager.addMention(iterator.next());
        }
    }
    
    public static void loadClickGui() {
        Exception ex;
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(FileManager.CLICKGUI));
            final JsonObject jsonObject = (JsonObject)FileManager.jsonParser.parse((Reader)bufferedReader);
            bufferedReader.close();
            for (final Map.Entry<K, JsonObject> entry : jsonObject.entrySet()) {
                final JsonObject jsonObject2 = entry.getValue();
                final String anObject = (String)entry.getKey();
                final int asInt = jsonObject2.get("posX").getAsInt();
                final int asInt2 = jsonObject2.get("posY").getAsInt();
                final boolean asBoolean = jsonObject2.get("maximized").getAsBoolean();
                for (final Frame frame : ClickGuiScreen.clickGui.getFrames()) {
                    if (frame.getText().equals(anObject)) {
                        frame.setxPos(asInt);
                        frame.setyPos(asInt2);
                        frame.setMaximized(asBoolean);
                    }
                }
            }
            return;
        }
        catch (Exception ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    public static void loadFriends() {
        final Iterator<String> iterator = read(FileManager.FRIENDS).iterator();
        while (iterator.hasNext()) {
            FriendManager.addFriend(iterator.next());
        }
    }
    
    public static void loadHacks() {
        IOException ex;
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(FileManager.HACKS));
            final JsonObject jsonObject = (JsonObject)FileManager.jsonParser.parse((Reader)bufferedReader);
            bufferedReader.close();
            for (final Map.Entry<String, V> entry : jsonObject.entrySet()) {
                final Modules module = ModuleManager.getModule(entry.getKey());
                if (module != null) {
                    final JsonObject jsonObject2 = (JsonObject)entry.getValue();
                    if (jsonObject2.get("toggled").getAsBoolean()) {
                        module.setToggled(true);
                    }
                    if (!module.getValues().isEmpty()) {
                        for (final Value value : module.getValues()) {
                            try {
                                if (value instanceof BooleanValue) {
                                    value.setValue(jsonObject2.get(value.getName()).getAsBoolean());
                                }
                                if (value instanceof DoubleValue) {
                                    value.setValue(jsonObject2.get(value.getName()).getAsDouble());
                                }
                                Label_0678: {
                                    if (value instanceof IntegerValue) {
                                        Value value2;
                                        Integer value3;
                                        try {
                                            value.setValue(jsonObject2.get(value.getName()).getAsBigInteger());
                                            break Label_0678;
                                        }
                                        catch (NumberFormatException ex3) {
                                            final double asDouble = jsonObject2.get(value.getName()).getAsDouble();
                                            value2 = value;
                                            value3 = (int)asDouble;
                                        }
                                        value2.setValue(value3);
                                    }
                                }
                                if (value instanceof ColorValue) {
                                    value.setValue(jsonObject2.get(value.getName()).getAsInt());
                                }
                                if (value instanceof com.krazzzzymonkey.catalyst.value.types.Number) {
                                    value.setValue(jsonObject2.get(value.getName()).getAsInt());
                                }
                                if (!(value instanceof ModeValue)) {
                                    continue;
                                }
                                for (final Mode mode : ((ModeValue)value).getModes()) {
                                    mode.setToggled(jsonObject2.get(mode.getName()).getAsBoolean());
                                }
                                continue;
                            }
                            catch (NullPointerException ex4) {
                                Main.logger.warn("Unknown Config for: " + module.getName() + ". Setting Default config!");
                            }
                            saveHacks();
                        }
                    }
                    module.setKey(jsonObject2.get("key").getAsInt());
                }
            }
            return;
        }
        catch (IOException ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    public static void savePrefix() {
        IOException ex;
        try {
            final JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("prefix", CommandManager.prefix);
            final PrintWriter printWriter = new PrintWriter(new FileWriter(FileManager.PREFIX));
            printWriter.println(FileManager.gsonPretty.toJson((JsonElement)jsonObject));
            printWriter.close();
            return;
        }
        catch (IOException ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    public static void saveEnemys() {
        write(FileManager.ENEMYS, EnemyManager.enemysList, true, true);
    }
    
    public static List read(final File file) {
        final ArrayList<String> list = new ArrayList<String>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        }
        catch (Exception ex) {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
            catch (Exception ex2) {}
        }
        return list;
    }
    
    public static void saveXRayData() {
        IOException ex;
        try {
            final JsonObject jsonObject = new JsonObject();
            for (final XRayData xRayData : XRayManager.xrayList) {
                final JsonObject jsonObject2 = new JsonObject();
                jsonObject2.addProperty("red", (Number)xRayData.getRed());
                jsonObject2.addProperty("green", (Number)xRayData.getGreen());
                jsonObject2.addProperty("blue", (Number)xRayData.getBlue());
                jsonObject.add("" + xRayData.getId() + ":" + xRayData.getMeta(), (JsonElement)jsonObject2);
            }
            final PrintWriter printWriter = new PrintWriter(new FileWriter(FileManager.XRAYDATA));
            printWriter.println(FileManager.gsonPretty.toJson((JsonElement)jsonObject));
            printWriter.close();
            return;
        }
        catch (IOException ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    public static void loadPrefix() {
        Exception ex;
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(FileManager.PREFIX));
            final JsonObject jsonObject = (JsonObject)FileManager.jsonParser.parse((Reader)bufferedReader);
            bufferedReader.close();
            CommandManager.prefix = jsonObject.get("prefix").getAsString();
            return;
        }
        catch (Exception ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    static {
        FileManager.gsonPretty = new GsonBuilder().setPrettyPrinting().create();
        FileManager.jsonParser = new JsonParser();
        FileManager.CATALYST_DIR = new File(String.format("%s%s%s%s", Wrapper.INSTANCE.mc().mcDataDir, File.separator, "Catalyst", File.separator));
        FileManager.ALT_DIR = new File(FileManager.CATALYST_DIR + "/Catalyst Account Manager/");
        FileManager.CLICKGUI = new File(FileManager.CATALYST_DIR, "clickgui.json");
        FileManager.HACKS = new File(FileManager.CATALYST_DIR, "hacks.json");
        FileManager.CHATMENTION = new File(FileManager.CATALYST_DIR, "chatmention.json");
        FileManager.XRAYDATA = new File(FileManager.CATALYST_DIR, "xraydata.json");
        FileManager.FRIENDS = new File(FileManager.CATALYST_DIR, "friends.json");
        FileManager.ENEMYS = new File(FileManager.CATALYST_DIR, "enemys.json");
        FileManager.PREFIX = new File(FileManager.CATALYST_DIR, "prefix.json");
    }
    
    public static void loadEnemys() {
        final Iterator<String> iterator = read(FileManager.ENEMYS).iterator();
        while (iterator.hasNext()) {
            EnemyManager.addEnemy(iterator.next());
        }
    }
    
    public static void write(final File file, final List list, final boolean b, final boolean b2) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file, !b2));
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                writer.write(iterator.next());
                ((BufferedWriter)writer).flush();
                if (b) {
                    ((BufferedWriter)writer).newLine();
                }
            }
        }
        catch (Exception ex) {
            try {
                if (writer != null) {
                    ((BufferedWriter)writer).close();
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public static void saveClickGui() {
        Exception ex;
        try {
            final JsonObject jsonObject = new JsonObject();
            for (final Frame frame : ClickGuiScreen.clickGui.getFrames()) {
                final JsonObject jsonObject2 = new JsonObject();
                jsonObject2.addProperty("posX", (Number)frame.getX());
                jsonObject2.addProperty("posY", (Number)frame.getY());
                jsonObject2.addProperty("maximized", Boolean.valueOf(frame.isMaximized()));
                jsonObject.add(frame.getText(), (JsonElement)jsonObject2);
            }
            final PrintWriter printWriter = new PrintWriter(new FileWriter(FileManager.CLICKGUI));
            printWriter.println(FileManager.gsonPretty.toJson((JsonElement)jsonObject));
            printWriter.close();
            return;
        }
        catch (Exception ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    public static void saveFriends() {
        write(FileManager.FRIENDS, FriendManager.friendsList, true, true);
    }
    
    public FileManager() {
        if (!FileManager.CATALYST_DIR.exists()) {
            FileManager.CATALYST_DIR.mkdir();
        }
        if (!FileManager.ALT_DIR.exists()) {
            FileManager.ALT_DIR.mkdir();
        }
        if (!FileManager.HACKS.exists()) {
            saveHacks();
        }
        else {
            loadHacks();
        }
        if (!FileManager.CHATMENTION.exists()) {
            saveChatMention();
        }
        else {
            loadChatMentions();
        }
        if (!FileManager.XRAYDATA.exists()) {
            saveXRayData();
        }
        else {
            loadXRayData();
        }
        if (!FileManager.FRIENDS.exists()) {
            saveFriends();
        }
        else {
            loadFriends();
        }
        if (!FileManager.ENEMYS.exists()) {
            saveEnemys();
        }
        else {
            loadEnemys();
        }
        if (!FileManager.PREFIX.exists()) {
            savePrefix();
        }
        else {
            loadPrefix();
        }
    }
}
