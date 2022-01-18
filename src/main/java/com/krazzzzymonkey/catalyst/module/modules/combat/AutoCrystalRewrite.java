/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.network.play.client.CPacketPlayer;
import com.krazzzzymonkey.catalyst.events.PacketEvent$Out;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.util.math.Vec3d;
import com.krazzzzymonkey.catalyst.module.modules.render.HoleESP;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.events.MotionEvent$PREWALK;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import com.krazzzzymonkey.catalyst.utils.EntityUtils;
import net.minecraft.item.ItemTool;
import net.minecraft.item.ItemSword;
import java.util.Objects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;
import com.krazzzzymonkey.catalyst.managers.FriendManager;
import com.krazzzzymonkey.catalyst.module.modules.chat.AutoGG;
import com.krazzzzymonkey.catalyst.utils.CrystalUtils;
import net.minecraft.util.math.AxisAlignedBB;
import com.krazzzzymonkey.catalyst.utils.MathUtils;
import java.util.Iterator;
import net.minecraft.util.EnumHand;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.network.play.server.SPacketDestroyEntities;
import com.krazzzzymonkey.catalyst.utils.BlockUtils;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketUseEntity$Action;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.world.World;
import java.util.Collection;
import java.util.ArrayList;
import net.minecraft.network.play.server.SPacketSpawnObject;
import com.krazzzzymonkey.catalyst.events.PacketEvent$In;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.entity.player.EntityPlayer;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import net.minecraft.entity.item.EntityEnderCrystal;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class AutoCrystalRewrite extends Modules
{
    public int obiFeetCounter;
    public boolean didAnything;
    public ModeValue facePlaceLogic;
    public int chainCount;
    public BooleanValue spoofPlaceSwing;
    public BooleanValue stopOnSword;
    public IntegerValue hitDelay;
    public DoubleValue hitRange;
    public BooleanValue ignoreBlocks;
    public BooleanValue multiPlace;
    public double renderDamageVal;
    public float pitch;
    public boolean facePlacing;
    public BlockPos staticPos;
    public BooleanValue multiThreaded;
    public boolean hasPacketBroke;
    public ModeValue crystalLogic;
    public ModeValue rotateMode;
    public boolean placeTimeoutFlag;
    public BooleanValue place;
    public boolean alreadyAttacking;
    public int currentChainCounter;
    public IntegerValue placeDelay;
    public IntegerValue facePlaceHealth;
    public int breakDelayCounter;
    public IntegerValue maxPredictTime;
    public IntegerValue targetHealthPlace;
    public BooleanValue rainbow;
    public BooleanValue hit;
    public DoubleValue placeRange;
    public IntegerValue espHeight;
    public boolean isRotating;
    public DoubleValue hitWallRange;
    public BooleanValue packetHit;
    public List<EntityEnderCrystal> attemptedCrystals;
    public EntityEnderCrystal staticEnderCrystal;
    public BooleanValue antiWeakness;
    public IntegerValue facePlaceDelay;
    public DoubleValue targetRange;
    public int placeDelayCounter;
    public static AutoCrystalRewrite INSTANCE;
    public ColorValue espColor;
    public int placeTimeout;
    public BooleanValue packetEntityPredict;
    public IntegerValue maxSelfDmg;
    public BlockPos renderBlock;
    public ModeValue autoSwitch;
    public EntityPlayer ezTarget;
    public BooleanValue packetBlockPredict;
    public BooleanValue raytrace;
    public long start;
    public BooleanValue facePlace;
    public ModeValue renderMode;
    public int breakTimeout;
    public IntegerValue facePlaceArmorDur;
    public float yaw;
    public IntegerValue targetHealthBreak;
    public long crystalLatency;
    public int facePlaceDelayCounter;
    public BooleanValue packetCrystalPredict;
    public BooleanValue thirteen;
    public BooleanValue antiSuicide;
    public DoubleValue placeWallRange;
    public ModeValue swing;
    
    public void doCrystalAura() {
        if (AutoCrystalRewrite.mc.player == null || AutoCrystalRewrite.mc.world == null) {
            this.toggle();
            return;
        }
        this.didAnything = false;
        if ((boolean)this.place.getValue() && this.placeDelayCounter > this.placeTimeout && (this.facePlaceDelayCounter >= this.facePlaceDelay.getValue() || !this.facePlacing)) {
            this.start = System.currentTimeMillis();
            this.placeCrystal();
        }
        if ((boolean)this.hit.getValue() && this.breakDelayCounter > this.breakTimeout && !this.hasPacketBroke) {
            this.breakCrystal();
        }
        if (!this.didAnything) {
            this.hasPacketBroke = false;
            this.ezTarget = null;
            this.isRotating = false;
            this.currentChainCounter = 0;
        }
        ++this.currentChainCounter;
        ++this.breakDelayCounter;
        ++this.placeDelayCounter;
        ++this.facePlaceDelayCounter;
        ++this.obiFeetCounter;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (!this.rotateMode.getMode("Rotate").isToggled()) {
            this.doCrystalAura();
        }
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent$In packetEvent$In) {
        final Packet<?> packet = packetEvent$In.packet;
        final SPacketSpawnObject sPacketSpawnObject;
        if (packet instanceof SPacketSpawnObject && (sPacketSpawnObject = (SPacketSpawnObject)packet).getType() == 51) {
            final Iterator<EntityPlayer> iterator = new ArrayList<EntityPlayer>(AutoCrystalRewrite.mc.world.playerEntities).iterator();
            while (iterator.hasNext()) {
                if (this.isCrystalGood(new EntityEnderCrystal((World)AutoCrystalRewrite.mc.world, sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ()), iterator.next()) != 0.0) {
                    if (this.packetCrystalPredict.getValue()) {
                        final CPacketUseEntity cPacketUseEntity = new CPacketUseEntity();
                        cPacketUseEntity.entityId = sPacketSpawnObject.getEntityID();
                        cPacketUseEntity.action = CPacketUseEntity$Action.ATTACK;
                        AutoCrystalRewrite.mc.player.connection.sendPacket((Packet)cPacketUseEntity);
                        if (!this.swing.getMode("Cancel").isToggled()) {
                            BlockUtils.swingArm(this.swing);
                        }
                        this.hasPacketBroke = true;
                        this.didAnything = true;
                        break;
                    }
                    break;
                }
            }
        }
        if (packet instanceof SPacketDestroyEntities) {
            for (final int n : ((SPacketDestroyEntities)packet).getEntityIDs()) {
                try {
                    final Entity entityByID = AutoCrystalRewrite.mc.world.getEntityByID(n);
                    if (entityByID instanceof EntityEnderCrystal) {
                        this.attemptedCrystals.remove(entityByID);
                    }
                }
                catch (Exception ex) {}
            }
        }
        if (packet instanceof SPacketSoundEffect && ((SPacketSoundEffect)packet).getCategory() == SoundCategory.BLOCKS && ((SPacketSoundEffect)packet).getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
            for (final Entity entity : new ArrayList<Entity>(AutoCrystalRewrite.mc.world.loadedEntityList)) {
                if (entity instanceof EntityEnderCrystal && entity.getDistance(((SPacketSoundEffect)packet).getX(), ((SPacketSoundEffect)packet).getY(), ((SPacketSoundEffect)packet).getZ()) <= this.hitRange.getValue()) {
                    this.crystalLatency = System.currentTimeMillis() - this.start;
                    if (!this.crystalLogic.getMode("CheckCrystal").isToggled()) {
                        continue;
                    }
                    entity.setDead();
                }
            }
        }
        if (packet instanceof SPacketExplosion) {
            final SPacketExplosion sPacketExplosion = (SPacketExplosion)packet;
            final BlockPos down = new BlockPos(Math.floor(sPacketExplosion.getX()), Math.floor(sPacketExplosion.getY()), Math.floor(sPacketExplosion.getZ())).down();
            if (this.packetBlockPredict.getValue()) {
                final Iterator<EntityPlayer> iterator3 = new ArrayList<EntityPlayer>(AutoCrystalRewrite.mc.world.playerEntities).iterator();
                while (iterator3.hasNext()) {
                    if (this.isBlockGood(down, iterator3.next()) > 0.0) {
                        if (AutoCrystalRewrite.mc.player.getHeldItemOffhand().getItem() instanceof ItemEndCrystal && !(AutoCrystalRewrite.mc.player.getHeldItemMainhand().getItem() instanceof ItemEndCrystal)) {
                            BlockUtils.placeCrystalOnBlock(down, EnumHand.OFF_HAND, (boolean)this.spoofPlaceSwing.getValue());
                        }
                        else {
                            BlockUtils.placeCrystalOnBlock(down, EnumHand.MAIN_HAND, (boolean)this.spoofPlaceSwing.getValue());
                        }
                    }
                }
            }
        }
    }
    
    public EntityEnderCrystal getBestCrystal() {
        double n = 0.0;
        EntityEnderCrystal entityEnderCrystal = null;
        for (final Entity entity : AutoCrystalRewrite.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityEnderCrystal)) {
                continue;
            }
            final EntityEnderCrystal entityEnderCrystal2 = (EntityEnderCrystal)entity;
            for (final EntityPlayer ezTarget : new ArrayList<EntityPlayer>(AutoCrystalRewrite.mc.world.playerEntities)) {
                if (AutoCrystalRewrite.mc.player.getDistanceSq((Entity)ezTarget) > MathUtils.square(this.targetRange.getValue().floatValue())) {
                    continue;
                }
                if (this.packetEntityPredict.getValue()) {
                    if (ezTarget != AutoCrystalRewrite.mc.player) {
                        final float n2 = ezTarget.width / 2.0f;
                        ezTarget.setEntityBoundingBox(new AxisAlignedBB(ezTarget.posX - n2, ezTarget.posY, ezTarget.posZ - n2, ezTarget.posX + n2, ezTarget.posY + ezTarget.height, ezTarget.posZ + n2));
                        ezTarget.setEntityBoundingBox(CrystalUtils.getPredictedPosition((Entity)ezTarget, this.maxPredictTime.getValue()).getEntityBoundingBox());
                    }
                }
                final double crystalGood = this.isCrystalGood(entityEnderCrystal2, ezTarget);
                if (crystalGood == 0.0) {
                    continue;
                }
                if (crystalGood <= n) {
                    continue;
                }
                n = crystalGood;
                this.ezTarget = ezTarget;
                entityEnderCrystal = entityEnderCrystal2;
            }
        }
        if (this.ezTarget != null) {
            AutoGG.addTargetedPlayer(this.ezTarget.getName());
        }
        return entityEnderCrystal;
    }
    
    public boolean isPlayerValid(final EntityPlayer entityPlayer) {
        if (entityPlayer.getHealth() + entityPlayer.getAbsorptionAmount() > 0.0f) {
            if (entityPlayer != AutoCrystalRewrite.mc.player) {
                return !FriendManager.friendsList.contains(entityPlayer.getName()) && !entityPlayer.getName().equals(AutoCrystalRewrite.mc.player.getName()) && entityPlayer.getDistanceSq((Entity)AutoCrystalRewrite.mc.player) <= 169.0;
            }
        }
        return false;
    }
    
    public void breakCrystal() {
        EntityEnderCrystal yawPitch;
        if ((boolean)this.multiThreaded.getValue() && AutoCrystalRewrite.mc.fpsCounter > 60) {
            new Threads(ThreadType.CRYSTAL).start();
            yawPitch = this.staticEnderCrystal;
        }
        else {
            yawPitch = this.getBestCrystal();
        }
        if (yawPitch == null) {
            return;
        }
        if (CrystalUtils.calculateDamage((Entity)yawPitch, (Entity)AutoCrystalRewrite.mc.player, false) > this.maxSelfDmg.getValue()) {
            this.staticEnderCrystal = null;
            return;
        }
        Label_0551: {
            if ((boolean)this.antiWeakness.getValue() && AutoCrystalRewrite.mc.player.isPotionActive(MobEffects.WEAKNESS)) {
                boolean b = true;
                if (AutoCrystalRewrite.mc.player.isPotionActive(MobEffects.STRENGTH) && Objects.requireNonNull(AutoCrystalRewrite.mc.player.getActivePotionEffect(MobEffects.STRENGTH)).getAmplifier() == 2) {
                    b = false;
                }
                if (b) {
                    while (true) {
                        switch (1582891105 - 351339955 + 1) {
                            case -1997283686: {
                                continue;
                            }
                            case -1252525524: {
                                if (!this.alreadyAttacking) {
                                    this.alreadyAttacking = true;
                                }
                                int currentItem = -1;
                                int i = 0;
                                while (true) {
                                    while (i < 9) {
                                        final ItemStack stackInSlot = AutoCrystalRewrite.mc.player.inventory.getStackInSlot(i);
                                        if (stackInSlot.getItem() instanceof ItemSword || stackInSlot.getItem() instanceof ItemTool) {
                                            currentItem = i;
                                            AutoCrystalRewrite.mc.playerController.updateController();
                                            if (currentItem != -1) {
                                                AutoCrystalRewrite.mc.player.inventory.currentItem = currentItem;
                                                break Label_0551;
                                            }
                                            break Label_0551;
                                        }
                                        else {
                                            ++i;
                                        }
                                    }
                                    continue;
                                }
                            }
                            default: {
                                throw null;
                            }
                        }
                    }
                }
            }
        }
        this.didAnything = true;
        this.setYawPitch(yawPitch);
        EntityUtils.attackEntity((Entity)yawPitch, (boolean)this.packetHit.getValue());
        if (!this.swing.getMode("Cancel").isToggled()) {
            BlockUtils.swingArm(this.swing);
        }
        this.breakDelayCounter = 0;
    }
    
    public void setPlayerRotations(final float n, final float rotationPitch) {
        AutoCrystalRewrite.mc.player.rotationYaw = n;
        AutoCrystalRewrite.mc.player.rotationYawHead = n;
        AutoCrystalRewrite.mc.player.rotationPitch = rotationPitch;
    }
    
    public void placeCrystal() {
        BlockPos yawPitch = null;
        Label_0147: {
            if (this.multiThreaded.getValue()) {
                if (AutoCrystalRewrite.mc.fpsCounter > 60) {
                    new Threads(ThreadType.BLOCK).start();
                    yawPitch = this.staticPos;
                    break Label_0147;
                }
            }
            yawPitch = this.getBestBlock();
        }
        if (yawPitch == null) {
            return;
        }
        this.placeDelayCounter = 0;
        this.facePlaceDelayCounter = 0;
        this.alreadyAttacking = false;
        boolean b = false;
        if (CrystalUtils.calculateDamage(yawPitch, (Entity)AutoCrystalRewrite.mc.player, false) > this.maxSelfDmg.getValue()) {
            return;
        }
        if (AutoCrystalRewrite.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
            if (AutoCrystalRewrite.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL) {
                if (this.autoSwitch.getMode("Everything").isToggled() || this.autoSwitch.getMode("NoGapSwitch").isToggled()) {
                    if (this.autoSwitch.getMode("NoGapSwitch").isToggled() && AutoCrystalRewrite.mc.player.getHeldItemMainhand().getItem() == Items.GOLDEN_APPLE) {
                        return;
                    }
                    if (this.findCrystalsHotbar() == -1) {
                        return;
                    }
                    AutoCrystalRewrite.mc.player.inventory.currentItem = this.findCrystalsHotbar();
                    AutoCrystalRewrite.mc.playerController.syncCurrentPlayItem();
                }
            }
        }
        else {
            b = true;
        }
        this.didAnything = true;
        if (AutoCrystalRewrite.mc.player.getHeldItemMainhand().getItem() instanceof ItemEndCrystal || AutoCrystalRewrite.mc.player.getHeldItemOffhand().getItem() instanceof ItemEndCrystal) {
            this.setYawPitch(yawPitch);
            BlockUtils.placeCrystalOnBlock(yawPitch, b ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, !(boolean)this.spoofPlaceSwing.getValue());
        }
    }
    
    public void setYawPitch(final EntityEnderCrystal entityEnderCrystal) {
        final float[] calcAngle = MathUtils.calcAngle(AutoCrystalRewrite.mc.player.getPositionEyes(AutoCrystalRewrite.mc.getRenderPartialTicks()), entityEnderCrystal.getPositionEyes(AutoCrystalRewrite.mc.getRenderPartialTicks()));
        this.yaw = calcAngle[0];
        this.pitch = calcAngle[1];
        this.isRotating = true;
    }
    
    public double isCrystalGood(final EntityEnderCrystal entityEnderCrystal, final EntityPlayer entityPlayer) {
        if (!this.isPlayerValid(entityPlayer)) {
            return 0.0;
        }
        if (AutoCrystalRewrite.mc.player.canEntityBeSeen((Entity)entityEnderCrystal)) {
            if (AutoCrystalRewrite.mc.player.getDistanceSq((Entity)entityEnderCrystal) > MathUtils.square(this.hitRange.getValue().floatValue())) {
                return 0.0;
            }
        }
        else if (AutoCrystalRewrite.mc.player.getDistanceSq((Entity)entityEnderCrystal) > MathUtils.square(this.hitWallRange.getValue().floatValue())) {
            return 0.0;
        }
        if (entityEnderCrystal.isDead) {
            return 0.0;
        }
        if (this.attemptedCrystals.contains(entityEnderCrystal)) {
            return 0.0;
        }
        double n = 0.0;
        Label_0720: {
            if (CrystalUtils.calculateDamage((Entity)entityEnderCrystal, (Entity)entityPlayer, (boolean)this.ignoreBlocks.getValue()) >= this.targetHealthPlace.getValue()) {
                this.facePlacing = false;
                n = this.targetHealthBreak.getValue();
            }
            else {
                Label_0687: {
                    Label_0593: {
                        if (EntityUtils.getHealth((Entity)entityPlayer) > this.facePlaceHealth.getValue() || !(boolean)this.facePlace.getValue()) {
                            if (!CrystalUtils.getArmourFucker(entityPlayer, this.facePlaceArmorDur.getValue())) {
                                break Label_0687;
                            }
                            final ModeValue facePlaceLogic = this.facePlaceLogic;
                            final String s = "None";
                            while (true) {
                                switch (-247305904 + 838090074 + 1) {
                                    case -2104493769: {
                                        continue;
                                    }
                                    case -1061792758: {
                                        if (!facePlaceLogic.getMode(s).isToggled()) {
                                            break Label_0593;
                                        }
                                        break Label_0687;
                                    }
                                    default: {
                                        throw null;
                                    }
                                }
                            }
                        }
                    }
                    if (!(boolean)this.stopOnSword.getValue()) {
                        n = (EntityUtils.isInHole((Entity)entityPlayer) ? 1.0 : 2.0);
                        this.facePlacing = true;
                        break Label_0720;
                    }
                }
                this.facePlacing = false;
                n = this.targetHealthBreak.getValue();
            }
        }
        final double n2 = CrystalUtils.calculateDamage((Entity)entityEnderCrystal, (Entity)entityPlayer, (boolean)this.ignoreBlocks.getValue());
        if (n2 < n) {
            if (EntityUtils.getHealth((Entity)entityPlayer) - n2 > 0.0) {
                return 0.0;
            }
        }
        final double n3 = 0.0;
        if (n3 > this.maxSelfDmg.getValue()) {
            return 0.0;
        }
        if (EntityUtils.getHealth((Entity)AutoCrystalRewrite.mc.player) - n3 <= 0.0 && (boolean)this.antiSuicide.getValue()) {
            return 0.0;
        }
        return n2;
    }
    
    public int findCrystalsHotbar() {
        for (int i = 0; i < 9; ++i) {
            if (AutoCrystalRewrite.mc.player.inventory.getStackInSlot(i).getItem() == Items.END_CRYSTAL) {
                return i;
            }
        }
        return -1;
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayerEvent(final MotionEvent$PREWALK motionEvent$PREWALK) {
        if (this.rotateMode.getMode("Rotate").isToggled()) {
            if (this.isRotating) {
                this.setPlayerRotations(this.yaw, this.pitch);
            }
            this.doCrystalAura();
        }
    }
    
    @Override
    public void onEnable() {
        this.placeTimeout = this.placeDelay.getValue();
        this.breakTimeout = this.hitDelay.getValue();
        this.placeTimeoutFlag = false;
        this.isRotating = false;
        this.ezTarget = null;
        this.facePlacing = false;
        this.attemptedCrystals.clear();
        this.hasPacketBroke = false;
        this.placeTimeoutFlag = false;
        this.alreadyAttacking = false;
        this.currentChainCounter = 0;
        this.obiFeetCounter = 0;
        this.crystalLatency = 0L;
        this.start = 0L;
        this.staticEnderCrystal = null;
        this.staticPos = null;
        super.onEnable();
    }
    
    public void drawCurrentBlock(final BlockPos blockPos, final int n, final int n2, final int n3, final int n4) {
        if (this.renderMode.getMode("Full").isToggled()) {
            RenderUtils.drawBlockESP(blockPos, n / 255.0f, n2 / 255.0f, n3 / 255.0f, this.espHeight.getValue());
        }
        else if (this.renderMode.getMode("Outline").isToggled()) {
            RenderUtils.drawOutlinedBox(HoleESP.AxisAligned(blockPos), n / 255.0f, n2 / 255.0f, n3 / 255.0f, 1.0f, this.espHeight.getValue());
        }
    }
    
    public double isBlockGood(final BlockPos blockPos, final EntityPlayer entityPlayer) {
        if (!this.isPlayerValid(entityPlayer)) {
            return 0.0;
        }
        if (!CrystalUtils.canSeePos(blockPos) && (boolean)this.raytrace.getValue()) {
            return 0.0;
        }
        if (!CrystalUtils.canSeePos(blockPos)) {
            if (AutoCrystalRewrite.mc.player.getDistanceSq(blockPos) > MathUtils.square(this.placeWallRange.getValue().floatValue())) {
                return 0.0;
            }
        }
        else if (AutoCrystalRewrite.mc.player.getDistanceSq(blockPos) > MathUtils.square(this.placeRange.getValue().floatValue())) {
            return 0.0;
        }
        double n = 0.0;
        Label_0710: {
            if (CrystalUtils.calculateDamage(blockPos, (Entity)entityPlayer, (boolean)this.ignoreBlocks.getValue()) >= this.targetHealthPlace.getValue()) {
                this.facePlacing = false;
                n = this.targetHealthBreak.getValue();
            }
            else {
                Label_0677: {
                    if (EntityUtils.getHealth((Entity)entityPlayer) > this.facePlaceHealth.getValue() || !(boolean)this.facePlace.getValue()) {
                        if (!CrystalUtils.getArmourFucker(entityPlayer, this.facePlaceArmorDur.getValue())) {
                            break Label_0677;
                        }
                        if (this.facePlaceLogic.getMode("None").isToggled()) {
                            break Label_0677;
                        }
                    }
                    if (!(boolean)this.stopOnSword.getValue()) {
                        n = (EntityUtils.isInHole((Entity)entityPlayer) ? 1.0 : 2.0);
                        this.facePlacing = true;
                        break Label_0710;
                    }
                }
                n = this.targetHealthPlace.getValue();
                this.facePlacing = false;
            }
        }
        final double n2 = CrystalUtils.calculateDamage(blockPos, (Entity)entityPlayer, (boolean)this.ignoreBlocks.getValue());
        if (n2 < n && EntityUtils.getHealth((Entity)entityPlayer) - n2 > 0.0) {
            return 0.0;
        }
        final double n3 = 0.0;
        if (n3 > this.maxSelfDmg.getValue()) {
            return 0.0;
        }
        if (EntityUtils.getHealth((Entity)AutoCrystalRewrite.mc.player) - n3 <= 0.0 && (boolean)this.antiSuicide.getValue()) {
            return 0.0;
        }
        return n2;
    }
    
    public void setYawPitch(final BlockPos blockPos) {
        final float[] calcAngle = MathUtils.calcAngle(AutoCrystalRewrite.mc.player.getPositionEyes(AutoCrystalRewrite.mc.getRenderPartialTicks()), new Vec3d((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() + 0.5f), (double)(blockPos.getZ() + 0.5f)));
        this.yaw = calcAngle[0];
        this.pitch = calcAngle[1];
        this.isRotating = true;
    }
    
    public AutoCrystalRewrite() {
        super("AutoCrystalRewrite", ModuleCategory.COMBAT, "Automatically places and breaks end crystals");
        this.thirteen = new BooleanValue("1.13+Place", false);
        this.place = new BooleanValue("Place", true);
        this.hit = new BooleanValue("Hit", true);
        this.hitRange = new DoubleValue("HitRange", 5.0, 0.0, 6.0);
        this.hitWallRange = new DoubleValue("HitWallRange", 3.5, 0.0, 6.0);
        this.placeRange = new DoubleValue("PlaceRange", 5.0, 0.0, 6.0);
        this.placeWallRange = new DoubleValue("PlaceWallRange", 3.5, 0.0, 6.0);
        this.targetRange = new DoubleValue("TargetRange", 15.0, 0.0, 20.0);
        this.placeDelay = new IntegerValue("PlaceDelay", 0, 0, 10);
        this.hitDelay = new IntegerValue("HitDelay", 0, 0, 10);
        this.targetHealthPlace = new IntegerValue("TargetHealthPlace", 10, 0, 36);
        this.targetHealthBreak = new IntegerValue("TargetHealthBreak", 9, 0, 36);
        this.multiPlace = new BooleanValue("MultiPlace", false);
        this.packetHit = new BooleanValue("PacketHit", true);
        this.antiSuicide = new BooleanValue("AntiSuicide", true);
        this.maxSelfDmg = new IntegerValue("MaxSelfDmg", 5, 0, 36);
        this.rotateMode = new ModeValue("Rotate", new Mode[] { new Mode("RotateOff", true), new Mode("RotatePacket", false), new Mode("Rotate", false) });
        this.stopOnSword = new BooleanValue("StopOnSword", false);
        this.spoofPlaceSwing = new BooleanValue("SpoofPlaceSwing", true);
        this.swing = new ModeValue("BreakSwing", new Mode[] { new Mode("Mainhand", true), new Mode("Offhand", false), new Mode("Cancel", false) });
        this.raytrace = new BooleanValue("Raytrace", false);
        this.autoSwitch = new ModeValue("AutoSwitch", new Mode[] { new Mode("Nothing", false), new Mode("Everything", false), new Mode("NoGapSwitch", false) });
        this.multiThreaded = new BooleanValue("MultiThreaded", false);
        this.packetBlockPredict = new BooleanValue("PacketBlockPredict", true);
        this.packetCrystalPredict = new BooleanValue("PacketCrystalPredict", true);
        this.packetEntityPredict = new BooleanValue("PacketEntityPredict", true);
        this.maxPredictTime = new IntegerValue("MaxPredictTime", 3, 0, 5);
        this.crystalLogic = new ModeValue("CrystalLogic", new Mode[] { new Mode("IgnoreCrystal", true), new Mode("RemoveCrystal", false), new Mode("CheckCrystal", false), new Mode("NoLogic", false) });
        this.facePlace = new BooleanValue("FacePlace", true);
        this.facePlaceLogic = new ModeValue("FacePlaceLogic", new Mode[] { new Mode("Smart", true), new Mode("ArmorDurability", false), new Mode("None", false) });
        this.facePlaceHealth = new IntegerValue("FacePlaceHealth", 8, 0, 36);
        this.facePlaceArmorDur = new IntegerValue("FacePlaceArmorDur", 25, 0, 100);
        this.facePlaceDelay = new IntegerValue("FacePlaceDelay", 0, 0, 10);
        this.antiWeakness = new BooleanValue("AntiWeakness", true);
        this.ignoreBlocks = new BooleanValue("IgnoreBlocks", true);
        this.renderMode = new ModeValue("RenderMode", new Mode[] { new Mode("Full", false), new Mode("Outline", true) });
        this.espColor = new ColorValue("EspColor", Color.CYAN.getRGB());
        this.rainbow = new BooleanValue("EspRainbow", false);
        this.espHeight = new IntegerValue("EspHeight", 1, 0, 2);
        this.attemptedCrystals = new ArrayList<EntityEnderCrystal>();
        this.ezTarget = null;
        this.renderBlock = null;
        this.renderDamageVal = 0.0;
        this.start = 0L;
        this.addValue(this.thirteen, this.place, this.hit, this.hitRange, this.hitWallRange, this.placeRange, this.placeWallRange, this.targetRange, this.placeDelay, this.hitDelay, this.targetHealthPlace, this.targetHealthBreak, this.multiPlace, this.packetHit, this.antiSuicide, this.maxSelfDmg, this.rotateMode, this.stopOnSword, this.spoofPlaceSwing, this.swing, this.raytrace, this.autoSwitch, this.multiThreaded, this.packetBlockPredict, this.packetCrystalPredict, this.packetEntityPredict, this.maxPredictTime, this.crystalLogic, this.facePlace, this.facePlaceLogic, this.facePlaceHealth, this.facePlaceArmorDur, this.facePlaceDelay, this.antiWeakness, this.ignoreBlocks, this.renderMode, this.espColor, this.rainbow, this.espHeight);
        AutoCrystalRewrite.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent$Out packetEvent$Out) {
        final Packet<?> packet = packetEvent$Out.packet;
        if (packet instanceof CPacketPlayer && this.isRotating && this.rotateMode.getMode("RotatePacket").isToggled()) {
            final CPacketPlayer cPacketPlayer = (CPacketPlayer)packet;
            cPacketPlayer.yaw = this.yaw;
            cPacketPlayer.pitch = this.pitch;
        }
        final CPacketUseEntity cPacketUseEntity;
        if (packet instanceof CPacketUseEntity && (cPacketUseEntity = (CPacketUseEntity)packet).getAction() == CPacketUseEntity$Action.ATTACK && cPacketUseEntity.getEntityFromWorld((World)AutoCrystalRewrite.mc.world) instanceof EntityEnderCrystal && this.crystalLogic.getMode("RemoveCrystal").isToggled()) {
            Objects.requireNonNull(cPacketUseEntity.getEntityFromWorld((World)AutoCrystalRewrite.mc.world)).setDead();
            AutoCrystalRewrite.mc.world.removeEntityFromWorld(cPacketUseEntity.entityId);
        }
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        final Color rainbow = ColorUtils.rainbow();
        final Color color = new Color(rainbow.getRed(), rainbow.getGreen(), rainbow.getBlue(), 255);
        if (this.renderBlock != null && AutoCrystalRewrite.mc.player != null) {
            if (this.rainbow.getValue()) {
                this.drawCurrentBlock(this.renderBlock, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
            }
            else {
                this.drawCurrentBlock(this.renderBlock, this.espColor.getColor().getRed(), this.espColor.getColor().getGreen(), this.espColor.getColor().getBlue(), 255);
            }
        }
    }
    
    public BlockPos getBestBlock() {
        if (this.getBestCrystal() != null && this.crystalLogic.getMode("NoLogic").isToggled()) {
            this.placeTimeoutFlag = true;
            return null;
        }
        if (this.placeTimeoutFlag) {
            this.placeTimeoutFlag = false;
            return null;
        }
        double renderDamageVal = 0.0;
        BlockPos renderBlock = null;
        final ArrayList list = new ArrayList();
        for (final EntityPlayer ezTarget : new ArrayList<EntityPlayer>(AutoCrystalRewrite.mc.world.playerEntities)) {
            if (AutoCrystalRewrite.mc.player.getDistanceSq((Entity)ezTarget) > MathUtils.square(this.targetRange.getValue().floatValue())) {
                continue;
            }
            if ((boolean)this.packetEntityPredict.getValue() && ezTarget != AutoCrystalRewrite.mc.player) {
                final float n = ezTarget.width / 2.0f;
                ezTarget.setEntityBoundingBox(new AxisAlignedBB(ezTarget.posX - n, ezTarget.posY, ezTarget.posZ - n, ezTarget.posX + n, ezTarget.posY + ezTarget.height, ezTarget.posZ + n));
                ezTarget.setEntityBoundingBox(CrystalUtils.getPredictedPosition((Entity)ezTarget, this.maxPredictTime.getValue()).getEntityBoundingBox());
            }
            for (final BlockPos blockPos : CrystalUtils.possiblePlacePositions(this.placeRange.getValue().floatValue(), !(boolean)this.multiPlace.getValue(), (boolean)this.thirteen.getValue())) {
                final double blockGood = this.isBlockGood(blockPos, ezTarget);
                if (blockGood == 0.0) {
                    continue;
                }
                if (CrystalUtils.calculateDamage(blockPos, (Entity)AutoCrystalRewrite.mc.player, false) > this.maxSelfDmg.getValue()) {
                    renderBlock = null;
                }
                else {
                    if (blockGood <= renderDamageVal) {
                        continue;
                    }
                    renderDamageVal = blockGood;
                    renderBlock = blockPos;
                    this.ezTarget = ezTarget;
                }
            }
        }
        if (this.ezTarget != null) {
            AutoGG.addTargetedPlayer(this.ezTarget.getName());
        }
        this.renderDamageVal = renderDamageVal;
        return this.renderBlock = renderBlock;
    }
}
