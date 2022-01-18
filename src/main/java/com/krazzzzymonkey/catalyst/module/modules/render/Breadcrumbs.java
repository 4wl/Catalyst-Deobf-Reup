/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import net.minecraft.entity.item.EntityEnderPearl;
import java.util.function.Consumer;
import java.util.function.Predicate;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.function.BiConsumer;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.util.math.Vec3d;
import java.util.LinkedList;
import net.minecraft.entity.Entity;
import java.util.HashMap;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Breadcrumbs extends Modules
{
    public static ArrayList<String> names;
    public static ColorValue colorValue;
    public static IntegerValue maxPoints;
    public static IntegerValue ticks;
    public static BooleanValue traceEnderPearls;
    public static IntegerValue lineWidth;
    public static HashMap<Entity, LinkedList<Vec3d>> tracedEntities;
    public static HashMap<Entity, LinkedList<Vec3d>> tracedPlayers;
    public static LinkedList<Vec3d> points;
    public static BooleanValue self;
    public int ticksPassed;
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.ticksPassed = 0;
    }
    
    public Breadcrumbs() {
        super("Breadcrumbs", ModuleCategory.RENDER, "Traces the movement of entities and players");
        this.ticksPassed = 0;
        Breadcrumbs.ticks = new IntegerValue("Ticks", 5, 1, 100);
        Breadcrumbs.maxPoints = new IntegerValue("MaxPoints", 100, 1, 5000);
        Breadcrumbs.lineWidth = new IntegerValue("LineWidth", 2, 1, 10);
        Breadcrumbs.colorValue = new ColorValue("Color", new Color(255, 254, 254));
        Breadcrumbs.traceEnderPearls = new BooleanValue("EnderPearls", true);
        Breadcrumbs.self = new BooleanValue("Self", true);
        this.addValue(Breadcrumbs.self, Breadcrumbs.traceEnderPearls, Breadcrumbs.ticks, Breadcrumbs.maxPoints, Breadcrumbs.colorValue, Breadcrumbs.lineWidth);
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        while (true) {
            switch (-620759612 + 297035015 + 1) {
                case 2057171920: {
                    continue;
                }
                case -884240189: {
                    this.ticksPassed = 0;
                }
                default: {
                    throw null;
                }
            }
        }
    }
    
    static {
        Breadcrumbs.points = new LinkedList<Vec3d>();
        Breadcrumbs.tracedEntities = new HashMap<Entity, LinkedList<Vec3d>>();
        Breadcrumbs.names = new ArrayList<String>();
        Breadcrumbs.tracedPlayers = new HashMap<Entity, LinkedList<Vec3d>>();
    }
    
    @SubscribeEvent
    public void onRender3D(final RenderWorldLastEvent renderWorldLastEvent) {
        if ((boolean)Breadcrumbs.self.getValue() && !Breadcrumbs.points.isEmpty()) {
            Breadcrumbs.points.iterator().forEachRemaining(Breadcrumbs::lambda$onRender3D$3);
            RenderUtils.drawLine3D((float)Breadcrumbs.points.getLast().x, (float)Breadcrumbs.points.getLast().y, (float)Breadcrumbs.points.getLast().z, (float)Breadcrumbs.mc.player.posX, (float)Breadcrumbs.mc.player.posY, (float)Breadcrumbs.mc.player.posZ, Breadcrumbs.lineWidth.getValue(), Breadcrumbs.colorValue.getColor().getRed() / 255.0f, Breadcrumbs.colorValue.getColor().getGreen() / 255.0f, Breadcrumbs.colorValue.getColor().getBlue() / 255.0f, 1.0f);
        }
        if (Breadcrumbs.traceEnderPearls.getValue()) {
            Breadcrumbs.tracedEntities.forEach(Breadcrumbs::lambda$onRender3D$5);
        }
        if (!Breadcrumbs.tracedPlayers.isEmpty()) {
            Breadcrumbs.tracedPlayers.forEach(Breadcrumbs::lambda$onRender3D$7);
        }
    }
    
    public static void lambda$null$4(final Vec3d[] array, final Vec3d vec3d) {
        RenderUtils.drawLine3D((float)array[0].x, (float)array[0].y, (float)array[0].z, (float)vec3d.x, (float)vec3d.y, (float)vec3d.z, Breadcrumbs.lineWidth.getValue(), Breadcrumbs.colorValue.getColor().getRed() / 255.0f, Breadcrumbs.colorValue.getColor().getGreen() / 255.0f, Breadcrumbs.colorValue.getColor().getBlue() / 255.0f, 1.0f);
        array[0] = vec3d;
    }
    
    public static void lambda$onRender3D$5(final Entity key, final LinkedList list) {
        if (Breadcrumbs.mc.world.loadedEntityList.contains(key) && !Breadcrumbs.tracedEntities.get(key).isEmpty()) {
            list.iterator().forEachRemaining(Breadcrumbs::lambda$null$4);
        }
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Breadcrumbs.mc.player != null) {
            if (Breadcrumbs.self.getValue()) {
                ++this.ticksPassed;
                if (this.ticksPassed % Breadcrumbs.ticks.getValue() == 0) {
                    Breadcrumbs.points.addLast(new Vec3d(Breadcrumbs.mc.player.posX, Breadcrumbs.mc.player.posY, Breadcrumbs.mc.player.posZ));
                    if (Breadcrumbs.points.size() > Breadcrumbs.maxPoints.getValue()) {
                        Breadcrumbs.points.removeFirst();
                    }
                }
            }
            else {
                Breadcrumbs.points.clear();
            }
            if (Breadcrumbs.traceEnderPearls.getValue()) {
                Breadcrumbs.mc.world.loadedEntityList.stream().filter(Breadcrumbs::lambda$onClientTick$0).forEach(Breadcrumbs::lambda$onClientTick$1);
            }
            Breadcrumbs.mc.world.loadedEntityList.forEach(Breadcrumbs::lambda$onClientTick$2);
        }
        else {
            Breadcrumbs.points.clear();
            Breadcrumbs.tracedEntities.clear();
        }
    }
    
    public static void lambda$onRender3D$7(final Entity key, final LinkedList list) {
        if (Breadcrumbs.mc.world.loadedEntityList.contains(key) && !Breadcrumbs.tracedPlayers.get(key).isEmpty()) {
            list.iterator().forEachRemaining(Breadcrumbs::lambda$null$6);
            RenderUtils.drawLine3D((float)list.getLast().x, (float)list.getLast().y, (float)list.getLast().z, (float)key.posX, (float)key.posY, (float)key.posZ, Breadcrumbs.lineWidth.getValue(), Breadcrumbs.colorValue.getColor().getRed() / 255.0f, Breadcrumbs.colorValue.getColor().getGreen() / 255.0f, Breadcrumbs.colorValue.getColor().getBlue() / 255.0f, 1.0f);
        }
    }
    
    public static void lambda$onClientTick$1(final Entity key) {
        if (!Breadcrumbs.tracedEntities.containsKey(key)) {
            final LinkedList<Vec3d> value = new LinkedList<Vec3d>();
            value.addLast(new Vec3d(key.posX, key.posY, key.posZ));
            Breadcrumbs.tracedEntities.put(key, value);
        }
        else {
            Breadcrumbs.tracedEntities.get(key).addLast(new Vec3d(key.posX, key.posY, key.posZ));
        }
    }
    
    public static void lambda$onClientTick$2(final Entity key) {
        if (Breadcrumbs.names.contains(key.getName().toLowerCase())) {
            if (!Breadcrumbs.tracedPlayers.containsKey(key)) {
                final LinkedList<Vec3d> value = new LinkedList<Vec3d>();
                value.addLast(new Vec3d(key.posX, key.posY, key.posZ));
                Breadcrumbs.tracedPlayers.put(key, value);
            }
            else {
                Breadcrumbs.tracedPlayers.get(key).addLast(new Vec3d(key.posX, key.posY, key.posZ));
            }
        }
    }
    
    public static boolean lambda$onClientTick$0(final Entity entity) {
        return entity instanceof EntityEnderPearl;
    }
    
    public static void lambda$onRender3D$3(final Vec3d[] array, final Vec3d vec3d) {
        RenderUtils.drawLine3D((float)array[0].x, (float)array[0].y, (float)array[0].z, (float)vec3d.x, (float)vec3d.y, (float)vec3d.z, Breadcrumbs.lineWidth.getValue(), Breadcrumbs.colorValue.getColor().getRed() / 255.0f, Breadcrumbs.colorValue.getColor().getGreen() / 255.0f, Breadcrumbs.colorValue.getColor().getBlue() / 255.0f, 1.0f);
        array[0] = vec3d;
    }
    
    public static void lambda$null$6(final Vec3d[] array, final Vec3d vec3d) {
        RenderUtils.drawLine3D((float)array[0].x, (float)array[0].y, (float)array[0].z, (float)vec3d.x, (float)vec3d.y, (float)vec3d.z, Breadcrumbs.lineWidth.getValue(), Breadcrumbs.colorValue.getColor().getRed() / 255.0f, Breadcrumbs.colorValue.getColor().getGreen() / 255.0f, Breadcrumbs.colorValue.getColor().getBlue() / 255.0f, 1.0f);
        array[0] = vec3d;
    }
}
