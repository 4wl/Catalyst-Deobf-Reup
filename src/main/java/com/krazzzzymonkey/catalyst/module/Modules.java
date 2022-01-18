/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module;

import com.krazzzzymonkey.catalyst.utils.system.Connection$Side;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.gui.click.ClickGuiScreen;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.ChatColor;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import com.krazzzzymonkey.catalyst.value.Mode;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import net.minecraftforge.common.MinecraftForge;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import com.krazzzzymonkey.catalyst.value.Value;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;

public class Modules
{
    public ModuleCategory category;
    public String description;
    public int key;
    public String name;
    public BooleanValue drawn;
    public static Minecraft mc;
    public boolean toggled;
    public ArrayList<Value> values;
    public boolean ignoreDrawn;
    
    public Modules(final String name, final ModuleCategory category, final String description, final boolean ignoreDrawn) {
        this.drawn = new BooleanValue("Drawn", true);
        this.values = new ArrayList<Value>();
        this.name = name;
        this.category = category;
        this.description = description;
        this.toggled = false;
        this.key = -1;
        this.ignoreDrawn = ignoreDrawn;
    }
    
    public ArrayList getValues() {
        return this.values;
    }
    
    public void debug(final float f) {
        ChatUtils.message("Debug: " + f);
    }
    
    public Modules(final String name, final ModuleCategory category, final String description) {
        this.drawn = new BooleanValue("Drawn", true);
        this.values = new ArrayList<Value>();
        this.name = name;
        this.category = category;
        this.description = description;
        this.toggled = false;
        this.key = -1;
        this.ignoreDrawn = false;
        this.addValue(this.drawn);
    }
    
    public void setCategory(final ModuleCategory category) {
        this.category = category;
    }
    
    public void setToggled(final boolean toggled) {
        this.toggled = toggled;
        if (toggled) {
            MinecraftForge.EVENT_BUS.register((Object)this);
        }
        else {
            MinecraftForge.EVENT_BUS.unregister((Object)this);
        }
    }
    
    public void debug(final int i) {
        ChatUtils.message("Debug: " + i);
    }
    
    public int getKey() {
        return this.key;
    }
    
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public boolean isToggledMode(final String anotherString) {
        for (final Value value : this.values) {
            if (value instanceof ModeValue) {
                for (final Mode mode : ((ModeValue)value).getModes()) {
                    if (mode.getName().equalsIgnoreCase(anotherString) && mode.isToggled()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean isToggled() {
        return this.toggled;
    }
    
    public void setKey(final int key) {
        this.key = key;
    }
    
    public void setValues(final ArrayList list) {
        for (final Value value : list) {
            for (final Value value2 : this.values) {
                if (value.getName().equalsIgnoreCase(value2.getName())) {
                    value2.setValue(value.getValue());
                }
            }
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public void debug() {
        ChatUtils.message("Debug: 0");
    }
    
    public void toggle() {
        this.toggled = !this.toggled;
        if (this.toggled) {
            this.onEnable();
            if (ModuleManager.getModule("ToggleMessages").isToggled() && Modules.mc.player != null) {
                ChatUtils.message(ChatColor.GRAY + this.getName() + ChatColor.DARK_GREEN + " ON");
            }
        }
        else {
            this.onDisable();
            if (ModuleManager.getModule("ToggleMessages").isToggled() && Modules.mc.player != null) {
                ChatUtils.message(ChatColor.GRAY + this.getName() + ChatColor.DARK_RED + " OFF");
            }
        }
        RenderUtils.splashTickPos = 0;
        if (!RenderUtils.isSplash) {
            if (!(Wrapper.INSTANCE.mc().currentScreen instanceof ClickGuiScreen)) {
                RenderUtils.isSplash = true;
            }
        }
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    static {
        Modules.mc = Minecraft.getMinecraft();
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public boolean isToggledValue(final String anotherString) {
        for (final Value value : this.values) {
            if (value instanceof BooleanValue) {
                final BooleanValue booleanValue = (BooleanValue)value;
                if (booleanValue.getName().equalsIgnoreCase(anotherString) && (boolean)booleanValue.getValue()) {
                    return true;
                }
                continue;
            }
        }
        return false;
    }
    
    public void setDrawn(final boolean b) {
        if (this.ignoreDrawn) {
            return;
        }
        this.drawn.setValue(b);
    }
    
    public ModuleCategory getCategory() {
        return this.category;
    }
    
    public void debug(final double d) {
        ChatUtils.message("Debug: " + d);
    }
    
    public void debug(final long lng) {
        ChatUtils.message("Debug: " + lng);
    }
    
    public boolean onPacket(final Object o, final Connection$Side connection$Side) {
        return true;
    }
    
    public boolean isDrawn() {
        return !this.ignoreDrawn && (boolean)this.drawn.getValue();
    }
    
    public void addValue(final Value... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            this.getValues().add(array[i]);
        }
    }
}
