/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import com.krazzzzymonkey.catalyst.utils.Utils;
import com.krazzzzymonkey.catalyst.utils.ValidUtils;
import net.minecraft.entity.item.EntityArmorStand;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import java.util.Iterator;
import net.minecraft.util.EnumHand;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.enchantment.EnchantmentHelper;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.entity.EntityLivingBase;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.utils.TimerUtils;
import com.krazzzzymonkey.catalyst.module.Modules;

public class KillAura extends Modules
{
    public TimerUtils timer;
    public DoubleValue packetRange;
    public ModeValue priority;
    public BooleanValue walls;
    public IntegerValue tickDelay;
    public BooleanValue enemies;
    public BooleanValue mobs;
    public BooleanValue autoDelay;
    public BooleanValue invisibles;
    public static float[] facingCam;
    public BooleanValue players;
    public DoubleValue range;
    public static EntityLivingBase target;
    public int waitCounter;
    
    @Override
    public void onEnable() {
        KillAura.facingCam = null;
        super.onEnable();
    }
    
    public boolean isLowHealth(final EntityLivingBase entityLivingBase, final EntityLivingBase entityLivingBase2) {
        return entityLivingBase2 == null || entityLivingBase.getHealth() < entityLivingBase2.getHealth();
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world != null) {
            if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.getUniqueID() != null) {
                for (final EntityLivingBase next : Wrapper.INSTANCE.world().loadedEntityList) {
                    if (next instanceof EntityLivingBase) {
                        final EntityLivingBase target = next;
                        if (!this.check(target)) {
                            continue;
                        }
                        KillAura.target = target;
                    }
                }
                if (KillAura.target != null) {
                    if (KillAura.target.getHealth() > 0.0f) {
                        if (this.autoDelay.getValue()) {
                            if (Wrapper.INSTANCE.player().getCooledAttackStrength(0.0f) == 1.0f) {
                                if (!this.isInAttackRange(KillAura.target) || !this.isInAttackFOV(KillAura.target)) {
                                    return;
                                }
                                final EntityPlayerSP player = Wrapper.INSTANCE.player();
                                final float modifierForCreature = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), KillAura.target.getCreatureAttribute());
                                if (this.autoDelay.getValue()) {
                                    Wrapper.INSTANCE.mc().playerController.attackEntity((EntityPlayer)player, (Entity)KillAura.target);
                                }
                                else {
                                    Wrapper.INSTANCE.sendPacket((Packet)new CPacketUseEntity((Entity)KillAura.target));
                                }
                                player.swingArm(EnumHand.MAIN_HAND);
                                if (modifierForCreature > 0.0f) {
                                    player.onEnchantmentCritical((Entity)KillAura.target);
                                }
                                Minecraft.getMinecraft().player.swingArm(EnumHand.MAIN_HAND);
                                KillAura.target = null;
                            }
                        }
                        else if (this.autoDelay.getValue()) {
                            if (this.tickDelay.getValue() > 0) {
                                if (this.waitCounter < this.tickDelay.getValue()) {
                                    ++this.waitCounter;
                                }
                                else {
                                    this.waitCounter = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public KillAura() {
        super("KillAura", ModuleCategory.COMBAT, "Automatically attacks entities for you");
        this.players = new BooleanValue("Players", true);
        this.mobs = new BooleanValue("Mobs", false);
        this.invisibles = new BooleanValue("Invisibles", true);
        this.enemies = new BooleanValue("Only Enemies", false);
        this.priority = new ModeValue("Priority", new Mode[] { new Mode("Closest", true), new Mode("Health", false) });
        this.tickDelay = new IntegerValue("Tick Delay", 3, 0, 30);
        this.walls = new BooleanValue("Through Walls", true);
        this.autoDelay = new BooleanValue("AutoDelay", true);
        this.packetRange = new DoubleValue("PacketRange", 10.0, 1.0, 100.0);
        this.range = new DoubleValue("Range", 3.4, 1.0, 7.0);
        this.addValue(this.players, this.mobs, this.invisibles, this.enemies, this.priority, this.tickDelay, this.walls, this.autoDelay, this.packetRange, this.range);
        this.timer = new TimerUtils();
    }
    
    @Override
    public void onDisable() {
        KillAura.facingCam = null;
        KillAura.target = null;
        super.onDisable();
    }
    
    public boolean isPriority(final EntityLivingBase entityLivingBase) {
        return (this.priority.getMode("Closest").isToggled() && this.isClosest(entityLivingBase, KillAura.target)) || (this.priority.getMode("Health").isToggled() && this.isLowHealth(entityLivingBase, KillAura.target));
    }
    
    public boolean check(final EntityLivingBase entityLivingBase) {
        return !(entityLivingBase instanceof EntityArmorStand) && !ValidUtils.isValidEntityKillAura(entityLivingBase) && entityLivingBase != Wrapper.INSTANCE.player() && !entityLivingBase.isDead && entityLivingBase.deathTime <= 0 && ValidUtils.isFriendEnemyKillAura(entityLivingBase) && ValidUtils.isInvisibleKillAura(entityLivingBase) && this.isInAttackFOV(entityLivingBase) && this.isInAttackRange(entityLivingBase) && ((boolean)this.walls.getValue() || Wrapper.INSTANCE.player().canEntityBeSeen((Entity)entityLivingBase)) && this.isPriority(entityLivingBase);
    }
    
    public boolean isClosest(final EntityLivingBase entityLivingBase, final EntityLivingBase entityLivingBase2) {
        return entityLivingBase2 == null || Wrapper.INSTANCE.player().getDistance((Entity)entityLivingBase) < Wrapper.INSTANCE.player().getDistance((Entity)entityLivingBase2);
    }
    
    static {
        KillAura.facingCam = null;
    }
    
    public boolean isInAttackFOV(final EntityLivingBase entityLivingBase) {
        return Utils.getDistanceFromMouse(entityLivingBase) <= 180;
    }
    
    public boolean isInAttackRange(final EntityLivingBase entityLivingBase) {
        return entityLivingBase.getDistance((Entity)Wrapper.INSTANCE.player()) <= this.range.getValue().floatValue();
    }
}
