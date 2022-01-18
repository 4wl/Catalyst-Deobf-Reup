/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer$PositionRotation;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.util.math.MathHelper;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class PacketFly extends Modules
{
    public long lastMS;
    public ModeValue mode;
    public long currentMS;
    
    public boolean hasDelayRun(final long n) {
        return this.currentMS - this.lastMS >= n;
    }
    
    public static double getRelativeX(final float n) {
        return MathHelper.sin(-n * 0.017453292f);
    }
    
    public PacketFly() {
        super("PacketFly", ModuleCategory.MOVEMENT, "Allows you to fly with packets");
        this.currentMS = 0L;
        this.lastMS = -1L;
        this.mode = new ModeValue("Mode", new Mode[] { new Mode("Infinite", true), new Mode("Bypass", false), new Mode("Old", false) });
        this.addValue(this.mode);
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        Label_0106: {
            if (Minecraft.getMinecraft().world != null) {
                final Minecraft minecraft = Minecraft.getMinecraft();
                while (true) {
                    switch (-813816359 - 38383240 + 1) {
                        case 1377095450: {
                            continue;
                        }
                        default: {
                            if (minecraft.player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
                                break Label_0106;
                            }
                            Label_2643: {
                                if (this.mode.getMode("Infinite").isToggled()) {
                                    float n = 0.0f;
                                    float n2 = 0.0f;
                                    final double n3 = 2.8;
                                    final float sin = MathHelper.sin(Wrapper.INSTANCE.player().rotationYaw * 3.1415927f / 180.0f);
                                    final float cos = MathHelper.cos(Wrapper.INSTANCE.player().rotationYaw * 3.1415927f / 180.0f);
                                    if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown() && !Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown() && !Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown() && !Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown()) {
                                        n += 0.1f;
                                    }
                                    else if (!Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown() && Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown() && !Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown() && !Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown()) {
                                        n -= 0.1f;
                                    }
                                    else if (!Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown() && !Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown() && Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown() && !Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown()) {
                                        n2 += 0.1f;
                                    }
                                    Label_1514: {
                                        if (!Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown() && !Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown() && !Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown() && Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown()) {
                                            n2 -= 0.1f;
                                        }
                                        else if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown() && !Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown() && Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown() && !Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown()) {
                                            n += 0.0624f;
                                            n2 += 0.0624f;
                                        }
                                        else {
                                            if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown()) {
                                                if (!Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown()) {
                                                    if (!Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown() && Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown()) {
                                                        n += 0.0624f;
                                                        n2 -= 0.0624f;
                                                        break Label_1514;
                                                    }
                                                }
                                            }
                                            Label_1356: {
                                                if (!Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown()) {
                                                    if (Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown()) {
                                                        final Minecraft minecraft2 = Minecraft.getMinecraft();
                                                        while (true) {
                                                            switch (1606976013 + 989356728 + 1) {
                                                                case -182841278: {
                                                                    continue;
                                                                }
                                                                default: {
                                                                    if (minecraft2.gameSettings.keyBindLeft.isKeyDown() && !Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown()) {
                                                                        n -= 0.0624f;
                                                                        n2 += 0.0624f;
                                                                        break Label_1514;
                                                                    }
                                                                    break Label_1356;
                                                                }
                                                                case -1141851275: {
                                                                    throw null;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            if (!Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown() && Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown() && !Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown() && Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown()) {
                                                n -= 0.0624f;
                                                n2 -= 0.0624f;
                                            }
                                        }
                                    }
                                    final double n4 = (n2 * cos - n * sin) * n3;
                                    final double n5 = (n * cos + n2 * sin) * n3;
                                    final double n6 = (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown() ? 0.0624 : 0.0) - (Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown() ? 0.0624 : 0.0);
                                    this.setCurrentMS();
                                    Wrapper.INSTANCE.player().motionY = 0.0;
                                    if (this.hasDelayRun(500L) && !Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown()) {
                                        Wrapper.INSTANCE.player().connection.sendPacket((Packet)new CPacketPlayer$PositionRotation(Wrapper.INSTANCE.player().posX, Wrapper.INSTANCE.player().posY - 0.0624, Wrapper.INSTANCE.player().posZ, Wrapper.INSTANCE.player().rotationYaw, Wrapper.INSTANCE.player().rotationPitch, false));
                                        Wrapper.INSTANCE.player().connection.sendPacket((Packet)new CPacketPlayer$PositionRotation(Wrapper.INSTANCE.player().posX, Wrapper.INSTANCE.player().posY - 999.0, Wrapper.INSTANCE.player().posZ, Wrapper.INSTANCE.player().rotationYaw, Wrapper.INSTANCE.player().rotationPitch, true));
                                        this.setLastMS();
                                        return;
                                    }
                                    Label_2348: {
                                        Label_2317: {
                                            if (!Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown()) {
                                                if (!Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown() && !Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown() && !Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown()) {
                                                    if (!Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown()) {
                                                        if (!Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()) {
                                                            break Label_2317;
                                                        }
                                                    }
                                                }
                                            }
                                            if (!this.hasDelayRun(1800L)) {
                                                break Label_2348;
                                            }
                                        }
                                        if (!Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown()) {
                                            break Label_2643;
                                        }
                                    }
                                    Wrapper.INSTANCE.player().connection.sendPacket((Packet)new CPacketPlayer$PositionRotation(Wrapper.INSTANCE.player().posX + n4, Wrapper.INSTANCE.player().posY + n6, Wrapper.INSTANCE.player().posZ + n5, Wrapper.INSTANCE.player().rotationYaw, Wrapper.INSTANCE.player().rotationPitch, false));
                                    Wrapper.INSTANCE.player().connection.sendPacket((Packet)new CPacketPlayer$PositionRotation(Wrapper.INSTANCE.player().posX + n4, Wrapper.INSTANCE.player().posY - 999.0, Wrapper.INSTANCE.player().posZ + n5, Wrapper.INSTANCE.player().rotationYaw, Wrapper.INSTANCE.player().rotationPitch, true));
                                }
                            }
                            if (this.mode.getMode("Old").isToggled()) {
                                Wrapper.INSTANCE.player().connection.sendPacket((Packet)new CPacketPlayer$PositionRotation(Wrapper.INSTANCE.player().posX + Wrapper.INSTANCE.player().motionX + (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown() ? 0.0624 : 0.0) - (Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown() ? 0.0624 : 0.0), Wrapper.INSTANCE.player().posY + (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown() ? 0.0624 : 0.0) - (Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown() ? 0.0624 : 0.0), Wrapper.INSTANCE.player().posZ + Wrapper.INSTANCE.player().motionZ + (Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown() ? 0.0624 : 0.0) - (Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown() ? 0.0624 : 0.0), Wrapper.INSTANCE.player().rotationYaw, Wrapper.INSTANCE.player().rotationPitch, false));
                                Wrapper.INSTANCE.player().connection.sendPacket((Packet)new CPacketPlayer$PositionRotation(Wrapper.INSTANCE.player().posX + Wrapper.INSTANCE.player().motionX, Wrapper.INSTANCE.player().posY - 42069.0, Wrapper.INSTANCE.player().posZ + Wrapper.INSTANCE.player().motionZ, Wrapper.INSTANCE.player().rotationYaw, Wrapper.INSTANCE.player().rotationPitch, true));
                            }
                            if (this.mode.getMode("Bypass").isToggled()) {
                                final boolean keyDown = Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown();
                                final boolean keyDown2 = Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown();
                                final boolean keyDown3 = Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown();
                                final boolean keyDown4 = Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown();
                                if (!keyDown || !keyDown2 || !keyDown3 || !keyDown4) {
                                    Minecraft.getMinecraft().player.motionX = 0.0;
                                    Minecraft.getMinecraft().player.motionZ = 0.0;
                                }
                                int n7;
                                if (keyDown2 && keyDown3) {
                                    n7 = (keyDown ? 0 : (keyDown4 ? 180 : -1));
                                }
                                else if (keyDown && keyDown4) {
                                    n7 = (keyDown2 ? -90 : (keyDown3 ? 90 : -1));
                                }
                                else {
                                    n7 = (keyDown2 ? -90 : (keyDown3 ? 90 : 0));
                                    if (keyDown) {
                                        n7 /= 2;
                                    }
                                    else if (keyDown4) {
                                        n7 = 180 - n7 / 2;
                                    }
                                }
                                if (n7 != -1 && (keyDown || keyDown2 || keyDown3 || keyDown4)) {
                                    final float n8 = Minecraft.getMinecraft().player.rotationYaw + n7;
                                    Minecraft.getMinecraft().player.motionX = getRelativeX(n8) * 0.20000000298023224;
                                    Minecraft.getMinecraft().player.motionZ = getRelativeZ(n8) * 0.20000000298023224;
                                }
                                Minecraft.getMinecraft().player.motionY = 0.0;
                                Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketPlayer$PositionRotation(Minecraft.getMinecraft().player.posX + Minecraft.getMinecraft().player.motionX, Minecraft.getMinecraft().player.posY + (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown() ? 0.0622 : 0.0) - (Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown() ? 0.0622 : 0.0), Minecraft.getMinecraft().player.posZ + Minecraft.getMinecraft().player.motionZ, Minecraft.getMinecraft().player.rotationYaw, Minecraft.getMinecraft().player.rotationPitch, false));
                                Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketPlayer$PositionRotation(Minecraft.getMinecraft().player.posX + Minecraft.getMinecraft().player.motionX, Minecraft.getMinecraft().player.posY - 42069.0, Minecraft.getMinecraft().player.posZ + Minecraft.getMinecraft().player.motionZ, Minecraft.getMinecraft().player.rotationYaw, Minecraft.getMinecraft().player.rotationPitch, true));
                            }
                            return;
                        }
                        case -2085090654: {
                            throw null;
                        }
                    }
                }
            }
        }
    }
    
    public void setCurrentMS() {
        this.currentMS = System.nanoTime() / 1000000L;
    }
    
    public static double getRelativeZ(final float n) {
        return MathHelper.cos(n * 0.017453292f);
    }
    
    @Override
    public void onDisable() {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages(false);
        super.onDisable();
    }
    
    public void reset() {
        this.currentMS = System.nanoTime() / 1000000L;
    }
    
    public void setLastMS() {
        this.lastMS = System.nanoTime() / 1000000L;
    }
}
