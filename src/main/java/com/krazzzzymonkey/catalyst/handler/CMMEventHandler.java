/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.handler;

import net.minecraftforge.client.event.GuiScreenEvent;
import java.util.List;
import java.util.ArrayList;
import net.minecraftforge.fml.common.gameevent.TickEvent$Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import java.util.Iterator;
import org.apache.logging.log4j.Level;
import com.krazzzzymonkey.catalyst.Main;
import net.minecraftforge.fml.common.Loader;
import com.krazzzzymonkey.catalyst.gui.GuiCustomButton;
import net.minecraft.client.gui.GuiButton;
import java.util.HashMap;
import com.krazzzzymonkey.catalyst.gui.GuiFakeMain;
import net.minecraftforge.client.event.GuiScreenEvent$InitGuiEvent$Post;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Post;
import java.lang.reflect.Field;
import com.krazzzzymonkey.catalyst.gui.GuiCustom;

public class CMMEventHandler
{
    public GuiCustom actualGui;
    public Field guiField;
    public GuiCustom customMainMenu;
    public long displayMs;
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void renderScreenPost(final GuiScreenEvent$DrawScreenEvent$Post guiScreenEvent$DrawScreenEvent$Post) {
        if (ModuleManager.getModule("CustomMainMenu").isToggled() && this.displayMs != -1L) {
            if (System.currentTimeMillis() - this.displayMs < 5000L) {
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("Error loading config file, see console for more information", 0.0f, 80.0f, 16711680);
            }
            else {
                this.displayMs = -1L;
            }
        }
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void initGuiPost(final GuiScreenEvent$InitGuiEvent$Post guiScreenEvent$InitGuiEvent$Post) {
        if (ModuleManager.getModule("CustomMainMenu").isToggled() && guiScreenEvent$InitGuiEvent$Post.getGui() instanceof GuiFakeMain) {
            final GuiFakeMain guiFakeMain = (GuiFakeMain)guiScreenEvent$InitGuiEvent$Post.getGui();
            final HashMap<Integer, GuiButton> hashMap = new HashMap<Integer, GuiButton>();
            final Iterator iterator = guiScreenEvent$InitGuiEvent$Post.getButtonList().iterator();
            while (iterator.hasNext()) {
                final GuiButton value = iterator.next();
                if (!(value instanceof GuiCustomButton)) {
                    iterator.remove();
                    hashMap.put(value.id, value);
                    if (value.id == 666 && Loader.isModLoaded("OpenEye")) {
                        Main.logger.log(Level.DEBUG, "Found OpenEye button, use a wrapped button to config this. (" + value.id + ")");
                    }
                    else if (value.id == 404 && Loader.isModLoaded("VersionChecker")) {
                        Main.logger.log(Level.DEBUG, "Found VersionChecker button, use a wrapped button to config this. (" + value.id + ")");
                    }
                    else {
                        Main.logger.log(Level.DEBUG, "Found unsupported button, use a wrapped button to config this. (" + value.id + ")");
                    }
                }
            }
        }
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void openGui(final GuiOpenEvent guiOpenEvent) {
        if (ModuleManager.getModule("CustomMainMenu").isToggled()) {
            if (guiOpenEvent.getGui() instanceof GuiMainMenu) {
                this.customMainMenu = Main.config.getGUI("mainmenu");
                if (this.customMainMenu != null) {
                    guiOpenEvent.setGui((GuiScreen)this.customMainMenu);
                }
            }
            else if (guiOpenEvent.getGui() instanceof GuiCustom) {
                final GuiCustom guiCustom = (GuiCustom)guiOpenEvent.getGui();
                final GuiCustom gui = Main.config.getGUI(guiCustom.guiConfig.name);
                if (gui != guiCustom) {
                    guiOpenEvent.setGui((GuiScreen)gui);
                }
            }
        }
    }
    
    @SubscribeEvent
    public void tick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (ModuleManager.getModule("CustomMainMenu").isToggled() && tickEvent$ClientTickEvent.phase == TickEvent$Phase.END && Main.config != null) {
            Main.config.tick();
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void initGuiPostEarly(final GuiScreenEvent$InitGuiEvent$Post obj) {
        if (ModuleManager.getModule("CustomMainMenu").isToggled() && obj.getGui() instanceof GuiCustom) {
            final GuiCustom actualGui = (GuiCustom)obj.getGui();
            if (actualGui.guiConfig.name.equals("mainmenu")) {
                obj.setButtonList((List)new ArrayList());
                this.actualGui = actualGui;
                Throwable t;
                try {
                    this.guiField.set(obj, new GuiFakeMain());
                    return;
                }
                catch (IllegalArgumentException | IllegalAccessException ex) {
                    final Object o;
                    t = (Throwable)o;
                }
                t.printStackTrace();
            }
        }
    }
    
    public CMMEventHandler() {
        this.displayMs = -1L;
        try {
            (this.guiField = GuiScreenEvent.class.getDeclaredField("gui")).setAccessible(true);
        }
        catch (NoSuchFieldException | SecurityException ex) {
            final Throwable t;
            t.printStackTrace();
        }
    }
}
