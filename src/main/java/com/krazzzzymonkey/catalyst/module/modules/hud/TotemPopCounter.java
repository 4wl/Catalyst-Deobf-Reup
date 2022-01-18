/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.hud;

import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.module.modules.gui.ClickGui;
import net.minecraft.client.gui.ScaledResolution;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Text;
import net.minecraft.network.Packet;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import com.krazzzzymonkey.catalyst.utils.ChatColor;
import net.minecraft.world.World;
import net.minecraft.network.play.server.SPacketEntityStatus;
import com.krazzzzymonkey.catalyst.events.PacketEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;
import com.krazzzzymonkey.catalyst.Main;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import net.minecraft.entity.Entity;
import java.util.HashMap;
import com.krazzzzymonkey.catalyst.module.Modules;

public class TotemPopCounter extends Modules
{
    public HashMap<String, Integer> TotemPopContainerArray;
    public int colorRect;
    public int colorRect2;
    public int count;
    public boolean isDrawing;
    public Entity entity;
    public int fade;
    public HashMap<String, Integer> TotemPopContainer;
    public int clearAll;
    public ModeValue arrayLocation;
    public boolean isDead;
    public ModeValue mode;
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        Label_0178: {
            if (this.fade == 1) {
                this.isDrawing = true;
                Logger logger;
                NullPointerException ex;
                try {
                    this.TotemPopContainerArray.put(this.entity.getName(), this.TotemPopContainer.get(this.entity.getName()));
                    break Label_0178;
                }
                catch (NullPointerException ex2) {
                    logger = Main.logger;
                    ex = ex2;
                }
                logger.error((Object)ex);
            }
        }
        if (this.isDrawing) {
            ++this.fade;
            if (this.fade > 500) {
                this.isDrawing = false;
                this.TotemPopContainerArray.remove(this.entity.getName());
                this.fade = 0;
            }
        }
        if (this.fade == 0) {
            ++this.clearAll;
            if (this.clearAll > 500) {
                this.TotemPopContainerArray.clear();
                this.clearAll = 0;
            }
        }
    }
    
    public TotemPopCounter() {
        super("TotemPopCounter", ModuleCategory.HUD, "Shows you how many totems a player has popped in render distance", true);
        this.TotemPopContainer = new HashMap<String, Integer>();
        this.TotemPopContainerArray = new HashMap<String, Integer>();
        this.isDead = false;
        this.colorRect = ColorUtils.color(0.0f, 0.0f, 0.0f, 0.0f);
        this.colorRect2 = ColorUtils.color(0.0f, 0.0f, 0.0f, 0.5f);
        this.clearAll = 0;
        this.mode = new ModeValue("Mode", new Mode[] { new Mode("ArrayList", true), new Mode("Chat", false), new Mode("Gui", false) });
        this.arrayLocation = new ModeValue("ArrayLocation", new Mode[] { new Mode("TopLeft", false), new Mode("TopRight", false), new Mode("BottomLeft", false), new Mode("BottomRight", true) });
        this.addValue(this.mode, this.arrayLocation);
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent packetEvent) {
        final Packet<?> packet = packetEvent.packet;
        if (packet instanceof SPacketEntityStatus) {
            final SPacketEntityStatus sPacketEntityStatus = (SPacketEntityStatus)packet;
            if (sPacketEntityStatus.getOpCode() == 35) {
                this.entity = sPacketEntityStatus.getEntity((World)Minecraft.getMinecraft().world);
                if (sPacketEntityStatus != null) {
                    this.fade = 1;
                    this.count = 1;
                    if (this.TotemPopContainer.containsKey(this.entity.getName())) {
                        this.count = this.TotemPopContainer.get(this.entity.getName());
                        this.TotemPopContainer.put(this.entity.getName(), ++this.count);
                    }
                    else {
                        this.TotemPopContainer.put(this.entity.getName(), this.count);
                    }
                    this.TotemPopContainerArray.put(this.entity.getName(), this.count);
                    if (this.mode.getMode("Chat").isToggled()) {
                        if (this.count > 1) {
                            ChatUtils.message(ChatColor.GOLD + this.entity.getName() + ChatColor.WHITE + " just popped " + ChatColor.RED + this.count + ChatColor.WHITE + " totems.");
                        }
                        else {
                            ChatUtils.message(ChatColor.GOLD + this.entity.getName() + ChatColor.WHITE + " just popped " + ChatColor.RED + this.count + ChatColor.WHITE + " totem.");
                        }
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent$Text renderGameOverlayEvent$Text) {
        int n = 2;
        final ScaledResolution scaledResolution = new ScaledResolution(Wrapper.INSTANCE.mc());
        if (this.arrayLocation.getMode("BottomLeft").isToggled()) {
            n = scaledResolution.getScaledHeight() - 10;
        }
        if (this.arrayLocation.getMode("BottomRight").isToggled()) {
            n = scaledResolution.getScaledHeight() - 10;
        }
        if (this.fade > 1) {
            if (this.mode.getMode("Gui").isToggled()) {
                if (this.count > 1) {
                    RenderUtils.drawStringWithRect(this.entity.getName() + " just popped §6§l" + this.count + "§r totems!", scaledResolution.getScaledWidth() / 2 - Wrapper.INSTANCE.fontRenderer().getStringWidth(this.entity.getName() + " just popped " + this.count + " totem!") / 2, 10, ClickGui.getColor(), this.colorRect, this.colorRect2);
                }
                else {
                    RenderUtils.drawStringWithRect(this.entity.getName() + " just popped §6§l" + this.count + "§r totem!", scaledResolution.getScaledWidth() / 2 - Wrapper.INSTANCE.fontRenderer().getStringWidth(this.entity.getName() + " just popped " + this.count + " totem!") / 2, 10, ClickGui.getColor(), this.colorRect, this.colorRect2);
                }
            }
        }
        for (final EntityPlayer next : Wrapper.INSTANCE.world().loadedEntityList) {
            if (next instanceof EntityPlayer) {
                if (this.entity == null) {
                    return;
                }
                final EntityPlayer entityPlayer = next;
                if (!this.TotemPopContainerArray.toString().contains(entityPlayer.getName())) {
                    continue;
                }
                final String name = entityPlayer.getGameProfile().getName();
                if (entityPlayer.isDead || entityPlayer.getHealth() <= 0.0f) {
                    this.isDead = true;
                }
                else {
                    this.isDead = false;
                }
                String s;
                if (!this.isDead) {
                    if (this.count > 1) {
                        s = name + " popped §6§l" + this.TotemPopContainerArray.get(entityPlayer.getName()) + "§r totems";
                    }
                    else {
                        s = name + " popped §6§l" + this.TotemPopContainerArray.get(entityPlayer.getName()) + "§r totem";
                    }
                }
                else {
                    s = name + "§c Died§r after popping §6§l" + this.TotemPopContainerArray.get(entityPlayer.getName()) + "§r totems";
                    this.TotemPopContainer.remove(entityPlayer.getName());
                }
                final int color = ColorUtils.color(255, 255, 255, 255);
                if (!this.mode.getMode("ArrayList").isToggled()) {
                    continue;
                }
                if (this.arrayLocation.getMode("TopRight").isToggled()) {
                    Wrapper.INSTANCE.fontRenderer().drawString(s, scaledResolution.getScaledWidth() - Wrapper.INSTANCE.fontRenderer().getStringWidth(s), n, color);
                    n += 12;
                }
                if (this.arrayLocation.getMode("TopLeft").isToggled()) {
                    Wrapper.INSTANCE.fontRenderer().drawString(s, 6, n, color);
                    n += 12;
                }
                if (this.arrayLocation.getMode("BottomRight").isToggled()) {
                    Wrapper.INSTANCE.fontRenderer().drawString(s, scaledResolution.getScaledWidth() - Wrapper.INSTANCE.fontRenderer().getStringWidth(s), n, color);
                    n -= 12;
                }
                if (!this.arrayLocation.getMode("BottomLeft").isToggled()) {
                    continue;
                }
                Wrapper.INSTANCE.fontRenderer().drawString(s, 6, n, color);
                n -= 12;
            }
        }
    }
}
