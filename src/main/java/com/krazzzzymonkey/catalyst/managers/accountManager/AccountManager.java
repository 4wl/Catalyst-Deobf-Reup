/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers.accountManager;

import java.lang.reflect.Field;
import com.krazzzzymonkey.catalyst.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class AccountManager
{
    public static void init() {
        Config.load();
    }
    
    public static void setSession(final Session s) throws Exception {
        final Class<? extends Minecraft> mc = Minecraft.getMinecraft().getClass();
        try {
            Field session = null;
            for (final Field f : mc.getDeclaredFields()) {
                if (f.getType().isInstance(s)) {
                    session = f;
                    Main.logger.info("Found field " + f.toString() + ", injecting...");
                }
            }
            if (session == null) {
                throw new IllegalStateException("No field of type " + Session.class.getCanonicalName() + " declared.");
            }
            session.setAccessible(true);
            session.set(Minecraft.getMinecraft(), s);
            session.setAccessible(false);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
