/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers.accountManager.alt;

import java.util.Iterator;
import com.krazzzzymonkey.catalyst.managers.accountManager.config.ConfigValues;
import com.krazzzzymonkey.catalyst.managers.accountManager.AccountManager;
import net.minecraft.util.Session;
import com.mojang.util.UUIDTypeAdapter;
import com.krazzzzymonkey.catalyst.managers.accountManager.AlreadyLoggedInException;
import com.krazzzzymonkey.catalyst.managers.accountManager.tools.EncryptionTools;
import com.mojang.authlib.AuthenticationService;
import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import net.minecraft.client.Minecraft;
import java.util.UUID;
import com.mojang.authlib.UserAuthentication;

public class AltManager
{
    private static AltManager manager;
    private final UserAuthentication auth;
    
    private AltManager() {
        final UUID uuid = UUID.randomUUID();
        final AuthenticationService authService = (AuthenticationService)new YggdrasilAuthenticationService(Minecraft.getMinecraft().getProxy(), uuid.toString());
        this.auth = authService.createUserAuthentication(Agent.MINECRAFT);
        authService.createMinecraftSessionService();
    }
    
    public static AltManager getInstance() {
        if (AltManager.manager == null) {
            AltManager.manager = new AltManager();
        }
        return AltManager.manager;
    }
    
    public Throwable setUser(final String username, final String password) {
        Throwable throwable = null;
        if (!Minecraft.getMinecraft().getSession().getUsername().equals(EncryptionTools.decode(username)) || Minecraft.getMinecraft().getSession().getToken().equals("0")) {
            if (!Minecraft.getMinecraft().getSession().getToken().equals("0")) {
                for (final AccountData data : AltDatabase.getInstance().getAlts()) {
                    if (data.alias.equals(Minecraft.getMinecraft().getSession().getUsername()) && data.user.equals(username)) {
                        throwable = new AlreadyLoggedInException();
                        return throwable;
                    }
                }
            }
            this.auth.logOut();
            this.auth.setUsername(EncryptionTools.decode(username));
            this.auth.setPassword(EncryptionTools.decode(password));
            try {
                this.auth.logIn();
                final Session session = new Session(this.auth.getSelectedProfile().getName(), UUIDTypeAdapter.fromUUID(this.auth.getSelectedProfile().getId()), this.auth.getAuthenticatedToken(), this.auth.getUserType().getName());
                AccountManager.setSession(session);
                for (int i = 0; i < AltDatabase.getInstance().getAlts().size(); ++i) {
                    final AccountData data2 = AltDatabase.getInstance().getAlts().get(i);
                    if (data2.user.equals(username) && data2.pass.equals(password)) {
                        data2.alias = session.getUsername();
                    }
                }
            }
            catch (Exception e) {
                throwable = e;
            }
        }
        else if (!ConfigValues.ENABLERELOG) {
            throwable = new AlreadyLoggedInException();
        }
        return throwable;
    }
    
    public void setUserOffline(final String username) {
        this.auth.logOut();
        final Session session = new Session(username, username, "0", "legacy");
        try {
            AccountManager.setSession(session);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static {
        AltManager.manager = null;
    }
}
