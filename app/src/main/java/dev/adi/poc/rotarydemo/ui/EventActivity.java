package dev.adi.poc.rotarydemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.model.EventModel;
import dev.adi.poc.rotarydemo.model.MemberModel;

public class EventActivity extends AppCompatActivity {

    public static final String TAG = EventActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        EventModel eventData = getIntent().getExtras().getParcelable("event-detail");

        getSupportActionBar().setTitle("Event Info");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView ivEvePhoto = (ImageView) findViewById(R.id.iv_event_photo);
        TextView tvEveTitle = (TextView) findViewById(R.id.tv_event_title);
        TextView tvEveDate = (TextView) findViewById(R.id.tv_event_date);
        TextView tvEveDesc = (TextView) findViewById(R.id.tv_event_desc);

        tvEveTitle.setText(eventData.eveName);
        tvEveDate.setText("Date : " + eventData.eveDate);
        tvEveDesc.setText(eventData.eveDescription);

        if (eventData.evePhotoUrl.length() > 0) {
            Picasso.with(this).setLoggingEnabled(true);
            Picasso.with(this).load(eventData.evePhotoUrl).fit().into(ivEvePhoto);
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
