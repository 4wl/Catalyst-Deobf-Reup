/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.lib;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.ForgeVersion;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Calendar;
import net.minecraftforge.fml.common.Loader;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

public class StringReplacer
{
    static final String TIME_FORMAT = "HH:mm";
    static final SimpleDateFormat timeFormat;
    static Field mcpversionField;
    static Field mcversionField;
    public static String mcpversion;
    public static String mcversion;
    
    public static String replacePlaceholders(final String source) {
        final int tModCount = Loader.instance().getModList().size();
        final int aModCount = Loader.instance().getActiveModList().size();
        final Calendar calendar = Calendar.getInstance();
        final String clock = StringReplacer.timeFormat.format(calendar.getTime());
        final DateFormat formatter = DateFormat.getDateInstance(2, Locale.getDefault());
        final String date = formatter.format(new Date());
        return source.replace("#date#", date).replace("#time#", clock).replace("#mcversion#", StringReplacer.mcversion).replace("#fmlversion#", Loader.instance().getFMLVersionString()).replace("#mcpversion#", StringReplacer.mcpversion).replace("#modsloaded#", tModCount + "").replace("#modsactive#", aModCount + "").replace("#forgeversion#", ForgeVersion.getVersion()).replace("#username#", Minecraft.getMinecraft().getSession().getUsername());
    }
    
    static {
        timeFormat = new SimpleDateFormat("HH:mm");
        try {
            (StringReplacer.mcpversionField = Loader.class.getDeclaredField("mcpversion")).setAccessible(true);
            StringReplacer.mcpversion = (String)StringReplacer.mcpversionField.get(null);
            StringReplacer.mcversionField = ForgeVersion.class.getDeclaredField("mcVersion");
            StringReplacer.mcversion = (String)StringReplacer.mcversionField.get(null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
