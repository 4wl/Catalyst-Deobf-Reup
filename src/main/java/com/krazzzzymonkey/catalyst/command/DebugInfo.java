/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.command;

import java.util.Optional;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import com.krazzzzymonkey.catalyst.utils.paste.PasteBuilder$Data;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import com.krazzzzymonkey.catalyst.managers.FileManager;
import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import com.krazzzzymonkey.catalyst.utils.system.Wrapper;
import java.util.ArrayList;
import net.minecraftforge.fml.common.ModContainer;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import net.minecraftforge.fml.common.Loader;
import com.krazzzzymonkey.catalyst.utils.paste.PasteBuilder;

public class DebugInfo extends Command
{
    public DebugInfo() {
        super("debuginfo");
    }
    
    @Override
    public void runCommand(final String s, final String[] array) {
        final PasteBuilder setName = new PasteBuilder().setName("Catalyst Debug Infos");
        setName.addContent("mods.csv", Loader.instance().getIndexedModList().values().stream().map((Function<? super Object, ?>)DebugInfo::lambda$runCommand$0).collect((Collector<? super Object, ?, String>)Collectors.joining(System.lineSeparator())));
        CompletableFuture.runAsync(DebugInfo::lambda$runCommand$1);
    }
    
    @Override
    public String getDescription() {
        return "Upload debug info to a paste.";
    }
    
    @Override
    public String getSyntax() {
        return "debuginfo";
    }
    
    public static String lambda$runCommand$0(final ModContainer modContainer) {
        return modContainer.getName() + ", " + modContainer.getModId() + ", " + modContainer.getVersion();
    }
    
    public static void lambda$runCommand$1(final PasteBuilder pasteBuilder) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("java vendor, " + System.getProperty("java.vendor"));
        list.add("java spec vendor, " + System.getProperty("java.specification.vendor"));
        list.add("java runtime, " + System.getProperty("java.runtime.name"));
        list.add("java vm, " + System.getProperty("java.vm.name"));
        list.add("java version, " + System.getProperty("java.version"));
        list.add("java runtime version, " + System.getProperty("java.runtime.version"));
        list.add("os arch, " + System.getProperty("os.arch"));
        list.add("os name, " + System.getProperty("os.name"));
        list.add("os version, " + System.getProperty("os.version"));
        list.add("encoding, " + System.getProperty("sun.jnu.encoding"));
        list.add("cores, " + Runtime.getRuntime().availableProcessors());
        list.add("memory, " + Math.round(Runtime.getRuntime().freeMemory() / 1024.0f / 1024.0f) + "mb / " + Math.round(Runtime.getRuntime().maxMemory() / 1024.0f / 1024.0f) + "mb (init " + Math.round(Runtime.getRuntime().totalMemory() / 1024.0f / 1024.0f) + "mb)");
        pasteBuilder.addContent("environment.csv", list.stream().collect((Collector<? super Object, ?, String>)Collectors.joining(System.lineSeparator())));
        final File file = new File(Wrapper.INSTANCE.mc().mcDataDir, "options.txt");
        if (file.isFile() && file.canRead()) {
            try {
                pasteBuilder.addContent("options.txt", Files.lines(file.toPath()).collect(Collectors.joining(System.lineSeparator())));
            }
            catch (IOException ex) {
                pasteBuilder.addContent("options.txt", ex.getMessage());
            }
        }
        else {
            pasteBuilder.addContent("options.txt", "options.txt not a file or not readable");
        }
        final File file2 = new File(FileManager.CATALYST_DIR, "hacks.json");
        Label_2025: {
            if (file2.isFile() && file2.canRead()) {
                try {
                    pasteBuilder.addContent("hacks.json", Files.lines(file2.toPath()).collect(Collectors.joining(System.lineSeparator())));
                }
                catch (IOException ex3) {
                    final String s = "hacks.json";
                    final IOException ex2 = ex3;
                Label_1964:
                    while (true) {
                        switch (-332328824 + 2032924696 + 1) {
                            case -2066872137: {
                                continue;
                            }
                            case -1793398640: {
                                break Label_1964;
                            }
                            default: {
                                throw null;
                            }
                        }
                    }
                    pasteBuilder.addContent(s, ex2.getMessage());
                }
                break Label_2025;
            }
            pasteBuilder.addContent("hacks.json", "hacks.json not a file or not readable");
        }
        final Optional post = pasteBuilder.post();
        if (!post.isPresent()) {
            ChatUtils.normalMessage("Pasted upload failed :(");
            return;
        }
        final PasteBuilder$Data pasteBuilder$Data = post.get();
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(pasteBuilder$Data.getUrl()), null);
        ChatUtils.normalMessage("URL copied to clipboard, deletion key: " + pasteBuilder$Data.deleteKey);
    }
}
