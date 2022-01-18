/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.module.Modules;

public class FullBright extends Modules
{
    @Override
    public void onEnable() {
        super.onEnable();
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        Wrapper.INSTANCE.mcSettings().gammaSetting = 20.0f;
    }
    
    public FullBright() {
        super("FullBright", ModuleCategory.RENDER, "Allows you to see in the dark");
    }
    
    @Override
    public void onDisable() {
        Wrapper.INSTANCE.mcSettings().gammaSetting = 1.0f;
        super.onDisable();
    }
}
