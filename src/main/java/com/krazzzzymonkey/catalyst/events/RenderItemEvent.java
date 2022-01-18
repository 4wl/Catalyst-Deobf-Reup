/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.events;

import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class RenderItemEvent extends Event
{
    public double offRy;
    public double mainX;
    public double offHandScaleX;
    public double offX;
    public double mainRx;
    public double mainHandScaleX;
    public double offY;
    public double offRx;
    public double offHandScaleY;
    public double offRz;
    public double mainHandScaleY;
    public double offRAngel;
    public double mainRAngel;
    public double offHandScaleZ;
    public double mainRy;
    public double mainZ;
    public double mainRz;
    public double offZ;
    public double mainY;
    public double mainHandScaleZ;
    
    public void setOffRx(final double offRx) {
        this.offRx = offRx;
    }
    
    public void setMainRy(final double mainRy) {
        this.mainRy = mainRy;
    }
    
    public void setOffX(final double offX) {
        this.offX = offX;
    }
    
    public void setMainRAngel(final double mainRAngel) {
        this.mainRAngel = mainRAngel;
    }
    
    public double getMainHandScaleX() {
        return this.mainHandScaleX;
    }
    
    public void setOffRy(final double offRy) {
        this.offRy = offRy;
    }
    
    public double getMainZ() {
        return this.mainZ;
    }
    
    public double getMainY() {
        return this.mainY;
    }
    
    public double getMainX() {
        return this.mainX;
    }
    
    public double getOffHandScaleZ() {
        return this.offHandScaleZ;
    }
    
    public void setMainHandScaleY(final double mainHandScaleY) {
        this.mainHandScaleY = mainHandScaleY;
    }
    
    public double getOffY() {
        return this.offY;
    }
    
    public void setMainRz(final double mainRz) {
        this.mainRz = mainRz;
    }
    
    public double getMainRAngel() {
        return this.mainRAngel;
    }
    
    public void setOffRAngel(final double offRAngel) {
        this.offRAngel = offRAngel;
    }
    
    public double getMainHandScaleY() {
        return this.mainHandScaleY;
    }
    
    public double getOffRz() {
        return this.offRz;
    }
    
    public void setOffY(final double offY) {
        this.offY = offY;
    }
    
    public double getOffRAngel() {
        return this.offRAngel;
    }
    
    public double getOffX() {
        return this.offX;
    }
    
    public void setOffRz(final double offRz) {
        this.offRz = offRz;
    }
    
    public double getOffRx() {
        return this.offRx;
    }
    
    public double getMainRz() {
        return this.mainRz;
    }
    
    public double getOffZ() {
        return this.offZ;
    }
    
    public void setMainRx(final double mainRx) {
        this.mainRx = mainRx;
    }
    
    public double getMainRy() {
        return this.mainRy;
    }
    
    public void setMainZ(final double mainZ) {
        this.mainZ = mainZ;
    }
    
    public void setOffHandScaleZ(final double offHandScaleZ) {
        this.offHandScaleZ = offHandScaleZ;
    }
    
    public void setOffHandScaleY(final double offHandScaleY) {
        this.offHandScaleY = offHandScaleY;
    }
    
    public void setOffZ(final double offZ) {
        this.offZ = offZ;
    }
    
    public double getOffHandScaleX() {
        return this.offHandScaleX;
    }
    
    public void setMainHandScaleZ(final double mainHandScaleZ) {
        this.mainHandScaleZ = mainHandScaleZ;
    }
    
    public double getOffHandScaleY() {
        return this.offHandScaleY;
    }
    
    public RenderItemEvent(final double mainX, final double mainY, final double mainZ, final double offX, final double offY, final double offZ, final double mainRAngel, final double mainRx, final double mainRy, final double mainRz, final double offRAngel, final double offRx, final double offRy, final double offRz, final double mainHandScaleX, final double mainHandScaleY, final double mainHandScaleZ, final double offHandScaleX, final double offHandScaleY, final double offHandScaleZ) {
        this.mainX = mainX;
        this.mainY = mainY;
        this.mainZ = mainZ;
        this.offX = offX;
        this.offY = offY;
        this.offZ = offZ;
        this.mainRAngel = mainRAngel;
        this.mainRx = mainRx;
        this.mainRy = mainRy;
        this.mainRz = mainRz;
        this.offRAngel = offRAngel;
        this.offRx = offRx;
        this.offRy = offRy;
        this.offRz = offRz;
        this.mainHandScaleX = mainHandScaleX;
        this.mainHandScaleY = mainHandScaleY;
        this.mainHandScaleZ = mainHandScaleZ;
        this.offHandScaleX = offHandScaleX;
        this.offHandScaleY = offHandScaleY;
        this.offHandScaleZ = offHandScaleZ;
    }
    
    public void setMainX(final double mainX) {
        this.mainX = mainX;
    }
    
    public void setMainY(final double mainY) {
        this.mainY = mainY;
    }
    
    public double getMainHandScaleZ() {
        return this.mainHandScaleZ;
    }
    
    public void setOffHandScaleX(final double offHandScaleX) {
        this.offHandScaleX = offHandScaleX;
    }
    
    public void setMainHandScaleX(final double mainHandScaleX) {
        this.mainHandScaleX = mainHandScaleX;
    }
    
    public double getMainRx() {
        return this.mainRx;
    }
    
    public double getOffRy() {
        return this.offRy;
    }
}
