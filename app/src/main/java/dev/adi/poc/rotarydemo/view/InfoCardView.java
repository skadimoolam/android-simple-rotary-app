package dev.adi.poc.rotarydemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import dev.adi.poc.rotarydemo.R;

public class InfoCardView extends LinearLayout {

    Context context;
    AttributeSet attrs;

    public InfoCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;

        setupLayout();
    }

    public InfoCardView(Context context) {
        super(context);
        this.context = context;

        setupLayout();
    }

    void setupLayout() {
        inflate(context, R.layout.info_card, this);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InfoCardView, 0, 0);

        if (a.getString(R.styleable.InfoCardView_heading).length() > 0) {
            setTitle(a.getString(R.styleable.InfoCardView_heading));
        }

        a.recycle();

        String [] items = {};
        setListItems(items);
    }

    public void setTitle(String title) {
        TextView tvTitle = (TextView) findViewById(R.id.tv_card_title);
        tvTitle.setText(title);
    }

    public void setListItems(String[] listItems) {
        ListView lvList = (ListView) findViewById(R.id.lv_card_list);
        TextView tvContent = (TextView) findViewById(R.id.tv_card_text);

        if (listItems.length > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, listItems);
            lvList.setAdapter(adapter);
            View listItem = adapter.getView(0, null, lvList);
            listItem.measure(0, 0);

            int itemHeight = listItem.getMeasuredHeight() + lvList.getDividerHeight();
            lvList.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, itemHeight * listItems.length));

            lvList.setVisibility(View.VISIBLE);
            tvContent.setVisibility(View.GONE);
        } else {
            lvList.setVisibility(View.GONE);
            tvContent.setVisibility(View.VISIBLE);
        }
    }
}
