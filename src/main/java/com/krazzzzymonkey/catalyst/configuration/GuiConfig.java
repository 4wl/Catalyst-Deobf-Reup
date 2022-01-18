/**
 * Obfuscator: Binsecure,   Decompiler: Procyon
 * De-obfuscated by Gopro336
 */
package com.krazzzzymonkey.catalyst.configuration;

import com.krazzzzymonkey.catalyst.lib.ANCHOR;
import com.krazzzzymonkey.catalyst.lib.textures.TextureResourceLocation;
import com.krazzzzymonkey.catalyst.lib.textures.TextureApng;
import com.krazzzzymonkey.catalyst.lib.textures.TextureURL;
import com.krazzzzymonkey.catalyst.lib.textures.ITexture;
import com.krazzzzymonkey.catalyst.lib.texts.TextString;
import java.util.Collection;
import java.util.HashSet;
import com.krazzzzymonkey.catalyst.lib.actions.ActionOpenModConfig;
import com.krazzzzymonkey.catalyst.lib.actions.ActionOpenFolder;
import com.krazzzzymonkey.catalyst.lib.actions.ActionRefresh;
import com.krazzzzymonkey.catalyst.lib.actions.ActionQuit;
import com.krazzzzymonkey.catalyst.lib.actions.ActionOpenGUI;
import com.krazzzzymonkey.catalyst.lib.actions.ActionConnectToServer;
import com.krazzzzymonkey.catalyst.lib.actions.ActionLoadWorld;
import com.krazzzzymonkey.catalyst.lib.actions.ActionOpenLink;
import com.krazzzzymonkey.catalyst.lib.actions.IAction;
import java.util.function.Consumer;
import java.util.Iterator;
import java.util.Map;
import org.apache.logging.log4j.Level;
import java.util.Random;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.krazzzzymonkey.catalyst.configuration.elements.Slideshow;
import com.krazzzzymonkey.catalyst.Main;
import com.krazzzzymonkey.catalyst.lib.texts.IText;
import com.krazzzzymonkey.catalyst.lib.texts.TextResourceLocation;
import com.google.gson.JsonObject;
import com.krazzzzymonkey.catalyst.configuration.elements.SplashText;
import com.krazzzzymonkey.catalyst.configuration.elements.Label;
import com.krazzzzymonkey.catalyst.configuration.elements.Button;
import com.krazzzzymonkey.catalyst.configuration.elements.Panorama;
import com.krazzzzymonkey.catalyst.configuration.elements.Background;
import com.krazzzzymonkey.catalyst.lib.texts.TextURL;
import java.util.List;
import com.krazzzzymonkey.catalyst.configuration.elements.Image;
import java.util.ArrayList;
import java.util.HashMap;

public class GuiConfig
{
    public HashMap<String, Alignment> alignments;
    public ArrayList<Image> customImages;
    public List<TextURL> textUrls;
    public Background background;
    public String name;
    public int guiScale;
    public Panorama panorama;
    public ArrayList<Button> customButtons;
    public ArrayList<Label> customLabels;
    public SplashText splashText;
    
    public void loadOthers(final JsonObject jsonObject) {
        final JsonObject jsonObject2 = (JsonObject)jsonObject.get("other");
        final JsonObject jsonObject3 = (JsonObject)jsonObject2.get("splash-text");
        if (jsonObject3 != null) {
            if (jsonObject3.has("color") && jsonObject3.has("alignment")) {
                this.splashText = new SplashText(this, jsonObject3.get("posX").getAsInt(), jsonObject3.get("posY").getAsInt(), jsonObject3.get("color").getAsInt(), jsonObject3.get("alignment").getAsString());
            }
            else if (jsonObject3.has("color")) {
                this.splashText = new SplashText(this, jsonObject3.get("posX").getAsInt(), jsonObject3.get("posY").getAsInt(), jsonObject3.get("color").getAsInt(), "top_center");
            }
            else if (jsonObject3.has("alignment")) {
                this.splashText = new SplashText(this, jsonObject3.get("posX").getAsInt(), jsonObject3.get("posY").getAsInt(), jsonObject3.get("alignment").getAsString());
            }
            else {
                this.splashText = new SplashText(this, jsonObject3.get("posX").getAsInt(), jsonObject3.get("posY").getAsInt(), "top_center");
            }
            if (jsonObject3.has("synced")) {
                this.splashText.synced = jsonObject3.get("synced").getAsBoolean();
            }
            if (jsonObject3.has("texts")) {
                this.splashText.setSplashTexts(this.getWantedText(jsonObject3.get("texts")));
            }
            if (jsonObject3.has("file")) {
                this.splashText.setSplashTexts(new TextResourceLocation(this.getStringPlease(jsonObject3.get("file"))));
            }
        }
        final JsonObject jsonObject4 = (JsonObject)jsonObject2.get("panorama");
        if (jsonObject4 != null) {
            this.panorama = new Panorama(this, this.getStringPlease(jsonObject4.get("images")), jsonObject4.get("blur").getAsBoolean(), jsonObject4.get("gradient").getAsBoolean());
            if (jsonObject4.has("animate")) {
                this.panorama.setAnimate(jsonObject4.get("animate").getAsBoolean());
            }
            if (jsonObject4.has("position")) {
                this.panorama.setPosition(jsonObject4.get("position").getAsInt());
            }
            if (jsonObject4.has("animationSpeed")) {
                this.panorama.setAnimationSpeed(jsonObject4.get("animationSpeed").getAsInt());
            }
            if (jsonObject4.has("synced")) {
                this.panorama.synced = jsonObject4.get("synced").getAsBoolean();
            }
        }
        final JsonElement value = jsonObject2.get("background");
        if (value != null) {
            if (value.isJsonPrimitive() && value.getAsJsonPrimitive().isString() && value.getAsString().equals("options")) {
                this.background = Background.OPTIONS_BACKGROUND;
            }
            else {
                final JsonObject jsonObject5 = (JsonObject)value;
                this.background = new Background(this, getWantedTexture(this.getStringPlease(jsonObject5.get("image"))));
                if (jsonObject5.has("mode")) {
                    this.background.setMode(jsonObject5.get("mode").getAsString());
                }
                if (jsonObject5.has("slideshow")) {
                    final JsonObject asJsonObject = jsonObject5.get("slideshow").getAsJsonObject();
                    this.background.ichBinEineSlideshow = true;
                    if (asJsonObject.has("synced") && asJsonObject.get("synced").getAsBoolean()) {
                        this.background.slideShow = Main.config.getGUI("mainmenu").guiConfig.background.slideShow;
                    }
                    else {
                        final JsonArray asJsonArray = asJsonObject.get("images").getAsJsonArray();
                        final String[] array = new String[asJsonArray.size()];
                        for (int i = 0; i < array.length; ++i) {
                            array[i] = asJsonArray.get(i).getAsString();
                        }
                        final Slideshow slideShow = new Slideshow(this, array);
                        if (asJsonObject.has("displayDuration")) {
                            slideShow.displayDuration = asJsonObject.get("displayDuration").getAsInt();
                        }
                        if (asJsonObject.has("fadeDuration")) {
                            slideShow.fadeDuration = asJsonObject.get("fadeDuration").getAsInt();
                        }
                        if (asJsonObject.has("shuffle") && asJsonObject.get("shuffle").getAsBoolean()) {
                            slideShow.shuffle();
                        }
                        this.background.slideShow = slideShow;
                    }
                }
            }
        }
    }
    
    public Button getButton(final JsonObject jsonObject) {
        final String s = "text";
        while (true) {
            switch (-1516499072 - 1783383917 + 1) {
                case 306433300: {
                    continue;
                }
                default: {
                    final Button button = new Button(this, this.getWantedText(jsonObject.get(s)), jsonObject.get("posX").getAsInt(), jsonObject.get("posY").getAsInt(), jsonObject.get("width").getAsInt(), jsonObject.get("height").getAsInt());
                    if (jsonObject.has("alignment")) {
                        button.setStringAlignment(jsonObject.get("alignment").getAsString());
                    }
                    if (jsonObject.has("texture")) {
                        button.setTexture(getWantedTexture(this.getStringPlease(jsonObject.get("texture"))));
                    }
                    if (jsonObject.has("normalTextColor")) {
                        button.normalTextColor = jsonObject.get("normalTextColor").getAsInt();
                    }
                    if (jsonObject.has("hoverTextColor")) {
                        button.hoverTextColor = jsonObject.get("hoverTextColor").getAsInt();
                    }
                    if (jsonObject.has("shadow")) {
                        button.shadow = jsonObject.get("shadow").getAsBoolean();
                    }
                    if (jsonObject.has("imageWidth")) {
                        button.imageWidth = jsonObject.get("imageWidth").getAsInt();
                    }
                    if (jsonObject.has("imageHeight")) {
                        button.imageHeight = jsonObject.get("imageHeight").getAsInt();
                    }
                    if (jsonObject.has("wrappedButton")) {
                        button.setWrappedButton(jsonObject.get("wrappedButton").getAsInt());
                    }
                    if (jsonObject.has("action")) {
                        button.action = this.getWantedAction((JsonObject)jsonObject.get("action"));
                    }
                    if (jsonObject.has("tooltip")) {
                        button.tooltip = this.getWantedText(jsonObject.get("tooltip"));
                    }
                    if (jsonObject.has("hoverText")) {
                        button.hoverText = this.getWantedText(jsonObject.get("hoverText"));
                    }
                    if (jsonObject.has("pressSound")) {
                        button.pressSound = this.getStringPlease(jsonObject.get("pressSound"));
                    }
                    if (jsonObject.has("hoverSound")) {
                        button.hoverSound = this.getStringPlease(jsonObject.get("hoverSound"));
                    }
                    if (jsonObject.has("textOffsetX")) {
                        button.textOffsetX = jsonObject.get("textOffsetX").getAsInt();
                    }
                    if (jsonObject.has("textOffsetY")) {
                        button.textOffsetY = jsonObject.get("textOffsetY").getAsInt();
                    }
                    return button;
                }
                case -1130803260: {
                    throw null;
                }
            }
        }
    }
    
    public String getStringPlease(final JsonElement jsonElement) {
        final Random random = new Random();
        if (jsonElement.isJsonPrimitive()) {
            return jsonElement.getAsString();
        }
        if (jsonElement.isJsonArray()) {
            final JsonArray asJsonArray = jsonElement.getAsJsonArray();
            return asJsonArray.get(random.nextInt(asJsonArray.size())).getAsString();
        }
        Main.logger.log(Level.ERROR, "Error getting random value out of " + jsonElement.toString());
        return "ERROR";
    }
    
    public void load(String name, final JsonObject jsonObject) {
        if (name.endsWith("_small")) {
            this.guiScale = 1;
            name = name.replace("_small", "");
        }
        else if (name.endsWith("_normal")) {
            this.guiScale = 2;
            name = name.replace("_normal", "");
        }
        else if (name.endsWith("_large")) {
            this.guiScale = 3;
            name = name.replace("_large", "");
        }
        else if (name.endsWith("_auto")) {
            this.guiScale = 0;
            name = name.replace("_auto", "");
        }
        else {
            this.guiScale = -1;
        }
        this.name = name;
        this.loadAlignments(jsonObject);
        this.customLabels = new ArrayList<Label>();
        this.customImages = new ArrayList<Image>();
        this.customButtons = new ArrayList<Button>();
        this.splashText = null;
        this.panorama = null;
        this.background = null;
        if (jsonObject.has("buttons")) {
            this.loadButtons(jsonObject);
        }
        if (jsonObject.has("labels")) {
            this.loadLabels(jsonObject);
        }
        if (jsonObject.has("images")) {
            this.loadImages(jsonObject);
        }
        if (jsonObject.has("other")) {
            this.loadOthers(jsonObject);
        }
    }
    
    public static void lambda$tick$0(final TextURL textURL) {
        textURL.tick();
    }
    
    public void loadButtons(final JsonObject jsonObject) {
        for (final Map.Entry<String, V> entry : jsonObject.get("buttons").getAsJsonObject().entrySet()) {
            final String s = entry.getKey();
            final Button button = this.getButton((JsonObject)entry.getValue());
            button.name = entry.getKey();
            this.customButtons.add(button);
        }
    }
    
    public void loadAlignments(final JsonObject jsonObject) {
        (this.alignments = new HashMap<String, Alignment>()).put("bottom_left", new Alignment(0.0f, 1.0f));
        this.alignments.put("top_left", new Alignment(0.0f, 0.0f));
        this.alignments.put("top_right", new Alignment(1.0f, 0.0f));
        this.alignments.put("bottom_right", new Alignment(1.0f, 1.0f));
        this.alignments.put("center", new Alignment(0.5f, 0.5f));
        this.alignments.put("button", new Alignment(0.5f, 0.25f));
        this.alignments.put("top_center", new Alignment(0.5f, 0.0f));
        this.alignments.put("left_center", new Alignment(0.0f, 0.5f));
        this.alignments.put("bottom_center", new Alignment(0.5f, 1.0f));
        this.alignments.put("right_center", new Alignment(1.0f, 0.5f));
        if (jsonObject.has("alignments")) {
            for (final Map.Entry<String, V> entry : ((JsonObject)jsonObject.get("alignments")).entrySet()) {
                final String key = entry.getKey();
                final JsonObject jsonObject2 = (JsonObject)entry.getValue();
                this.alignments.put(key, new Alignment(jsonObject2.get("factorWidth").getAsFloat(), jsonObject2.get("factorHeight").getAsFloat()));
            }
        }
    }
    
    public Image getImage(final JsonObject jsonObject) {
        final Image image = new Image(this, jsonObject.get("posX").getAsInt(), jsonObject.get("posY").getAsInt(), jsonObject.get("width").getAsInt(), jsonObject.get("height").getAsInt(), this.getAlignment("top_left"));
        if (jsonObject.has("alignment")) {
            image.alignment = this.getAlignment(jsonObject.get("alignment").getAsString());
        }
        if (jsonObject.has("hoverImage")) {
            image.hoverImage = getWantedTexture(this.getStringPlease(jsonObject.get("hoverImage")));
        }
        if (jsonObject.has("image")) {
            image.image = getWantedTexture(this.getStringPlease(jsonObject.get("image")));
        }
        else {
            if (!jsonObject.has("slideshow")) {
                throw new RuntimeException("Images either need an image or slideshow property");
            }
            final JsonObject asJsonObject = jsonObject.get("slideshow").getAsJsonObject();
            image.ichBinEineSlideshow = true;
            final JsonArray asJsonArray = asJsonObject.get("images").getAsJsonArray();
            final String[] array = new String[asJsonArray.size()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = asJsonArray.get(i).getAsString();
            }
            final Slideshow slideShow = new Slideshow(this, array);
            Label_0742: {
                if (asJsonObject.has("displayDuration")) {
                    final Slideshow slideshow = slideShow;
                    while (true) {
                        switch (1032906152 - 361767439 + 1) {
                            case 2089591529: {
                                continue;
                            }
                            case -671139751: {
                                slideshow.displayDuration = asJsonObject.get("displayDuration").getAsInt();
                                break Label_0742;
                            }
                            default: {
                                throw null;
                            }
                        }
                    }
                }
            }
            if (asJsonObject.has("fadeDuration")) {
                slideShow.fadeDuration = asJsonObject.get("fadeDuration").getAsInt();
            }
            if (asJsonObject.has("shuffle") && asJsonObject.get("shuffle").getAsBoolean()) {
                slideShow.shuffle();
            }
            image.slideShow = slideShow;
        }
        return image;
    }
    
    public void tick() {
        this.textUrls.forEach(GuiConfig::lambda$tick$0);
    }
    
    public IAction getWantedAction(final JsonObject jsonObject) {
        final String asString = jsonObject.get("type").getAsString();
        if (asString.equals("openLink")) {
            return new ActionOpenLink(jsonObject.get("link").getAsString());
        }
        if (asString.equalsIgnoreCase("loadWorld")) {
            return new ActionLoadWorld(jsonObject.get("dirName").getAsString(), jsonObject.get("saveName").getAsString());
        }
        if (asString.equalsIgnoreCase("connectToServer")) {
            return new ActionConnectToServer(jsonObject.get("ip").getAsString(), (jsonObject.get("serverName") != null) ? jsonObject.get("serverName").getAsString() : "Minecraft Server");
        }
        if (asString.equalsIgnoreCase("openGui")) {
            return new ActionOpenGUI(jsonObject.get("gui").getAsString());
        }
        if (asString.equalsIgnoreCase("quit")) {
            return new ActionQuit();
        }
        if (asString.equalsIgnoreCase("refresh")) {
            return new ActionRefresh();
        }
        if (asString.equalsIgnoreCase("openFolder")) {
            return new ActionOpenFolder(jsonObject.get("folderName").getAsString());
        }
        if (asString.equalsIgnoreCase("openModConfig")) {
            return new ActionOpenModConfig(jsonObject.get("modid").getAsString());
        }
        return null;
    }
    
    public void loadLabels(final JsonObject jsonObject) {
        final HashSet<Map.Entry<String, V>> set = new HashSet<Map.Entry<String, V>>();
        if (jsonObject.get("texts") != null) {
            Main.logger.log(Level.ERROR, "The texts category in CMM has been renamed to labels.");
        }
        if (jsonObject.get("labels") != null) {
            set.addAll((Collection<?>)jsonObject.get("labels").getAsJsonObject().entrySet());
        }
        for (final Map.Entry<String, V> entry : set) {
            this.customLabels.add(this.getLabel(entry.getKey(), (JsonObject)entry.getValue()));
        }
    }
    
    public GuiConfig() {
        this.textUrls = new ArrayList<TextURL>();
    }
    
    public IText getWantedText(final JsonElement jsonElement) {
        if (!jsonElement.isJsonPrimitive() && !jsonElement.isJsonArray()) {
            if (jsonElement.isJsonObject()) {
                final JsonObject asJsonObject = jsonElement.getAsJsonObject();
                final String asString = asJsonObject.get("type").getAsString();
                if (asString.equals("web")) {
                    final String asString2 = asJsonObject.get("url").getAsString();
                    int asInt = -1;
                    if (asJsonObject.has("refreshInterval")) {
                        asInt = asJsonObject.get("refreshInterval").getAsInt();
                    }
                    final TextURL textURL = new TextURL(asString2, asInt);
                    this.textUrls.add(textURL);
                    return textURL;
                }
                if (asString.equals("file")) {
                    return new TextResourceLocation(asJsonObject.get("location").getAsString());
                }
            }
            return new TextString("INVALID TEXT");
        }
        final String stringPlease = this.getStringPlease(jsonElement);
        if (stringPlease.startsWith("web:")) {
            final TextURL textURL2 = new TextURL(stringPlease.substring(4, stringPlease.length()), -1);
            this.textUrls.add(textURL2);
            return textURL2;
        }
        if (stringPlease.startsWith("file:")) {
            return new TextResourceLocation(stringPlease.substring(5, stringPlease.length()));
        }
        return new TextString(stringPlease);
    }
    
    public static ITexture getWantedTexture(final String s) {
        if (s.startsWith("web:")) {
            return new TextureURL(s.substring(4, s.length()));
        }
        if (s.endsWith("apng")) {
            return new TextureApng(s);
        }
        return new TextureResourceLocation(s);
    }
    
    public Alignment getAlignment(final String s) {
        if (this.alignments.containsKey(s)) {
            return this.alignments.get(s);
        }
        return this.alignments.get("top_left");
    }
    
    public void loadImages(final JsonObject jsonObject) {
        for (final Map.Entry<String, V> entry : jsonObject.get("images").getAsJsonObject().entrySet()) {
            final String s = entry.getKey();
            this.customImages.add(this.getImage((JsonObject)entry.getValue()));
        }
    }
    
    public Label getLabel(final String s, final JsonObject jsonObject) {
        while (true) {
            switch (413294488 + 225415035 + 1) {
                case -517017010: {
                    continue;
                }
                case 365809891: {
                    final Label label = new Label(this, s, this.getWantedText(jsonObject.get("text")), jsonObject.get("posX").getAsInt(), jsonObject.get("posY").getAsInt());
                    if (jsonObject.has("alignment")) {
                        label.setAlignment(jsonObject.get("alignment").getAsString());
                    }
                    if (jsonObject.has("color")) {
                        label.setColor(jsonObject.get("color").getAsInt());
                    }
                    if (jsonObject.has("hoverColor")) {
                        label.setHoverColor(jsonObject.get("hoverColor").getAsInt());
                    }
                    if (jsonObject.has("action")) {
                        label.action = this.getWantedAction((JsonObject)jsonObject.get("action"));
                    }
                    if (jsonObject.has("hoverText")) {
                        label.hoverText = this.getWantedText(jsonObject.get("hoverText"));
                    }
                    if (jsonObject.has("fontSize")) {
                        label.fontSize = jsonObject.get("fontSize").getAsFloat();
                    }
                    if (jsonObject.has("pressSound")) {
                        label.pressSound = this.getStringPlease(jsonObject.get("pressSound"));
                    }
                    if (jsonObject.has("hoverSound")) {
                        label.hoverSound = this.getStringPlease(jsonObject.get("hoverSound"));
                    }
                    if (jsonObject.has("anchor")) {
                        final String asString = jsonObject.get("anchor").getAsString();
                        if (asString.equals("start")) {
                            label.anchor = ANCHOR.START;
                        }
                        else if (asString.equals("middle")) {
                            label.anchor = ANCHOR.MIDDLE;
                        }
                        else if (asString.equals("end")) {
                            label.anchor = ANCHOR.END;
                        }
                    }
                    return label;
                }
                default: {
                    throw null;
                }
            }
        }
    }
}
