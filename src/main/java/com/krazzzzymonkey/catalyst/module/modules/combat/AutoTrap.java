/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import com.krazzzzymonkey.catalyst.managers.FriendManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.utils.BlockUtils;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.utils.PlayerControllerUtils;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.entity.player.EntityPlayer;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.module.Modules;

public class AutoTrap extends Modules
{
    public static Minecraft mc;
    public Vec3d[] offsetsFace;
    public ModeValue swing;
    public int offsetStep;
    public DoubleValue range;
    public static Vec3d[] TRAP;
    public static Vec3d[] CRYSTAL;
    public BooleanValue rotate;
    public static Vec3d[] CRYSTALEXA;
    public static EntityPlayer target;
    public Vec3d[] offsetsDefault;
    public static BooleanValue noGhostBlock;
    public static Vec3d[] CRYSTALFULLROOF;
    public ModeValue mode;
    public Vec3d[] offsetsExtra;
    public int yLevel;
    public String lastTickTargetName;
    public boolean firstRun;
    public DoubleValue blocksPerTick;
    
    public AutoTrap() {
        super("AutoTrap", ModuleCategory.COMBAT, "Automatically traps a player in obsidian");
        this.offsetStep = 0;
        this.lastTickTargetName = "";
        this.firstRun = true;
        this.offsetsDefault = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 1.0), new Vec3d(1.0, 3.0, 0.0), new Vec3d(-1.0, 3.0, 0.0), new Vec3d(0.0, 3.0, 0.0) };
        this.offsetsFace = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 1.0), new Vec3d(1.0, 3.0, 0.0), new Vec3d(-1.0, 3.0, 0.0), new Vec3d(0.0, 3.0, 0.0) };
        this.offsetsExtra = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 0.0), new Vec3d(0.0, 4.0, 0.0) };
        this.mode = new ModeValue("Mode", new Mode[] { new Mode("FullRoof", true), new Mode("ExtraTop", false), new Mode("FacePlace", false), new Mode("Normal", false), new Mode("CrystalMinimum", false), new Mode("Crystal", false), new Mode("CrystalFull", false) });
        this.rotate = new BooleanValue("Rotate", true);
        this.swing = new ModeValue("Swing", new Mode[] { new Mode("Mainhand", true), new Mode("Offhand", false), new Mode("Cancel", false) });
        this.range = new DoubleValue("Range", 5.0, 0.0, 10.0);
        this.blocksPerTick = new DoubleValue("Blocks Per Tick", 8.0, 1.0, 15.0);
        AutoTrap.noGhostBlock = new BooleanValue("AntiGhostBlock", false);
        this.addValue(this.mode, this.rotate, this.swing, this.range, this.blocksPerTick, AutoTrap.noGhostBlock);
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world != null) {
            if (Minecraft.getMinecraft().player != null) {
                if (Minecraft.getMinecraft().player.getUniqueID() != null && PlayerControllerUtils.findObiInHotbar() != -1) {
                    AutoTrap.target = this.findClosestTarget();
                    if (AutoTrap.target == null) {
                        return;
                    }
                    if ((int)Math.round(AutoTrap.mc.player.posY) != this.yLevel) {
                        return;
                    }
                    if (this.firstRun) {
                        this.firstRun = false;
                        this.lastTickTargetName = AutoTrap.target.getName();
                    }
                    else if (!this.lastTickTargetName.equals(AutoTrap.target.getName())) {
                        this.lastTickTargetName = AutoTrap.target.getName();
                        this.offsetStep = 0;
                    }
                    final ArrayList<Object> c = new ArrayList<Object>();
                    if (this.mode.getMode("FullRoof").isToggled()) {
                        Collections.addAll(c, this.offsetsDefault);
                    }
                    else if (this.mode.getMode("ExtraTop").isToggled()) {
                        Collections.addAll(c, this.offsetsExtra);
                    }
                    else if (this.mode.getMode("Normal").isToggled()) {
                        Collections.addAll(c, AutoTrap.TRAP);
                    }
                    else if (this.mode.getMode("CrystalMinimum").isToggled()) {
                        Collections.addAll(c, AutoTrap.CRYSTALEXA);
                    }
                    else if (this.mode.getMode("Crystal").isToggled()) {
                        Collections.addAll(c, AutoTrap.CRYSTAL);
                    }
                    else if (this.mode.getMode("CrystalFull").isToggled()) {
                        Collections.addAll(c, AutoTrap.CRYSTALFULLROOF);
                    }
                    else {
                        Collections.addAll(c, this.offsetsFace);
                    }
                    int n = 0;
                Label_0700:
                    while (n < this.blocksPerTick.getValue()) {
                        if (this.offsetStep >= c.size()) {
                            this.offsetStep = 0;
                            break;
                        }
                        final BlockPos blockPos = new BlockPos((Vec3d)c.get(this.offsetStep));
                        final BlockPos add = new BlockPos(AutoTrap.target.getPositionVector()).down().add(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                        boolean b = true;
                        if (!AutoTrap.mc.world.getBlockState(add).getMaterial().isReplaceable()) {
                            b = false;
                        }
                        while (true) {
                            for (final Entity entity : AutoTrap.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(add))) {
                                if (!(entity instanceof EntityItem) && !(entity instanceof EntityXPOrb)) {
                                    b = false;
                                    if (b && BlockUtils.placeBlock(add, PlayerControllerUtils.findObiInHotbar(), (boolean)this.rotate.getValue(), (boolean)this.rotate.getValue(), this.swing, (boolean)AutoTrap.noGhostBlock.getValue())) {
                                        ++n;
                                    }
                                    ++this.offsetStep;
                                    continue Label_0700;
                                }
                            }
                            continue;
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void onEnable() {
        this.yLevel = (int)Math.round(AutoTrap.mc.player.posY);
        this.firstRun = true;
        super.onEnable();
    }
    
    public EntityPlayer findClosestTarget() {
        if (AutoTrap.mc.world.playerEntities.isEmpty()) {
            return null;
        }
        Object o = null;
        for (final EntityPlayer entityPlayer : AutoTrap.mc.world.playerEntities) {
            if (entityPlayer != AutoTrap.mc.player) {
                if (!entityPlayer.isEntityAlive()) {
                    continue;
                }
                if (AutoTrap.mc.player.getDistance((Entity)entityPlayer) > this.range.getValue()) {
                    continue;
                }
                if (FriendManager.friendsList.contains(entityPlayer.getName())) {
                    continue;
                }
                if (entityPlayer.getHealth() <= 0.0f) {
                    continue;
                }
                if (o != null) {
                    if (AutoTrap.mc.player.getDistance((Entity)entityPlayer) > AutoTrap.mc.player.getDistance((Entity)o)) {
                        continue;
                    }
                }
                o = entityPlayer;
            }
        }
        return (EntityPlayer)o;
    }
    
    static {
        AutoTrap.mc = Minecraft.getMinecraft();
        AutoTrap.TRAP = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 0.0) };
        AutoTrap.CRYSTALEXA = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(-1.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 1.0), new Vec3d(1.0, 2.0, -1.0), new Vec3d(-1.0, 2.0, 1.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 0.0) };
        AutoTrap.CRYSTAL = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(-1.0, 0.0, 1.0), new Vec3d(1.0, 0.0, -1.0), new Vec3d(-1.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 1.0), new Vec3d(-1.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 1.0), new Vec3d(1.0, 1.0, -1.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(-1.0, 2.0, 1.0), new Vec3d(1.0, 2.0, -1.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 0.0) };
        AutoTrap.CRYSTALFULLROOF = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(-1.0, 0.0, 1.0), new Vec3d(1.0, 0.0, -1.0), new Vec3d(-1.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 1.0), new Vec3d(-1.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 1.0), new Vec3d(1.0, 1.0, -1.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(-1.0, 2.0, 1.0), new Vec3d(1.0, 2.0, -1.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(1.0, 3.0, 0.0), new Vec3d(0.0, 3.0, 1.0), new Vec3d(-1.0, 3.0, 0.0), new Vec3d(0.0, 3.0, 0.0) };
    }
}
