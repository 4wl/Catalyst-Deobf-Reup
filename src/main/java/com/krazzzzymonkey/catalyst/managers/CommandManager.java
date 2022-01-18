/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.managers;

import java.util.function.Function;
import java.util.Comparator;
import com.krazzzzymonkey.catalyst.command.BreadcrumbsCommand;
import com.krazzzzymonkey.catalyst.command.Prefix;
import com.krazzzzymonkey.catalyst.command.Enemy;
import com.krazzzzymonkey.catalyst.command.Friend;
import com.krazzzzymonkey.catalyst.command.Login;
import com.krazzzzymonkey.catalyst.command.DebugInfo;
import com.krazzzzymonkey.catalyst.command.ChatMention;
import com.krazzzzymonkey.catalyst.command.VClip;
import com.krazzzzymonkey.catalyst.command.Bind;
import com.krazzzzymonkey.catalyst.command.Module;
import com.krazzzzymonkey.catalyst.command.Help;
import com.krazzzzymonkey.catalyst.command.MsgAll;
import com.krazzzzymonkey.catalyst.command.NameHistory;
import com.krazzzzymonkey.catalyst.command.Drawn;
import com.krazzzzymonkey.catalyst.command.EntityDesync;
import com.krazzzzymonkey.catalyst.command.Toggle;
import java.util.function.Consumer;
import com.krazzzzymonkey.catalyst.utils.visual.ChatUtils;
import java.util.concurrent.atomic.AtomicBoolean;
import com.krazzzzymonkey.catalyst.command.Command;
import java.util.ArrayList;

public class CommandManager
{
    public static CommandManager instance;
    public static ArrayList<Command> commands;
    public static String prefix;
    
    public static void lambda$runCommands$0(final String anotherString, final AtomicBoolean atomicBoolean, final String s, final Command command) {
        if (command.getCommand().equalsIgnoreCase(anotherString)) {
            atomicBoolean.set(true);
            String syntax;
            try {
                command.runCommand(s, s.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"));
                return;
            }
            catch (Exception ex) {
                syntax = command.getSyntax();
            }
            ChatUtils.error(syntax);
        }
    }
    
    static {
        CommandManager.commands = new ArrayList<Command>();
        CommandManager.prefix = "~";
    }
    
    public void runCommands(final String s) {
        final String str = s.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)")[0];
        final String trim = s.substring(str.length()).trim();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        CommandManager.commands.forEach((Consumer<? super Command>)CommandManager::lambda$runCommands$0);
        if (!atomicBoolean.get()) {
            ChatUtils.error("Cannot resolve internal command: §c" + str);
        }
    }
    
    public ArrayList getCommands() {
        return CommandManager.commands;
    }
    
    public static CommandManager getInstance() {
        if (CommandManager.instance == null) {
            CommandManager.instance = new CommandManager();
        }
        return CommandManager.instance;
    }
    
    public static void addCommand(final Command e) {
        CommandManager.commands.add(e);
    }
    
    public void addCommands() {
        addCommand(new Toggle());
        addCommand(new EntityDesync());
        addCommand(new Drawn());
        addCommand(new NameHistory());
        addCommand(new MsgAll());
        addCommand(new Help());
        addCommand(new Module());
        addCommand(new Bind());
        addCommand(new VClip());
        addCommand(new ChatMention());
        addCommand(new DebugInfo());
        addCommand(new Login());
        addCommand(new Friend());
        addCommand(new Enemy());
        addCommand(new Prefix());
        addCommand(new BreadcrumbsCommand());
        CommandManager.commands.sort(Comparator.comparing((Function<? super Command, ? extends Comparable>)Command::getCommand));
    }
    
    public CommandManager() {
        FileManager.loadPrefix();
        this.addCommands();
    }
}
