package dev.adi.poc.rotarydemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.model.EventModel;

public class TextOnlyActivity extends AppCompatActivity {

    public static final String TAG = TextOnlyActivity.class.getSimpleName();
    TextView tvPromptData;
    ImageView ivPromptLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_only);

        String activityTitle = getIntent().getExtras().getString("data-title");
        String activityText = getIntent().getExtras().getString("data-text");

        getSupportActionBar().setTitle(activityTitle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvPromptData = (TextView) findViewById(R.id.tv_prompt_text);
        ivPromptLogo = (ImageView) findViewById(R.id.iv_prompt_logo);

        tvPromptData.setText(activityText);

        int activityLogo = getIntent().getExtras().getInt("data-logo");
        if (activityLogo != 0) {
            ivPromptLogo.setVisibility(View.VISIBLE);
            ivPromptLogo.setImageResource(activityLogo);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
