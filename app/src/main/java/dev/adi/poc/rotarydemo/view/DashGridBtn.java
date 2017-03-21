package dev.adi.poc.rotarydemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import dev.adi.poc.rotarydemo.R;

public class DashGridBtn extends LinearLayout {

    Context context;
    AttributeSet attrs;

    public DashGridBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;
        setupLayout();
    }

    public DashGridBtn(Context context) {
        super(context);
        this.context = context;
        setupLayout();
    }

    void setupLayout() {
        inflate(context, R.layout.item_dash_btn, this);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DashGridBtn, 0, 0);

        if (a.hasValue(R.styleable.DashGridBtn_btn_title)) {
            TextView tvTitle = (TextView) findViewById(R.id.tv_item_title);
            tvTitle.setText(a.getString(R.styleable.DashGridBtn_btn_title));
        }

        if (a.hasValue(R.styleable.DashGridBtn_btn_img)) {
            ImageView ivIcon = (ImageView) findViewById(R.id.iv_item_img);
            ivIcon.setImageResource(a.getInt(R.styleable.DashGridBtn_btn_title, R.drawable.dash_icon_about_2));
        }

        a.recycle();
    }
}
