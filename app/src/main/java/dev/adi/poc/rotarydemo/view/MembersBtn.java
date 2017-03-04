package dev.adi.poc.rotarydemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import dev.adi.poc.rotarydemo.R;

public class MembersBtn extends RelativeLayout {

    Context context;
    AttributeSet attrs;

    public MembersBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;

        setupLayout();
    }

    public MembersBtn(Context context) {
        super(context);
        this.context = context;

        setupLayout();
    }

    void setupLayout() {
        inflate(context, R.layout.btn_members, this);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MembersBtn, 0, 0);

        if (a.hasValue(R.styleable.MembersBtn_memberTitle)) {
            setTitle(a.getString(R.styleable.MembersBtn_memberTitle));
        }

        a.recycle();
    }

    public void setTitle(String title) {
        TextView tvTitle = (TextView) findViewById(R.id.tv_mem_title);
        tvTitle.setText(title);
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
