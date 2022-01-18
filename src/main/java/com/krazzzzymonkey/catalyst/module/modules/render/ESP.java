/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.entity.Entity;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import java.util.Collection;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.value.Mode;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.value.types.ModeValue;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class ESP extends Modules
{
    public static BooleanValue ThirdPerson;
    public static ColorValue colorEnd;
    public static IntegerValue groundPlateOpacity;
    public static IntegerValue opacityStart;
    public static ModeValue Mode;
    public static BooleanValue colorEndRainbow;
    public static DoubleValue endOffset;
    public static BooleanValue groundPlate;
    public static DoubleValue startOffset;
    public static BooleanValue Players;
    public static DoubleValue lineWidth;
    public static BooleanValue Mobs;
    public static BooleanValue Combat;
    public static IntegerValue opacityEnd;
    public static ColorValue colorStart;
    public static BooleanValue colorStartRainbow;
    
    public ESP() {
        super("ESP", ModuleCategory.RENDER, "Shows you where entities are");
        ESP.Players = new BooleanValue("Players", true);
        ESP.Mobs = new BooleanValue("Mobs", false);
        ESP.Combat = new BooleanValue("In Combat", false);
        ESP.ThirdPerson = new BooleanValue("Third Person", false);
        ESP.Mode = new ModeValue("ESP Mode", new Mode[] { new Mode("Rect", true), new Mode("Glow", false), new Mode("CSGO", false), new Mode("Diamond", false), new Mode("Box", false), new Mode("Halo", false), new Mode("WireFrame", false) });
        ESP.lineWidth = new DoubleValue("Line Width", 1.0, 1.0, 10.0);
        this.addValue(ESP.Mode, ESP.lineWidth, ESP.Players, ESP.Mobs, ESP.Combat, ESP.ThirdPerson, ESP.colorStart, ESP.colorStartRainbow, ESP.opacityStart, ESP.colorEnd, ESP.colorEndRainbow, ESP.opacityEnd, ESP.startOffset, ESP.endOffset, ESP.groundPlate, ESP.groundPlateOpacity);
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        final float partialTicks = renderWorldLastEvent.getPartialTicks();
        final ArrayList<Entity> list = new ArrayList<Entity>((Collection<? extends Entity>)Wrapper.INSTANCE.world().loadedEntityList.stream().filter(ESP::lambda$onRenderWorldLast$0).collect(Collectors.toList()));
        final ArrayList<Entity> list2 = new ArrayList<Entity>((Collection<? extends Entity>)Wrapper.INSTANCE.world().loadedEntityList.stream().filter(ESP::lambda$onRenderWorldLast$1).filter(ESP::lambda$onRenderWorldLast$2).collect(Collectors.toList()));
        float n;
        float n2;
        float n3;
        Color color;
        if (ESP.colorStartRainbow.getValue()) {
            n = ColorUtils.rainbow().getRed() / 255.0f;
            n2 = ColorUtils.rainbow().getGreen() / 255.0f;
            n3 = ColorUtils.rainbow().getBlue() / 255.0f;
            color = ColorUtils.rainbow();
        }
        else {
            n = ESP.colorStart.getColor().getRed() / 255.0f;
            n2 = ESP.colorStart.getColor().getGreen() / 255.0f;
            n3 = ESP.colorStart.getColor().getBlue() / 255.0f;
            color = ESP.colorStart.getColor();
        }
        Color color2;
        if (ESP.colorEndRainbow.getValue()) {
            color2 = ColorUtils.rainbow();
        }
        else {
            color2 = ESP.colorEnd.getColor();
        }
        for (final Entity entity : list) {
            entity.setGlowing((boolean)ESP.Mobs.getValue() && ESP.Mode.getMode("Glow").isToggled());
            if (ESP.Mobs.getValue()) {
                if (ESP.Mode.getMode("CSGO").isToggled()) {
                    RenderUtils.drawESPCSGO(entity, n, n2, n3, 1.0f, partialTicks, ESP.lineWidth.getValue().floatValue());
                }
            }
            if ((boolean)ESP.Mobs.getValue() && ESP.Mode.getMode("Rect").isToggled()) {
                RenderUtils.drawPlayerESPRect(entity, n, n2, n3, 1.0f, partialTicks, ESP.lineWidth.getValue().floatValue());
            }
            if ((boolean)ESP.Mobs.getValue() && ESP.Mode.getMode("Diamond").isToggled()) {
                RenderUtils.drawESPDiamond(entity, n, n2, n3, 1.0f, partialTicks, ESP.lineWidth.getValue().floatValue());
            }
            if ((boolean)ESP.Mobs.getValue() && ESP.Mode.getMode("Box").isToggled()) {
                RenderUtils.drawOutlineBox(entity, n, n2, n3, partialTicks, ESP.lineWidth.getValue().floatValue());
            }
            if ((boolean)ESP.Mobs.getValue() && ESP.Mode.getMode("Halo").isToggled()) {
                RenderUtils.drawHaloESP(entity, color, color2, 0.0f, ESP.startOffset.getValue().floatValue(), ESP.endOffset.getValue().floatValue(), (boolean)ESP.groundPlate.getValue(), ESP.groundPlateOpacity.getValue(), ESP.opacityStart.getValue(), ESP.opacityEnd.getValue());
            }
        }
        for (final Entity entity2 : list2) {
            if ((boolean)ESP.Players.getValue() && ESP.Mode.getMode("CSGO").isToggled()) {
                RenderUtils.drawESPCSGO(entity2, n, n2, n3, 1.0f, partialTicks, ESP.lineWidth.getValue().floatValue());
            }
            if ((boolean)ESP.Players.getValue() && ESP.Mode.getMode("Rect").isToggled()) {
                RenderUtils.drawPlayerESPRect(entity2, n, n2, n3, 1.0f, partialTicks, ESP.lineWidth.getValue().floatValue());
            }
            if ((boolean)ESP.Players.getValue() && ESP.Mode.getMode("Diamond").isToggled()) {
                RenderUtils.drawESPDiamond(entity2, n, n2, n3, 1.0f, partialTicks, ESP.lineWidth.getValue().floatValue());
            }
            if ((boolean)ESP.Players.getValue() && ESP.Mode.getMode("Box").isToggled()) {
                RenderUtils.drawOutlineBox(entity2, n, n2, n3, partialTicks, ESP.lineWidth.getValue().floatValue());
            }
            if ((boolean)ESP.Players.getValue() && ESP.Mode.getMode("Halo").isToggled()) {
                RenderUtils.drawHaloESP(entity2, color, color2, 0.0f, ESP.startOffset.getValue().floatValue(), ESP.endOffset.getValue().floatValue(), (boolean)ESP.groundPlate.getValue(), ESP.groundPlateOpacity.getValue(), ESP.opacityStart.getValue(), ESP.opacityEnd.getValue());
            }
        }
    }
    
    static {
        ESP.colorStart = new ColorValue("Color", new Color(255, 255, 254));
        ESP.colorStartRainbow = new BooleanValue("ColorRainbow", false);
        ESP.colorEnd = new ColorValue("EndColor", new Color(255, 255, 254));
        ESP.colorEndRainbow = new BooleanValue("EndColorRainbow", false);
        ESP.opacityStart = new IntegerValue("StartOpacity", 100, 0, 100);
        ESP.opacityEnd = new IntegerValue("EndOpacity", 0, 0, 100);
        ESP.startOffset = new DoubleValue("StartOffset", 0.0, 0.0, 2.0);
        ESP.endOffset = new DoubleValue("EndOffset", 0.0, 0.0, 2.0);
        ESP.groundPlate = new BooleanValue("DrawBottomQuad", true);
        ESP.groundPlateOpacity = new IntegerValue("BottomQuadOpacity", 50, 0, 100);
    }
    
    public static boolean lambda$onRenderWorldLast$1(final Entity entity) {
        return entity instanceof EntityPlayer;
    }
    
    @Override
    public void onDisable() {
        for (final Entity entity : Wrapper.INSTANCE.world().loadedEntityList) {
            if (entity.isGlowing()) {
                entity.setGlowing(false);
            }
        }
        super.onDisable();
    }
    
    public static boolean lambda$onRenderWorldLast$0(final Entity entity) {
        return !(entity instanceof EntityPlayer);
    }
    
    public static boolean lambda$onRenderWorldLast$2(final Entity entity) {
        return entity != Wrapper.INSTANCE.player() || (ESP.mc.gameSettings.thirdPersonView != 0 && (boolean)ESP.ThirdPerson.getValue());
    }
}
