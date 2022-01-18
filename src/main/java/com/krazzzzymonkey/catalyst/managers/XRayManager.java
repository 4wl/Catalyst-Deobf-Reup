/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers;

import java.util.Iterator;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import net.minecraft.block.Block;
import com.krazzzzymonkey.catalyst.xray.XRayData;
import java.util.LinkedList;

public class XRayManager
{
    public static LinkedList<XRayData> xrayList;
    
    public static void removeData(final int n) {
        for (final XRayData xRayData : getDataById(n)) {
            if (XRayManager.xrayList.contains(xRayData)) {
                XRayManager.xrayList.remove(xRayData);
                FileManager.saveXRayData();
                ChatUtils.message(String.format("§7ID: §3%s §7NAME: §3%s §7RGB: §c%s§7, §a%s§7, §9%s §7- REMOVED.", xRayData.getId(), Block.getBlockById(xRayData.getId()).getLocalizedName(), xRayData.getRed(), xRayData.getGreen(), xRayData.getBlue()));
            }
        }
    }
    
    public static XRayData getDataByMeta(final int n) {
        XRayData xRayData = null;
        for (final XRayData xRayData2 : XRayManager.xrayList) {
            if (xRayData2.getMeta() == n) {
                xRayData = xRayData2;
            }
        }
        return xRayData;
    }
    
    public static void clear() {
        if (!XRayManager.xrayList.isEmpty()) {
            XRayManager.xrayList.clear();
            FileManager.saveXRayData();
            ChatUtils.message("§dXRay §7list clear.");
        }
    }
    
    public static void add(final XRayData xRayData) {
        if (Block.getBlockById(xRayData.getId()) == null) {
            ChatUtils.error("Block is null.");
            return;
        }
        final LinkedList dataById = getDataById(xRayData.getId());
        if (dataById.isEmpty()) {
            addData(xRayData);
            return;
        }
        boolean b = false;
        boolean b2 = false;
        for (final XRayData xRayData2 : dataById) {
            if (xRayData2.getId() == xRayData.getId()) {
                b = true;
            }
            if (xRayData2.getMeta() == xRayData.getMeta()) {
                b2 = true;
            }
        }
        if (b && b2) {
            return;
        }
        addData(xRayData);
    }
    
    static {
        XRayManager.xrayList = new LinkedList<XRayData>();
    }
    
    public static void addData(final XRayData e) {
        XRayManager.xrayList.add(e);
        FileManager.saveXRayData();
        ChatUtils.message(String.format("§7ID: §3%s §7META: §3%s §7NAME: §3%s §7RGB: §c%s§7, §a%s§7, §9%s §7- ADDED.", e.getId(), e.getMeta(), Block.getBlockById(e.getId()).getLocalizedName(), e.getRed(), e.getGreen(), e.getBlue()));
    }
    
    public static LinkedList getDataById(final int n) {
        final LinkedList<XRayData> list = new LinkedList<XRayData>();
        for (final XRayData e : XRayManager.xrayList) {
            if (e.getId() == n) {
                list.add(e);
            }
        }
        return list;
    }
}
