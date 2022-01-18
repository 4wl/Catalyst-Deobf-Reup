/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers.accountManager;

import com.krazzzzymonkey.catalyst.Main;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.managers.accountManager.alt.AltDatabase;
import com.krazzzzymonkey.catalyst.managers.accountManager.alt.AccountData;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.DosFileAttributes;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import com.krazzzzymonkey.catalyst.managers.accountManager.tools.EncryptionTools;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.File;
import com.krazzzzymonkey.catalyst.managers.FileManager;

public final class Standards
{
    public static final String cfgn = "catalyst alts.cfg";
    public static final String pwdn = "catalyst alts.encrypted";
    
    public static String getPassword() {
        final File passwordFile = new File(FileManager.ALT_DIR, "catalyst alts.encrypted");
        Exception e = null;
        if (passwordFile.exists()) {
            String pass;
            try {
                final ObjectInputStream stream = new ObjectInputStream(new FileInputStream(passwordFile));
                pass = (String)stream.readObject();
                stream.close();
            }
            catch (IOException | ClassNotFoundException ex2) {
                final Exception ex;
                e = ex;
                throw new RuntimeException(e);
            }
            return pass;
        }
        final String newPass = EncryptionTools.generatePassword();
        try {
            final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(passwordFile));
            out.writeObject(newPass);
            out.close();
        }
        catch (IOException e2) {
            throw new RuntimeException(e2);
        }
        try {
            final Path file = passwordFile.toPath();
            final DosFileAttributes attr = Files.readAttributes(file, DosFileAttributes.class, new LinkOption[0]);
            final DosFileAttributeView view = Files.getFileAttributeView(file, DosFileAttributeView.class, new LinkOption[0]);
            if (!attr.isHidden()) {
                view.setHidden(true);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return newPass;
    }
    
    public static void updateFolder() {
        final String OS = System.getProperty("os.name").toUpperCase();
        if (OS.contains("WIN")) {
            final String dir = System.getenv("AppData");
        }
        else {
            String dir = System.getProperty("user.home");
            if (OS.contains("MAC")) {
                dir += "/Library/Application Support";
            }
        }
    }
    
    public static void importAccounts() {
        processData(getConfigV3());
        processData(getConfigV2());
        processData(getConfigV1(), false);
    }
    
    private static boolean hasData(final AccountData data) {
        for (final AccountData edata : AltDatabase.getInstance().getAlts()) {
            if (edata.equalsBasic(data)) {
                return true;
            }
        }
        return false;
    }
    
    private static void processData(final Config olddata) {
        processData(olddata, true);
    }
    
    private static void processData(final Config olddata, final boolean decrypt) {
        if (olddata != null) {
            for (final AccountData data : ((AltDatabase)olddata.getKey("altaccounts")).getAlts()) {
                final AccountData data2 = convertData(data, decrypt);
                if (!hasData(data2)) {
                    AltDatabase.getInstance().getAlts().add(data2);
                }
            }
        }
    }
    
    private static ExtendedAccountData convertData(final AccountData oldData, final boolean decrypt) {
        if (decrypt) {
            if (oldData instanceof ExtendedAccountData) {
                return new ExtendedAccountData(EncryptionTools.decodeOld(oldData.user), EncryptionTools.decodeOld(oldData.pass), oldData.alias, ((ExtendedAccountData)oldData).useCount, ((ExtendedAccountData)oldData).lastused, ((ExtendedAccountData)oldData).premium);
            }
            return new ExtendedAccountData(EncryptionTools.decodeOld(oldData.user), EncryptionTools.decodeOld(oldData.pass), oldData.alias);
        }
        else {
            if (oldData instanceof ExtendedAccountData) {
                return new ExtendedAccountData(oldData.user, oldData.pass, oldData.alias, ((ExtendedAccountData)oldData).useCount, ((ExtendedAccountData)oldData).lastused, ((ExtendedAccountData)oldData).premium);
            }
            return new ExtendedAccountData(oldData.user, oldData.pass, oldData.alias);
        }
    }
    
    private static Config getConfigV3() {
        final File f = new File(FileManager.ALT_DIR, ".ias");
        Config cfg = null;
        try {
            final ObjectInputStream stream = new ObjectInputStream(new FileInputStream(f));
            cfg = (Config)stream.readObject();
            stream.close();
        }
        catch (IOException ex) {}
        catch (ClassNotFoundException ex2) {}
        f.delete();
        return cfg;
    }
    
    private static Config getConfigV2() {
        final File f = new File(FileManager.ALT_DIR, ".ias");
        Config cfg = null;
        if (f.exists()) {
            Main.logger.info(f.getName() + "Exists");
            try {
                final ObjectInputStream stream = new ObjectInputStream(new FileInputStream(f));
                cfg = (Config)stream.readObject();
                stream.close();
            }
            catch (IOException | ClassNotFoundException ex2) {
                final Exception ex;
                final Exception e = ex;
                e.printStackTrace();
            }
            f.delete();
        }
        return cfg;
    }
    
    private static Config getConfigV1() {
        final File f = new File(FileManager.ALT_DIR, "user.cfg");
        Config cfg = null;
        if (f.exists()) {
            try {
                final ObjectInputStream stream = new ObjectInputStream(new FileInputStream(f));
                cfg = (Config)stream.readObject();
                stream.close();
            }
            catch (IOException | ClassNotFoundException ex2) {
                final Exception ex;
                final Exception e = ex;
                e.printStackTrace();
            }
            f.delete();
        }
        return cfg;
    }
}
