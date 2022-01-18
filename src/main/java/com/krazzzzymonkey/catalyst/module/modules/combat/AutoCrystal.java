/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import net.minecraft.util.math.RayTraceResult;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;
import net.minecraft.item.ItemTool;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemStack;
import net.minecraft.init.MobEffects;
import java.util.Comparator;
import java.util.function.Function;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.MathHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.util.CombatRules;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import com.krazzzzymonkey.catalyst.module.modules.render.HoleESP;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAppleGold;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.network.Packet;
import java.util.ConcurrentModificationException;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.network.play.server.SPacketSoundEffect;
import com.krazzzzymonkey.catalyst.events.PacketEvent$In;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.util.Collection;
import net.minecraft.util.NonNullList;
import java.util.List;
import net.minecraft.world.World;
import net.minecraft.world.Explosion;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.entity.item.EntityEnderCrystal;
import com.krazzzzymonkey.catalyst.managers.FriendManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import net.minecraft.util.EnumFacing;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class AutoCrystal extends Modules
{
    public int oldSlot;
    public static boolean isSpoofingAngles;
    public BooleanValue explode;
    public BooleanValue antiWeakness;
    public DoubleValue range;
    public boolean isAttacking;
    public BooleanValue spoofRotations;
    public DoubleValue minDmg;
    public DoubleValue placeRange;
    public BooleanValue raytrace;
    public BooleanValue autoSwitch;
    public static double yaw;
    public DoubleValue minHitDmg;
    public int newSlot;
    public boolean switchCooldown;
    public EnumFacing f;
    public BooleanValue rainbow;
    public BooleanValue nodesync;
    public IntegerValue espHeight;
    public BooleanValue rotate;
    public static double pitch;
    public BooleanValue place;
    public DoubleValue walls;
    public DoubleValue facePlace;
    public int waitCounter;
    public IntegerValue waitTick;
    public ModeValue renderMode;
    public ColorValue espColor;
    public BooleanValue noGappleSwitch;
    public boolean isActive;
    public DoubleValue maxSelfDmg;
    public BooleanValue onePointThirteen;
    public BlockPos render;
    public Entity renderEnt;
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.render = null;
        this.renderEnt = null;
        resetRotation();
        this.isActive = false;
    }
    
    public static double[] calculateLookAt(final double n, final double n2, final double n3, final EntityPlayer entityPlayer) {
        final double n4 = entityPlayer.posX - n;
        final double n5 = entityPlayer.posY - n2;
        final double n6 = entityPlayer.posZ - n3;
        final double sqrt = Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6);
        return new double[] { Math.atan2(n6 / sqrt, n4 / sqrt) * 180.0 / 3.141592653589793 + 90.0, Math.asin(n5 / sqrt) * 180.0 / 3.141592653589793 };
    }
    
    public static boolean lambda$onClientTick$8(final EntityPlayer entityPlayer) {
        return !FriendManager.friendsList.contains(entityPlayer.getName());
    }
    
    public static EntityEnderCrystal lambda$onClientTick$2(final Entity entity) {
        return (EntityEnderCrystal)entity;
    }
    
    public AutoCrystal() {
        super("AutoCrystal", ModuleCategory.COMBAT, "Automatically places and breaks end crystals");
        this.switchCooldown = false;
        this.isAttacking = false;
        this.oldSlot = -1;
        this.isActive = false;
        this.onePointThirteen = new BooleanValue("1.13+ Place", false);
        this.explode = new BooleanValue("Hit", true);
        this.waitTick = new IntegerValue("TickDelay", 1, 0, 20);
        this.range = new DoubleValue("HitRange", 5.0, 0.0, 10.0);
        this.walls = new DoubleValue("WallsRange", 3.5, 0.0, 10.0);
        this.antiWeakness = new BooleanValue("AntiWeakness", true);
        this.nodesync = new BooleanValue("AntiDesync", true);
        this.noGappleSwitch = new BooleanValue("NoGapSwitch", true);
        this.place = new BooleanValue("Place", true);
        this.autoSwitch = new BooleanValue("AutoSwitch", true);
        this.placeRange = new DoubleValue("PlaceRange", 5.0, 0.0, 10.0);
        this.minDmg = new DoubleValue("MinDmg", 5.0, 0.0, 40.0);
        this.minHitDmg = new DoubleValue("MinHitDmg", 6.0, 0.0, 40.0);
        this.facePlace = new DoubleValue("FacePlaceHP", 6.0, 0.0, 40.0);
        this.raytrace = new BooleanValue("Raytrace", false);
        this.rotate = new BooleanValue("Rotate", true);
        this.spoofRotations = new BooleanValue("SpoofAngles", true);
        this.maxSelfDmg = new DoubleValue("MaxSelfDmg", 10.0, 0.0, 36.0);
        this.rainbow = new BooleanValue("EspRainbow", false);
        this.espColor = new ColorValue("EspColor", Color.CYAN.getRGB());
        this.espHeight = new IntegerValue("EspHeight", 1, 0, 2);
        this.renderMode = new ModeValue("RenderMode", new Mode[] { new Mode("Full", false), new Mode("Outline", true) });
        this.addValue(this.onePointThirteen, this.explode, this.waitTick, this.range, this.walls, this.antiWeakness, this.nodesync, this.noGappleSwitch, this.place, this.autoSwitch, this.minDmg, this.minHitDmg, this.facePlace, this.raytrace, this.rotate, this.spoofRotations, this.maxSelfDmg, this.rainbow, this.espColor, this.espHeight, this.renderMode);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.isActive = false;
    }
    
    public static float calculateDamage(final EntityEnderCrystal entityEnderCrystal, final Entity entity) {
        return calculateDamage(entityEnderCrystal.posX, entityEnderCrystal.posY, entityEnderCrystal.posZ, entity);
    }
    
    public static Float lambda$onClientTick$3(final EntityEnderCrystal entityEnderCrystal) {
        return AutoCrystal.mc.player.getDistance((Entity)entityEnderCrystal);
    }
    
    public static float calculateDamage(final double n, final double n2, final double n3, final Entity entity) {
        final float n4 = 12.0f;
        final double n5 = (1.0 - entity.getDistance(n, n2, n3) / n4) * entity.world.getBlockDensity(new Vec3d(n, n2, n3), entity.getEntityBoundingBox());
        final float n6 = (float)(int)((n5 * n5 + n5) / 2.0 * 7.0 * n4 + 1.0);
        double n7 = 1.0;
        if (entity instanceof EntityLivingBase) {
            n7 = getBlastReduction((EntityLivingBase)entity, getDamageMultiplied(n6), new Explosion((World)Minecraft.getMinecraft().world, (Entity)null, n, n2, n3, 6.0f, false, true));
        }
        return (float)n7;
    }
    
    public static boolean lambda$onClientTick$6(final EntityPlayer entityPlayer) {
        return entityPlayer.getHealth() > 0.0f;
    }
    
    public static boolean lambda$onClientTick$7(final EntityPlayer entityPlayer) {
        return !entityPlayer.isDead;
    }
    
    public List findCrystalBlocks() {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)this.getSphere(getPlayerPos(), this.placeRange.getValue().floatValue(), (int)this.placeRange.getValue().floatValue(), false, true, 0).stream().filter(this::canPlaceCrystal).collect(Collectors.toList()));
        return (List)create;
    }
    
    public static void setYawAndPitch(final float n, final float n2) {
        AutoCrystal.yaw = n;
        AutoCrystal.pitch = n2;
        AutoCrystal.isSpoofingAngles = true;
    }
    
    @SubscribeEvent
    public boolean onPacket(final PacketEvent$In packetEvent$In) {
        final Packet<?> packet = packetEvent$In.packet;
        if (packet instanceof SPacketSoundEffect) {
            if (this.nodesync.getValue()) {
                final SPacketSoundEffect sPacketSoundEffect = (SPacketSoundEffect)packet;
                if (sPacketSoundEffect.getCategory() == SoundCategory.BLOCKS) {
                    if (sPacketSoundEffect.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                        try {
                            for (final Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
                                if (entity instanceof EntityEnderCrystal && entity.getDistance(sPacketSoundEffect.getX(), sPacketSoundEffect.getY(), sPacketSoundEffect.getZ()) <= 6.0) {
                                    entity.setDead();
                                }
                            }
                        }
                        catch (ConcurrentModificationException ex) {}
                    }
                }
            }
        }
        return true;
    }
    
    public static boolean lambda$onClientTick$0(final Entity entity) {
        return entity instanceof EntityEnderCrystal;
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        final Color rainbow = ColorUtils.rainbow();
        final Color color = new Color(rainbow.getRed(), rainbow.getGreen(), rainbow.getBlue(), 255);
        if (this.render != null && AutoCrystal.mc.player != null) {
            if (this.rainbow.getValue()) {
                this.drawCurrentBlock(this.render, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
            }
            else {
                this.drawCurrentBlock(this.render, this.espColor.getColor().getRed(), this.espColor.getColor().getGreen(), this.espColor.getColor().getBlue(), 255);
            }
        }
    }
    
    public static void resetRotation() {
        if (AutoCrystal.isSpoofingAngles) {
            AutoCrystal.yaw = Minecraft.getMinecraft().player.rotationYaw;
            AutoCrystal.pitch = Minecraft.getMinecraft().player.rotationPitch;
            AutoCrystal.isSpoofingAngles = false;
        }
    }
    
    public List getSphere(final BlockPos blockPos, final float n, final int n2, final boolean b, final boolean b2, final int n3) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int x = blockPos.getX();
        final int y = blockPos.getY();
        final int z = blockPos.getZ();
        for (int n4 = x - (int)n; n4 <= x + n; ++n4) {
            for (int n5 = z - (int)n; n5 <= z + n; ++n5) {
                for (int n6 = b2 ? (y - (int)n) : y; n6 < (b2 ? (y + n) : ((float)(y + n2))); ++n6) {
                    final double n7 = (x - n4) * (x - n4) + (z - n5) * (z - n5) + (b2 ? ((y - n6) * (y - n6)) : 0);
                    if (n7 < n * n) {
                        if (b) {
                            if (n7 < (n - 1.0f) * (n - 1.0f)) {
                                continue;
                            }
                        }
                        list.add(new BlockPos(n4, n6 + n3, n5));
                    }
                }
            }
        }
        return list;
    }
    
    public static float getDamageMultiplied(final float n) {
        final int difficultyId = Minecraft.getMinecraft().world.getDifficulty().getDifficultyId();
        return n * ((difficultyId == 0) ? 0.0f : ((difficultyId == 2) ? 1.0f : ((difficultyId == 1) ? 0.5f : 1.5f)));
    }
    
    public boolean lambda$onClientTick$1(final Entity entity) {
        return AutoCrystal.mc.player.getDistance(entity) <= this.range.getValue();
    }
    
    public static boolean lambda$onClientTick$9(final EntityPlayer entityPlayer) {
        return !FriendManager.friendsList.contains(entityPlayer.getName()) && !AutoCrystal.mc.player.getName().equals(entityPlayer.getName());
    }
    
    public boolean isEatingGap() {
        return AutoCrystal.mc.player.getHeldItemMainhand().getItem() instanceof ItemAppleGold && AutoCrystal.mc.player.isHandActive();
    }
    
    public static boolean lambda$onClientTick$5(final EntityPlayer entityPlayer) {
        return AutoCrystal.mc.player.getDistance((Entity)entityPlayer) <= 11.0f;
    }
    
    public static boolean lambda$onClientTick$4(final EntityPlayer entityPlayer) {
        return AutoCrystal.mc.player != entityPlayer;
    }
    
    public boolean canPlaceCrystal(final BlockPos blockPos) {
        final BlockPos add = blockPos.add(0, 1, 0);
        BlockPos blockPos2 = blockPos.add(0, 2, 0);
        if (this.onePointThirteen.getValue()) {
            blockPos2 = blockPos.add(0, 1, 0);
        }
        if (AutoCrystal.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK) {
            if (AutoCrystal.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
        }
        if (AutoCrystal.mc.world.getBlockState(add).getBlock() == Blocks.AIR && AutoCrystal.mc.world.getBlockState(blockPos2).getBlock() == Blocks.AIR && AutoCrystal.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).isEmpty() && AutoCrystal.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos2)).isEmpty()) {
            return true;
        }
        return false;
    }
    
    public void lookAtPacket(final double n, final double n2, final double n3, final EntityPlayer entityPlayer) {
        final double[] calculateLook = calculateLookAt(n, n2, n3, entityPlayer);
        setYawAndPitch((float)calculateLook[0], (float)calculateLook[1]);
    }
    
    public void drawCurrentBlock(final BlockPos blockPos, final int n, final int n2, final int n3, final int n4) {
        if (this.renderMode.getMode("Full").isToggled()) {
            RenderUtils.drawBlockESP(blockPos, n / 255.0f, n2 / 255.0f, n3 / 255.0f, this.espHeight.getValue());
        }
        else if (this.renderMode.getMode("Outline").isToggled()) {
            RenderUtils.drawOutlinedBox(HoleESP.AxisAligned(blockPos), n / 255.0f, n2 / 255.0f, n3 / 255.0f, 1.0f, this.espHeight.getValue());
        }
    }
    
    public static Float lambda$onClientTick$10(final EntityPlayer entityPlayer) {
        return AutoCrystal.mc.player.getDistance((Entity)entityPlayer);
    }
    
    public static float getBlastReduction(final EntityLivingBase entityLivingBase, float n, final Explosion explosion) {
        if (entityLivingBase instanceof EntityPlayer) {
            final EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
            final DamageSource causeExplosionDamage = DamageSource.causeExplosionDamage(explosion);
            n = CombatRules.getDamageAfterAbsorb(n, (float)entityPlayer.getTotalArmorValue(), (float)entityPlayer.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
            n *= 1.0f - MathHelper.clamp((float)EnchantmentHelper.getEnchantmentModifierDamage(entityPlayer.getArmorInventoryList(), causeExplosionDamage), 0.0f, 20.0f) / 25.0f;
            if (entityLivingBase.isPotionActive(Potion.getPotionById(11))) {
                n -= n / 4.0f;
            }
            return n;
        }
        n = CombatRules.getDamageAfterAbsorb(n, (float)entityLivingBase.getTotalArmorValue(), (float)entityLivingBase.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
        return n;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        this.isActive = false;
        if (AutoCrystal.mc.player == null || AutoCrystal.mc.player.isDead) {
            return;
        }
        final EntityEnderCrystal entityEnderCrystal = (EntityEnderCrystal)AutoCrystal.mc.world.loadedEntityList.stream().filter(AutoCrystal::lambda$onClientTick$0).filter(this::lambda$onClientTick$1).map(AutoCrystal::lambda$onClientTick$2).min(Comparator.comparing((Function<? super T, ? extends Comparable>)AutoCrystal::lambda$onClientTick$3)).orElse(null);
        if ((boolean)this.explode.getValue() && entityEnderCrystal != null) {
            if (!AutoCrystal.mc.player.canEntityBeSeen((Entity)entityEnderCrystal) && AutoCrystal.mc.player.getDistance((Entity)entityEnderCrystal) > this.walls.getValue()) {
                return;
            }
            if (this.waitTick.getValue() > 0) {
                if (this.waitCounter < this.waitTick.getValue()) {
                    ++this.waitCounter;
                    return;
                }
                this.waitCounter = 0;
            }
            if ((boolean)this.antiWeakness.getValue() && AutoCrystal.mc.player.isPotionActive(MobEffects.WEAKNESS)) {
                if (!this.isAttacking) {
                    this.oldSlot = AutoCrystal.mc.player.inventory.currentItem;
                    this.isAttacking = true;
                }
                this.newSlot = -1;
                for (int i = 0; i < 9; ++i) {
                    final ItemStack stackInSlot = AutoCrystal.mc.player.inventory.getStackInSlot(i);
                    if (stackInSlot != ItemStack.EMPTY) {
                        if (stackInSlot.getItem() instanceof ItemSword) {
                            this.newSlot = i;
                            break;
                        }
                        if (stackInSlot.getItem() instanceof ItemTool) {
                            this.newSlot = i;
                            break;
                        }
                    }
                }
                if (this.newSlot != -1) {
                    AutoCrystal.mc.player.inventory.currentItem = this.newSlot;
                    this.switchCooldown = true;
                }
            }
            boolean b = this.minHitDmg.getValue() <= 0.0;
            if (this.minHitDmg.getValue() >= 0.0) {
                for (final Entity entity : (List)AutoCrystal.mc.world.playerEntities.stream().filter(AutoCrystal::lambda$onClientTick$4).filter(AutoCrystal::lambda$onClientTick$5).filter(AutoCrystal::lambda$onClientTick$6).filter(AutoCrystal::lambda$onClientTick$7).filter(AutoCrystal::lambda$onClientTick$8).collect(Collectors.toList())) {
                    if (entityEnderCrystal != null) {
                        b = (calculateDamage(entityEnderCrystal, entity) >= this.minHitDmg.getValue());
                    }
                }
            }
            this.isActive = true;
            if (b) {
                if (this.rotate.getValue()) {
                    this.lookAtPacket(entityEnderCrystal.posX, entityEnderCrystal.posY, entityEnderCrystal.posZ, (EntityPlayer)AutoCrystal.mc.player);
                }
                AutoCrystal.mc.playerController.attackEntity((EntityPlayer)AutoCrystal.mc.player, (Entity)entityEnderCrystal);
                AutoCrystal.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
            this.isActive = false;
        }
        else {
            resetRotation();
            if (this.oldSlot != -1) {
                AutoCrystal.mc.player.inventory.currentItem = this.oldSlot;
                this.oldSlot = -1;
            }
            this.isAttacking = false;
            this.isActive = false;
            int currentItem = (AutoCrystal.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) ? AutoCrystal.mc.player.inventory.currentItem : -1;
            if (currentItem == -1) {
                for (int j = 0; j < 9; ++j) {
                    if (AutoCrystal.mc.player.inventory.getStackInSlot(j).getItem() == Items.END_CRYSTAL) {
                        currentItem = j;
                        break;
                    }
                }
            }
            boolean b2 = false;
            if (AutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
                b2 = true;
            }
            else if (currentItem == -1) {
                return;
            }
            final List crystalBlocks = this.findCrystalBlocks();
            final ArrayList<Entity> list = new ArrayList<Entity>();
            list.addAll((Collection<?>)AutoCrystal.mc.world.playerEntities.stream().filter(AutoCrystal::lambda$onClientTick$9).sorted(Comparator.comparing((Function<? super T, ? extends Comparable>)AutoCrystal::lambda$onClientTick$10)).collect(Collectors.toList()));
            BlockPos render = null;
            double n = 0.5;
            for (final Entity renderEnt : list) {
                if (renderEnt == AutoCrystal.mc.player) {
                    continue;
                }
                if (((EntityLivingBase)renderEnt).getHealth() <= 0.0f || renderEnt.isDead) {
                    continue;
                }
                if (AutoCrystal.mc.player == null) {
                    continue;
                }
                for (final BlockPos blockPos : crystalBlocks) {
                    if (renderEnt.getDistanceSq(blockPos) >= 169.0) {
                        continue;
                    }
                    final double n2 = calculateDamage(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, renderEnt);
                    if (n2 < this.minDmg.getValue() && ((EntityLivingBase)renderEnt).getHealth() + ((EntityLivingBase)renderEnt).getAbsorptionAmount() > this.facePlace.getValue()) {
                        continue;
                    }
                    if (n2 <= n) {
                        continue;
                    }
                    final double n3 = calculateDamage(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, (Entity)AutoCrystal.mc.player);
                    if (n3 > n2 && n2 >= ((EntityLivingBase)renderEnt).getHealth()) {
                        continue;
                    }
                    if (n3 - 0.5 > AutoCrystal.mc.player.getHealth()) {
                        continue;
                    }
                    if (n3 > this.maxSelfDmg.getValue()) {
                        continue;
                    }
                    n = n2;
                    render = blockPos;
                    this.renderEnt = renderEnt;
                }
            }
            if (n == 0.5) {
                this.render = null;
                this.renderEnt = null;
                resetRotation();
                return;
            }
            this.render = render;
            if (this.place.getValue()) {
                if (AutoCrystal.mc.player == null) {
                    return;
                }
                this.isActive = true;
                if (this.rotate.getValue()) {
                    this.lookAtPacket(render.getX() + 0.5, render.getY() - 0.5, render.getZ() + 0.5, (EntityPlayer)AutoCrystal.mc.player);
                }
                final RayTraceResult rayTraceBlocks = AutoCrystal.mc.world.rayTraceBlocks(new Vec3d(AutoCrystal.mc.player.posX, AutoCrystal.mc.player.posY + AutoCrystal.mc.player.getEyeHeight(), AutoCrystal.mc.player.posZ), new Vec3d(render.getX() + 0.5, render.getY() - 0.5, render.getZ() + 0.5));
                if (this.raytrace.getValue()) {
                    if (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) {
                        this.f = null;
                        this.render = null;
                        resetRotation();
                        this.isActive = false;
                        return;
                    }
                    this.f = rayTraceBlocks.sideHit;
                }
                if (!b2 && AutoCrystal.mc.player.inventory.currentItem != currentItem) {
                    if (this.autoSwitch.getValue()) {
                        if ((boolean)this.noGappleSwitch.getValue() && this.isEatingGap()) {
                            this.isActive = false;
                            resetRotation();
                            return;
                        }
                        this.isActive = true;
                        AutoCrystal.mc.player.inventory.currentItem = currentItem;
                        resetRotation();
                        this.switchCooldown = true;
                    }
                    return;
                }
                if (this.switchCooldown) {
                    this.switchCooldown = false;
                    return;
                }
                if (render != null && AutoCrystal.mc.player != null) {
                    this.isActive = true;
                    if ((boolean)this.raytrace.getValue() && this.f != null) {
                        AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(render, this.f, b2 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                    }
                    else {
                        AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(render, EnumFacing.UP, b2 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                    }
                }
                this.isActive = false;
            }
        }
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(Minecraft.getMinecraft().player.posX), Math.floor(Minecraft.getMinecraft().player.posY), Math.floor(Minecraft.getMinecraft().player.posZ));
    }
}
