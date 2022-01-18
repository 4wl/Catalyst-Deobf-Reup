/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.movement;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import com.krazzzzymonkey.catalyst.events.PacketEvent$Out;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.Entity;
import com.krazzzzymonkey.catalyst.utils.EntityUtils;
import net.minecraft.block.BlockLiquid;
import com.krazzzzymonkey.catalyst.events.AddCollisionBoxToListEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.item.EntityBoat;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import net.minecraft.util.math.AxisAlignedBB;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Jesus extends Modules
{
    public static AxisAlignedBB WATER_WALK_AA;
    public static ModeValue mode;
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (!Wrapper.INSTANCE.player().isInWater() && !Wrapper.INSTANCE.player().isInLava()) {
            return;
        }
        if (Wrapper.INSTANCE.player().isSneaking() || Wrapper.INSTANCE.mcSettings().keyBindJump.isKeyDown()) {
            return;
        }
        if (Jesus.mode.getMode("Jump").isToggled()) {
            Wrapper.INSTANCE.player().jump();
        }
        else if (Jesus.mode.getMode("Dolphin").isToggled()) {
            final EntityPlayerSP player = Wrapper.INSTANCE.player();
            player.motionY += 0.03999999910593033;
        }
        else if (Jesus.mode.getMode("Fish").isToggled()) {
            final EntityPlayerSP player2 = Wrapper.INSTANCE.player();
            player2.motionY += 0.019999999552965164;
        }
        else if (Jesus.mode.getMode("Solid").isToggled() && !ModuleManager.getModule("Freecam").isToggled() && isInLiquid() && !Jesus.mc.player.isSneaking()) {
            Jesus.mc.player.motionY = 0.1;
            if (Jesus.mc.player.getRidingEntity() != null) {
                if (!(Jesus.mc.player.getRidingEntity() instanceof EntityBoat)) {
                    Jesus.mc.player.getRidingEntity().motionY = 0.3;
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onCollision(final AddCollisionBoxToListEvent addCollisionBoxToListEvent) {
        if (Jesus.mode.getMode("Solid").isToggled() && Jesus.mc.player != null && addCollisionBoxToListEvent.getBlock() instanceof BlockLiquid) {
            if (!EntityUtils.isDrivenByPlayer(addCollisionBoxToListEvent.getEntity())) {
                if (addCollisionBoxToListEvent.getEntity() != Jesus.mc.player) {
                    return;
                }
            }
            if (!(addCollisionBoxToListEvent.getEntity() instanceof EntityBoat) && !Jesus.mc.player.isSneaking() && Jesus.mc.player.fallDistance < 3.0f && !isInWater((Entity)Jesus.mc.player) && (isAboveWater((Entity)Jesus.mc.player, false) || isAboveWater(Jesus.mc.player.getRidingEntity(), false)) && EntityUtils.isAboveBlock((Entity)Jesus.mc.player, addCollisionBoxToListEvent.getPos())) {
                final AxisAlignedBB offset = Jesus.WATER_WALK_AA.offset(addCollisionBoxToListEvent.getPos());
                if (addCollisionBoxToListEvent.getEntityBox().intersects(offset)) {
                    addCollisionBoxToListEvent.getCollidingBoxes().add(offset);
                }
                addCollisionBoxToListEvent.setCanceled(true);
            }
        }
    }
    
    public static boolean isInLiquid() {
        if (Jesus.mc.player.fallDistance >= 3.0f) {
            return false;
        }
        boolean b = false;
        final AxisAlignedBB axisAlignedBB = (Jesus.mc.player.getRidingEntity() != null) ? Jesus.mc.player.getRidingEntity().getEntityBoundingBox() : Jesus.mc.player.getEntityBoundingBox();
        final int n = (int)axisAlignedBB.minY;
        for (int i = MathHelper.floor(axisAlignedBB.minX); i < MathHelper.floor(axisAlignedBB.maxX) + 1; ++i) {
            for (int j = MathHelper.floor(axisAlignedBB.minZ); j < MathHelper.floor(axisAlignedBB.maxZ) + 1; ++j) {
                final Block block = Jesus.mc.world.getBlockState(new BlockPos(i, n, j)).getBlock();
                if (!(block instanceof BlockAir)) {
                    if (!(block instanceof BlockLiquid)) {
                        return false;
                    }
                    b = true;
                }
            }
        }
        return b;
    }
    
    public static boolean isInWater(final Entity entity) {
        if (entity == null) {
            return false;
        }
        final double n = entity.posY + 0.01;
        for (int i = MathHelper.floor(entity.posX); i < MathHelper.ceil(entity.posX); ++i) {
            for (int j = MathHelper.floor(entity.posZ); j < MathHelper.ceil(entity.posZ); ++j) {
                if (Wrapper.INSTANCE.world().getBlockState(new BlockPos(i, (int)n, j)).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isAboveWater(final Entity entity, final boolean b) {
        if (entity == null) {
            return false;
        }
        final double n = entity.posY - (b ? 0.03 : (Utils.isPlayer(entity) ? 0.2 : 0.5));
        for (int i = MathHelper.floor(entity.posX); i < MathHelper.ceil(entity.posX); ++i) {
            for (int j = MathHelper.floor(entity.posZ); j < MathHelper.ceil(entity.posZ); ++j) {
                if (Jesus.mc.world.getBlockState(new BlockPos(i, MathHelper.floor(n), j)).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isAboveLand(final Entity entity) {
        if (entity == null) {
            return false;
        }
        final double n = entity.posY - 0.01;
        for (int i = MathHelper.floor(entity.posX); i < MathHelper.ceil(entity.posX); ++i) {
            for (int j = MathHelper.floor(entity.posZ); j < MathHelper.ceil(entity.posZ); ++j) {
                final BlockPos blockPos = new BlockPos(i, MathHelper.floor(n), j);
                if (Wrapper.INSTANCE.world().getBlockState(blockPos).getBlock().isFullBlock(Wrapper.INSTANCE.world().getBlockState(blockPos))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Jesus() {
        super("Jesus", ModuleCategory.MOVEMENT, "Automatically swims for you");
        Jesus.mode = new ModeValue("Mode", new Mode[] { new Mode("Solid", true), new Mode("Jump", false), new Mode("Dolphin", false), new Mode("Fish", false) });
        this.addValue(Jesus.mode);
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent$Out packetEvent$Out) {
        if (Jesus.mode.getMode("Solid").isToggled()) {
            final Packet<?> packet = packetEvent$Out.packet;
            if (packet instanceof CPacketPlayer && isAboveWater((Entity)Jesus.mc.player, true) && !isInWater((Entity)Jesus.mc.player) && !isAboveLand((Entity)Jesus.mc.player) && Jesus.mc.player.ticksExisted % 2 == 0) {
                final CPacketPlayer cPacketPlayer = (CPacketPlayer)packet;
                cPacketPlayer.y += 0.02;
            }
        }
    }
    
    static {
        Jesus.WATER_WALK_AA = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.99, 1.0);
    }
}
