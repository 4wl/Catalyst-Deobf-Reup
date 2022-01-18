/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.world;

import com.krazzzzymonkey.catalyst.events.PacketEvent$Out;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.utils.BlockUtils;
import com.krazzzzymonkey.catalyst.utils.PlayerControllerUtils;
import net.minecraftforge.event.entity.player.PlayerInteractEvent$LeftClickBlock;
import com.krazzzzymonkey.catalyst.events.DamageBlockEvent;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerDigging$Action;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import com.krazzzzymonkey.catalyst.module.Modules;

public class FastBreak extends Modules
{
    public BlockPos lastBlock;
    public boolean packetCancel;
    public EnumFacing direction;
    public long systime;
    public IntegerValue delay;
    public BlockPos renderBlock;
    public BooleanValue autoBreak;
    public BooleanValue pickaxeOnly;
    public ModeValue modes;
    
    @SubscribeEvent
    public void onWorldRender(final RenderWorldLastEvent renderWorldLastEvent) {
        if (this.modes.getMode("Instant").isToggled() && this.renderBlock != null) {
            RenderUtils.drawBlockESP(this.renderBlock, 255.0f, 0.0f, 255.0f, 1.0);
        }
    }
    
    public BlockPos getTarget() {
        return this.renderBlock;
    }
    
    public FastBreak() {
        super("FastBreak", ModuleCategory.WORLD, "Allows you to mine blocks faster");
        this.modes = new ModeValue("Mode", new Mode[] { new Mode("Instant", true), new Mode("Basic", false), new Mode("Packet", false) });
        this.packetCancel = false;
        this.systime = System.currentTimeMillis();
        this.autoBreak = new BooleanValue("AutoBreak", true);
        this.delay = new IntegerValue("Delay", 20, 0, 500);
        this.pickaxeOnly = new BooleanValue("Pickaxe Only", true);
        this.addValue(this.modes, this.autoBreak, this.delay, this.pickaxeOnly);
    }
    
    public void setTarget(final BlockPos blockPos) {
        this.renderBlock = blockPos;
        this.packetCancel = false;
        FastBreak.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging$Action.START_DESTROY_BLOCK, blockPos, EnumFacing.DOWN));
        this.packetCancel = true;
        FastBreak.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging$Action.STOP_DESTROY_BLOCK, blockPos, EnumFacing.DOWN));
        this.direction = EnumFacing.DOWN;
        this.lastBlock = blockPos;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        if (this.modes.getMode("Basic").isToggled()) {
            Minecraft.getMinecraft().playerController.blockHitDelay = 0;
        }
        if (this.modes.getMode("Instant").isToggled()) {
            if (this.renderBlock != null) {
                if (this.autoBreak.getValue()) {
                    if (this.systime + this.delay.getValue() < System.currentTimeMillis()) {
                        if ((boolean)this.pickaxeOnly.getValue() && FastBreak.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() != Items.DIAMOND_PICKAXE) {
                            return;
                        }
                        FastBreak.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging$Action.STOP_DESTROY_BLOCK, this.renderBlock, this.direction));
                        this.systime = System.currentTimeMillis();
                    }
                }
            }
            try {
                FastBreak.mc.playerController.blockHitDelay = 0;
            }
            catch (Exception ex) {}
        }
    }
    
    @SubscribeEvent
    public void onBlockDamage(final DamageBlockEvent damageBlockEvent) {
        if (this.modes.getMode("Instant").isToggled() && this.canBreak(damageBlockEvent.getPos())) {
            if (this.lastBlock == null || damageBlockEvent.getPos().getX() != this.lastBlock.getX() || damageBlockEvent.getPos().getY() != this.lastBlock.getY() || damageBlockEvent.getPos().getZ() != this.lastBlock.getZ()) {
                this.packetCancel = false;
                FastBreak.mc.player.swingArm(EnumHand.MAIN_HAND);
                FastBreak.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging$Action.START_DESTROY_BLOCK, damageBlockEvent.getPos(), damageBlockEvent.getFacing()));
                this.packetCancel = true;
            }
            else {
                this.packetCancel = true;
            }
            FastBreak.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging$Action.STOP_DESTROY_BLOCK, damageBlockEvent.getPos(), damageBlockEvent.getFacing()));
            this.renderBlock = damageBlockEvent.getPos();
            this.lastBlock = damageBlockEvent.getPos();
            this.direction = damageBlockEvent.getFacing();
            damageBlockEvent.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onLeftClickBlock(final PlayerInteractEvent$LeftClickBlock playerInteractEvent$LeftClickBlock) {
        if (this.modes.getMode("Packet").isToggled()) {
            if (PlayerControllerUtils.getCurBlockDamageMP() + BlockUtils.getHardness(playerInteractEvent$LeftClickBlock.getPos()) >= 1.0f) {
                return;
            }
            Wrapper.INSTANCE.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging$Action.STOP_DESTROY_BLOCK, playerInteractEvent$LeftClickBlock.getPos(), Wrapper.INSTANCE.mc().objectMouseOver.sideHit));
        }
    }
    
    public boolean canBreak(final BlockPos blockPos) {
        final IBlockState blockState = FastBreak.mc.world.getBlockState(blockPos);
        return blockState.getBlock().getBlockHardness(blockState, (World)FastBreak.mc.world, blockPos) != -1.0f;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent$Out packetEvent$Out) {
        if (this.modes.getMode("Instant").isToggled()) {
            final Packet<?> packet = packetEvent$Out.packet;
            if (packet instanceof CPacketPlayerDigging) {
                final CPacketPlayerDigging cPacketPlayerDigging = (CPacketPlayerDigging)packet;
                if (((CPacketPlayerDigging)packet).getAction() == CPacketPlayerDigging$Action.START_DESTROY_BLOCK && this.packetCancel) {
                    packetEvent$Out.setCanceled(true);
                }
            }
        }
    }
}
