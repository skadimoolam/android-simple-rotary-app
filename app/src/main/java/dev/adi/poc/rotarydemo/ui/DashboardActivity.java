package dev.adi.poc.rotarydemo.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import dev.adi.poc.rotarydemo.R;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    final String TAG = DashboardActivity.class.getSimpleName();
    SharedPreferences.Editor sharePrefEditor;
    
    DrawerLayout navDrawer;
    NavigationView navMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.side_nav);

        getSupportActionBar().setTitle("Welcome [username]");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navDrawer = (DrawerLayout) findViewById(R.id.drawer);
        navMenu = (NavigationView) findViewById(R.id.nav);
        navMenu.setNavigationItemSelectedListener(this);

        findViewById(R.id.dash_btn_district).setOnClickListener(this);
        findViewById(R.id.dash_btn_memebers).setOnClickListener(this);
        findViewById(R.id.dash_btn_calender).setOnClickListener(this);
        findViewById(R.id.dash_btn_notice).setOnClickListener(this);
        findViewById(R.id.dash_btn_bday).setOnClickListener(this);
        findViewById(R.id.dash_btn_events).setOnClickListener(this);
        findViewById(R.id.dash_btn_createclub).setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.dash_btn_district:
                intent = new Intent(DashboardActivity.this, DummyActivity.class);
                break;

            case R.id.dash_btn_memebers:
                intent = new Intent(DashboardActivity.this, MembersActivity.class);
                break;

            case R.id.dash_btn_calender:
                intent = new Intent(DashboardActivity.this, DummyActivity.class);
                break;

            case R.id.dash_btn_notice:
                intent = new Intent(DashboardActivity.this, DummyActivity.class);
                break;

            case R.id.dash_btn_bday:
                intent = new Intent(DashboardActivity.this, DummyActivity.class);
                break;

            case R.id.dash_btn_events:
                intent = new Intent(DashboardActivity.this, DummyActivity.class);
                break;

            case R.id.dash_btn_createclub:
                intent = new Intent(DashboardActivity.this, DummyActivity.class);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }
}
