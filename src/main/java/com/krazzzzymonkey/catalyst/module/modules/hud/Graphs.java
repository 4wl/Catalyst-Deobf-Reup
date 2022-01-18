/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.hud;

import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import org.lwjgl.opengl.GL11;
import java.text.DecimalFormat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import com.krazzzzymonkey.catalyst.utils.MouseUtils;
import com.krazzzzymonkey.catalyst.gui.click.HudGuiScreen;
import com.krazzzzymonkey.catalyst.utils.LagCompensator;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Text;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Font;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import java.util.Arrays;
import net.minecraft.network.play.server.SPacketDestroyEntities;
import com.krazzzzymonkey.catalyst.events.PacketEvent$Out;
import com.krazzzzymonkey.catalyst.events.PacketEvent$In;
import com.krazzzzymonkey.catalyst.events.PacketEvent;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.utils.font.CFontRenderer;
import com.krazzzzymonkey.catalyst.value.types.Number;
import com.krazzzzymonkey.catalyst.utils.CrystalClickCounter;
import java.util.List;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Graphs extends Modules
{
    public int finalMouseX;
    public BooleanValue BPSBoolean;
    public BooleanValue incomingBoolean;
    public List<Double> ping;
    public List<Double> outgoingPackets;
    public BooleanValue CrystalsBoolean;
    public boolean isAlreadyDragging;
    public CrystalClickCounter fpsCounter;
    public List<Double> incomingPackets;
    public Number xOffset;
    public BooleanValue TPSBoolean;
    public CFontRenderer graphFontRenderer;
    public boolean isDragging;
    public int Ping;
    public List<Double> CrystalsPS;
    public Number yOffset;
    public BooleanValue pingBoolean;
    public List<Double> BPS;
    public List<Double> tps;
    public CrystalClickCounter IncomingPackets;
    public int finalMouseY;
    public String entityID;
    public CrystalClickCounter breakSpeed;
    public List<Double> fps;
    public CrystalClickCounter OutgoingPackets;
    public BooleanValue outgoingBoolean;
    public BooleanValue fpsBoolean;
    
    @Override
    public void onDisable() {
        this.fps = new ArrayList<Double>();
        this.tps = new ArrayList<Double>();
        this.ping = new ArrayList<Double>();
        this.BPS = new ArrayList<Double>();
        this.CrystalsPS = new ArrayList<Double>();
        this.incomingPackets = new ArrayList<Double>();
        this.outgoingPackets = new ArrayList<Double>();
        super.onDisable();
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent packetEvent) {
        if (packetEvent instanceof PacketEvent$In) {
            this.IncomingPackets.onBreak();
        }
        if (packetEvent instanceof PacketEvent$Out) {
            this.OutgoingPackets.onBreak();
        }
        final Packet<?> packet = packetEvent.packet;
        if (packet instanceof SPacketDestroyEntities && this.entityID != null) {
            try {
                if (Arrays.toString(((SPacketDestroyEntities)packet).getEntityIDs()).contains(this.entityID)) {
                    this.breakSpeed.onBreak();
                }
            }
            catch (NullPointerException ex) {}
        }
    }
    
    public Graphs() {
        super("Graphs", ModuleCategory.HUD, "Shows you how many crystals you explode every second", true);
        this.IncomingPackets = new CrystalClickCounter();
        this.OutgoingPackets = new CrystalClickCounter();
        this.fpsCounter = new CrystalClickCounter();
        this.breakSpeed = new CrystalClickCounter();
        this.fps = new ArrayList<Double>();
        this.tps = new ArrayList<Double>();
        this.ping = new ArrayList<Double>();
        this.BPS = new ArrayList<Double>();
        this.CrystalsPS = new ArrayList<Double>();
        this.incomingPackets = new ArrayList<Double>();
        this.outgoingPackets = new ArrayList<Double>();
        this.graphFontRenderer = new CFontRenderer(new Font("Arial", 0, 12), true, true);
        this.finalMouseX = 0;
        this.finalMouseY = 0;
        this.isDragging = false;
        this.isAlreadyDragging = false;
        this.Ping = 0;
        this.fpsBoolean = new BooleanValue("FPS", true);
        this.pingBoolean = new BooleanValue("Ping", true);
        this.TPSBoolean = new BooleanValue("TPS", true);
        this.BPSBoolean = new BooleanValue("BPS", true);
        this.CrystalsBoolean = new BooleanValue("CrystalsPerSecond", true);
        this.incomingBoolean = new BooleanValue("IncomingPackets", true);
        this.outgoingBoolean = new BooleanValue("OutgoingPackets", true);
        this.xOffset = new Number("X Offset", 0);
        this.yOffset = new Number("Y Offset", 0);
        this.addValue(this.fpsBoolean, this.pingBoolean, this.TPSBoolean, this.BPSBoolean, this.CrystalsBoolean, this.incomingBoolean, this.outgoingBoolean, this.xOffset, this.yOffset);
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent$Text renderGameOverlayEvent$Text) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        this.fpsCounter.onBreak();
        final double n = Minecraft.getMinecraft().player.posX - Minecraft.getMinecraft().player.prevPosX;
        final double n2 = Minecraft.getMinecraft().player.posZ - Minecraft.getMinecraft().player.prevPosZ;
        final double n3 = MathHelper.sqrt(n * n + n2 * n2) / (double)(Minecraft.getMinecraft().timer.tickLength / 1000.0f);
        try {
            this.Ping = Minecraft.getMinecraft().player.connection.getPlayerInfo(Minecraft.getMinecraft().player.getUniqueID()).getResponseTime();
        }
        catch (NullPointerException ex) {}
        final int intValue = (int)this.yOffset.getValue();
        final int intValue2 = (int)this.xOffset.getValue();
        int n4 = 0;
        if (this.fpsBoolean.getValue()) {
            this.makeGraph(intValue2, intValue + n4, this.fps, this.fpsCounter.getCps(), "FPS", 1.0f, 1.0f, 1.0f);
            n4 += 35;
        }
        if (this.pingBoolean.getValue()) {
            this.makeGraph(intValue2, intValue + n4, this.ping, this.Ping, "Ping", 1.0f, 1.0f, 1.0f);
            n4 += 35;
        }
        if (this.TPSBoolean.getValue()) {
            this.makeGraph(intValue2, intValue + n4, this.tps, LagCompensator.INSTANCE.getTickRate(), "TPS", 1.0f, 1.0f, 1.0f);
            n4 += 35;
        }
        if (this.BPSBoolean.getValue()) {
            this.makeGraph(intValue2, intValue + n4, this.BPS, n3, "BPS", 1.0f, 1.0f, 1.0f);
            n4 += 35;
        }
        if (this.CrystalsBoolean.getValue()) {
            this.makeGraph(intValue2, intValue + n4, this.CrystalsPS, this.breakSpeed.getCps(), "Crystals Per Second", 1.0f, 1.0f, 1.0f);
            n4 += 35;
        }
        if (this.incomingBoolean.getValue()) {
            this.makeGraph(intValue2, intValue + n4, this.incomingPackets, this.IncomingPackets.getCps(), "Incoming Packets", 1.0f, 1.0f, 1.0f);
            n4 += 35;
        }
        if (this.outgoingBoolean.getValue()) {
            this.makeGraph(intValue2, intValue + n4, this.outgoingPackets, this.OutgoingPackets.getCps(), "Outgoing Packets", 1.0f, 1.0f, 1.0f);
            n4 += 35;
        }
        if (Minecraft.getMinecraft().currentScreen instanceof HudGuiScreen) {
            if (MouseUtils.isLeftClicked()) {
                if (!MouseUtils.isMouseOver(intValue2, intValue2 + 100, intValue, intValue + 35)) {
                    this.isAlreadyDragging = true;
                }
            }
            if (!MouseUtils.isLeftClicked() && this.isAlreadyDragging) {
                this.isAlreadyDragging = false;
            }
            if (!this.isAlreadyDragging || this.isDragging) {
                if (MouseUtils.isMouseOver(intValue2, intValue2 + 100, intValue, intValue + 35)) {
                    this.isDragging = true;
                }
                if (MouseUtils.isLeftClicked() && this.isDragging) {
                    this.finalMouseX = MouseUtils.getMouseX();
                    this.finalMouseY = MouseUtils.getMouseY();
                    this.xOffset.value = this.finalMouseX - 50;
                    this.yOffset.value = this.finalMouseY - 17;
                }
                else {
                    this.isDragging = false;
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onAttackEntity(final AttackEntityEvent attackEntityEvent) {
        if (Minecraft.getMinecraft().player.isSwingInProgress) {
            try {
                if (attackEntityEvent.getTarget() instanceof EntityEnderCrystal) {
                    this.entityID = String.valueOf(attackEntityEvent.getTarget().getEntityId());
                }
            }
            catch (NullPointerException ex) {}
        }
    }
    
    public void makeGraph(final int n, int n2, final List list, final double n3, final String str, final float n4, final float n5, final float n6) {
        n2 += 35;
        final DecimalFormat decimalFormat = new DecimalFormat("0.0");
        final double n7 = 17.5 / list.stream().max(Double::compareTo).orElse(1.0);
        list.add(n3 + 1.0);
        while (list.size() > 200) {
            list.remove(0);
        }
        int n8 = 0;
        GL11.glColor4f(n4, n5, n6, 1.0f);
        GL11.glLineWidth(2.0f);
        RenderUtils.drawBorderedRect(n, n2, n + 100, n2 - 35, 1.0f, ColorUtils.rainbow().getRGB(), ColorUtils.getColor(150, 0, 0, 0));
        this.graphFontRenderer.drawString(decimalFormat.format(list.get(list.size() - 1) - 1.0) + " " + str, (float)(n + 2), (float)(n2 - 33), -1);
        GL11.glDisable(3553);
        GL11.glBegin(3);
        n8 += 3;
        final double n9 = (100 - n8) / (double)list.size();
        for (int i = 0; i < list.size(); ++i) {
            GL11.glVertex2d(n8 + i * n9 + n, n2 + n7 - n7 * list.get(i));
        }
        GL11.glEnd();
        GL11.glEnable(3553);
    }
    
    @Override
    public void onEnable() {
        this.fps = new ArrayList<Double>();
        this.tps = new ArrayList<Double>();
        this.ping = new ArrayList<Double>();
        this.BPS = new ArrayList<Double>();
        this.CrystalsPS = new ArrayList<Double>();
        this.incomingPackets = new ArrayList<Double>();
        this.outgoingPackets = new ArrayList<Double>();
        super.onEnable();
    }
}
