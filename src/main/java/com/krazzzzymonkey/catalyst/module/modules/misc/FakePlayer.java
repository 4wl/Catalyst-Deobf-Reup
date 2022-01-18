/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.world.GameType;
import net.minecraft.entity.Entity;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class FakePlayer extends Modules
{
    public BooleanValue CopyPlayerInventory;
    
    public FakePlayer() {
        super("FakePlayer", ModuleCategory.MISC, "Spawns in a Fake player (Client Side)");
        this.CopyPlayerInventory = new BooleanValue("Copy Player Armor", true);
        this.addValue(this.CopyPlayerInventory);
    }
    
    @Override
    public void onEnable() {
        if (FakePlayer.mc.player == null || FakePlayer.mc.player.isDead) {
            this.toggle();
            return;
        }
        final EntityOtherPlayerMP entityOtherPlayerMP = new EntityOtherPlayerMP((World)FakePlayer.mc.world, new GameProfile(UUID.fromString("3a54cd18-783e-4b6c-9f5f-70c23fd9dca9"), "CatalystClient"));
        DefaultPlayerSkin.TEXTURE_ALEX = new ResourceLocation("catalyst/fakeplayer/skin.png");
        entityOtherPlayerMP.copyLocationAndAnglesFrom((Entity)FakePlayer.mc.player);
        entityOtherPlayerMP.rotationYawHead = FakePlayer.mc.player.rotationYawHead;
        entityOtherPlayerMP.rotationYaw = FakePlayer.mc.player.rotationYaw;
        entityOtherPlayerMP.rotationPitch = FakePlayer.mc.player.rotationPitch;
        entityOtherPlayerMP.setGameType(GameType.SURVIVAL);
        entityOtherPlayerMP.setHealth(20.0f);
        FakePlayer.mc.world.addEntityToWorld(-9999, (Entity)entityOtherPlayerMP);
        if (this.CopyPlayerInventory.getValue()) {
            entityOtherPlayerMP.inventory = FakePlayer.mc.player.inventory;
        }
        entityOtherPlayerMP.onLivingUpdate();
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (FakePlayer.mc.world == null) {
            this.toggle();
        }
    }
    
    @Override
    public void onDisable() {
        if (FakePlayer.mc.world != null) {
            FakePlayer.mc.world.removeEntityFromWorld(-9999);
            DefaultPlayerSkin.TEXTURE_ALEX = new ResourceLocation("textures/entity/alex.png");
        }
    }
}
