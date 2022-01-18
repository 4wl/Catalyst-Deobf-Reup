/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import com.krazzzzymonkey.catalyst.utils.BlockUtils;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.math.Vec3d;
import com.krazzzzymonkey.catalyst.managers.FriendManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager$DestFactor;
import net.minecraft.client.renderer.GlStateManager$SourceFactor;
import net.minecraft.entity.EntityLivingBase;
import com.krazzzzymonkey.catalyst.utils.MathUtils;
import com.krazzzzymonkey.catalyst.value.Value;
import net.minecraft.init.Enchantments;
import java.awt.Font;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Comparator;
import java.util.function.Predicate;
import com.krazzzzymonkey.catalyst.utils.EntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import java.util.Iterator;
import net.minecraft.entity.player.InventoryPlayer;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemElytra;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.enchantment.EnchantmentHelper;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import com.krazzzzymonkey.catalyst.utils.ChatColor;
import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.sliders.DoubleValue;
import com.krazzzzymonkey.catalyst.utils.font.CFontRenderer;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Nametags extends Modules
{
    public BooleanValue gamemode;
    public BooleanValue ping;
    public Nametags$EnchantEntry[] enchants;
    public CFontRenderer fontRendererSmall;
    public DoubleValue scale;
    public BooleanValue animals;
    public BooleanValue health;
    public BooleanValue durability;
    public BooleanValue simplifyEnchants;
    public BooleanValue players;
    public BooleanValue armor;
    public CFontRenderer fontRendererIn;
    public BooleanValue mobs;
    public IntegerValue range;
    
    public void drawDamage(final ItemStack itemStack, final int n, final int n2) {
        final float n3 = (itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / itemStack.getMaxDamage();
        final float n4 = 1.0f - n3;
        final int n5 = 100;
        final int n6 = (int)(n4 * 100.0f);
        final int n7 = (n5 & ~n6) + (n6 & ~n5);
        GlStateManager.disableDepth();
        Nametags.mc.fontRenderer.drawStringWithShadow(n7 + "", n - Nametags.mc.fontRenderer.getStringWidth(n7 + "") / 2.0f, (float)(n2 + 50), new Color((int)(n4 * 255.0f), (int)(n3 * 255.0f), 0).getRGB());
        GlStateManager.enableDepth();
    }
    
    public String getShortGameType(final String s) {
        if (s.equalsIgnoreCase("survival")) {
            return ChatColor.GRAY + "S";
        }
        if (s.equalsIgnoreCase("creative")) {
            return ChatColor.AQUA + "C";
        }
        if (s.equalsIgnoreCase("adventure")) {
            return ChatColor.GRAY + "A";
        }
        if (s.equalsIgnoreCase("spectator")) {
            return ChatColor.RED + "SP";
        }
        return "";
    }
    
    public static Float lambda$onRenderWorldLast$3(final Entity entity) {
        return -Nametags.mc.player.getDistance(entity);
    }
    
    public void renderItem(final ItemStack itemStack, final int n, int n2) {
        final FontRenderer fontRenderer = Nametags.mc.fontRenderer;
        final RenderItem renderItem = Nametags.mc.getRenderItem();
        GlStateManager.pushMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.clear(256);
        RenderHelper.enableStandardItemLighting();
        Nametags.mc.getRenderItem().zLevel = -150.0f;
        GlStateManager.disableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.disableCull();
        Nametags.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, n, n2);
        Nametags.mc.getRenderItem().renderItemOverlays(Nametags.mc.fontRenderer, itemStack, n, n2);
        Nametags.mc.getRenderItem().zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.scale(0.75f, 0.75f, 0.75f);
        GlStateManager.scale(1.33f, 1.33f, 1.33f);
        n2 -= 6;
        if ((boolean)this.simplifyEnchants.getValue() && this.isMaxEnchants(itemStack)) {
            GlStateManager.translate((float)(n - 1), (float)(n2 + 2), 0.0f);
            GlStateManager.scale(0.42f, 0.42f, 0.42f);
            GlStateManager.disableDepth();
            GlStateManager.disableLighting();
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            this.fontRendererSmall.drawString(ChatColor.RED + "MAX", (float)(20 - this.fontRendererSmall.getStringWidth("MAX6") / 2), 0.0, -1, false);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            GlStateManager.scale(2.42f, 2.42f, 2.42f);
            GlStateManager.translate((float)(-n + 1), (float)(-(-n2) - 1), 0.0f);
            renderItem.zLevel = 0.0f;
            RenderHelper.disableStandardItemLighting();
            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
            GlStateManager.disableLighting();
            GlStateManager.popMatrix();
            return;
        }
        Nametags$EnchantEntry[] enchants;
        for (int length = (enchants = this.enchants).length, i = 0; i < length; ++i) {
            final Nametags$EnchantEntry nametags$EnchantEntry = enchants[i];
            final int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(nametags$EnchantEntry.getEnchant(), itemStack);
            String string = "" + enchantmentLevel;
            if (enchantmentLevel > 10) {
                string = "10+";
            }
            if (enchantmentLevel > 0) {
                GlStateManager.translate((float)(n - 1), (float)(n2 + 2), 0.0f);
                GlStateManager.scale(0.42f, 0.42f, 0.42f);
                GlStateManager.disableDepth();
                GlStateManager.disableLighting();
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                this.fontRendererSmall.drawString("§f" + nametags$EnchantEntry.getName() + " " + string, (float)(20 - this.fontRendererSmall.getStringWidth("§f" + nametags$EnchantEntry.getName() + " " + 6) / 2), 0.0, -1, false);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GlStateManager.enableLighting();
                GlStateManager.enableDepth();
                GlStateManager.scale(2.42f, 2.42f, 2.42f);
                GlStateManager.translate((float)(-n + 1), (float)(-n2), 0.0f);
                n2 -= (int)((this.fontRendererSmall.getHeight() + 5) * 0.5f);
            }
        }
        renderItem.zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableLighting();
        GlStateManager.popMatrix();
    }
    
    public boolean isMaxEnchants(final ItemStack itemStack) {
        return (itemStack.getItem() instanceof ItemElytra && itemStack.getEnchantmentTagList().tagCount() > 1) || itemStack.getEnchantmentTagList().tagCount() > 2;
    }
    
    public void renderArmor(final EntityPlayer entityPlayer, int n, final int n2) {
        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(true);
        GlStateManager.clear(256);
        GlStateManager.enableDepth();
        GlStateManager.disableAlpha();
        final InventoryPlayer inventory = entityPlayer.inventory;
        final ItemStack heldItemMainhand = entityPlayer.getHeldItemMainhand();
        final ItemStack armorItemInSlot = inventory.armorItemInSlot(0);
        final ItemStack armorItemInSlot2 = inventory.armorItemInSlot(1);
        final ItemStack armorItemInSlot3 = inventory.armorItemInSlot(2);
        final ItemStack armorItemInSlot4 = inventory.armorItemInSlot(3);
        final ItemStack heldItemOffhand = entityPlayer.getHeldItemOffhand();
        ItemStack[] array;
        if (heldItemMainhand != null && heldItemOffhand != null) {
            array = new ItemStack[] { heldItemMainhand, armorItemInSlot4, armorItemInSlot3, armorItemInSlot2, armorItemInSlot, heldItemOffhand };
        }
        else if (heldItemMainhand != null && heldItemOffhand == null) {
            array = new ItemStack[] { heldItemMainhand, armorItemInSlot4, armorItemInSlot3, armorItemInSlot2, armorItemInSlot };
        }
        else if (heldItemMainhand == null && heldItemOffhand != null) {
            array = new ItemStack[] { armorItemInSlot4, armorItemInSlot3, armorItemInSlot2, armorItemInSlot, heldItemOffhand };
        }
        else {
            array = new ItemStack[] { armorItemInSlot4, armorItemInSlot3, armorItemInSlot2, armorItemInSlot };
        }
        final ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        ItemStack[] array2;
        for (int length = (array2 = array).length, i = 0; i < length; ++i) {
            final ItemStack itemStack = array2[i];
            if (itemStack != null && itemStack.getItem() != null) {
                list.add(itemStack);
            }
        }
        n -= 16 * list.size() / 2;
        GlStateManager.disableDepth();
        for (final ItemStack itemStack2 : list) {
            this.renderItem(itemStack2, n, n2);
            if (this.durability.getValue()) {
                this.renderItemDurability(itemStack2, n, n2);
            }
            n += 16;
        }
        GlStateManager.enableDepth();
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null || !this.isToggled()) {
            return;
        }
        GlStateManager.disableDepth();
        Minecraft.getMinecraft().world.loadedEntityList.stream().filter(EntityUtils::isLiving).filter(Nametags::lambda$onRenderWorldLast$0).filter(this::lambda$onRenderWorldLast$1).filter(this::lambda$onRenderWorldLast$2).sorted(Comparator.comparing((Function<? super T, ? extends Comparable>)Nametags::lambda$onRenderWorldLast$3)).forEach(this::drawNametag);
        GlStateManager.enableDepth();
    }
    
    public Nametags() {
        super("Nametags", ModuleCategory.RENDER, "Renders more detailed nametags above entities");
        this.fontRendererIn = new CFontRenderer(new Font("Dosis", 1, 20), true, true);
        this.fontRendererSmall = new CFontRenderer(new Font("Arial", 0, 20), true, false);
        this.enchants = new Nametags$EnchantEntry[] { new Nametags$EnchantEntry(Enchantments.PROTECTION, "Pro"), new Nametags$EnchantEntry(Enchantments.THORNS, "Thr"), new Nametags$EnchantEntry(Enchantments.SHARPNESS, "Sha"), new Nametags$EnchantEntry(Enchantments.FIRE_ASPECT, "Fia"), new Nametags$EnchantEntry(Enchantments.KNOCKBACK, "Knb"), new Nametags$EnchantEntry(Enchantments.UNBREAKING, "Unb"), new Nametags$EnchantEntry(Enchantments.POWER, "Pow"), new Nametags$EnchantEntry(Enchantments.FIRE_PROTECTION, "Fpr"), new Nametags$EnchantEntry(Enchantments.FEATHER_FALLING, "Fea"), new Nametags$EnchantEntry(Enchantments.BLAST_PROTECTION, "Bla"), new Nametags$EnchantEntry(Enchantments.PROJECTILE_PROTECTION, "Ppr"), new Nametags$EnchantEntry(Enchantments.RESPIRATION, "Res"), new Nametags$EnchantEntry(Enchantments.AQUA_AFFINITY, "Aqu"), new Nametags$EnchantEntry(Enchantments.DEPTH_STRIDER, "Dep"), new Nametags$EnchantEntry(Enchantments.FROST_WALKER, "Fro"), new Nametags$EnchantEntry(Enchantments.BINDING_CURSE, "Bin"), new Nametags$EnchantEntry(Enchantments.SMITE, "Smi"), new Nametags$EnchantEntry(Enchantments.BANE_OF_ARTHROPODS, "Ban"), new Nametags$EnchantEntry(Enchantments.LOOTING, "Loo"), new Nametags$EnchantEntry(Enchantments.SWEEPING, "Swe"), new Nametags$EnchantEntry(Enchantments.EFFICIENCY, "Eff"), new Nametags$EnchantEntry(Enchantments.SILK_TOUCH, "Sil"), new Nametags$EnchantEntry(Enchantments.FORTUNE, "For"), new Nametags$EnchantEntry(Enchantments.FLAME, "Fla"), new Nametags$EnchantEntry(Enchantments.LUCK_OF_THE_SEA, "Luc"), new Nametags$EnchantEntry(Enchantments.LURE, "Lur"), new Nametags$EnchantEntry(Enchantments.MENDING, "Men"), new Nametags$EnchantEntry(Enchantments.VANISHING_CURSE, "Van"), new Nametags$EnchantEntry(Enchantments.PUNCH, "Pun") };
        this.players = new BooleanValue("Players", true);
        this.animals = new BooleanValue("Animals", false);
        this.mobs = new BooleanValue("Mobs", false);
        this.armor = new BooleanValue("Armor", true);
        this.simplifyEnchants = new BooleanValue("SimplifyEnchants", true);
        this.durability = new BooleanValue("Durability", true);
        this.ping = new BooleanValue("Ping", true);
        this.health = new BooleanValue("Health", true);
        this.gamemode = new BooleanValue("Gamemode", true);
        this.scale = new DoubleValue("Scale", 2.5, 0.5, 10.0);
        this.range = new IntegerValue("Range", 200, 10, 500);
        this.addValue(this.players, this.animals, this.mobs, this.armor, this.simplifyEnchants, this.durability, this.ping, this.health, this.gamemode, this.scale, this.range);
    }
    
    public static boolean lambda$onRenderWorldLast$0(final Entity entity) {
        return !EntityUtils.isFakeLocalPlayer(entity);
    }
    
    public boolean isFakePlayer(final EntityPlayer entityPlayer) {
        return entityPlayer.getEntityId() == -9999;
    }
    
    public void drawNametag(final Entity entity) {
        GlStateManager.pushMatrix();
        final Vec3d interpolatedRenderPos = getInterpolatedRenderPos(entity, Nametags.mc.getRenderPartialTicks());
        final float n = entity.height + 0.5f - (entity.isSneaking() ? 0.25f : 0.0f);
        final double x = interpolatedRenderPos.x;
        final double n2 = interpolatedRenderPos.y + n;
        final double z = interpolatedRenderPos.z;
        final float playerViewY = Nametags.mc.getRenderManager().playerViewY;
        final float playerViewX = Nametags.mc.getRenderManager().playerViewX;
        final boolean b = Nametags.mc.getRenderManager().options.thirdPersonView == 2;
        GlStateManager.translate(x, n2, z);
        GlStateManager.rotate(-playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate((b ? -1 : 1) * playerViewX, 1.0f, 0.0f, 0.0f);
        float distance = Nametags.mc.player.getDistance(entity);
        if (distance <= 5.0f) {
            distance = 5.0f;
        }
        final float n3 = distance / 8.0f * (float)Math.pow(1.258925437927246, this.scale.getValue());
        GlStateManager.scale(n3, n3, n3);
        String str = "";
        if (entity instanceof EntityPlayer) {
            try {
                final int n4 = (int)MathUtils.clamp((float)Nametags.mc.getConnection().getPlayerInfo(entity.getUniqueID()).getResponseTime(), 0.0f, 1000.0f);
                if (n4 < 100) {
                    str = ChatColor.GREEN + String.valueOf(n4) + "ms";
                }
                else if (n4 < 200) {
                    str = ChatColor.YELLOW + String.valueOf(n4) + "ms";
                }
                else if (n4 < 300) {
                    str = ChatColor.GOLD + String.valueOf(n4) + "ms";
                }
                else {
                    str = ChatColor.RED + String.valueOf(n4) + "ms";
                }
            }
            catch (NullPointerException ex) {}
        }
        final int round = Math.round(((EntityLivingBase)entity).getHealth() + ((entity instanceof EntityPlayer) ? ((EntityPlayer)entity).getAbsorptionAmount() : 0.0f));
        String str2;
        if (round <= ((EntityLivingBase)entity).getMaxHealth() * 0.25) {
            str2 = "§4";
        }
        else if (round <= (((EntityLivingBase)entity).getMaxHealth() + ((EntityLivingBase)entity).getAbsorptionAmount()) * 0.5) {
            str2 = "§6";
        }
        else if (round <= (((EntityLivingBase)entity).getMaxHealth() + ((EntityLivingBase)entity).getAbsorptionAmount()) * 0.75) {
            str2 = "§e";
        }
        else {
            str2 = "§2";
        }
        final String string = str2 + round;
        GlStateManager.scale(-0.025f, -0.025f, 0.025f);
        String s;
        if (entity instanceof EntityPlayer && !this.isFakePlayer((EntityPlayer)entity)) {
            try {
                s = (this.gamemode.getValue() ? (ChatColor.DARK_GRAY + "[" + this.getShortGameType(Nametags.mc.player.connection.getPlayerInfo(((EntityPlayer)entity).getGameProfile().getId()).getGameType().getName()) + ChatColor.DARK_GRAY + "] ") : "") + ChatColor.RESET + entity.getName() + (((boolean)this.ping.getValue() && !str.equals("")) ? (" " + str) : "") + (this.health.getValue() ? (" " + string) : "");
            }
            catch (Exception ex2) {
                s = entity.getName() + (((boolean)this.ping.getValue() && !str.equals("")) ? (" " + str) : "") + (this.health.getValue() ? (" " + string) : "");
            }
        }
        else {
            s = entity.getName() + (((boolean)this.ping.getValue() && !str.equals("")) ? (" " + str) : "") + (this.health.getValue() ? (" " + string) : "");
        }
        final int n5 = this.fontRendererIn.getStringWidth(s) / 2;
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager$SourceFactor.SRC_ALPHA, GlStateManager$DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager$SourceFactor.ONE, GlStateManager$DestFactor.ZERO);
        GlStateManager.disableTexture2D();
        final Tessellator instance = Tessellator.getInstance();
        final BufferBuilder buffer = instance.getBuffer();
        GlStateManager.disableDepth();
        GL11.glTranslatef(0.0f, -20.0f, 0.0f);
        buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos((double)(-n5 - 2), 8.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.5f).endVertex();
        buffer.pos((double)(-n5 - 2), 19.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.5f).endVertex();
        buffer.pos((double)(n5 + 2), 19.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.5f).endVertex();
        buffer.pos((double)(n5 + 2), 8.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.5f).endVertex();
        instance.draw();
        buffer.begin(2, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos((double)(-n5 - 1), 8.0, 0.0).color(0.1f, 0.1f, 0.1f, 0.1f).endVertex();
        buffer.pos((double)(-n5 - 1), 19.0, 0.0).color(0.1f, 0.1f, 0.1f, 0.1f).endVertex();
        buffer.pos((double)(n5 + 1), 19.0, 0.0).color(0.1f, 0.1f, 0.1f, 0.1f).endVertex();
        buffer.pos((double)(n5 + 1), 8.0, 0.0).color(0.1f, 0.1f, 0.1f, 0.1f).endVertex();
        instance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
        if (!entity.isSneaking()) {
            this.fontRendererIn.drawString(s, (float)(-n5), 9.0f, (entity instanceof EntityPlayer) ? (FriendManager.friendsList.contains(entity.getName()) ? 49151 : 16777215) : 16777215);
        }
        else {
            this.fontRendererIn.drawString(s, (float)(-n5), 9.0f, 16755200);
        }
        if (entity instanceof EntityPlayer && (boolean)this.armor.getValue()) {
            this.renderArmor((EntityPlayer)entity, 0, -(this.fontRendererIn.getHeight() + 1) - 5);
        }
        GlStateManager.glNormal3f(0.0f, 0.0f, 0.0f);
        GL11.glTranslatef(0.0f, 20.0f, 0.0f);
        GlStateManager.scale(-40.0f, -40.0f, 40.0f);
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }
    
    public void renderItemDurability(final ItemStack itemStack, final int n, final int n2) {
        if (itemStack.getMaxDamage() == 0) {
            return;
        }
        float n4;
        final float n3 = n4 = (itemStack.getMaxDamage() - itemStack.getItemDamage()) / (float)itemStack.getMaxDamage();
        if (n4 > 1.0f) {
            n4 = 1.0f;
        }
        else if (n4 < 0.0f) {
            n4 = 0.0f;
        }
        final float n5 = 1.0f - n4;
        final float n6 = n3 * 100.0f;
        int n7 = n * 2;
        if (n6 < 10.0f) {
            n7 = n * 2 + 12;
        }
        else if (n6 < 100.0f) {
            n7 = n * 2 + 8;
        }
        else if (n6 >= 100.0f) {
            n7 = n * 2 + 5;
        }
        GlStateManager.scale(0.5, 0.5, 0.5);
        this.fontRendererSmall.drawString((int)n6 + "%", (float)n7, (float)(n2 + 20), new Color((int)(n5 * 255.0f), (int)(n4 * 255.0f), 0).getRGB());
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
    }
    
    public static Vec3d getInterpolatedRenderPos(final Entity entity, final float n) {
        return getInterpolatedPos(entity, n).subtract(Minecraft.getMinecraft().getRenderManager().renderPosX, Minecraft.getMinecraft().getRenderManager().renderPosY, Minecraft.getMinecraft().getRenderManager().renderPosZ);
    }
    
    public boolean lambda$onRenderWorldLast$1(final Entity entity) {
        return (boolean)((entity instanceof EntityPlayer) ? ((boolean)this.players.getValue() && Nametags.mc.player != entity) : (EntityUtils.isPassive(entity) ? this.animals.getValue() : ((boolean)this.mobs.getValue())));
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(BlockUtils.getInterpolatedAmount(entity, n));
    }
    
    public boolean lambda$onRenderWorldLast$2(final Entity entity) {
        return Nametags.mc.player.getDistance(entity) < this.range.getValue();
    }
}
