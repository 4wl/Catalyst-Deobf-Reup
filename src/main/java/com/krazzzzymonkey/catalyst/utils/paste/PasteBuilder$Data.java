/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils.paste;

public class PasteBuilder$Data
{
    public String expires;
    public String id;
    public String deleteKey;
    
    public PasteBuilder$Data(final String id, final String deleteKey, final String expires) {
        this.id = id;
        this.deleteKey = deleteKey;
        this.expires = expires;
    }
    
    public PasteBuilder$Data(final String s, final String s2, final String s3, final PasteBuilder$1 pasteBuilder$1) {
        this(s, s2, s3);
    }
    
    public String getUrl() {
        return "https://paste.gg/" + this.id;
    }
}
