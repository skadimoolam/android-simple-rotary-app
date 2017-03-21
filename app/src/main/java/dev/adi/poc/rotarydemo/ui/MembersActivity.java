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
import java.util.List;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.adapter.EventListAdapter;
import dev.adi.poc.rotarydemo.adapter.MemberListAdapter;
import dev.adi.poc.rotarydemo.helper.Config;
import dev.adi.poc.rotarydemo.helper.HttpHelper;
import dev.adi.poc.rotarydemo.model.EventModel;
import dev.adi.poc.rotarydemo.model.MemberModel;

public class MembersActivity extends AppCompatActivity {

    public static final String TAG = MembersActivity.class.getSimpleName();

    RecyclerView rvListMembers;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        getSupportActionBar().setTitle("Members");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvListMembers = (RecyclerView) findViewById(R.id.rv_list_memebers);
        rvListMembers.setLayoutManager(new LinearLayoutManager(this));

        getData();
    }

    private void getData() {
        if (HttpHelper.hasNetworkAccess(this)) {
            progressDialog = ProgressDialog.show(this, "Loading...", "Please wait!", false);
            HttpHelper.getData(Config.url_get_members, null, new HttpHelper.OnRequestCompleteListener() {
                @Override
                public void OnSuccess(Ason data) {
                    try {
                        AsonArray<MemberModel> asonItems = new AsonArray<>(data.get("data").toString());
                        List<MemberModel> items = asonItems.deserializeList(MemberModel.class);
                        Log.i(TAG, items.toString());
                        rvListMembers.setAdapter(new MemberListAdapter(MembersActivity.this, items));
                    } catch (Exception e) {
                        e.printStackTrace();
                        findViewById(R.id.tv_prompt_no_data).setVisibility(View.VISIBLE);
                        rvListMembers.setVisibility(View.GONE);
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
