/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.module.Modules;

public class AcidMode extends Modules
{
    public static boolean firstRun;
    
    static {
        AcidMode.firstRun = true;
    }
    
    public AcidMode() {
        super("AcidMode", ModuleCategory.RENDER, "Renders acid shader over game");
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent renderGameOverlayEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (OpenGlHelper.shadersSupported) {
            if (Minecraft.getMinecraft().currentScreen != null) {
                AcidMode.firstRun = true;
            }
            if (Minecraft.getMinecraft().currentScreen == null && AcidMode.firstRun) {
                Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("shaders/post/wobble.json"));
                AcidMode.firstRun = false;
            }
        }
    }
    
    @Override
    public void onDisable() {
        if (Minecraft.getMinecraft().entityRenderer.getShaderGroup() != null) {
            Minecraft.getMinecraft().entityRenderer.getShaderGroup().deleteShaderGroup();
        }
        AcidMode.firstRun = true;
        super.onDisable();
    }
}
