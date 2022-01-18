/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.chat;

import com.krazzzzymonkey.catalyst.value.Value;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import net.minecraft.block.Block;
import net.minecraftforge.event.world.BlockEvent$BreakEvent;
import java.text.DecimalFormat;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import net.minecraft.item.ItemFood;
import net.minecraft.network.play.client.CPacketEnchantItem;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.util.EnumHand;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import com.krazzzzymonkey.catalyst.events.PacketEvent;
import com.krazzzzymonkey.catalyst.value.sliders.IntegerValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class Announcer extends Modules
{
    public static String unSneakMessage;
    public int itemStack;
    public static String enchantMessage;
    public static String openGuiMessage;
    public BooleanValue jumping;
    public IntegerValue settingDelay;
    public BooleanValue greenText;
    public static String attackMessage;
    public static String jumpMessage;
    public static String walkMessage;
    public static String sneakMessage;
    public static long lastPositionUpdate;
    public static String breakMessage;
    public String prefix;
    public static String placeMessage;
    public BooleanValue placeBlock;
    public boolean sneak;
    public int blocksPlaced;
    public int healthUpdate;
    public static int eatingDelay;
    public BooleanValue booleanGui;
    public String heldItem;
    public int InAir;
    public BooleanValue walkDistance;
    public static int jumpDelay;
    public int blocksBroken;
    public int Delay;
    public static int blockPlacedDelay;
    public static double lastPositionZ;
    public BooleanValue enchantItem;
    public BooleanValue eatItem;
    public int xpThrown;
    public static double lastPositionY;
    public static double lastPositionX;
    public boolean hasHealth;
    public static String throwMessage;
    public BooleanValue booleanSneak;
    public BooleanValue breakBlock;
    public boolean isEating;
    public float health;
    public static String closeGuiMessage;
    public static double speed;
    public static int attackDelay;
    public BooleanValue throwItem;
    public static int blockBrokeDelay;
    public int delay;
    public int eaten;
    public boolean clickGuiIsOpen;
    public boolean isInAir;
    public static String eatMessage;
    
    static {
        Announcer.walkMessage = "I just walked {blocks} blocks thanks to Catalyst!";
        Announcer.throwMessage = "I just threw {amount} {name} thanks to Catalyst! ";
        Announcer.placeMessage = "I just placed {amount} {name} thanks to Catalyst!";
        Announcer.jumpMessage = "I just jumped thanks to Catalyst!";
        Announcer.sneakMessage = "I started sneaking thanks to Catalyst!";
        Announcer.unSneakMessage = "I stopped sneaking thanks to Catalyst!";
        Announcer.breakMessage = "I just broke {amount} {name} thanks to Catalyst!";
        Announcer.attackMessage = "I just attacked {name} with a {item} thanks to Catalyst!";
        Announcer.eatMessage = "I just ate {amount} {name} thanks to Catalyst!";
        Announcer.openGuiMessage = "I just opened Catalyst ClickGUI!";
        Announcer.closeGuiMessage = "I just closed Catalyst ClickGUI!";
        Announcer.enchantMessage = "I just enchanted an item thanks to Catalyst!";
        Announcer.blockPlacedDelay = 0;
        Announcer.blockBrokeDelay = 0;
        Announcer.jumpDelay = 0;
        Announcer.attackDelay = 0;
        Announcer.eatingDelay = 0;
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent packetEvent) {
        final Packet<?> packet = packetEvent.packet;
        if (packet instanceof CPacketPlayerTryUseItemOnBlock && Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemBlock && Minecraft.getMinecraft().player.getHeldItemMainhand().getDisplayName() != "Air") {
            ++this.blocksPlaced;
            final int nextInt = ThreadLocalRandom.current().nextInt(1, 11);
            if (this.placeBlock.getValue()) {
                if (this.blocksPlaced > nextInt) {
                    final String replace = Announcer.placeMessage.replace("{amount}", this.blocksPlaced + "").replace("{name}", Minecraft.getMinecraft().player.getHeldItemMainhand().getDisplayName());
                    if (this.Delay > this.delay) {
                        Minecraft.getMinecraft().player.sendChatMessage(this.prefix + replace);
                        this.Delay = 0;
                        this.blocksPlaced = 0;
                    }
                }
            }
        }
        if (packet instanceof CPacketPlayerTryUseItem && Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemExpBottle) {
            ++this.xpThrown;
            final int nextInt2 = ThreadLocalRandom.current().nextInt(1, 31);
            if ((boolean)this.throwItem.getValue() && this.xpThrown > nextInt2) {
                final String replace2 = Announcer.throwMessage.replace("{amount}", this.xpThrown + "").replace("{name}", Minecraft.getMinecraft().player.getHeldItemMainhand().getDisplayName());
                if (this.Delay > this.delay) {
                    Minecraft.getMinecraft().player.sendChatMessage(this.prefix + replace2);
                    this.Delay = 0;
                    this.xpThrown = 0;
                }
            }
        }
        if (packet instanceof CPacketPlayerTryUseItem && Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemEnderPearl && (boolean)this.throwItem.getValue()) {
            final String replace3 = Announcer.throwMessage.replace("{amount}", "1").replace("{name}", Minecraft.getMinecraft().player.getHeldItemMainhand().getDisplayName());
            if (this.Delay > this.delay) {
                Minecraft.getMinecraft().player.sendChatMessage(this.prefix + replace3);
                this.Delay = 0;
            }
        }
        if (packet instanceof CPacketEnchantItem && (boolean)this.enchantItem.getValue()) {
            final String enchantMessage = Announcer.enchantMessage;
            if (this.Delay > this.delay) {
                Minecraft.getMinecraft().player.sendChatMessage(this.prefix + enchantMessage);
                this.Delay = 0;
            }
        }
        if (packet instanceof CPacketPlayerTryUseItem && (boolean)this.eatItem.getValue() && Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemFood) {
            if (!this.isEating) {
                this.itemStack = Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND).getCount();
                this.isEating = true;
            }
            if (this.isEating && Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND).getCount() < this.itemStack) {
                ++this.eaten;
            }
            if (this.eaten > 0) {
                final String replace4 = Announcer.eatMessage.replace("{amount}", this.eaten + "").replace("{name}", Minecraft.getMinecraft().player.getHeldItemMainhand().getDisplayName());
                if (this.Delay > this.delay) {
                    Minecraft.getMinecraft().player.sendChatMessage(this.prefix + replace4);
                    this.isEating = false;
                    this.Delay = 0;
                    this.eaten = 0;
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent$ClientTickEvent tickEvent$ClientTickEvent) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        this.delay = 1 + this.settingDelay.getValue();
        if (this.greenText.getValue()) {
            this.prefix = "> ";
        }
        if (!(boolean)this.greenText.getValue()) {
            this.prefix = "";
        }
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().player.getUniqueID() == null) {
            return;
        }
        ++this.InAir;
        ++this.Delay;
        final Modules module = ModuleManager.getModule("ClickGui");
        ++Announcer.blockBrokeDelay;
        ++Announcer.blockPlacedDelay;
        ++Announcer.jumpDelay;
        ++Announcer.attackDelay;
        ++Announcer.eatingDelay;
        this.heldItem = Minecraft.getMinecraft().player.getHeldItemMainhand().getDisplayName();
        if ((boolean)this.walkDistance.getValue() && Announcer.lastPositionUpdate + 10000L < System.currentTimeMillis()) {
            final double n = Announcer.lastPositionX - Minecraft.getMinecraft().player.lastTickPosX;
            final double n2 = Announcer.lastPositionY - Minecraft.getMinecraft().player.lastTickPosY;
            final double n3 = Announcer.lastPositionZ - Minecraft.getMinecraft().player.lastTickPosZ;
            Announcer.speed = Math.sqrt(n * n + n2 * n2 + n3 * n3);
            if (Announcer.speed > 1.0 && Announcer.speed <= 5000.0) {
                final String format = new DecimalFormat("0").format(Announcer.speed);
                if (this.Delay > this.delay) {
                    Minecraft.getMinecraft().player.sendChatMessage(this.prefix + Announcer.walkMessage.replace("{blocks}", format));
                    this.Delay = 0;
                }
            }
            Announcer.lastPositionUpdate = System.currentTimeMillis();
            Announcer.lastPositionX = Minecraft.getMinecraft().player.lastTickPosX;
            Announcer.lastPositionY = Minecraft.getMinecraft().player.lastTickPosY;
            Announcer.lastPositionZ = Minecraft.getMinecraft().player.lastTickPosZ;
        }
        if (this.booleanSneak.getValue()) {
            if (Minecraft.getMinecraft().player.isSneaking() && this.Delay > this.delay && !this.sneak) {
                Minecraft.getMinecraft().player.sendChatMessage(this.prefix + Announcer.sneakMessage);
                this.sneak = true;
                this.Delay = 0;
            }
            if (!Minecraft.getMinecraft().player.isSneaking() && this.sneak && this.Delay > this.delay) {
                Minecraft.getMinecraft().player.sendChatMessage(this.prefix + Announcer.unSneakMessage);
                this.sneak = false;
                this.Delay = 0;
            }
        }
        if ((boolean)this.booleanGui.getValue() && module.isToggled() && !this.clickGuiIsOpen) {
            if (this.Delay > this.delay) {
                Minecraft.getMinecraft().player.sendChatMessage(this.prefix + Announcer.openGuiMessage);
                this.clickGuiIsOpen = true;
                this.Delay = 0;
            }
            if (!module.isToggled() && this.clickGuiIsOpen && this.Delay > this.delay) {
                Minecraft.getMinecraft().player.sendChatMessage(this.prefix + Announcer.closeGuiMessage);
                this.clickGuiIsOpen = false;
                this.Delay = 0;
            }
        }
        if ((boolean)this.jumping.getValue() && Minecraft.getMinecraft().player.fallDistance > 1.1) {
            this.isInAir = true;
        }
        if ((boolean)this.jumping.getValue() && this.isInAir && Minecraft.getMinecraft().player.fallDistance == 0.0f) {
            if (this.InAir > 15) {
                if (this.Delay - 200 > this.delay) {
                    Minecraft.getMinecraft().player.sendChatMessage(this.prefix + Announcer.jumpMessage);
                    this.isInAir = false;
                    this.Delay = 0;
                }
            }
            else {
                this.isInAir = false;
                this.InAir = 0;
            }
        }
    }
    
    @SubscribeEvent
    public void onBlockBreak(final BlockEvent$BreakEvent blockEvent$BreakEvent) {
        final Block block = blockEvent$BreakEvent.getState().getBlock();
        ++this.blocksBroken;
        final int nextInt = ThreadLocalRandom.current().nextInt(1, 11);
        if ((boolean)this.breakBlock.getValue() && this.blocksBroken > nextInt) {
            final String replace = Announcer.breakMessage.replace("{amount}", this.blocksBroken + "").replace("{name}", block.getLocalizedName());
            if (this.Delay > this.delay) {
                Minecraft.getMinecraft().player.sendChatMessage(this.prefix + replace);
                this.Delay = 0;
                this.blocksBroken = 0;
            }
        }
    }
    
    public Announcer() {
        super("Announcer", ModuleCategory.CHAT, "Announces Player actions");
        this.heldItem = "";
        this.blocksPlaced = 0;
        this.blocksBroken = 0;
        this.xpThrown = 0;
        this.eaten = 0;
        this.clickGuiIsOpen = false;
        this.sneak = false;
        this.isEating = false;
        this.itemStack = 0;
        this.hasHealth = false;
        this.isInAir = false;
        this.Delay = 0;
        this.InAir = 0;
        this.healthUpdate = 0;
        this.prefix = "";
        this.delay = 0;
        this.settingDelay = new IntegerValue("Chat Delay", 50, 1, 500);
        this.walkDistance = new BooleanValue("Walking", true);
        this.placeBlock = new BooleanValue("PlaceBlock", true);
        this.breakBlock = new BooleanValue("BreakBlock", true);
        this.eatItem = new BooleanValue("ItemEat", true);
        this.throwItem = new BooleanValue("ItemThrow", true);
        this.booleanSneak = new BooleanValue("Sneaking", true);
        this.booleanGui = new BooleanValue("Click Gui", true);
        this.enchantItem = new BooleanValue("EnchantItem", true);
        this.jumping = new BooleanValue("Jumping", true);
        this.greenText = new BooleanValue("GreenText", true);
        this.addValue(this.settingDelay, this.walkDistance, this.placeBlock, this.breakBlock, this.eatItem, this.throwItem, this.booleanSneak, this.booleanGui, this.enchantItem, this.greenText);
    }
}
