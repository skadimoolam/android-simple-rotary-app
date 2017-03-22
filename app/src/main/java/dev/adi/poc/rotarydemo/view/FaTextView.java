package dev.adi.poc.rotarydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import dev.adi.poc.rotarydemo.helper.FontManager;


public class FaTextView extends TextView {

    public FaTextView(Context context) {
        super(context);
        init(context, null, 0);
    }
    public FaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }
    public FaTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        setTypeface(FontManager.get(context, FontManager.FONTAWESOME));
    }

}
