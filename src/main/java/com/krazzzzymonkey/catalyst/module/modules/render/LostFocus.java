/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.Display;
import net.minecraftforge.fml.common.gameevent.TickEvent$Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.module.Modules;

public class LostFocus extends Modules
{
    public int maxFpsActive;
    public static int maxFpsInactive;
    
    @Override
    public void onEnable() {
        this.maxFpsActive = LostFocus.mc.gameSettings.limitFramerate;
    }
    
    static {
        LostFocus.maxFpsInactive = 1;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (LostFocus.mc.world == null) {
            return;
        }
        if (tickEvent$ClientTickEvent.phase == TickEvent$Phase.START) {
            return;
        }
        if (!Display.isActive() && LostFocus.mc.gameSettings.limitFramerate != 1) {
            this.maxFpsActive = LostFocus.mc.gameSettings.limitFramerate;
            LostFocus.mc.gameSettings.limitFramerate = 1;
        }
        else if (LostFocus.mc.gameSettings.limitFramerate == 1) {
            LostFocus.mc.gameSettings.limitFramerate = this.maxFpsActive;
        }
    }
    
    public LostFocus() {
        super("LostFocus", ModuleCategory.RENDER, "Slow rendering while window focus is lost");
    }
}
