package dev.adi.poc.rotarydemo.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.ason.Ason;
import com.squareup.picasso.Picasso;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.helper.Config;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    final String TAG = DashboardActivity.class.getSimpleName();
    SharedPreferences.Editor sharePrefEditor;
    SharedPreferences preferences;
    
    DrawerLayout navDrawer;
    NavigationView navMenu;

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

        new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.rl_btn_about).setOnClickListener(DashboardActivity.this);
                findViewById(R.id.rl_btn_avenue_service).setOnClickListener(DashboardActivity.this);
                findViewById(R.id.rl_btn_calender).setOnClickListener(DashboardActivity.this);
                findViewById(R.id.rl_btn_club_meeting).setOnClickListener(DashboardActivity.this);
                findViewById(R.id.rl_btn_district_projects).setOnClickListener(DashboardActivity.this);
                findViewById(R.id.rl_btn_district_teams).setOnClickListener(DashboardActivity.this);
                findViewById(R.id.rl_btn_members).setOnClickListener(DashboardActivity.this);
                findViewById(R.id.rl_btn_news).setOnClickListener(DashboardActivity.this);

                navMenu.getHeaderView(0).findViewById(R.id.iv_profile_edit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        navDrawer.closeDrawer(Gravity.START);
                        startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
                    }
                });

                if (ason.get("profile_photo").toString().length() > 0) {
                    Picasso.with(DashboardActivity.this).load(ason.get("profile_photo").toString()).fit().into((ImageView) navMenu.getHeaderView(0).findViewById(R.id.iv_profile_img));
                } else {
                    Toast.makeText(DashboardActivity.this, "Upload a Profile photo", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View view) {
        Intent i = new Intent();

        switch (view.getId()) {
            case R.id.rl_btn_about:
                i.putExtra("data-title", "About the Club");
                i.putExtra("data-logo", R.drawable.logo_trans);
                i.putExtra("data-text", getResources().getString(R.string.about_page_prompt));
                i.setClass(this, TextOnlyActivity.class);
                break;

            case R.id.rl_btn_avenue_service:
                i.setClass(this, AvenueServicesActivity.class);
                break;

            case R.id.rl_btn_calender:
                i.setClass(this, DummyActivity.class);
                break;

            case R.id.rl_btn_club_meeting:
                i.setClass(this, ClubMeetingActivity.class);
                break;

            case R.id.rl_btn_district_teams:
                i.setClass(this, DummyActivity.class);
                break;

            case R.id.rl_btn_district_projects:
                i.setClass(this, DummyActivity.class);
                break;

            case R.id.rl_btn_members:
                i.setClass(this, MembersActivity.class);
                break;

            case R.id.rl_btn_news:
                i.setClass(this, NewsLetterActivity.class);
                break;

            default:
                i.setClass(this, DummyActivity.class);
                break;
        }

        startActivity(i);
    }
}
