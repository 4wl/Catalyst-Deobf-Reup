/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import java.util.Iterator;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraft.network.play.server.SPacketChunkData;
import com.krazzzzymonkey.catalyst.events.PacketEvent$In;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import net.minecraft.world.chunk.Chunk;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.module.Modules;

public class NewChunks extends Modules
{
    public static ArrayList<Chunk> coords;
    public ColorValue color;
    public BooleanValue rainbow;
    public IntegerValue renderHeight;
    
    public NewChunks() {
        super("NewChunks", ModuleCategory.RENDER, "Makes newly generated chunks glow");
        this.color = new ColorValue("Color", Color.RED);
        this.rainbow = new BooleanValue("Rainbow", false);
        this.renderHeight = new IntegerValue("RenderHeight", 0, 0, 255);
        this.addValue(this.color, this.rainbow, this.renderHeight);
    }
    
    public static void chunkESP(final double n, final double n2, final double n3, final Color color) {
        final double n4 = n - Minecraft.getMinecraft().getRenderManager().renderPosX;
        final double n5 = n2 - Minecraft.getMinecraft().getRenderManager().renderPosY;
        final double n6 = n3 - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glPushMatrix();
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.0f);
        GL11.glColor3f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
        GL11.glBegin(2);
        GL11.glVertex3d(n4, n5, n6);
        GL11.glVertex3d(n4 + 16.0, n5, n6);
        GL11.glVertex3d(n4 + 16.0, n5, n6);
        GL11.glVertex3d(n4, n5, n6);
        GL11.glEnd();
        GL11.glBegin(2);
        GL11.glVertex3d(n4, n5, n6);
        GL11.glVertex3d(n4, n5, n6 + 16.0);
        GL11.glEnd();
        GL11.glBegin(2);
        GL11.glVertex3d(n4, n5, n6 + 16.0);
        GL11.glVertex3d(n4 + 16.0, n5, n6 + 16.0);
        GL11.glVertex3d(n4 + 16.0, n5, n6 + 16.0);
        GL11.glVertex3d(n4, n5, n6 + 16.0);
        GL11.glEnd();
        GL11.glBegin(2);
        GL11.glVertex3d(n4 + 16.0, n5, n6 + 16.0);
        GL11.glVertex3d(n4 + 16.0, n5, n6);
        GL11.glVertex3d(n4 + 16.0, n5, n6);
        GL11.glVertex3d(n4 + 16.0, n5, n6 + 16.0);
        GL11.glColor3f(189.0f, 0.0f, 0.0f);
        GL11.glEnd();
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent$In packetEvent$In) {
        final Packet<?> packet = packetEvent$In.packet;
        if (packet instanceof SPacketChunkData) {
            final SPacketChunkData sPacketChunkData = (SPacketChunkData)packet;
            if (!sPacketChunkData.isFullChunk()) {
                NewChunks.coords.add(Wrapper.INSTANCE.world().getChunkFromChunkCoords(sPacketChunkData.getChunkX(), sPacketChunkData.getChunkZ()));
            }
        }
    }
    
    static {
        NewChunks.coords = new ArrayList<Chunk>();
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        try {
            for (final Chunk chunk : NewChunks.coords) {
                final int n = chunk.x * 16;
                final int intValue = this.renderHeight.getValue();
                final int n2 = chunk.z * 16;
                if (this.rainbow.getValue()) {
                    chunkESP(n, intValue, n2, ColorUtils.rainbow());
                }
                else {
                    chunkESP(n, intValue, n2, this.color.getColor());
                }
            }
        }
        catch (Exception ex) {}
    }
}
