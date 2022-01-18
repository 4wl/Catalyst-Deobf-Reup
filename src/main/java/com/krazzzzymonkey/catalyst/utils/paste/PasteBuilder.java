/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.utils.paste;

import java.util.ArrayList;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import com.google.gson.JsonObject;
import java.io.Reader;
import com.google.gson.JsonParser;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.util.Optional;
import com.google.gson.annotations.Expose;
import com.google.gson.Gson;
import java.util.List;

public class PasteBuilder
{
    public String description;
    public String expires;
    public String name;
    public List<PasteBuilder$PasteFile> files;
    public String visibility;
    @Expose(serialize = false, deserialize = false)
    public static Gson GSON;
    
    public Optional post() {
        try {
            return Optional.ofNullable(upload(PasteBuilder.GSON.toJson((Object)this)));
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
            return Optional.empty();
        }
    }
    
    public PasteBuilder addContent(final String s, String string) {
        if (!string.endsWith("\n") && !string.endsWith("\r")) {
            string += "\n";
        }
        this.files.add(new PasteBuilder$PasteFile(s, string, null));
        return this;
    }
    
    public static PasteBuilder$Data upload(final String s) {
        final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL("https://api.paste.gg/v1/pastes").openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=" + StandardCharsets.UTF_8);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Accept", "application/json");
        final OutputStream outputStream = httpURLConnection.getOutputStream();
        final byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        outputStream.write(bytes, 0, bytes.length);
        return parseResponse(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8));
    }
    
    public static PasteBuilder$Data parseResponse(final InputStreamReader inputStreamReader) {
        final JsonObject asJsonObject = new JsonParser().parse((Reader)inputStreamReader).getAsJsonObject();
        if (!asJsonObject.get("status").getAsString().equals("success")) {
            return null;
        }
        final JsonObject asJsonObject2 = asJsonObject.get("result").getAsJsonObject();
        return new PasteBuilder$Data(asJsonObject2.get("id").getAsString(), asJsonObject2.get("deletion_key").getAsString(), asJsonObject2.get("expires").getAsString(), null);
    }
    
    public PasteBuilder setExpiration(final int n) {
        this.expires = ZonedDateTime.now(ZoneOffset.UTC).plusHours(n).toString();
        return this;
    }
    
    public PasteBuilder setName(final String name) {
        this.name = name;
        return this;
    }
    
    static {
        PasteBuilder.GSON = new Gson();
    }
    
    public PasteBuilder setDescription(final String description) {
        this.description = description;
        return this;
    }
    
    public PasteBuilder() {
        this.visibility = "unlisted";
        this.visibility = "unlisted";
        this.files = new ArrayList<PasteBuilder$PasteFile>();
        this.name = "Catalyst Client";
        this.description = "Get Catalyst at: catalyst.sexy";
        this.expires = ZonedDateTime.now(ZoneOffset.UTC).plusDays(7L).toString();
    }
}
