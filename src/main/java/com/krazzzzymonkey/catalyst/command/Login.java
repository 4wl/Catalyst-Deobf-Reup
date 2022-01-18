/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import com.krazzzzymonkey.catalyst.utils.LoginUtils;

public class Login extends Command
{
    @Override
    public String getSyntax() {
        return "login <<email> <password>>/<username>";
    }
    
    @Override
    public void runCommand(final String s, final String[] array) {
        String string;
        try {
            if (array.length > 1 || array[0].contains(":")) {
                String s2;
                String s3;
                if (array[0].contains(":")) {
                    final String[] split = array[0].split(":", 2);
                    s2 = split[0];
                    s3 = split[1];
                }
                else {
                    s2 = array[0];
                    s3 = array[1];
                }
                ChatUtils.message(LoginUtils.loginAlt(s2, s3));
            }
            else {
                LoginUtils.changeCrackedName(array[0]);
                ChatUtils.warning("[Non Premium] Successfully Logged into " + Wrapper.INSTANCE.mc().getSession().getUsername());
            }
            return;
        }
        catch (Exception ex) {
            string = "Usage: " + this.getSyntax();
        }
        ChatUtils.error(string);
    }
    
    public Login() {
        super("login");
    }
    
    @Override
    public String getDescription() {
        return "Change session.";
    }
}
