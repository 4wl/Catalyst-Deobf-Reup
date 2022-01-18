/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.world;

import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.item.ItemStack;
import com.krazzzzymonkey.catalyst.utils.BlockUtils;
import com.krazzzzymonkey.catalyst.utils.Utils;
import net.minecraft.util.EnumFacing;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.util.math.BlockPos;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.utils.TimerUtils;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Scaffold extends Modules
{
    public static float[] facingCam;
    public static boolean cancelSneak;
    public float startYaw;
    public boolean isBridging;
    public TimerUtils timer;
    public boolean shouldKeepVelocity;
    public BooleanValue rotate;
    public ModeValue placeMode;
    public float startPitch;
    public BooleanValue towerMode;
    public BlockPos blockDown;
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        if (this.blockDown != null) {
            RenderUtils.drawBlockESP(this.blockDown, 1.0f, 1.0f, 1.0f, 1.0);
        }
    }
    
    public Scaffold() {
        super("Scaffold", ModuleCategory.WORLD, "Automatically places blocks under player");
        this.shouldKeepVelocity = false;
        this.isBridging = false;
        this.blockDown = null;
        this.startYaw = 0.0f;
        this.startPitch = 0.0f;
        this.placeMode = new ModeValue("PlaceMode", new Mode[] { new Mode("Normal", false), new Mode("Legit", false), new Mode("Simple", true) });
        this.towerMode = new BooleanValue("TowerMode", false);
        this.addValue(this.placeMode, this.towerMode);
        this.timer = new TimerUtils();
    }
    
    public void Simple() {
        this.blockDown = new BlockPos((Entity)Wrapper.INSTANCE.player()).down();
        if (Scaffold.mc.player.isSneaking()) {
            this.blockDown = this.blockDown.down();
            Scaffold.cancelSneak = true;
        }
        else {
            Scaffold.cancelSneak = false;
        }
        if (Minecraft.getMinecraft().player.motionX != 0.0 || (Minecraft.getMinecraft().player.motionZ != 0.0 && (boolean)this.towerMode.getValue())) {
            if (!Wrapper.INSTANCE.world().getBlockState(this.blockDown).getBlock().getMaterial(Wrapper.INSTANCE.world().getBlockState(this.blockDown).getBlock().getDefaultState()).isReplaceable()) {
                return;
            }
            int currentItem = -1;
            for (int i = 0; i < 9; ++i) {
                final ItemStack stackInSlot = Wrapper.INSTANCE.player().inventory.getStackInSlot(i);
                if (stackInSlot != null) {
                    if (stackInSlot.getItem() instanceof ItemBlock && Block.getBlockFromItem(stackInSlot.getItem()).getDefaultState().getBlock().isFullBlock(Wrapper.INSTANCE.world().getBlockState(this.blockDown).getBlock().getDefaultState())) {
                        currentItem = i;
                        break;
                    }
                }
            }
            if (currentItem == -1) {
                return;
            }
            final int currentItem2 = Wrapper.INSTANCE.player().inventory.currentItem;
            Wrapper.INSTANCE.player().inventory.currentItem = currentItem;
            if (!hasNeighbour(this.blockDown)) {
                final EnumFacing[] values = EnumFacing.VALUES;
                final int length = values.length;
                int j = 0;
                while (j < length) {
                    final BlockPos offset = this.blockDown.offset(values[j]);
                    if (hasNeighbour(offset)) {
                        if (this.placeMode.getMode("Normal").isToggled()) {
                            Utils.placeBlockScaffold(offset);
                            break;
                        }
                        if (this.placeMode.getMode("Legit").isToggled()) {
                            BlockUtils.placeBlockLegit(offset);
                            break;
                        }
                        if (this.placeMode.getMode("Simple").isToggled()) {
                            BlockUtils.placeBlockSimple(offset);
                            break;
                        }
                        break;
                    }
                    else {
                        ++j;
                    }
                }
            }
            if (this.placeMode.getMode("Normal").isToggled()) {
                Utils.placeBlockScaffold(this.blockDown);
            }
            else if (this.placeMode.getMode("Legit").isToggled()) {
                BlockUtils.placeBlockLegit(this.blockDown);
            }
            else if (this.placeMode.getMode("Simple").isToggled()) {
                BlockUtils.placeBlockSimple(this.blockDown);
            }
            Wrapper.INSTANCE.player().inventory.currentItem = currentItem2;
        }
        else if (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown() && (boolean)this.towerMode.getValue() && Scaffold.mc.player.motionX == 0.0 && Scaffold.mc.player.motionZ == 0.0) {
            int currentItem3 = -1;
            for (int k = 0; k < 9; ++k) {
                final ItemStack stackInSlot2 = Wrapper.INSTANCE.player().inventory.getStackInSlot(k);
                if (stackInSlot2 != null && stackInSlot2.getItem() instanceof ItemBlock && Block.getBlockFromItem(stackInSlot2.getItem()).getDefaultState().getBlock().isFullBlock(Wrapper.INSTANCE.world().getBlockState(this.blockDown).getBlock().getDefaultState())) {
                    currentItem3 = k;
                    break;
                }
            }
            if (currentItem3 == -1) {
                return;
            }
            final int currentItem4 = Wrapper.INSTANCE.player().inventory.currentItem;
            Wrapper.INSTANCE.player().inventory.currentItem = currentItem3;
            Minecraft.getMinecraft().player.setVelocity(0.0, 0.30000001192092896, 0.0);
            if (this.placeMode.getMode("Normal").isToggled()) {
                Utils.placeBlockScaffold(this.blockDown);
            }
            else if (this.placeMode.getMode("Legit").isToggled()) {
                BlockUtils.placeBlockLegit(this.blockDown);
            }
            else if (this.placeMode.getMode("Simple").isToggled()) {
                BlockUtils.placeBlockSimple(this.blockDown);
            }
            this.shouldKeepVelocity = true;
            Wrapper.INSTANCE.player().inventory.currentItem = currentItem4;
        }
        else {
            if (!Wrapper.INSTANCE.world().getBlockState(this.blockDown).getBlock().getMaterial(Wrapper.INSTANCE.world().getBlockState(this.blockDown).getBlock().getDefaultState()).isReplaceable()) {
                return;
            }
            int currentItem5 = -1;
            for (int l = 0; l < 9; ++l) {
                final ItemStack stackInSlot3 = Wrapper.INSTANCE.player().inventory.getStackInSlot(l);
                if (stackInSlot3 != null && stackInSlot3.getItem() instanceof ItemBlock && Block.getBlockFromItem(stackInSlot3.getItem()).getDefaultState().getBlock().isFullBlock(Wrapper.INSTANCE.world().getBlockState(this.blockDown).getBlock().getDefaultState())) {
                    currentItem5 = l;
                    break;
                }
            }
            if (currentItem5 == -1) {
                return;
            }
            final int currentItem6 = Wrapper.INSTANCE.player().inventory.currentItem;
            Wrapper.INSTANCE.player().inventory.currentItem = currentItem5;
            if (!hasNeighbour(this.blockDown)) {
                final EnumFacing[] values2 = EnumFacing.VALUES;
                final int length2 = values2.length;
                int n = 0;
                while (n < length2) {
                    final BlockPos offset2 = this.blockDown.offset(values2[n]);
                    if (hasNeighbour(offset2)) {
                        if (this.placeMode.getMode("Normal").isToggled()) {
                            Utils.placeBlockScaffold(offset2);
                            break;
                        }
                        if (this.placeMode.getMode("Legit").isToggled()) {
                            BlockUtils.placeBlockLegit(offset2);
                            break;
                        }
                        if (this.placeMode.getMode("Simple").isToggled()) {
                            BlockUtils.placeBlockSimple(offset2);
                            break;
                        }
                        break;
                    }
                    else {
                        ++n;
                    }
                }
            }
            if (this.placeMode.getMode("Normal").isToggled()) {
                Utils.placeBlockScaffold(this.blockDown);
            }
            else if (this.placeMode.getMode("Legit").isToggled()) {
                BlockUtils.placeBlockLegit(this.blockDown);
            }
            else if (this.placeMode.getMode("Simple").isToggled()) {
                BlockUtils.placeBlockSimple(this.blockDown);
            }
            Wrapper.INSTANCE.player().inventory.currentItem = currentItem6;
        }
        if (this.shouldKeepVelocity && !Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()) {
            Minecraft.getMinecraft().player.motionY = -0.2800000011920929;
            this.shouldKeepVelocity = false;
        }
    }
    
    @Override
    public void onDisable() {
        Scaffold.facingCam = null;
        Scaffold.cancelSneak = false;
        super.onDisable();
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        this.Simple();
    }
    
    static {
        Scaffold.facingCam = null;
        Scaffold.cancelSneak = false;
    }
    
    @Override
    public void onEnable() {
        this.blockDown = null;
        Scaffold.facingCam = null;
        this.isBridging = false;
        this.startYaw = 0.0f;
        this.startPitch = 0.0f;
        Scaffold.cancelSneak = false;
        super.onEnable();
    }
    
    public static boolean hasNeighbour(final BlockPos blockPos) {
        final EnumFacing[] values = EnumFacing.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            if (!Minecraft.getMinecraft().world.getBlockState(blockPos.offset(values[i])).getMaterial().isReplaceable()) {
                return true;
            }
        }
        return false;
    }
}
