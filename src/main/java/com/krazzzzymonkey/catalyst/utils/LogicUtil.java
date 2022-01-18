/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import net.minecraft.client.resources.I18n;
import com.krazzzzymonkey.catalyst.lib.StringReplacer;
import java.util.ArrayList;

public class LogicUtil
{
    public static ArrayList getTooltip(final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        final String[] split = s.split("\n");
        for (int length = split.length, i = 0; i < length; ++i) {
            list.add(I18n.format(StringReplacer.replacePlaceholders(split[i]), new Object[0]));
        }
        return list;
    }
}
