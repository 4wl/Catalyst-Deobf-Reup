/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils;

import java.util.regex.Pattern;

public enum ChatColor
{
    MAGIC("MAGIC", 16, 'k', true);
    
    public static char COLOR_CHAR;
    
    DARK_GRAY("DARK_GRAY", 8, '8'), 
    STRIKETHROUGH("STRIKETHROUGH", 18, 'm', true), 
    DARK_AQUA("DARK_AQUA", 3, '3'), 
    DARK_GREEN("DARK_GREEN", 2, '2');
    
    public boolean isFormat;
    
    DARK_RED("DARK_RED", 4, '4'), 
    GRAY("GRAY", 7, '7'), 
    BOLD("BOLD", 17, 'l', true), 
    LIGHT_PURPLE("LIGHT_PURPLE", 13, 'd'), 
    DARK_BLUE("DARK_BLUE", 1, '1'), 
    RESET("RESET", 21, 'r'), 
    WHITE("WHITE", 15, 'f');
    
    public char code;
    
    YELLOW("YELLOW", 14, 'e'), 
    ITALIC("ITALIC", 20, 'o', true), 
    BLUE("BLUE", 9, '9');
    
    public static ChatColor[] $VALUES;
    
    DARK_PURPLE("DARK_PURPLE", 5, '5'), 
    GOLD("GOLD", 6, '6'), 
    GREEN("GREEN", 10, 'a');
    
    public String toString;
    
    BLACK("BLACK", 0, '0'), 
    UNDERLINE("UNDERLINE", 19, 'n', true), 
    AQUA("AQUA", 11, 'b'), 
    RED("RED", 12, 'c');
    
    public boolean isFormat() {
        return this.isFormat;
    }
    
    static {
        ChatColor.COLOR_CHAR = '§';
        ChatColor.$VALUES = new ChatColor[] { ChatColor.BLACK, ChatColor.DARK_BLUE, ChatColor.DARK_GREEN, ChatColor.DARK_AQUA, ChatColor.DARK_RED, ChatColor.DARK_PURPLE, ChatColor.GOLD, ChatColor.GRAY, ChatColor.DARK_GRAY, ChatColor.BLUE, ChatColor.GREEN, ChatColor.AQUA, ChatColor.RED, ChatColor.LIGHT_PURPLE, ChatColor.YELLOW, ChatColor.WHITE, ChatColor.MAGIC, ChatColor.BOLD, ChatColor.STRIKETHROUGH, ChatColor.UNDERLINE, ChatColor.ITALIC, ChatColor.RESET };
    }
    
    public ChatColor(final String name, final int ordinal, final char code, final boolean isFormat) {
        this.code = code;
        this.isFormat = isFormat;
        this.toString = new String(new char[] { '§', code });
    }
    
    public char getChar() {
        return this.code;
    }
    
    public boolean isColor() {
        return !this.isFormat && this != ChatColor.RESET;
    }
    
    public static String stripColor(final String input) {
        return (input == null) ? null : Pattern.compile("(?i)§[0-9A-FK-OR]").matcher(input).replaceAll("");
    }
    
    @Override
    public String toString() {
        return this.toString;
    }
    
    public ChatColor(final String s, final int n, final char c) {
        this(s, n, c, false);
    }
    
    public static String translateAlternateColorCodes(final char c, final String s) {
        final char[] charArray = s.toCharArray();
        for (int n = charArray.length - 1, i = 0; i < n; ++i) {
            if (charArray[i] == c && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(charArray[i + 1]) > -1) {
                charArray[i] = '§';
                charArray[i + 1] = Character.toLowerCase(charArray[i + 1]);
            }
        }
        return new String(charArray);
    }
}
