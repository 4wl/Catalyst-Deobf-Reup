/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.movement;

import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.MathHelper;
import com.krazzzzymonkey.catalyst.utils.MathUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import java.util.function.Predicate;
import net.minecraft.network.Packet;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction$Action;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.init.Items;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class ElytraFly extends Modules
{
    public boolean isHackFlying;
    public int tickspassed;
    public BooleanValue autoBoost;
    public ModeValue Modes;
    
    public ElytraFly() {
        super("ElytraFly", ModuleCategory.MOVEMENT, "Better Elytra Flying");
        this.isHackFlying = false;
        this.tickspassed = 0;
        this.Modes = new ModeValue("Mode", new Mode[] { new Mode("CFly", true), new Mode("Packet", false), new Mode("Boost", false) });
        this.autoBoost = new BooleanValue("AutoBoost", false);
        this.addValue(this.Modes, this.autoBoost);
    }
    
    public int getSlotWithRockets() {
        for (int i = 0; i < 9; ++i) {
            if (ElytraFly.mc.player.inventory.getStackInSlot(i).getItem() == Items.FIREWORKS) {
                return i;
            }
        }
        return -1;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (ElytraFly.mc.player == null) {
            return;
        }
        if (ElytraFly.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != Items.ELYTRA) {
            return;
        }
        if (this.Modes.getMode("CFly").isToggled()) {
            if (!ElytraFly.mc.player.onGround && !this.isHackFlying) {
                ElytraFly.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFly.mc.player, CPacketEntityAction$Action.START_FALL_FLYING));
                ElytraFly.mc.player.capabilities.isFlying = true;
                this.isHackFlying = true;
            }
            if (this.autoBoost.getValue()) {
                if (ElytraFly.mc.world.loadedEntityList.stream().filter(ElytraFly::lambda$onClientTick$0).noneMatch(ElytraFly::lambda$onClientTick$1)) {
                    ++this.tickspassed;
                    if (this.tickspassed > 5) {
                        this.tickspassed = 0;
                        final int currentItem = ElytraFly.mc.player.inventory.currentItem;
                        final int slotWithRockets = this.getSlotWithRockets();
                        if (slotWithRockets != -1) {
                            ElytraFly.mc.player.inventory.currentItem = slotWithRockets;
                            ElytraFly.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slotWithRockets));
                            ElytraFly.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                        }
                        else {
                            ChatUtils.normalMessage("No rockets found in hotbar, disabling AutoBoost");
                            this.autoBoost.setValue(false);
                        }
                    }
                }
            }
            this.isHackFlying = !ElytraFly.mc.player.onGround;
        }
        if (this.Modes.getMode("Packet").isToggled()) {
            ElytraFly.mc.player.motionX = 0.0;
            ElytraFly.mc.player.motionY = 0.0;
            ElytraFly.mc.player.motionZ = 0.0;
            final double[] directionSpeed = MathUtils.directionSpeed(1.0);
            if (ElytraFly.mc.player.movementInput.jump) {
                ElytraFly.mc.player.motionY = 1.0;
            }
            if (ElytraFly.mc.player.movementInput.sneak) {
                ElytraFly.mc.player.motionY = -1.0;
            }
            if (ElytraFly.mc.player.movementInput.moveStrafe != 0.0f || ElytraFly.mc.player.movementInput.moveForward != 0.0f) {
                ElytraFly.mc.player.motionX = directionSpeed[0];
                ElytraFly.mc.player.motionZ = directionSpeed[1];
            }
            ElytraFly.mc.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFly.mc.player, CPacketEntityAction$Action.START_FALL_FLYING));
            ElytraFly.mc.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFly.mc.player, CPacketEntityAction$Action.START_FALL_FLYING));
        }
        if (this.Modes.getMode("Boost").isToggled()) {
            if (!ElytraFly.mc.player.isElytraFlying() || ElytraFly.mc.player.onGround) {
                return;
            }
            if (ElytraFly.mc.gameSettings.keyBindJump.isKeyDown()) {
                final EntityPlayerSP player = ElytraFly.mc.player;
                player.motionY += 0.08;
            }
            else if (ElytraFly.mc.gameSettings.keyBindSneak.isKeyDown()) {
                final EntityPlayerSP player2 = ElytraFly.mc.player;
                player2.motionY -= 0.04;
            }
            if (ElytraFly.mc.gameSettings.keyBindForward.isKeyDown()) {
                final float n = (float)Math.toRadians(ElytraFly.mc.player.rotationYaw);
                final EntityPlayerSP player3 = ElytraFly.mc.player;
                player3.motionX -= MathHelper.sin(n) * 0.05f;
                final EntityPlayerSP player4 = ElytraFly.mc.player;
                player4.motionZ += MathHelper.cos(n) * 0.05f;
            }
            if (ElytraFly.mc.gameSettings.keyBindBack.isKeyDown()) {
                final float n2 = (float)Math.toRadians(ElytraFly.mc.player.rotationYaw);
                final EntityPlayerSP player5 = ElytraFly.mc.player;
                player5.motionX += MathHelper.sin(n2) * 0.05f;
                final EntityPlayerSP player6 = ElytraFly.mc.player;
                player6.motionZ -= MathHelper.cos(n2) * 0.05f;
            }
        }
    }
    
    public static boolean lambda$onClientTick$0(final Entity entity) {
        return entity instanceof EntityFireworkRocket;
    }
    
    @Override
    public void onDisable() {
        if (ElytraFly.mc.player == null) {
            return;
        }
        this.isHackFlying = false;
        ElytraFly.mc.player.capabilities.isFlying = false;
        super.onDisable();
    }
    
    public static boolean lambda$onClientTick$1(final Entity entity) {
        return ((EntityFireworkRocket)entity).boostedEntity == ElytraFly.mc.player;
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        if (ElytraFly.mc.player == null) {
            return;
        }
        if (ElytraFly.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != Items.ELYTRA) {
            return;
        }
        ElytraFly.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFly.mc.player, CPacketEntityAction$Action.START_FALL_FLYING));
        ElytraFly.mc.player.capabilities.isFlying = true;
    }
    
    public void runNoKick() {
        if (!ElytraFly.mc.player.isElytraFlying() && ElytraFly.mc.player.ticksExisted % 4 == 0) {
            ElytraFly.mc.player.motionY = -0.03999999910593033;
        }
    }
}
