package dev.adi.poc.rotarydemo.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.ason.Ason;
import com.afollestad.ason.AsonArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.adapter.EventListAdapter;
import dev.adi.poc.rotarydemo.helper.Config;
import dev.adi.poc.rotarydemo.helper.HttpHelper;
import dev.adi.poc.rotarydemo.model.EventModel;

public class NewsLetterActivity extends AppCompatActivity {

    public static final String TAG = NewsLetterActivity.class.getSimpleName();

    RecyclerView rvListEvents;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsletter);

        getSupportActionBar().setTitle("News Letter");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvListEvents = (RecyclerView) findViewById(R.id.rv_list_events);
        rvListEvents.setLayoutManager(new LinearLayoutManager(this));

        getData();
    }

    private void getData() {
        if (HttpHelper.hasNetworkAccess(this)) {
            progressDialog = ProgressDialog.show(this, "Loading...", "Please wait!", false);
            HttpHelper.getData(Config.url_get_events, null, new HttpHelper.OnRequestCompleteListener() {
                @Override
                public void OnSuccess(Ason data) {
                    try {
                        AsonArray<EventModel> asonItems = new AsonArray<>(data.get("data").toString());
                        List<EventModel> items = asonItems.deserializeList(EventModel.class);
                        Log.i(TAG, items.toString());
                        rvListEvents.setAdapter(new EventListAdapter(NewsLetterActivity.this, items));
                    } catch (Exception e) {
                        e.printStackTrace();
                        findViewById(R.id.tv_prompt_no_data).setVisibility(View.VISIBLE);
                        rvListEvents.setVisibility(View.GONE);
                    }

                    progressDialog.dismiss();
                }

                @Override
                public void OnError(String error) {
                    showToast(error);
                    progressDialog.dismiss();
                }
            });
        } else {
            showToast("Please connect to the Internet");
        }
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
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
