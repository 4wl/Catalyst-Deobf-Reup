/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.module.modules.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.List;
import net.minecraft.entity.item.EntityMinecartChest;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.block.BlockChest$Type;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.entity.Entity;
import com.krazzzzymonkey.catalyst.value.Value;
import java.awt.Color;
import com.krazzzzymonkey.catalyst.module.ModuleCategory;
import com.krazzzzymonkey.catalyst.utils.visual.RenderUtils;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import java.util.ArrayDeque;
import com.krazzzzymonkey.catalyst.value.types.ColorValue;
import com.krazzzzymonkey.catalyst.value.types.BooleanValue;
import com.krazzzzymonkey.catalyst.module.Modules;

public class StorageESP extends Modules
{
    public boolean shouldInform;
    public BooleanValue shulkerBoxRainbow;
    public ColorValue enderChestColor;
    public BooleanValue enderChestRainbow;
    public ColorValue trappedChestColor;
    public BooleanValue chestRainbow;
    public BooleanValue trappedChestRainbow;
    public ArrayDeque<TileEntityChest> nonEmptyChests;
    public ArrayDeque<TileEntityChest> emptyChests;
    public ColorValue shulkerBoxColor;
    public int maxChests;
    public ColorValue chestColor;
    public TileEntityChest openChest;
    
    @Override
    public void onEnable() {
        this.shouldInform = true;
        this.emptyChests.clear();
        this.nonEmptyChests.clear();
        super.onEnable();
    }
    
    public void drawESP(final BooleanValue booleanValue, final ColorValue colorValue, final TileEntity tileEntity) {
        if (booleanValue.getValue()) {
            RenderUtils.drawBlockESP(tileEntity.getPos(), ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, 1.0);
        }
        else {
            RenderUtils.drawBlockESP(tileEntity.getPos(), colorValue.getColor().getRed() / 255.0f, colorValue.getColor().getGreen() / 255.0f, colorValue.getColor().getBlue() / 255.0f, 1.0);
        }
    }
    
    public StorageESP() {
        super("StorageESP", ModuleCategory.RENDER, "Shows where storage containers are");
        this.maxChests = 1000;
        this.shouldInform = true;
        this.emptyChests = new ArrayDeque<TileEntityChest>();
        this.nonEmptyChests = new ArrayDeque<TileEntityChest>();
        this.chestColor = new ColorValue("ChestColor", Color.YELLOW);
        this.chestRainbow = new BooleanValue("ChestRainbow", false);
        this.trappedChestColor = new ColorValue("TrappedChestColor", Color.RED);
        this.trappedChestRainbow = new BooleanValue("TrappedChestRainbow", false);
        this.enderChestColor = new ColorValue("EnderChestColor", Color.MAGENTA);
        this.enderChestRainbow = new BooleanValue("EnderChestRainbow", false);
        this.shulkerBoxColor = new ColorValue("ShulkerBoxColor", Color.PINK);
        this.shulkerBoxRainbow = new BooleanValue("ShulkerBoxRainbow", false);
        this.shulkerBoxColor = new ColorValue("ShulkerBoxColor", Color.PINK);
        this.shulkerBoxRainbow = new BooleanValue("ShulkerBoxRainbow", false);
        this.addValue(this.chestColor, this.chestRainbow, this.trappedChestColor, this.trappedChestRainbow, this.enderChestColor, this.enderChestRainbow, this.shulkerBoxColor, this.shulkerBoxRainbow);
    }
    
    public void drawESP(final BooleanValue booleanValue, final ColorValue colorValue, final Entity entity) {
        if (booleanValue.getValue()) {
            RenderUtils.drawBlockESP(entity.getPosition(), ColorUtils.rainbow().getRed() / 255.0f, ColorUtils.rainbow().getGreen() / 255.0f, ColorUtils.rainbow().getBlue() / 255.0f, 1.0);
        }
        else {
            RenderUtils.drawBlockESP(entity.getPosition(), colorValue.getColor().getRed() / 255.0f, colorValue.getColor().getGreen() / 255.0f, colorValue.getColor().getBlue() / 255.0f, 1.0);
        }
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent renderWorldLastEvent) {
        int n = 0;
        int i = 0;
    Label_0012:
        while (true) {
            while (i < Wrapper.INSTANCE.world().loadedTileEntityList.size()) {
                final List loadedTileEntityList = Wrapper.INSTANCE.world().loadedTileEntityList;
                while (true) {
                    switch (1477654856 - 1502075026 + 1) {
                        case -1383545005: {
                            continue;
                        }
                        default: {
                            final TileEntity tileEntity = loadedTileEntityList.get(i);
                            if (n >= this.maxChests) {
                                break Label_0012;
                            }
                            if (tileEntity instanceof TileEntityChest) {
                                ++n;
                                final TileEntityChest tileEntityChest = (TileEntityChest)tileEntity;
                                if (tileEntityChest.getChestType() == BlockChest$Type.TRAP) {
                                    this.drawESP(this.trappedChestRainbow, this.trappedChestColor, (TileEntity)tileEntityChest);
                                }
                                else {
                                    this.drawESP(this.chestRainbow, this.chestColor, (TileEntity)tileEntityChest);
                                }
                            }
                            else if (tileEntity instanceof TileEntityEnderChest) {
                                ++n;
                                this.drawESP(this.enderChestRainbow, this.enderChestColor, tileEntity);
                            }
                            else if (tileEntity instanceof TileEntityShulkerBox) {
                                ++n;
                                this.drawESP(this.shulkerBoxRainbow, this.shulkerBoxColor, tileEntity);
                            }
                            ++i;
                            continue Label_0012;
                        }
                        case -1251458445: {
                            throw null;
                        }
                    }
                }
            }
            break;
        }
        while (true) {
            for (int j = 0; j < Wrapper.INSTANCE.world().loadedEntityList.size(); ++j) {
                final Entity entity = Wrapper.INSTANCE.world().loadedEntityList.get(j);
                if (n >= this.maxChests) {
                    if (n >= this.maxChests && this.shouldInform) {
                        ChatUtils.message("Only rendering " + this.maxChests + " chests to prevent lag.");
                        this.shouldInform = false;
                    }
                    else if (n < this.maxChests) {
                        this.shouldInform = true;
                    }
                    return;
                }
                if (entity instanceof EntityMinecartChest) {
                    ++n;
                    this.drawESP(this.chestRainbow, this.chestColor, entity);
                }
            }
            continue;
        }
    }
}
