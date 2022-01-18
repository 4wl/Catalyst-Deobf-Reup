/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.mixin.client;

import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import com.krazzzzymonkey.catalyst.events.KeyDownEvent;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Minecraft.class })
public class MixinMinecraft
{
    @Inject(method = { "processKeyBinds" }, at = { @At("HEAD") })
    public void processKeyBinds(final CallbackInfo callbackInfo) {
        if (Minecraft.getMinecraft().player == null) {
            return;
        }
    }
    
    @Inject(method = { "dispatchKeypresses" }, at = { @At("HEAD") })
    public void onDispatchKeypresses(final CallbackInfo callbackInfo) {
        final int i = (Keyboard.getEventKey() == 0) ? (Keyboard.getEventCharacter() + '\u0100') : Keyboard.getEventKey();
        if (Keyboard.getEventKeyState()) {
            final KeyDownEvent e = new KeyDownEvent(i);
            MinecraftForge.EVENT_BUS.post((Event)e);
        }
    }
}
