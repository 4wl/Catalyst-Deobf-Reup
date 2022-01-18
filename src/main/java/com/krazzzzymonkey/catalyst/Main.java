/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst;

import com.krazzzzymonkey.catalyst.configuration.Config;
import com.krazzzzymonkey.catalyst.configuration.ConfigurationLoader;
import com.krazzzzymonkey.catalyst.events.ClientEvents;
import com.krazzzzymonkey.catalyst.events.CommandEvent;
import com.krazzzzymonkey.catalyst.gui.click.ClickGui;
import com.krazzzzymonkey.catalyst.gui.click.HudEditor;
import com.krazzzzymonkey.catalyst.handler.CMMEventHandler;
import com.krazzzzymonkey.catalyst.managers.FileManager;
import com.krazzzzymonkey.catalyst.managers.ModuleManager;
import com.krazzzzymonkey.catalyst.managers.RotationManager;
import com.krazzzzymonkey.catalyst.managers.accountManager.AccountManager;
import com.krazzzzymonkey.catalyst.managers.accountManager.Standards;
import com.krazzzzymonkey.catalyst.managers.accountManager.config.ConfigValues;
import com.krazzzzymonkey.catalyst.utils.CapeUtils;
import com.krazzzzymonkey.catalyst.utils.LagCompensator;
import com.krazzzzymonkey.catalyst.utils.font.CFontRenderer;
import com.krazzzzymonkey.catalyst.utils.visual.ColorUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import java.awt.*;
import java.io.File;

@Mod(modid = "catalyst", name = "Catalyst", version = "1.9.11", clientSideOnly = true, guiFactory = "com.krazzzzymonkey.catalyst.managers.accountManager.config.GuiFactory", acceptableRemoteVersions = "*")
public class Main
{
    public static int initCount;
    public static CFontRenderer fontRenderer;
    public static CFontRenderer smallFontRenderer;
    public static Config config;
    public static Configuration altConfig;
    public File configFolder;
    public CapeUtils capeUtils;
    @Mod.Instance("catalyst")
    public static Main INSTANCE;
    public static String VERSION;
    public static Logger logger;
    public ConfigurationLoader configLoader;
    public static CMMEventHandler EVENT_HANDLER;
    public static ColorUtils ColorEvents;
    public static String MODID;
    public static FileManager fileManager;
    public static ResourceLocation transparentTexture;
    public static ModuleManager moduleManager;
    public static String NAME;
    
    public static void bindTransparent() {
        Minecraft.getMinecraft().renderEngine.bindTexture(Main.transparentTexture);
    }
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent fmlPreInitializationEvent) {
        Main.logger.info("   ____      _        _           _      ____ _ _            _  ");
        Main.logger.info("  / ___|__ _| |_ __ _| |_   _ ___| |_   / ___| (_) ___ _ __ | |_ ");
        Main.logger.info(" | |   / _` | __/ _` | | | | / __| __| | |   | | |/ _ \\ '_ \\| __|");
        Main.logger.info(" | |__| (_| | || (_| | | |_| \\__ \\ |_  | |___| | |  __/ | | | |_ ");
        Main.logger.info("  \\____\\__,_|\\__\\__,_|_|\\__, |___/\\__|  \\____|_|_|\\___|_| |_|\\__|");
        Main.logger.info("                        |___/                                    ");
        Display.setTitle("Initializing Catalyst 1.9.11");
        this.configFolder = FileManager.CATALYST_DIR;
        Main.config = new Config();
        Main.transparentTexture = new ResourceLocation("catalyst", "textures/gui/transparent.png");
        MinecraftForge.EVENT_BUS.register((Object)new CommandEvent());
        MinecraftForge.EVENT_BUS.register((Object)new ClientEvents());
        MinecraftForge.EVENT_BUS.register((Object)new RotationManager());
        Main.EVENT_HANDLER = new CMMEventHandler();
        Main.ColorEvents = new ColorUtils();
        MinecraftForge.EVENT_BUS.register((Object)Main.EVENT_HANDLER);
        MinecraftForge.EVENT_BUS.register((Object)Main.ColorEvents);
        FMLCommonHandler.instance().bus().register((Object)Main.EVENT_HANDLER);
        FMLCommonHandler.instance().bus().register((Object)Main.ColorEvents);
        this.configLoader = new ConfigurationLoader(Main.config);
        Label_0595: {
            Exception ex;
            try {
                this.configLoader.load();
                break Label_0595;
            }
            catch (Exception ex2) {
                Main.logger.log(Level.ERROR, "Error while loading config file. Will have to crash here :(.");
                ex = ex2;
            }
            ex.printStackTrace();
        }
        (Main.altConfig = new Configuration(fmlPreInitializationEvent.getSuggestedConfigurationFile())).load();
        syncConfig();
        if (!fmlPreInitializationEvent.getModMetadata().version.equals("${version}")) {
            Standards.updateFolder();
        }
        else {
            Main.logger.info("Dev environment detected!");
        }
    }
    
    public void reload() {
        final Config config = Main.config;
        Main.config = new Config();
        this.configLoader = new ConfigurationLoader(Main.config);
        try {
            this.configLoader.load();
            Main.EVENT_HANDLER.displayMs = -1L;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            Main.EVENT_HANDLER.displayMs = System.currentTimeMillis();
            Main.logger.log(Level.ERROR, "Error while loading new config file, trying to keep the old one loaded.");
            Main.config = config;
        }
    }
    
    public static void syncConfig() {
        ConfigValues.CASESENSITIVE = false;
        ConfigValues.ENABLERELOG = false;
        if (Main.altConfig.hasChanged()) {
            Main.altConfig.save();
        }
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent fmlInitializationEvent) {
        if (Main.initCount > 0) {
            return;
        }
        Standards.importAccounts();
        LagCompensator.INSTANCE = new LagCompensator();
        (this.capeUtils = new CapeUtils()).getUsersCape();
        Main.moduleManager = new ModuleManager();
        Main.fileManager = new FileManager();
        Main.fontRenderer = new CFontRenderer(new Font("Arial", 0, 20), true, true);
        Main.smallFontRenderer = new CFontRenderer(new Font("Arial", 0, 15), true, true);
        AccountManager.init();
        ++Main.initCount;
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent fmlPostInitializationEvent) {
        Display.setTitle("Catalyst 1.9.11");
        Main.moduleManager.getGui();
        Main.moduleManager.getHudGui();
        ClickGui.onUpdate();
        HudEditor.onUpdate();
    }
    
    static {
        Main.NAME = "Catalyst";
        Main.MODID = "catalyst";
        Main.VERSION = "1.9.11";
        Main.logger = LogManager.getLogger("Catalyst");
        Main.initCount = 0;
    }
    
    public Main() {
        this.init(null);
    }
}
