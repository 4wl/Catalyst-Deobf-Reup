/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.gui.GuiChat;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Text;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.module.Modules;

public class InventoryWalk extends Modules
{
    public void pitch(final float rotationPitch) {
        InventoryWalk.mc.player.rotationPitch = rotationPitch;
    }
    
    public void yaw(final float rotationYaw) {
        InventoryWalk.mc.player.rotationYaw = rotationYaw;
    }
    
    public InventoryWalk() {
        super("InventoryWalk", ModuleCategory.MOVEMENT, "Allows you to move around while in a GUI screen");
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent$Text renderGameOverlayEvent$Text) {
        if (InventoryWalk.mc.currentScreen != null) {
            if (!(InventoryWalk.mc.currentScreen instanceof GuiChat)) {
                if (Keyboard.isKeyDown(200) && InventoryWalk.mc.player.rotationPitch > -90.0f) {
                    this.pitch(InventoryWalk.mc.player.rotationPitch - 2.0f);
                }
                if (Keyboard.isKeyDown(208) && InventoryWalk.mc.player.rotationPitch < 90.0f) {
                    this.pitch(InventoryWalk.mc.player.rotationPitch + 2.0f);
                }
                if (Keyboard.isKeyDown(203)) {
                    this.yaw(InventoryWalk.mc.player.rotationYaw - 3.0f);
                }
                if (Keyboard.isKeyDown(205)) {
                    this.yaw(InventoryWalk.mc.player.rotationYaw + 3.0f);
                }
            }
        }
    }
}
