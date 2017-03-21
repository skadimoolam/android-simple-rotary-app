package dev.adi.poc.rotarydemo.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.ason.Ason;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.adapter.DashGridAdapter;
import dev.adi.poc.rotarydemo.helper.Config;
import dev.adi.poc.rotarydemo.model.DashButtonModel;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    final String TAG = DashboardActivity.class.getSimpleName();
    SharedPreferences.Editor sharePrefEditor;
    SharedPreferences preferences;
    
    DrawerLayout navDrawer;
    NavigationView navMenu;
    RecyclerView rvDashGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.side_nav);

        preferences = getSharedPreferences(Config.perf_name, MODE_PRIVATE);
        sharePrefEditor = preferences.edit();
        final Ason ason = new Ason(preferences.getString("user-data", "test-data"));

        getSupportActionBar().setTitle("Welcome " + ason.get("first_name").toString());
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navDrawer = (DrawerLayout) findViewById(R.id.drawer);
        navMenu = (NavigationView) findViewById(R.id.nav);
        navMenu.setNavigationItemSelectedListener(this);

        TextView tvNavUsername = (TextView) navMenu.getHeaderView(0).findViewById(R.id.tv_nav_username);
        tvNavUsername.setText(ason.get("username").toString());

        findViewById(R.id.ll_btn_about).setOnClickListener(this);
        findViewById(R.id.ll_btn_avenue_service).setOnClickListener(this);
        findViewById(R.id.ll_btn_calender).setOnClickListener(this);
        findViewById(R.id.ll_btn_club_meeting).setOnClickListener(this);
        findViewById(R.id.ll_btn_district_projects).setOnClickListener(this);
        findViewById(R.id.ll_btn_district_team).setOnClickListener(this);
        findViewById(R.id.ll_btn_search).setOnClickListener(this);
        findViewById(R.id.ll_btn_member_directory).setOnClickListener(this);
        findViewById(R.id.ll_btn_news_letter).setOnClickListener(this);

        new Runnable() {
            @Override
            public void run() {
                navMenu.getHeaderView(0).findViewById(R.id.iv_profile_edit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        navDrawer.closeDrawer(Gravity.START);
                        startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
                    }
                });

                if (ason.get("profile_photo").toString().length() > 0) {
                    Picasso.with(DashboardActivity.this).load(ason.get("profile_photo").toString()).fit().into((ImageView) navMenu.getHeaderView(0).findViewById(R.id.iv_profile_img));
                }
            }
        }.run();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Logout");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle() == "Logout") {
            sharePrefEditor.remove("user-data");
            sharePrefEditor.remove("user-id");
            sharePrefEditor.apply();
            sharePrefEditor.commit();
            startActivity(new Intent(this, SplashActivity.class));
            finish();
        }

        if (item.getItemId() == android.R.id.home) {
            if (navDrawer.isDrawerOpen(GravityCompat.START)) {
                navDrawer.closeDrawer(GravityCompat.START);
            } else {
                navDrawer.openDrawer(GravityCompat.START);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }

    public void gotoView(View view) {
//        Intent i = new Intent();
//
//        switch (view.getId()) {
//            case R.id.ll_btn_about:
//                i.setClassName(this, "DummyActivity");
//                break;
//        }
//
//        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent();

        switch (view.getId()) {
            case R.id.ll_btn_about:
                i.setClass(this, DummyActivity.class);
                break;

            case R.id.ll_btn_avenue_service:
                i.setClass(this, DummyActivity.class);
                break;

            case R.id.ll_btn_calender:
                i.setClass(this, DummyActivity.class);
                break;

            case R.id.ll_btn_club_meeting:
                i.setClass(this, DummyActivity.class);
                break;

            case R.id.ll_btn_district_team:
                i.setClass(this, DummyActivity.class);
                break;

            case R.id.ll_btn_district_projects:
                i.setClass(this, DummyActivity.class);
                break;

            case R.id.ll_btn_search:
                i.setClass(this, DummyActivity.class);
                break;

            case R.id.ll_btn_member_directory:
                i.setClass(this, MembersActivity.class);
                break;

            case R.id.ll_btn_news_letter:
                i.setClass(this, NewsLetterActivity.class);
                break;

            default:
                i.setClass(this, DummyActivity.class);
                break;
        }

        startActivity(i);
    }
}
