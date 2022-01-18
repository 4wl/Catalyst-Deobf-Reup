/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.hud;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.client.renderer.RenderItem;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.gui.ScaledResolution;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraftforge.client.event.RenderGameOverlayEvent$Text;
import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class ArmorHUD extends Modules
{
    public int armorSpacing;
    public BooleanValue damage;
    public int armorCompress;
    
    public ArmorHUD() {
        super("ArmorHUD", ModuleCategory.HUD, "Shows armor and durability on HUD", true);
        this.damage = new BooleanValue("Damage", true);
        this.addValue(this.damage);
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(final RenderGameOverlayEvent$Text renderGameOverlayEvent$Text) {
        final ScaledResolution scaledResolution = new ScaledResolution(Wrapper.INSTANCE.mc());
        final RenderItem renderItem = Wrapper.INSTANCE.mc().getRenderItem();
        GlStateManager.enableTexture2D();
        final int n = scaledResolution.getScaledWidth() / 2;
        int n2 = 0;
        final int n3 = scaledResolution.getScaledHeight() - 55 - (Wrapper.INSTANCE.player().isInWater() ? 10 : 0);
        for (final ItemStack itemStack : Wrapper.INSTANCE.inventory().armorInventory) {
            ++n2;
            if (itemStack.isEmpty()) {
                continue;
            }
            final int n4 = n - 90 + (9 - n2) * this.armorSpacing + this.armorCompress;
            GlStateManager.enableDepth();
            renderItem.zLevel = 200.0f;
            renderItem.renderItemAndEffectIntoGUI(itemStack, n4, n3);
            renderItem.renderItemOverlayIntoGUI(Wrapper.INSTANCE.fontRenderer(), itemStack, n4, n3, "");
            renderItem.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            final String s = (itemStack.getCount() > 1) ? (itemStack.getCount() + "") : "";
            if (ModuleManager.getModule("CustomFont").isToggled()) {
                Main.fontRenderer.drawString(s, (float)(n4 + 19 - 2 - Main.fontRenderer.getStringWidth(s)), (float)(n3 + 9), 16777215);
            }
            else {
                Wrapper.INSTANCE.fontRenderer().drawString(s, n4 + 19 - 2 - ~Wrapper.INSTANCE.fontRenderer().getStringWidth(s) + 1, n3 + 9, 16777215);
            }
            if (this.damage.getValue()) {
                try {
                    float n5 = (itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / itemStack.getMaxDamage();
                    float n6 = 1.0f - n5;
                    final int n7 = 100 - (int)(n6 * 100.0f);
                    if (n6 > 255.0f || n6 < 0.0f) {
                        n6 = 255.0f;
                    }
                    if (n5 > 255.0f || n5 < 0.0f) {
                        n5 = 255.0f;
                    }
                    final int color = ColorUtils.color(n6, n5, 0.0f, 1.0f);
                    if (ModuleManager.getModule("CustomFont").isToggled()) {
                        Main.fontRenderer.drawString(n7 + "", n4 + 8 - Main.fontRenderer.getStringWidth(n7 + "") / 2.0f, (float)(n3 - 11), color);
                    }
                    else {
                        Wrapper.INSTANCE.fontRenderer().drawString(n7 + "", n4 + 8 - Wrapper.INSTANCE.fontRenderer().getStringWidth(n7 + "") / 2, n3 - 11, color);
                    }
                }
                catch (Exception ex) {}
            }
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
        this.armorCompress = 2;
        this.armorSpacing = 20;
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
    }
}
