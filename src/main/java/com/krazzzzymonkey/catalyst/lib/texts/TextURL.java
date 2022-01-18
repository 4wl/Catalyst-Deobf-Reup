/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.lib.texts;

import java.net.MalformedURLException;
import com.krazzzzymonkey.catalyst.lib.StringReplacer;
import com.krazzzzymonkey.catalyst.handler.LoadStringURL;
import java.net.URL;

public class TextURL implements IText
{
    URL url;
    public String string;
    int refreshInterval;
    int refreshCounter;
    LoadStringURL loadThread;
    
    public TextURL(final String url, final int refreshInterval) {
        try {
            this.url = new URL(StringReplacer.replacePlaceholders(url));
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.string = "";
        (this.loadThread = new LoadStringURL(this)).start();
        this.refreshInterval = refreshInterval;
        this.refreshCounter = 0;
    }
    
    public void tick() {
        if (this.refreshInterval != -1 && this.refreshInterval >= 60) {
            ++this.refreshCounter;
            if (this.refreshCounter >= this.refreshInterval && !this.loadThread.isAlive()) {
                (this.loadThread = new LoadStringURL(this)).start();
                this.refreshCounter = 0;
            }
        }
    }
    
    @Override
    public String get() {
        synchronized (this.string) {
            return this.string;
        }
    }
    
    public URL getURL() {
        return this.url;
    }
}
