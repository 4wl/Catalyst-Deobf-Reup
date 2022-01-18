/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.configuration;

import java.util.function.Consumer;
import com.krazzzzymonkey.catalyst.gui.GuiCustom;
import java.util.HashMap;

public class Config
{
    public HashMap<String, GuiEntry> guis;
    
    public Config() {
        this.guis = new HashMap<String, GuiEntry>();
    }
    
    public void addGui(final String s, final GuiCustom large) {
        GuiEntry value = this.guis.get(s);
        if (value == null) {
            value = new GuiEntry();
            this.guis.put(s, value);
        }
        final int guiScale = large.guiConfig.guiScale;
        if (guiScale == -1) {
            value.standard = large;
        }
        else if (guiScale == 0) {
            value.auto = large;
        }
        else if (guiScale == 1) {
            value.small = large;
        }
        else if (guiScale == 2) {
            value.normal = large;
        }
        else if (guiScale == 3) {
            value.large = large;
        }
    }
    
    public void tick() {
        this.guis.values().forEach(Config::lambda$tick$0);
    }
    
    public GuiCustom getGUI(final String key) {
        return this.guis.get(key).getCurrentGUI();
    }
    
    public static void lambda$tick$0(final GuiEntry guiEntry) {
        guiEntry.getCurrentGUI().tick();
    }
}
