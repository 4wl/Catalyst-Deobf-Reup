/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.RayTraceResult;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import com.krazzzzymonkey.catalyst.managers.XRayManager;
import com.krazzzzymonkey.catalyst.xray.XRayData;
import com.krazzzzymonkey.catalyst.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.util.math.RayTraceResult$Type;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;

public class XRay extends Command
{
    public XRay() {
        super("xray");
    }
    
    @Override
    public void runCommand(final String s, final String[] array) {
        String string;
        try {
            if (array[0].equalsIgnoreCase("add")) {
                if (array[1].equalsIgnoreCase("mouse") && Wrapper.INSTANCE.mc().objectMouseOver != null) {
                    final RayTraceResult objectMouseOver = Wrapper.INSTANCE.mc().objectMouseOver;
                    if (objectMouseOver.typeOfHit == RayTraceResult$Type.BLOCK) {
                        final IBlockState blockState = Wrapper.INSTANCE.world().getBlockState(objectMouseOver.getBlockPos());
                        XRayManager.addData(new XRayData(Block.getIdFromBlock(blockState.getBlock()), blockState.getBlock().getMetaFromState(blockState), Utils.random(0, 254), Utils.random(0, 254), Utils.random(0, 254)));
                    }
                }
                else if (array[1].contains(":")) {
                    final String[] split = array[1].split(":");
                    XRayManager.add(new XRayData(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4])));
                }
                else {
                    XRayManager.add(new XRayData(Integer.parseInt(array[1]), 0, Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4])));
                }
            }
            else if (array[0].equalsIgnoreCase("remove")) {
                XRayManager.removeData(Integer.parseInt(array[1]));
            }
            else if (array[0].equalsIgnoreCase("clear")) {
                XRayManager.clear();
            }
            return;
        }
        catch (Exception ex) {
            string = "Usage: " + this.getSyntax();
        }
        ChatUtils.error(string);
    }
    
    @Override
    public String getDescription() {
        return "XRay manager.";
    }
    
    @Override
    public String getSyntax() {
        return "xray add <id:meta> <red> <green> <blue> | add mouse | remove <id> | clear";
    }
}
