/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.events;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class RenderModelEntityLivingEvent extends Event
{
    public float netHeadYaw;
    public float headPitch;
    public EntityLivingBase entityLivingBase;
    public ModelBase modelBase;
    public float limbSwing;
    public float ageInTicks;
    public float scaleFactor;
    public float limbSwingAmount;
    
    public float getNetHeadYaw() {
        return this.netHeadYaw;
    }
    
    public ModelBase getModelBase() {
        return this.modelBase;
    }
    
    public void setLimbSwingAmount(final float limbSwingAmount) {
        this.limbSwingAmount = limbSwingAmount;
    }
    
    public float getLimbSwingAmount() {
        return this.limbSwingAmount;
    }
    
    public EntityLivingBase getEntityLivingBase() {
        return this.entityLivingBase;
    }
    
    public float getScaleFactor() {
        return this.scaleFactor;
    }
    
    public RenderModelEntityLivingEvent(final EntityLivingBase entityLivingBase, final ModelBase modelBase, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor) {
        this.entityLivingBase = entityLivingBase;
        this.modelBase = modelBase;
        this.limbSwing = limbSwing;
        this.limbSwingAmount = limbSwingAmount;
        this.ageInTicks = ageInTicks;
        this.netHeadYaw = netHeadYaw;
        this.headPitch = headPitch;
        this.scaleFactor = scaleFactor;
    }
    
    public void setHeadPitch(final float headPitch) {
        this.headPitch = headPitch;
    }
    
    public float getAgeInTicks() {
        return this.ageInTicks;
    }
    
    public void setLimbSwing(final float limbSwing) {
        this.limbSwing = limbSwing;
    }
    
    public float getHeadPitch() {
        return this.headPitch;
    }
    
    public void setNetHeadYaw(final float netHeadYaw) {
        this.netHeadYaw = netHeadYaw;
    }
    
    public void setModelBase(final ModelBase modelBase) {
        this.modelBase = modelBase;
    }
    
    public void setScaleFactor(final float scaleFactor) {
        this.scaleFactor = scaleFactor;
    }
    
    public void setAgeInTicks(final float ageInTicks) {
        this.ageInTicks = ageInTicks;
    }
    
    public float getLimbSwing() {
        return this.limbSwing;
    }
}
