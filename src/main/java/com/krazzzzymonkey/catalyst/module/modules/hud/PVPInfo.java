/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.hud;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.MouseUtils;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.gui.click.HudGuiScreen;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import org.lwjgl.opengl.GL11;
import java.util.function.ToIntFunction;
import java.util.function.Predicate;
import com.krazzzzymonkey.catalyst.utils.ChatColor;
import net.minecraft.client.gui.ScaledResolution;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Text;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import net.minecraft.client.Minecraft;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.value.types.Number;
import com.krazzzzymonkey.catalyst.module.Modules;

public class PVPInfo extends Modules
{
    public Number xOffset;
    public boolean isDragging;
    public Number yOffset;
    public boolean isAlreadyDragging;
    public BooleanValue rainbow;
    public Minecraft mc;
    public int colorRect;
    public int finalMouseX;
    public ColorValue colorValue;
    public int colorRect2;
    public int finalMouseY;
    
    public static boolean lambda$onRenderGameOverlay$0(final ItemStack itemStack) {
        return itemStack.getItem() == Items.TOTEM_OF_UNDYING;
    }
    
    public static boolean lambda$onRenderGameOverlay$3(final ItemStack itemStack) {
        return itemStack.getItem() == Items.GOLDEN_APPLE;
    }
    
    public PVPInfo() {
        super("PVPInfo", ModuleCategory.HUD, "Displays amount of PvP items in your inventory", true);
        this.mc = Minecraft.getMinecraft();
        this.colorRect = ColorUtils.color(0.0f, 0.0f, 0.0f, 0.0f);
        this.colorRect2 = ColorUtils.color(0.0f, 0.0f, 0.0f, 0.5f);
        this.finalMouseX = 0;
        this.finalMouseY = 0;
        this.isDragging = false;
        this.isAlreadyDragging = false;
        this.xOffset = new Number("OffsetX", 0);
        this.yOffset = new Number("OffsetY", 105);
        this.rainbow = new BooleanValue("Rainbow", false);
        this.colorValue = new ColorValue("Color", Color.CYAN);
        this.addValue(this.xOffset, this.yOffset, this.colorValue, this.rainbow, this.xOffset, this.yOffset);
    }
    
    public static boolean lambda$onRenderGameOverlay$2(final ItemStack itemStack) {
        return itemStack.getItem() == Items.EXPERIENCE_BOTTLE;
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent$Text renderGameOverlayEvent$Text) {
        int n = this.colorValue.getColor().getRGB();
        if (this.rainbow.getValue()) {
            n = ColorUtils.rainbow().getRGB();
        }
        final int intValue = (int)this.xOffset.getValue();
        final int intValue2 = (int)this.yOffset.getValue();
        final ScaledResolution scaledResolution = new ScaledResolution(Wrapper.INSTANCE.mc());
        final String string = "Totems: " + ChatColor.WHITE + this.mc.player.inventory.mainInventory.stream().filter(PVPInfo::lambda$onRenderGameOverlay$0).mapToInt(ItemStack::func_190916_E).sum() + ChatColor.RESET + " XP: " + ChatColor.WHITE + this.mc.player.inventory.mainInventory.stream().filter(PVPInfo::lambda$onRenderGameOverlay$2).mapToInt(ItemStack::func_190916_E).sum() + ChatColor.RESET + " Crystals: " + ChatColor.WHITE + this.mc.player.inventory.mainInventory.stream().filter(PVPInfo::lambda$onRenderGameOverlay$1).mapToInt(ItemStack::func_190916_E).sum() + ChatColor.RESET + " Gapples: " + ChatColor.WHITE + this.mc.player.inventory.mainInventory.stream().filter(PVPInfo::lambda$onRenderGameOverlay$3).mapToInt(ItemStack::func_190916_E).sum();
        GL11.glPushMatrix();
        if (ModuleManager.getModule("CustomFont").isToggled()) {
            Main.fontRenderer.drawStringWithShadow(string, intValue, intValue2, n);
        }
        else {
            Wrapper.INSTANCE.fontRenderer().drawStringWithShadow(string, (float)intValue, (float)intValue2, n);
        }
        if (Minecraft.getMinecraft().currentScreen instanceof HudGuiScreen) {
            if (ModuleManager.getModule("CustomFont").isToggled()) {
                RenderUtils.drawRect((float)intValue, (float)intValue2, (float)(intValue + Main.fontRenderer.getStringWidth(string)), (float)(intValue2 + 14), ColorUtils.color(0, 0, 0, 100));
            }
            else {
                RenderUtils.drawRect((float)intValue, (float)intValue2, (float)(intValue + Wrapper.INSTANCE.fontRenderer().getStringWidth(string)), (float)(intValue2 + 14), ColorUtils.color(0, 0, 0, 100));
            }
            if (MouseUtils.isLeftClicked() && !MouseUtils.isMouseOver(intValue, intValue + Main.fontRenderer.getStringWidth(string), intValue2, intValue2 + 14)) {
                this.isAlreadyDragging = true;
            }
            if (!MouseUtils.isLeftClicked() && this.isAlreadyDragging) {
                this.isAlreadyDragging = false;
            }
            if (!this.isAlreadyDragging || this.isDragging) {
                if (MouseUtils.isMouseOver(intValue, intValue + Main.fontRenderer.getStringWidth(string), intValue2, intValue2 + 14)) {
                    this.isDragging = true;
                }
                if (MouseUtils.isLeftClicked() && this.isDragging) {
                    this.finalMouseX = MouseUtils.getMouseX();
                    this.finalMouseY = MouseUtils.getMouseY();
                    this.xOffset.value = this.finalMouseX - Main.fontRenderer.getStringWidth(string) / 2;
                    this.yOffset.value = this.finalMouseY;
                    MouseUtils.isDragging = true;
                }
                else {
                    this.isDragging = false;
                }
            }
        }
        GL11.glPopMatrix();
    }
    
    public static boolean lambda$onRenderGameOverlay$1(final ItemStack itemStack) {
        return itemStack.getItem() == Items.END_CRYSTAL;
    }
}
