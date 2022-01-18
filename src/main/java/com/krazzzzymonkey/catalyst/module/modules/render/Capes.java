/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Capes extends Modules
{
    public static Boolean enabled;
    public boolean firstrun;
    
    static {
        Capes.enabled = false;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (this.firstrun) {
            Capes.enabled = true;
            this.firstrun = false;
        }
    }
    
    public Capes() {
        super("Capes", ModuleCategory.RENDER, "Renders custom Catalyst capes");
        this.firstrun = true;
    }
    
    @Override
    public void onEnable() {
        Capes.enabled = true;
        super.onEnable();
    }
    
    @Override
    public void onDisable() {
        Capes.enabled = false;
        super.onDisable();
    }
}
