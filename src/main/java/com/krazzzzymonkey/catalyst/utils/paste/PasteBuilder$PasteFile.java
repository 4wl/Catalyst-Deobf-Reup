/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils.paste;

public class PasteBuilder$PasteFile
{
    public String name;
    public PasteBuilder$PasteFile$PasteContent content;
    
    public PasteBuilder$PasteFile(final String name, final String s) {
        this.name = name;
        this.content = new PasteBuilder$PasteFile$PasteContent(s, null);
    }
    
    public PasteBuilder$PasteFile(final String s, final String s2, final PasteBuilder$1 pasteBuilder$1) {
        this(s, s2);
    }
}
