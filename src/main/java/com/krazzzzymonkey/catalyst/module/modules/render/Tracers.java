/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.utils.Utils;
import com.krazzzzymonkey.catalyst.managers.FriendManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.utils.ValidUtils;
import net.minecraft.entity.EntityLivingBase;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Tracers extends Modules
{
    public BooleanValue Combat;
    public static float lWidth;
    public BooleanValue Mobs;
    public BooleanValue Friends;
    public static DoubleValue lineWidth;
    public BooleanValue Players;
    public BooleanValue Invisables;
    
    public void render(final EntityLivingBase entityLivingBase, final float n) {
        if ((boolean)this.Mobs.getValue() && (ValidUtils.isValidEntityTracers(entityLivingBase) || entityLivingBase == Wrapper.INSTANCE.player())) {
            RenderUtils.drawLineToEntity((Entity)entityLivingBase, 1.0f, 1.0f, 1.0f, 0.5f);
            return;
        }
        if (!ValidUtils.isValidEntityTracers(entityLivingBase)) {
            if (entityLivingBase != Wrapper.INSTANCE.player()) {
                if ((boolean)this.Friends.getValue() && entityLivingBase instanceof EntityPlayer && FriendManager.friendsList.contains(Utils.getPlayerName((EntityPlayer)entityLivingBase))) {
                    RenderUtils.drawLineToEntity((Entity)entityLivingBase, 0.0f, 0.7f, 1.0f, 1.0f);
                    return;
                }
                if (this.Invisables.getValue()) {
                    if (entityLivingBase.isInvisible()) {
                        RenderUtils.drawLineToEntity((Entity)entityLivingBase, 0.0f, 0.0f, 0.0f, 0.5f);
                        return;
                    }
                }
                if ((boolean)this.Combat.getValue() && entityLivingBase.hurtTime > 0) {
                    RenderUtils.drawLineToEntity((Entity)entityLivingBase, 1.0f, 0.0f, 0.0f, 1.0f);
                    return;
                }
                if ((boolean)this.Players.getValue() && entityLivingBase != Wrapper.INSTANCE.player()) {
                    RenderUtils.drawLineToEntity((Entity)entityLivingBase, 1.0f, 1.0f, 1.0f, 0.5f);
                }
                RenderUtils.drawLineToEntity((Entity)entityLivingBase, 1.0f, 1.0f, 1.0f, 0.5f);
            }
        }
    }
    
    public Tracers() {
        super("Tracers", ModuleCategory.RENDER, "Draws line to entity");
        this.Players = new BooleanValue("Players", true);
        this.Friends = new BooleanValue("Friends", false);
        this.Mobs = new BooleanValue("Mobs", false);
        this.Invisables = new BooleanValue("Invisibles", false);
        this.Combat = new BooleanValue("In Combat", false);
        Tracers.lineWidth = new DoubleValue("Line Width", 1.0, 1.0, 15.0);
        this.addValue(this.Players, this.Friends, this.Mobs, this.Invisables, this.Combat, Tracers.lineWidth);
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        for (final EntityPlayerSP next : Wrapper.INSTANCE.world().loadedEntityList) {
            if (next instanceof EntityLivingBase && !(next instanceof EntityArmorStand) && next != Minecraft.getMinecraft().player) {
                this.render((EntityLivingBase)next, renderWorldLastEvent.getPartialTicks());
            }
        }
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world != null) {
            if (Minecraft.getMinecraft().player != null) {
                if (Minecraft.getMinecraft().player.getUniqueID() != null) {
                    Tracers.lWidth = Tracers.lineWidth.getValue().floatValue();
                }
            }
        }
    }
}
