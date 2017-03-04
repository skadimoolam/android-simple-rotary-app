package dev.adi.poc.rotarydemo.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import dev.adi.poc.rotarydemo.R;

public class DashBtn extends RelativeLayout {

    Context context;
    AttributeSet attrs;
    Typeface fontFace;

    public DashBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;

        setupLayout();
    }

    public DashBtn(Context context) {
        super(context);
        this.context = context;

        setupLayout();
    }

    void setupLayout() {
        inflate(context, R.layout.btn_dash, this);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DashBtn, 0, 0);
        fontFace = Typeface.createFromAsset(context.getAssets(), "fonts/Merriweather-Regular.ttf");

        if (a.getString(R.styleable.DashBtn_title).length() > 0) {
            setTitle(a.getString(R.styleable.DashBtn_title));
        } else {
            setTitle("Default");
        }

        if (a.hasValue(R.styleable.DashBtn_subTitle)) {
            setSubTitle(a.getString(R.styleable.DashBtn_subTitle));
        }

        setBackgroundColor(a.getColor(R.styleable.DashBtn_bgColor, Color.rgb(255, 255, 0)));

        a.recycle();
    }

    public void setTitle(String title) {
        TextView tvTitle = (TextView) findViewById(R.id.tv_text_primary);
        tvTitle.setTypeface(fontFace);
        tvTitle.setText(title);
    }

    public void setSubTitle(String subTitle) {
        TextView tvTitle = (TextView) findViewById(R.id.tv_text_secondry);
        tvTitle.setTypeface(fontFace);
        tvTitle.setText(subTitle);
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        Paint paint = new Paint();
//
//        paint.setColor(Color.WHITE);
//        paint.setStrokeWidth(5);
//
//        canvas.drawLine(0, 0, this.getWidth() - 1, 0, paint);
//        canvas.drawLine(0, 0, 0, this.getHeight() - 1, paint);
//        canvas.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, this.getHeight() - 1, paint);
//        canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1, this.getHeight() - 1, paint);
//    }
}
