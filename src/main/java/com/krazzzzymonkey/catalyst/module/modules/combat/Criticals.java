/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.combat;

import net.minecraft.init.MobEffects;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.world.World;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer$Position;
import net.minecraft.network.play.client.CPacketUseEntity$Action;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.network.play.client.CPacketUseEntity;
import com.krazzzzymonkey.catalyst.events.PacketEvent$Out;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.utils.TimerUtils;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Criticals extends Modules
{
    public ModeValue mode;
    public boolean cancelSomePackets;
    public TimerUtils timer;
    
    public Criticals() {
        super("Criticals", ModuleCategory.COMBAT, "Makes all your hits do a critical amount of damage");
        this.mode = new ModeValue("Mode", new Mode[] { new Mode("Packet", true), new Mode("Jump", false) });
        this.addValue(this.mode);
        this.timer = new TimerUtils();
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent$Out packetEvent$Out) {
        final Packet<?> packet = packetEvent$Out.packet;
        if (packet instanceof CPacketUseEntity) {
            if (Wrapper.INSTANCE.player().onGround) {
                final CPacketUseEntity cPacketUseEntity = (CPacketUseEntity)packet;
                if (cPacketUseEntity.getAction() == CPacketUseEntity$Action.ATTACK) {
                    if (this.mode.getMode("Packet").isToggled()) {
                        if (Wrapper.INSTANCE.player().collidedVertically && this.timer.isDelay(500L)) {
                            Wrapper.INSTANCE.sendPacket((Packet)new CPacketPlayer$Position(Wrapper.INSTANCE.player().posX, Wrapper.INSTANCE.player().posY + 0.0627, Wrapper.INSTANCE.player().posZ, false));
                            Wrapper.INSTANCE.sendPacket((Packet)new CPacketPlayer$Position(Wrapper.INSTANCE.player().posX, Wrapper.INSTANCE.player().posY, Wrapper.INSTANCE.player().posZ, false));
                            final Entity entityFromWorld = cPacketUseEntity.getEntityFromWorld((World)Wrapper.INSTANCE.world());
                            if (entityFromWorld != null) {
                                Wrapper.INSTANCE.player().onCriticalHit(entityFromWorld);
                            }
                            this.timer.setLastMS();
                            this.cancelSomePackets = true;
                        }
                    }
                    else if (this.mode.getMode("Jump").isToggled() && this.canJump()) {
                        Wrapper.INSTANCE.player().jump();
                    }
                }
            }
            else {
                while (true) {
                    int n = 0;
                    Label_0538: {
                        if (!this.mode.getMode("Packet").isToggled()) {
                            n = -248539245;
                            break Label_0538;
                        }
                        n = -248539236;
                    }
                    switch (n + 1484150539 + 1) {
                        default: {
                            continue;
                        }
                        case -1453735785: {
                            if (packet instanceof CPacketPlayer && this.cancelSomePackets) {
                                this.cancelSomePackets = false;
                                packetEvent$Out.setCanceled(true);
                                break;
                            }
                            break;
                        }
                        case -1453735784: {
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    public boolean canJump() {
        return !Wrapper.INSTANCE.player().isOnLadder() && !Wrapper.INSTANCE.player().isInWater() && !Wrapper.INSTANCE.player().isInLava() && !Wrapper.INSTANCE.player().isSneaking() && !Wrapper.INSTANCE.player().isRiding() && !Wrapper.INSTANCE.player().isPotionActive(MobEffects.BLINDNESS);
    }
}
