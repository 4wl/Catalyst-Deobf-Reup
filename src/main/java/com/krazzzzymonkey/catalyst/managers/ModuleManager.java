/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers;

import java.util.List;
import java.util.function.Function;
import java.util.Comparator;
import com.krazzzzymonkey.catalyst.module.modules.render.Viewmodel;
import com.krazzzzymonkey.catalyst.module.modules.render.Breadcrumbs;
import com.krazzzzymonkey.catalyst.module.modules.render.BarrierView;
import com.krazzzzymonkey.catalyst.module.modules.hud.TargetHUD;
import com.krazzzzymonkey.catalyst.module.modules.misc.NoEntityTrace;
import com.krazzzzymonkey.catalyst.module.modules.misc.DonkeyFinder;
import com.krazzzzymonkey.catalyst.module.modules.misc.DonkeyDrop;
import com.krazzzzymonkey.catalyst.module.modules.combat.HopperNuker;
import com.krazzzzymonkey.catalyst.module.modules.combat.AutoArmor;
import com.krazzzzymonkey.catalyst.module.modules.movement.Flight;
import com.krazzzzymonkey.catalyst.module.modules.gui.HudEditor;
import com.krazzzzymonkey.catalyst.module.modules.render.FogColors;
import com.krazzzzymonkey.catalyst.module.modules.render.NetherSky;
import com.krazzzzymonkey.catalyst.module.modules.combat.DispenserMeta;
import com.krazzzzymonkey.catalyst.module.modules.movement.Speed;
import com.krazzzzymonkey.catalyst.module.modules.hud.CustomFont;
import com.krazzzzymonkey.catalyst.module.modules.combat.Offhand;
import com.krazzzzymonkey.catalyst.module.modules.gui.ClickGui;
import com.krazzzzymonkey.catalyst.module.modules.hud.PlayerInfo;
import com.krazzzzymonkey.catalyst.module.modules.player.Disconnect;
import com.krazzzzymonkey.catalyst.module.modules.world.FastBreak;
import com.krazzzzymonkey.catalyst.module.modules.movement.Jesus;
import com.krazzzzymonkey.catalyst.module.modules.movement.AutoWalk;
import com.krazzzzymonkey.catalyst.module.modules.render.AntiRain;
import com.krazzzzymonkey.catalyst.module.modules.hud.PlayerRadar;
import com.krazzzzymonkey.catalyst.module.modules.misc.PluginsGetter;
import com.krazzzzymonkey.catalyst.module.modules.misc.PacketCanceller;
import com.krazzzzymonkey.catalyst.module.modules.render.BlockOverlay;
import com.krazzzzymonkey.catalyst.module.modules.player.Freecam;
import com.krazzzzymonkey.catalyst.module.modules.world.Scaffold;
import com.krazzzzymonkey.catalyst.module.modules.player.Blink;
import com.krazzzzymonkey.catalyst.module.modules.world.Nuker;
import com.krazzzzymonkey.catalyst.module.modules.misc.ChestStealer;
import com.krazzzzymonkey.catalyst.module.modules.movement.AutoSprint;
import com.krazzzzymonkey.catalyst.module.modules.player.Velocity;
import com.krazzzzymonkey.catalyst.module.modules.combat.KillAura;
import com.krazzzzymonkey.catalyst.module.modules.combat.Criticals;
import com.krazzzzymonkey.catalyst.module.modules.chat.Insulter;
import com.krazzzzymonkey.catalyst.module.modules.render.FullBright;
import com.krazzzzymonkey.catalyst.module.modules.render.Tracers;
import com.krazzzzymonkey.catalyst.module.modules.render.StorageESP;
import com.krazzzzymonkey.catalyst.module.modules.render.ItemESP;
import com.krazzzzymonkey.catalyst.module.modules.render.ESP;
import com.krazzzzymonkey.catalyst.module.modules.render.NoParticle;
import com.krazzzzymonkey.catalyst.module.modules.render.Trajectories;
import com.krazzzzymonkey.catalyst.module.modules.render.LowOffHand;
import com.krazzzzymonkey.catalyst.module.modules.chat.ChatMention;
import com.krazzzzymonkey.catalyst.module.modules.misc.CustomFOV;
import com.krazzzzymonkey.catalyst.module.modules.hud.Watermark;
import com.krazzzzymonkey.catalyst.module.modules.hud.ActiveModules;
import com.krazzzzymonkey.catalyst.module.modules.combat.BowRelease;
import com.krazzzzymonkey.catalyst.module.modules.movement.ElytraFly;
import com.krazzzzymonkey.catalyst.module.modules.movement.FastFall;
import com.krazzzzymonkey.catalyst.module.modules.combat.FastXP;
import com.krazzzzymonkey.catalyst.module.modules.hud.PVPInfo;
import com.krazzzzymonkey.catalyst.module.modules.chat.FancyChat;
import com.krazzzzymonkey.catalyst.module.modules.world.Timer;
import com.krazzzzymonkey.catalyst.module.modules.hud.InvPreview;
import com.krazzzzymonkey.catalyst.module.modules.chat.ChatTimeStamps;
import com.krazzzzymonkey.catalyst.module.modules.movement.InventoryWalk;
import com.krazzzzymonkey.catalyst.module.modules.chat.ChatSuffix;
import com.krazzzzymonkey.catalyst.module.modules.render.TabFriends;
import com.krazzzzymonkey.catalyst.module.modules.render.Nametags;
import com.krazzzzymonkey.catalyst.module.modules.chat.Announcer;
import com.krazzzzymonkey.catalyst.module.modules.chat.AutoGG;
import com.krazzzzymonkey.catalyst.module.modules.combat.AutoCrystal;
import com.krazzzzymonkey.catalyst.module.modules.render.HoleESP;
import com.krazzzzymonkey.catalyst.module.modules.misc.RPC;
import com.krazzzzymonkey.catalyst.module.modules.misc.AutoRespawn;
import com.krazzzzymonkey.catalyst.module.modules.render.BobIntensity;
import com.krazzzzymonkey.catalyst.module.modules.world.FastPlace;
import com.krazzzzymonkey.catalyst.module.modules.hud.TotemPopCounter;
import com.krazzzzymonkey.catalyst.module.modules.misc.XCarry;
import com.krazzzzymonkey.catalyst.module.modules.chat.VisualRange;
import com.krazzzzymonkey.catalyst.module.modules.misc.MiddleClickFriends;
import com.krazzzzymonkey.catalyst.module.modules.misc.AutoEat;
import com.krazzzzymonkey.catalyst.module.modules.chat.DeathAnnouncer;
import com.krazzzzymonkey.catalyst.module.modules.combat.ShulkerNuker;
import com.krazzzzymonkey.catalyst.module.modules.misc.NoRotate;
import com.krazzzzymonkey.catalyst.module.modules.misc.PortalChat;
import com.krazzzzymonkey.catalyst.module.modules.hud.Greeter;
import com.krazzzzymonkey.catalyst.module.modules.hud.Coordinates;
import com.krazzzzymonkey.catalyst.module.modules.render.MapTooltip;
import com.krazzzzymonkey.catalyst.module.modules.combat.AutoObsidian;
import com.krazzzzymonkey.catalyst.module.modules.misc.AutoHotbarRefill;
import com.krazzzzymonkey.catalyst.module.modules.combat.ObsidianReplace;
import com.krazzzzymonkey.catalyst.module.modules.misc.EnderChestMiner;
import com.krazzzzymonkey.catalyst.module.modules.misc.NoGlobalSounds;
import com.krazzzzymonkey.catalyst.module.modules.combat.AutoTrap;
import com.krazzzzymonkey.catalyst.module.modules.combat.HoleFill;
import com.krazzzzymonkey.catalyst.module.modules.movement.IceSpeed;
import com.krazzzzymonkey.catalyst.module.modules.hud.PVPModules;
import com.krazzzzymonkey.catalyst.module.modules.chat.AutoQueueMain;
import com.krazzzzymonkey.catalyst.module.modules.combat.SelfTrap;
import com.krazzzzymonkey.catalyst.module.modules.render.CameraClip;
import com.krazzzzymonkey.catalyst.module.modules.render.EnchantColor;
import com.krazzzzymonkey.catalyst.module.modules.movement.Step;
import com.krazzzzymonkey.catalyst.module.modules.movement.ReverseStep;
import com.krazzzzymonkey.catalyst.module.modules.hud.CrystalPlaceSpeed;
import com.krazzzzymonkey.catalyst.module.modules.movement.NoSlow;
import com.krazzzzymonkey.catalyst.module.modules.render.NoOverlay;
import com.krazzzzymonkey.catalyst.module.modules.render.NoHurtCam;
import com.krazzzzymonkey.catalyst.module.modules.player.EntityControl;
import com.krazzzzymonkey.catalyst.module.modules.render.ItemChams;
import com.krazzzzymonkey.catalyst.module.modules.render.RenderChams;
import com.krazzzzymonkey.catalyst.module.modules.player.AutoTool;
import com.krazzzzymonkey.catalyst.module.modules.render.ShulkerPreview;
import com.krazzzzymonkey.catalyst.module.modules.player.NoSwing;
import com.krazzzzymonkey.catalyst.module.modules.player.PortalGodMode;
import com.krazzzzymonkey.catalyst.module.modules.render.LogoutSpots;
import com.krazzzzymonkey.catalyst.module.modules.render.NewChunks;
import com.krazzzzymonkey.catalyst.module.modules.player.EntitySpeed;
import com.krazzzzymonkey.catalyst.module.modules.chat.NoChat;
import com.krazzzzymonkey.catalyst.module.modules.movement.PacketFly;
import com.krazzzzymonkey.catalyst.module.modules.hud.Graphs;
import com.krazzzzymonkey.catalyst.module.modules.movement.SafeWalk;
import com.krazzzzymonkey.catalyst.module.modules.render.Capes;
import com.krazzzzymonkey.catalyst.module.modules.render.LostFocus;
import com.krazzzzymonkey.catalyst.module.modules.render.AcidMode;
import com.krazzzzymonkey.catalyst.module.modules.render.CustomMainMenu;
import com.krazzzzymonkey.catalyst.module.modules.hud.ArmorHUD;
import com.krazzzzymonkey.catalyst.module.modules.render.MobOwner;
import com.krazzzzymonkey.catalyst.module.modules.misc.FakePlayer;
import com.krazzzzymonkey.catalyst.module.modules.combat.AutoCrystalRewrite;
import com.krazzzzymonkey.catalyst.module.modules.render.BreakESP;
import com.krazzzzymonkey.catalyst.module.modules.combat.AutoMend;
import com.krazzzzymonkey.catalyst.module.modules.hud.DurabilityAlert;
import com.krazzzzymonkey.catalyst.module.modules.combat.Burrow;
import com.krazzzzymonkey.catalyst.module.modules.render.NoArmor;
import com.krazzzzymonkey.catalyst.module.modules.chat.ToggleMessages;
import com.krazzzzymonkey.catalyst.module.modules.combat.AutoWeb;
import com.krazzzzymonkey.catalyst.module.modules.player.LiquidInteract;
import com.krazzzzymonkey.catalyst.module.modules.combat.Anchor;
import com.krazzzzymonkey.catalyst.module.modules.render.SkeletonESP;
import com.krazzzzymonkey.catalyst.module.modules.render.XRay;
import net.minecraftforge.common.MinecraftForge;
import com.krazzzzymonkey.catalyst.gui.click.theme.Theme;
import com.krazzzzymonkey.catalyst.gui.click.theme.dark.DarkTheme;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import com.krazzzzymonkey.catalyst.events.KeyDownEvent;
import java.util.Iterator;
import com.krazzzzymonkey.catalyst.gui.click.ClickGuiScreen;
import com.krazzzzymonkey.catalyst.gui.click.HudGuiScreen;
import java.util.ArrayList;
import com.krazzzzymonkey.catalyst.module.Modules;

public class ModuleManager
{
    public static Modules toggleModule;
    public GuiManager guiManager;
    public HudEditorManager hudManager;
    public static ArrayList<Modules> modules;
    public HudGuiScreen hudGuiScreen;
    public ClickGuiScreen guiScreen;
    
    public static Modules getModule(final String anObject) {
        Modules modules = null;
        for (final Modules modules2 : getModules()) {
            if (modules2.getName().equals(anObject)) {
                modules = modules2;
            }
        }
        return modules;
    }
    
    public static Modules getToggledModules() {
        return ModuleManager.toggleModule;
    }
    
    @SubscribeEvent
    public static void onKeyPressed(final KeyDownEvent keyDownEvent) {
        if (Wrapper.INSTANCE.mc().currentScreen != null) {
            return;
        }
        for (final Modules toggleModule : getModules()) {
            if (toggleModule.getKey() == keyDownEvent.getKeyId()) {
                toggleModule.toggle();
                ModuleManager.toggleModule = toggleModule;
            }
        }
    }
    
    public ClickGuiScreen getGui() {
        if (this.guiManager == null) {
            this.guiManager = new GuiManager();
            this.guiScreen = new ClickGuiScreen();
            ClickGuiScreen.clickGui = this.guiManager;
            this.guiManager.Init();
            this.guiManager.setTheme(new DarkTheme());
        }
        return this.guiManager;
    }
    
    static {
        ModuleManager.toggleModule = null;
    }
    
    public ModuleManager() {
        MinecraftForge.EVENT_BUS.register((Object)ModuleManager.class);
        ModuleManager.modules = new ArrayList<Modules>();
        addModule(new XRay());
        addModule(new SkeletonESP());
        addModule(new Anchor());
        addModule(new LiquidInteract());
        addModule(new AutoWeb());
        addModule(new ToggleMessages());
        addModule(new NoArmor());
        addModule(new Burrow());
        addModule(new DurabilityAlert());
        addModule(new AutoMend());
        addModule(new BreakESP());
        addModule(new AutoCrystalRewrite());
        addModule(new FakePlayer());
        addModule(new MobOwner());
        addModule(new ArmorHUD());
        addModule(new CustomMainMenu());
        addModule(new AcidMode());
        addModule(new LostFocus());
        addModule(new Capes());
        addModule(new SafeWalk());
        addModule(new Graphs());
        addModule(new PacketFly());
        addModule(new NoChat());
        addModule(new EntitySpeed());
        addModule(new NewChunks());
        addModule(new LogoutSpots());
        addModule(new PortalGodMode());
        addModule(new NoSwing());
        addModule(new ShulkerPreview());
        addModule(new AutoTool());
        addModule(new RenderChams());
        addModule(new ItemChams());
        addModule(new EntityControl());
        addModule(new NoHurtCam());
        addModule(new NoOverlay());
        addModule(new NoSlow());
        addModule(new CrystalPlaceSpeed());
        addModule(new ReverseStep());
        addModule(new Step());
        addModule(new EnchantColor());
        addModule(new CameraClip());
        addModule(new SelfTrap());
        addModule(new AutoQueueMain());
        addModule(new PVPModules());
        addModule(new IceSpeed());
        addModule(new HoleFill());
        addModule(new AutoTrap());
        addModule(new NoGlobalSounds());
        addModule(new EnderChestMiner());
        addModule(new ObsidianReplace());
        addModule(new AutoHotbarRefill());
        addModule(new AutoObsidian());
        addModule(new MapTooltip());
        addModule(new Coordinates());
        addModule(new Greeter());
        addModule(new PortalChat());
        addModule(new NoRotate());
        addModule(new ShulkerNuker());
        addModule(new DeathAnnouncer());
        addModule(new AutoEat());
        addModule(new MiddleClickFriends());
        addModule(new VisualRange());
        addModule(new XCarry());
        addModule(new TotemPopCounter());
        addModule(new FastPlace());
        addModule(new BobIntensity());
        addModule(new AutoRespawn());
        addModule(new RPC());
        addModule(new HoleESP());
        addModule(new AutoCrystal());
        addModule(new AutoGG());
        addModule(new Announcer());
        addModule(new Nametags());
        addModule(new TabFriends());
        addModule(new ChatSuffix());
        addModule(new InventoryWalk());
        addModule(new ChatTimeStamps());
        addModule(new InvPreview());
        addModule(new Timer());
        addModule(new FancyChat());
        addModule(new PVPInfo());
        addModule(new FastXP());
        addModule(new FastFall());
        addModule(new ElytraFly());
        addModule(new BowRelease());
        addModule(new ActiveModules());
        addModule(new Watermark());
        addModule(new CustomFOV());
        addModule(new ChatMention());
        addModule(new LowOffHand());
        addModule(new Trajectories());
        addModule(new NoParticle());
        addModule(new ESP());
        addModule(new ItemESP());
        addModule(new StorageESP());
        addModule(new Tracers());
        addModule(new FullBright());
        addModule(new Insulter());
        addModule(new Criticals());
        addModule(new KillAura());
        addModule(new Velocity());
        addModule(new AutoSprint());
        addModule(new ChestStealer());
        addModule(new Nuker());
        addModule(new Blink());
        addModule(new Scaffold());
        addModule(new Freecam());
        addModule(new BlockOverlay());
        addModule(new PacketCanceller());
        addModule(new PluginsGetter());
        addModule(new PlayerRadar());
        addModule(new AntiRain());
        addModule(new AutoWalk());
        addModule(new Jesus());
        addModule(new FastBreak());
        addModule(new Disconnect());
        addModule(new PlayerInfo());
        addModule(new ClickGui());
        addModule(new Offhand());
        addModule(new CustomFont());
        addModule(new Speed());
        addModule(new DispenserMeta());
        addModule(new NetherSky());
        addModule(new FogColors());
        addModule(new HudEditor());
        addModule(new Flight());
        addModule(new AutoArmor());
        addModule(new HopperNuker());
        addModule(new DonkeyDrop());
        addModule(new DonkeyFinder());
        addModule(new NoEntityTrace());
        addModule(new TargetHUD());
        addModule(new BarrierView());
        addModule(new Breadcrumbs());
        addModule(new Viewmodel());
        ModuleManager.modules.sort(Comparator.comparing((Function<? super Modules, ? extends Comparable>)Modules::getName));
    }
    
    public static void addModule(final Modules e) {
        ModuleManager.modules.add(e);
    }
    
    public HudEditorManager getHudGui() {
        if (this.hudManager == null) {
            this.hudManager = new HudEditorManager();
            this.hudGuiScreen = new HudGuiScreen();
            HudGuiScreen.hudGui = this.hudManager;
            this.hudManager.Initialization();
            this.hudManager.setTheme(new DarkTheme());
        }
        return this.hudManager;
    }
    
    public static ArrayList getModules() {
        return ModuleManager.modules;
    }
    
    public static List getSortedHacks() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: nop            
        //     4: nop            
        //     5: nop            
        //     6: athrow         
        //     7: athrow         
        //     8: new             Ljava/util/ArrayList;
        //    11: dup            
        //    12: goto            16
        //    15: athrow         
        //    16: invokespecial   java/util/ArrayList.<init>:()V
        //    19: goto            23
        //    22: athrow         
        //    23: astore_0       
        //    24: goto            28
        //    27: athrow         
        //    28: invokestatic    com/krazzzzymonkey/catalyst/managers/ModuleManager.getModules:()Ljava/util/ArrayList;
        //    31: goto            35
        //    34: athrow         
        //    35: goto            39
        //    38: athrow         
        //    39: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //    42: goto            46
        //    45: athrow         
        //    46: astore_1       
        //    47: aload_1        
        //    48: goto            52
        //    51: athrow         
        //    52: invokeinterface java/util/Iterator.hasNext:()Z
        //    57: goto            61
        //    60: athrow         
        //    61: ifeq            134
        //    64: aload_1        
        //    65: goto            69
        //    68: athrow         
        //    69: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    74: goto            78
        //    77: athrow         
        //    78: checkcast       Lcom/krazzzzymonkey/catalyst/module/Modules;
        //    81: astore_2       
        //    82: aload_2        
        //    83: goto            87
        //    86: athrow         
        //    87: invokevirtual   com/krazzzzymonkey/catalyst/module/Modules.isToggled:()Z
        //    90: goto            94
        //    93: athrow         
        //    94: ifeq            131
        //    97: aload_2        
        //    98: goto            102
        //   101: athrow         
        //   102: invokevirtual   com/krazzzzymonkey/catalyst/module/Modules.isDrawn:()Z
        //   105: goto            109
        //   108: athrow         
        //   109: ifne            115
        //   112: goto            47
        //   115: aload_0        
        //   116: aload_2        
        //   117: goto            121
        //   120: athrow         
        //   121: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   126: goto            130
        //   129: athrow         
        //   130: pop            
        //   131: goto            47
        //   134: aload_0        
        //   135: new             Lcom/krazzzzymonkey/catalyst/managers/ModuleManager$1;
        //   138: dup            
        //   139: goto            143
        //   142: athrow         
        //   143: invokespecial   com/krazzzzymonkey/catalyst/managers/ModuleManager$1.<init>:()V
        //   146: goto            150
        //   149: athrow         
        //   150: goto            154
        //   153: athrow         
        //   154: invokeinterface java/util/List.sort:(Ljava/util/Comparator;)V
        //   159: goto            163
        //   162: athrow         
        //   163: aload_0        
        //   164: areturn        
        //   165: nop            
        //   166: athrow         
        //    StackMapTable: 00 30 43 07 00 15 43 07 00 15 00 46 07 00 15 FF 00 00 00 00 00 02 08 00 08 08 00 08 45 07 00 15 40 07 00 1D 43 07 00 15 FC 00 00 07 00 1D FF 00 05 00 00 00 01 07 00 15 FF 00 00 00 01 07 00 1D 00 01 07 00 1D FF 00 02 00 00 00 01 07 00 15 FF 00 00 00 01 07 00 1D 00 01 07 00 1D FF 00 05 00 00 00 01 07 00 15 FF 00 00 00 01 07 00 1D 00 01 07 00 23 FC 00 00 07 00 23 FF 00 03 00 00 00 01 07 00 15 FF 00 00 00 02 07 00 1D 07 00 23 00 01 07 00 23 FF 00 07 00 00 00 01 07 00 15 FF 00 00 00 02 07 00 1D 07 00 23 00 01 01 FF 00 06 00 00 00 01 07 00 15 FF 00 00 00 02 07 00 1D 07 00 23 00 01 07 00 23 FF 00 07 00 00 00 01 07 00 15 FF 00 00 00 02 07 00 1D 07 00 23 00 01 07 00 04 FF 00 07 00 00 00 01 07 00 15 FF 00 00 00 03 07 00 1D 07 00 23 07 00 25 00 01 07 00 25 FF 00 05 00 00 00 01 07 00 15 FF 00 00 00 03 07 00 1D 07 00 23 07 00 25 00 01 01 FF 00 06 00 00 00 01 07 00 15 FF 00 00 00 03 07 00 1D 07 00 23 07 00 25 00 01 07 00 25 FF 00 05 00 00 00 01 07 00 15 FF 00 00 00 03 07 00 1D 07 00 23 07 00 25 00 01 01 05 FF 00 04 00 00 00 01 07 00 15 FF 00 00 00 03 07 00 1D 07 00 23 07 00 25 00 02 07 00 1D 07 00 25 FF 00 07 00 00 00 01 07 00 15 FF 00 00 00 03 07 00 1D 07 00 23 07 00 25 00 01 01 00 FA 00 02 FF 00 07 00 00 00 01 07 00 15 FF 00 00 00 02 07 00 1D 07 00 23 00 03 07 00 1D 08 00 87 08 00 87 FF 00 05 00 00 00 01 07 00 15 FF 00 00 00 02 07 00 1D 07 00 23 00 02 07 00 1D 07 02 61 FF 00 02 00 00 00 01 07 00 15 FF 00 00 00 02 07 00 1D 07 00 23 00 02 07 00 1D 07 02 61 FF 00 07 00 00 00 01 07 00 15 FD 00 00 07 00 1D 07 00 23 FF 00 01 00 00 00 01 07 00 15
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1119)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void setGuiManager(final GuiManager guiManager) {
        this.guiManager = guiManager;
    }
}
