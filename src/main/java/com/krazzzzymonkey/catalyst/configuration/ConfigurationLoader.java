/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.configuration;

import java.io.OutputStream;
import java.io.InputStream;
import com.krazzzzymonkey.catalyst.gui.GuiCustom;
import java.io.Reader;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.google.common.io.ByteStreams;
import java.io.FileOutputStream;
import java.io.File;
import com.krazzzzymonkey.catalyst.managers.FileManager;
import com.google.gson.JsonParser;

public class ConfigurationLoader
{
    public Config config;
    
    public void load() {
        final JsonParser jsonParser = new JsonParser();
        final File parent = new File(FileManager.CATALYST_DIR, "CatalystMainMenu");
        if (!parent.exists()) {
            parent.mkdir();
        }
        final File file = new File(parent, "mainmenu.json");
        InputStream resourceAsStream = null;
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            resourceAsStream = this.getClass().getResourceAsStream("/assets/minecraft/catalyst/mainmenu/config.json");
            ByteStreams.copy(resourceAsStream, outputStream);
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly(outputStream);
            IOUtils.closeQuietly(resourceAsStream);
        }
        final File[] listFiles;
        final File[] array = listFiles = parent.listFiles();
        for (final File file2 : listFiles) {
            if (file2.getName().equals("mainmenu.json")) {
                final GuiConfig guiConfig = new GuiConfig();
                final String replace = file2.getName().replace(".json", "");
                JsonReader jsonReader = null;
                Label_0548: {
                    FileNotFoundException ex3;
                    try {
                        jsonReader = new JsonReader((Reader)new FileReader(file2));
                        break Label_0548;
                    }
                    catch (FileNotFoundException ex4) {
                        ex3 = ex4;
                    }
                    ex3.printStackTrace();
                    try {
                        guiConfig.load(replace, jsonParser.parse(jsonReader).getAsJsonObject());
                    }
                    catch (Exception ex5) {
                        IOException ex6;
                        try {
                            jsonReader.close();
                            throw ex5;
                        }
                        catch (IOException ex7) {
                            ex6 = ex7;
                        }
                        ex6.printStackTrace();
                        throw ex5;
                    }
                }
                Label_0702: {
                    IOException ex8;
                    try {
                        jsonReader.close();
                        break Label_0702;
                    }
                    catch (IOException ex9) {
                        ex8 = ex9;
                    }
                    ex8.printStackTrace();
                }
                this.config.addGui(guiConfig.name, new GuiCustom(guiConfig));
            }
        }
        for (final File file3 : array) {
            if (!file3.getName().equals("mainmenu.json") && file3.getName().endsWith(".json")) {
                final GuiConfig guiConfig2 = new GuiConfig();
                final String replace2 = file3.getName().replace(".json", "");
                JsonReader jsonReader2 = null;
                Label_0973: {
                    FileNotFoundException ex10;
                    try {
                        jsonReader2 = new JsonReader((Reader)new FileReader(file3));
                        break Label_0973;
                    }
                    catch (FileNotFoundException ex11) {
                        ex10 = ex11;
                    }
                    ex10.printStackTrace();
                    try {
                        guiConfig2.load(replace2, jsonParser.parse(jsonReader2).getAsJsonObject());
                    }
                    catch (Exception ex12) {
                        IOException ex13;
                        try {
                            jsonReader2.close();
                            throw ex12;
                        }
                        catch (IOException ex14) {
                            ex13 = ex14;
                        }
                        ex13.printStackTrace();
                        throw ex12;
                    }
                }
                Label_1103: {
                    IOException ex15;
                    try {
                        jsonReader2.close();
                        break Label_1103;
                    }
                    catch (IOException ex16) {
                        ex15 = ex16;
                    }
                    ex15.printStackTrace();
                }
                this.config.addGui(guiConfig2.name, new GuiCustom(guiConfig2));
            }
        }
    }
    
    public ConfigurationLoader(final Config config) {
        this.config = config;
    }
}
