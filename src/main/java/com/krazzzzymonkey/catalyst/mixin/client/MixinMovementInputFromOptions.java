/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import org.lwjgl.input.Keyboard;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import net.minecraft.client.settings.KeyBinding;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInputFromOptions;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.util.MovementInput;

@Mixin({ MovementInputFromOptions.class })
public class MixinMovementInputFromOptions extends MovementInput
{
    @Shadow
    @Final
    private GameSettings gameSettings;
    
    @Overwrite
    public void updatePlayerMoveState() {
        this.moveStrafe = 0.0f;
        this.moveForward = 0.0f;
        if (this.gameSettings.keyBindForward.isKeyDown() || this.isKeyDown(this.gameSettings.keyBindForward)) {
            ++this.moveForward;
            this.forwardKeyDown = true;
        }
        else {
            this.forwardKeyDown = false;
        }
        if (this.gameSettings.keyBindBack.isKeyDown() || this.isKeyDown(this.gameSettings.keyBindBack)) {
            --this.moveForward;
            this.backKeyDown = true;
        }
        else {
            this.backKeyDown = false;
        }
        if (this.gameSettings.keyBindLeft.isKeyDown() || this.isKeyDown(this.gameSettings.keyBindLeft)) {
            ++this.moveStrafe;
            this.leftKeyDown = true;
        }
        else {
            this.leftKeyDown = false;
        }
        if (this.gameSettings.keyBindRight.isKeyDown() || this.isKeyDown(this.gameSettings.keyBindRight)) {
            --this.moveStrafe;
            this.rightKeyDown = true;
        }
        else {
            this.rightKeyDown = false;
        }
        this.jump = (this.gameSettings.keyBindJump.isKeyDown() || this.isKeyDown(this.gameSettings.keyBindJump));
        this.sneak = this.gameSettings.keyBindSneak.isKeyDown();
        if (this.sneak) {
            this.moveStrafe *= 0.3f;
            this.moveForward *= 0.3f;
        }
    }
    
    public boolean isKeyDown(final KeyBinding keyBinding) {
        if (ModuleManager.getModule("AutoWalk").isToggled() && keyBinding.equals(Minecraft.getMinecraft().gameSettings.keyBindForward)) {
            return true;
        }
        if ((ModuleManager.getModule("InventoryWalk").isToggled() && Minecraft.getMinecraft().currentScreen instanceof GuiChat) || (ModuleManager.getModule("InventoryWalk").isToggled() && Minecraft.getMinecraft().currentScreen instanceof GuiEditSign)) {
            return false;
        }
        if (ModuleManager.getModule("InventoryWalk").isToggled()) {
            return Keyboard.isKeyDown(keyBinding.getKeyCode());
        }
        return (!ModuleManager.getModule("ElytraFly").isToggled() || !keyBinding.equals(Minecraft.getMinecraft().gameSettings.keyBindJump)) && keyBinding.isKeyDown();
    }
}
