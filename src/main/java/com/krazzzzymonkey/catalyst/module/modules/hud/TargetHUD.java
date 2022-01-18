/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.hud;

import java.util.Iterator;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.client.renderer.OpenGlHelper;
import com.krazzzzymonkey.catalyst.utils.MouseUtils;
import com.krazzzzymonkey.catalyst.gui.click.HudGuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.gui.ScaledResolution;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import java.text.DecimalFormat;
import com.krazzzzymonkey.catalyst.utils.MathUtils;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Text;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityAmbientCreature;
import com.krazzzzymonkey.catalyst.managers.FriendManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Font;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import com.krazzzzymonkey.catalyst.value.types.Number;
import com.krazzzzymonkey.catalyst.utils.font.CFontRenderer;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class TargetHUD extends Modules
{
    public int finalMouseX;
    public int finalMouseY;
    public int x;
    public BooleanValue animals;
    public CFontRenderer fontRenderer;
    public boolean isAlreadyDragging;
    public BooleanValue monsters;
    public Number yOffset;
    public Number xOffset;
    public BooleanValue ambient;
    public boolean isDragging;
    public BooleanValue friends;
    public BooleanValue players;
    public int y;
    public BooleanValue squid;
    
    public static EntityLivingBase lambda$onRenderGameOverlay$1(final Entity entity) {
        return (EntityLivingBase)entity;
    }
    
    public TargetHUD() {
        super("TargetHUD", ModuleCategory.HUD, "Shows information about the nearest entity", true);
        this.finalMouseX = 0;
        this.finalMouseY = 0;
        this.isDragging = false;
        this.isAlreadyDragging = false;
        this.fontRenderer = new CFontRenderer(new Font("Arial", 0, 15), true, true);
        this.players = new BooleanValue("Players", true);
        this.monsters = new BooleanValue("Monsters", true);
        this.animals = new BooleanValue("Animals", true);
        this.friends = new BooleanValue("Friends", false);
        this.ambient = new BooleanValue("Ambient", false);
        this.squid = new BooleanValue("Squid", false);
        this.xOffset = new Number("X Offset", 0);
        this.yOffset = new Number("y Offset", 15);
        this.addValue(this.players, this.monsters, this.animals, this.friends, this.ambient, this.squid, this.xOffset, this.yOffset);
    }
    
    public boolean IsValidEntity(final Entity entity) {
        if (!(entity instanceof EntityLivingBase)) {
            return false;
        }
        Label_0203: {
            if (entity instanceof EntityPlayer) {
                if (entity == Minecraft.getMinecraft().player) {
                    return false;
                }
                if (!(boolean)this.players.getValue()) {
                    return false;
                }
                while (true) {
                    switch (1951831590 + 1358816873 + 1) {
                        case 1956060643: {
                            continue;
                        }
                        case 615215183: {
                            if (!(boolean)this.friends.getValue() && FriendManager.friendsList.contains(entity.getName())) {
                                return false;
                            }
                            break Label_0203;
                        }
                        default: {
                            throw null;
                        }
                    }
                }
            }
        }
        if (!(boolean)this.ambient.getValue() && entity instanceof EntityAmbientCreature) {
            return false;
        }
        if (!(boolean)this.squid.getValue() && entity instanceof EntitySquid) {
            return false;
        }
        if (!(boolean)this.monsters.getValue()) {
            if (this.isHostileMob(entity) && !(boolean)this.monsters.getValue()) {
                return false;
            }
        }
        if (!(entity instanceof EntityPigZombie)) {
            return !(entity instanceof EntityAnimal) || (boolean)this.animals.getValue();
        }
        return false;
    }
    
    public boolean isHostileMob(final Entity entity) {
        return entity.isCreatureType(EnumCreatureType.MONSTER, false) && !isNeutralMob(entity);
    }
    
    public static boolean isNeutralMob(final Entity entity) {
        return entity instanceof EntityPigZombie || entity instanceof EntityWolf || entity instanceof EntityEnderman;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        this.x = (int)this.xOffset.getValue();
        this.y = (int)this.yOffset.getValue();
    }
    
    public boolean lambda$onRenderGameOverlay$0(final Entity entity) {
        return this.IsValidEntity(entity);
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent$Text renderGameOverlayEvent$Text) {
        final EntityLivingBase entityLivingBase = (EntityLivingBase)Minecraft.getMinecraft().world.loadedEntityList.stream().filter(this::lambda$onRenderGameOverlay$0).map(TargetHUD::lambda$onRenderGameOverlay$1).min(Comparator.comparing((Function<? super T, ? extends Comparable>)TargetHUD::lambda$onRenderGameOverlay$2)).orElse(null);
        if (entityLivingBase == null) {
            return;
        }
        final float min = Math.min((entityLivingBase.getHealth() + entityLivingBase.getAbsorptionAmount()) / entityLivingBase.getMaxHealth() * 100.0f, 100.0f);
        final BlockPos position = entityLivingBase.getPosition();
        final BlockPos[] array = { position.north(), position.south(), position.east(), position.west() };
        int n = 0;
        boolean b = true;
        String str = "None";
        final BlockPos[] array2 = array;
        for (int length = array2.length, i = 0; i < length; ++i) {
            final Block block = Minecraft.getMinecraft().world.getBlockState(array2[i]).getBlock();
            if (block == Blocks.AIR) {
                break;
            }
            if (block != Blocks.BEDROCK) {
                b = false;
            }
            if (block == Blocks.OBSIDIAN || block == Blocks.BEDROCK) {
                ++n;
            }
        }
        if (n == 4) {
            if (b) {
                str = "Bedrock";
            }
            else {
                str = "Obsidian";
            }
        }
        if (entityLivingBase instanceof EntityPlayer) {
            final EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
            try {
                final int n2 = (int)MathUtils.clamp((float)Minecraft.getMinecraft().getConnection().getPlayerInfo(entityPlayer.getUniqueID()).getResponseTime(), 0.0f, 300.0f);
            }
            catch (NullPointerException ex) {}
        }
        final DecimalFormat decimalFormat = new DecimalFormat("#.#");
        RenderUtils.drawRect((float)this.x, (float)this.y, (float)(this.x + 120), (float)(this.y + 75), ColorUtils.color(0, 0, 0, 100));
        if (entityLivingBase.fallDistance < 0.0f) {}
        final int colorSlider = ColorUtils.ColorSlider(min / 3.0f * 115.0f, 1.0f);
        this.fontRenderer.drawStringWithShadow("Target: " + entityLivingBase.getName(), this.x + 20, this.y + 2, -1);
        this.fontRenderer.drawStringWithShadow("Distance: " + decimalFormat.format(entityLivingBase.getDistance((Entity)Minecraft.getMinecraft().player)), this.x + 20, this.y + 14, -1);
        this.fontRenderer.drawStringWithShadow("Health: " + decimalFormat.format(entityLivingBase.getHealth() + entityLivingBase.getAbsorptionAmount()) + " / " + decimalFormat.format(entityLivingBase.getMaxHealth() + entityLivingBase.getAbsorptionAmount()), this.x + 20, this.y + 26, -1);
        this.fontRenderer.drawStringWithShadow("Hole: " + str, this.x + 20, this.y + 38, -1);
        RenderUtils.drawRect((float)this.x, (float)(this.y + 70), this.x + min / 100.0f * 120.0f, (float)(this.y + 75), colorSlider);
        final ScaledResolution scaledResolution = new ScaledResolution(Wrapper.INSTANCE.mc());
        final RenderItem renderItem = Wrapper.INSTANCE.mc().getRenderItem();
        final ItemStack heldItemMainhand = entityLivingBase.getHeldItemMainhand();
        final ItemStack heldItemOffhand = entityLivingBase.getHeldItemOffhand();
        final int n3 = scaledResolution.getScaledWidth() / 2;
        renderItem.zLevel = 0.0f;
        GlStateManager.enableTexture2D();
        int x = this.x;
        for (final ItemStack itemStack : entityLivingBase.getArmorInventoryList()) {
            if (itemStack.isEmpty()) {
                continue;
            }
            GlStateManager.enableDepth();
            renderItem.zLevel = 200.0f;
            renderItem.renderItemAndEffectIntoGUI(itemStack, x + 80, this.y + 46);
            renderItem.renderItemOverlayIntoGUI(Wrapper.INSTANCE.fontRenderer(), itemStack, x + 80, this.y + 46, "");
            renderItem.zLevel = 0.0f;
            renderItem.zLevel = 200.0f;
            renderItem.renderItemAndEffectIntoGUI(heldItemMainhand, this.x + 16, this.y + 46);
            renderItem.renderItemOverlayIntoGUI(Wrapper.INSTANCE.fontRenderer(), heldItemMainhand, this.x + 16, this.y + 46, "");
            renderItem.zLevel = 0.0f;
            renderItem.zLevel = 200.0f;
            renderItem.renderItemAndEffectIntoGUI(heldItemOffhand, this.x + 2, this.y + 46);
            renderItem.renderItemOverlayIntoGUI(Wrapper.INSTANCE.fontRenderer(), heldItemOffhand, this.x + 2, this.y + 46, "");
            renderItem.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            if (itemStack.getCount() > 1) {
                new StringBuilder().append(itemStack.getCount()).append("").toString();
            }
            final int n4 = 100 - (int)((1.0f - (itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / itemStack.getMaxDamage()) * 100.0f);
            this.fontRenderer.drawStringWithShadow(n4 + "", x + 87 - this.fontRenderer.getStringWidth(n4 + "") / 2, this.y + 60, -1);
            x -= 15;
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
        if (Minecraft.getMinecraft().currentScreen instanceof HudGuiScreen) {
            if (MouseUtils.isLeftClicked() && !MouseUtils.isMouseOver(this.x, this.x + 120, this.y, this.y + 75)) {
                this.isAlreadyDragging = true;
            }
            if (!MouseUtils.isLeftClicked()) {
                if (this.isAlreadyDragging) {
                    this.isAlreadyDragging = false;
                }
            }
            if (!this.isAlreadyDragging || this.isDragging) {
                if (MouseUtils.isMouseOver(this.x, this.x + 120, this.y, this.y + 75)) {
                    this.isDragging = true;
                }
                if (MouseUtils.isLeftClicked() && this.isDragging) {
                    this.finalMouseX = MouseUtils.getMouseX();
                    this.finalMouseY = MouseUtils.getMouseY();
                    this.xOffset.setValue(this.finalMouseX - 60);
                    this.yOffset.setValue(this.finalMouseY);
                    MouseUtils.isDragging = true;
                }
                else {
                    this.isDragging = false;
                }
            }
        }
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        if (entityLivingBase instanceof EntityPlayer || entityLivingBase instanceof EntityEnderman || entityLivingBase instanceof EntitySpider || entityLivingBase instanceof EntityChicken) {
            if (entityLivingBase instanceof EntityPlayer) {
                GuiInventory.drawEntityOnScreen(this.x + 10, this.y + 35, 15, 0.0f, 10.0f, entityLivingBase);
            }
            if (entityLivingBase instanceof EntityEnderman) {
                GuiInventory.drawEntityOnScreen(this.x + 10, this.y + 40, 15, 0.0f, 10.0f, entityLivingBase);
            }
            if (entityLivingBase instanceof EntitySpider) {
                GuiInventory.drawEntityOnScreen(this.x + 10, this.y + 30, 15, 0.0f, 10.0f, entityLivingBase);
            }
            if (entityLivingBase instanceof EntityChicken) {
                GuiInventory.drawEntityOnScreen(this.x + 10, this.y + 30, 15, 0.0f, 10.0f, entityLivingBase);
            }
        }
        else {
            GuiInventory.drawEntityOnScreen(this.x + 10, this.y + 35, 15, 0.0f, 10.0f, entityLivingBase);
        }
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
    }
    
    public static Float lambda$onRenderGameOverlay$2(final EntityLivingBase entityLivingBase) {
        return Minecraft.getMinecraft().player.getDistance((Entity)entityLivingBase);
    }
}
