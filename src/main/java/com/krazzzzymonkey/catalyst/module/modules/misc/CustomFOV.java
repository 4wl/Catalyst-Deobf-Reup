/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class CustomFOV extends Modules
{
    public IntegerValue Fov;
    public float normalFOV;
    
    @Override
    public void onEnable() {
        this.normalFOV = Minecraft.getMinecraft().gameSettings.fovSetting;
        super.onEnable();
    }
    
    public CustomFOV() {
        super("CustomFOV", ModuleCategory.MISC, "Set a custom field of view");
        this.normalFOV = 80.0f;
        this.Fov = new IntegerValue("FOV", 60, 1, 155);
        this.addValue(this.Fov);
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world != null) {
            if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.getUniqueID() != null) {
                Minecraft.getMinecraft().gameSettings.fovSetting = this.Fov.getValue();
            }
        }
    }
    
    @Override
    public void onDisable() {
        Minecraft.getMinecraft().gameSettings.fovSetting = this.normalFOV;
        super.onDisable();
    }
}
