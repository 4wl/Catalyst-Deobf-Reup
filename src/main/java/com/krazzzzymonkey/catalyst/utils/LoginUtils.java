/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import java.net.Proxy;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import java.lang.reflect.Field;
import net.minecraft.util.Session;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.utils.system.Mapping;
import net.minecraft.client.Minecraft;

public class LoginUtils
{
    public static void changeCrackedName(final String s) {
        Exception ex;
        try {
            final Field declaredField = Minecraft.class.getDeclaredField(Mapping.session);
            declaredField.setAccessible(true);
            declaredField.set(Wrapper.INSTANCE.mc(), new Session(s, "", "", "mojang"));
            return;
        }
        catch (Exception ex2) {
            ex = ex2;
        }
        ex.printStackTrace();
    }
    
    public static String getName(final String username, final String password) {
        final YggdrasilUserAuthentication yggdrasilUserAuthentication = (YggdrasilUserAuthentication)new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
        yggdrasilUserAuthentication.setUsername(username);
        yggdrasilUserAuthentication.setPassword(password);
        try {
            yggdrasilUserAuthentication.logIn();
            return yggdrasilUserAuthentication.getSelectedProfile().getName();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static String loginAlt(final String username, final String password) {
        final YggdrasilUserAuthentication yggdrasilUserAuthentication = (YggdrasilUserAuthentication)new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
        yggdrasilUserAuthentication.setUsername(username);
        yggdrasilUserAuthentication.setPassword(password);
        String string;
        try {
            yggdrasilUserAuthentication.logIn();
            Exception ex;
            try {
                final Field declaredField = Minecraft.class.getDeclaredField(Mapping.session);
                declaredField.setAccessible(true);
                declaredField.set(Wrapper.INSTANCE.mc(), new Session(yggdrasilUserAuthentication.getSelectedProfile().getName(), yggdrasilUserAuthentication.getSelectedProfile().getId().toString(), yggdrasilUserAuthentication.getAuthenticatedToken(), "mojang"));
                string = "Successfully logged into: " + Wrapper.INSTANCE.mc().getSession().getUsername();
                return string;
            }
            catch (Exception ex2) {
                string = "An unknown error has occurred.";
                ex = ex2;
            }
            ex.printStackTrace();
        }
        catch (AuthenticationUnavailableException ex4) {
            string = "Cannot connect to authentication server!";
        }
        catch (AuthenticationException ex3) {
            if (ex3.getMessage().contains("Invalid username or password.") || ex3.getMessage().toLowerCase().contains("account migrated")) {
                string = "Incorrect password!";
            }
            else {
                string = "Cannot connect to authentication server!";
            }
        }
        catch (NullPointerException ex5) {
            string = "Incorrect password!";
        }
        return string;
    }
}
