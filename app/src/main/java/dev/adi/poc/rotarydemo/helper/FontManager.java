package dev.adi.poc.rotarydemo.helper;

import android.content.Context;
import android.graphics.Typeface;

public class FontManager {

    public static final String ROOT = "fonts/",
        FONTAWESOME = ROOT + "fontawesome-webfont.ttf",
        MERRIWEATHER = ROOT + "Merriweather-Regular.ttf";

    public static Typeface get(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }

}
