/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.handler;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.krazzzzymonkey.catalyst.lib.texts.TextURL;

public class LoadStringURL extends Thread
{
    public TextURL text;
    
    public LoadStringURL(final TextURL text) {
        this.text = text;
        this.setDaemon(true);
    }
    
    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        Label_0089: {
            IOException ex;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(this.text.getURL().openStream()));
                break Label_0089;
            }
            catch (IOException ex2) {
                ex = ex2;
            }
            ex.printStackTrace();
        }
        final StringBuilder sb = new StringBuilder();
        String str = null;
        do {
            if (str != null) {
                sb.append(str);
            }
            String line = null;
            Label_0172: {
                IOException ex3;
                try {
                    line = bufferedReader.readLine();
                    break Label_0172;
                }
                catch (IOException ex4) {
                    ex3 = ex4;
                }
                ex3.printStackTrace();
            }
            if (str != null) {
                sb.append("\n");
            }
            str = line;
        } while (str != null);
        Label_0228: {
            IOException ex5;
            try {
                bufferedReader.close();
                break Label_0228;
            }
            catch (IOException ex6) {
                ex5 = ex6;
            }
            ex5.printStackTrace();
        }
        synchronized (this.text.string) {
            this.text.string = sb.toString();
        }
    }
}
