/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.movement;

import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Flight extends Modules
{
    public int ticks;
    public ModeValue mode;
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world != null) {
            if (Minecraft.getMinecraft().player != null) {
                if (Minecraft.getMinecraft().player.getUniqueID() != null) {
                    final EntityPlayerSP player = Wrapper.INSTANCE.player();
                    if (this.mode.getMode("Hypixel").isToggled()) {
                        player.motionY = 0.0;
                        player.setSprinting(true);
                        player.onGround = true;
                        ++this.ticks;
                        Label_0387: {
                            if (this.ticks != 2 && this.ticks != 4 && this.ticks != 6 && this.ticks != 8 && this.ticks != 10) {
                                if (this.ticks != 12) {
                                    if (this.ticks != 14 && this.ticks != 16) {
                                        if (this.ticks != 18) {
                                            if (this.ticks != 20) {
                                                break Label_0387;
                                            }
                                        }
                                    }
                                }
                            }
                            player.setPosition(player.posX, player.posY + 1.28E-9, player.posZ);
                        }
                        if (this.ticks == 20) {
                            this.ticks = 0;
                        }
                    }
                    else if (this.mode.getMode("Simple").isToggled()) {
                        player.capabilities.isFlying = true;
                    }
                    else if (this.mode.getMode("Dynamic").isToggled()) {
                        final float n = 1.0f;
                        player.jumpMovementFactor = 0.4f;
                        player.motionX = 0.0;
                        player.motionY = 0.0;
                        player.motionZ = 0.0;
                        final EntityPlayerSP entityPlayerSP = player;
                        entityPlayerSP.jumpMovementFactor *= n * 3.0f;
                        if (Wrapper.INSTANCE.mcSettings().keyBindJump.isKeyDown()) {
                            final EntityPlayerSP entityPlayerSP2 = player;
                            entityPlayerSP2.motionY += n;
                        }
                        if (Wrapper.INSTANCE.mcSettings().keyBindSneak.isKeyDown()) {
                            final EntityPlayerSP entityPlayerSP3 = player;
                            entityPlayerSP3.motionY -= n;
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void onEnable() {
        this.ticks = 0;
        super.onEnable();
    }
    
    public Flight() {
        super("Flight", ModuleCategory.MOVEMENT, "Allows you to fly.");
        this.ticks = 0;
        this.mode = new ModeValue("Mode", new Mode[] { new Mode("Simple", true), new Mode("Dynamic", false), new Mode("Hypixel", false) });
    }
    
    @Override
    public void onDisable() {
        if (this.mode.getMode("Simple").isToggled()) {
            Wrapper.INSTANCE.player().capabilities.isFlying = false;
        }
        super.onDisable();
    }
}
