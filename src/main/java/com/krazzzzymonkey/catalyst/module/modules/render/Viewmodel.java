/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import java.awt.Color;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.events.RenderItemEvent;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Viewmodel extends Modules
{
    public DoubleValue mainScaleX;
    public DoubleValue offX;
    public static BooleanValue rainbowMain;
    public DoubleValue offRx;
    public DoubleValue offAngel;
    public DoubleValue offRz;
    public DoubleValue offScaleY;
    public DoubleValue mainRy;
    public DoubleValue mainRx;
    public DoubleValue mainScaleZ;
    public DoubleValue offY;
    public DoubleValue mainX;
    public DoubleValue offScaleZ;
    public static ColorValue mainColor;
    public DoubleValue mainY;
    public DoubleValue mainZ;
    public DoubleValue mainRz;
    public DoubleValue mainScaleY;
    public DoubleValue offZ;
    public DoubleValue mainAngel;
    public DoubleValue offRy;
    public DoubleValue offScaleX;
    
    public Viewmodel() {
        super("Viewmodel", ModuleCategory.RENDER, "Allows you to customize your viewmodel");
        this.mainX = new DoubleValue("xOffsetMain", 1.2, 0.0, 6.0);
        this.mainY = new DoubleValue("yOffsetMain", -0.95, -3.0, 3.0);
        this.mainZ = new DoubleValue("zOffsetMain", -1.45, -5.0, 5.0);
        this.offX = new DoubleValue("xOffsetOffhand", -1.2, -6.0, 0.0);
        this.offY = new DoubleValue("yOffsetOffhand", -0.95, -3.0, 3.0);
        this.offZ = new DoubleValue("zOffsetOffhand", -1.45, -5.0, 5.0);
        this.mainAngel = new DoubleValue("MainhandAngle", 0.0, 0.0, 360.0);
        this.mainRx = new DoubleValue("mainRotationPointX", 0.0, -1.0, 1.0);
        this.mainRy = new DoubleValue("mainRotationPointY", 0.0, -1.0, 1.0);
        this.mainRz = new DoubleValue("mainRotationPointZ", 0.0, -1.0, 1.0);
        this.offAngel = new DoubleValue("OffhandAngle", 0.0, 0.0, 360.0);
        this.offRx = new DoubleValue("offRotationPointX", 0.0, -1.0, 1.0);
        this.offRy = new DoubleValue("offRotationPointY", 0.0, -1.0, 1.0);
        this.offRz = new DoubleValue("offRotationPointZ", 0.0, -1.0, 1.0);
        this.mainScaleX = new DoubleValue("xScaleMain", 1.0, -5.0, 10.0);
        this.mainScaleY = new DoubleValue("yScaleMain", 1.0, -5.0, 10.0);
        this.mainScaleZ = new DoubleValue("zScaleMain", 1.0, -5.0, 10.0);
        this.offScaleX = new DoubleValue("xScaleOffhand", 1.0, -5.0, 10.0);
        this.offScaleY = new DoubleValue("yScaleOffhand", 1.0, -5.0, 10.0);
        this.offScaleZ = new DoubleValue("zScaleOffhand", 1.0, -5.0, 10.0);
        this.addValue(Viewmodel.mainColor, Viewmodel.rainbowMain, this.mainX, this.mainY, this.mainZ, this.mainRx, this.mainRy, this.mainRz, this.mainAngel, this.mainScaleX, this.mainScaleY, this.mainScaleZ, this.offX, this.offY, this.offZ, this.offRx, this.offRy, this.offRz, this.offAngel, this.offScaleX, this.offScaleY, this.offScaleZ);
    }
    
    @SubscribeEvent
    public void onItemRender(final RenderItemEvent renderItemEvent) {
        if (!this.isToggled() || Viewmodel.mc.player == null || Viewmodel.mc.world == null) {
            return;
        }
        renderItemEvent.setMainX(this.mainX.getValue());
        renderItemEvent.setMainY(this.mainY.getValue());
        renderItemEvent.setMainZ(this.mainZ.getValue());
        renderItemEvent.setOffX(this.offX.getValue());
        renderItemEvent.setOffY(this.offY.getValue());
        renderItemEvent.setOffZ(this.offZ.getValue());
        renderItemEvent.setMainRAngel(this.mainAngel.getValue());
        renderItemEvent.setMainRx(this.mainRx.getValue());
        renderItemEvent.setMainRy(this.mainRy.getValue());
        renderItemEvent.setMainRz(this.mainRz.getValue());
        renderItemEvent.setOffRAngel(this.offAngel.getValue());
        renderItemEvent.setOffRx(this.offRx.getValue());
        renderItemEvent.setOffRy(this.offRy.getValue());
        renderItemEvent.setOffRz(this.offRz.getValue());
        renderItemEvent.setMainHandScaleX(this.mainScaleX.getValue());
        renderItemEvent.setMainHandScaleY(this.mainScaleY.getValue());
        renderItemEvent.setMainHandScaleZ(this.mainScaleZ.getValue());
        renderItemEvent.setOffHandScaleX(this.offScaleX.getValue());
        renderItemEvent.setOffHandScaleY(this.offScaleY.getValue());
        renderItemEvent.setOffHandScaleZ(this.offScaleZ.getValue());
    }
    
    static {
        Viewmodel.mainColor = new ColorValue("ArmColor", new Color(255, 255, 254));
        Viewmodel.rainbowMain = new BooleanValue("RainbowMode", false);
    }
}
