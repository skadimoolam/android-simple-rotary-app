package dev.adi.poc.rotarydemo.ui;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.ason.Ason;

import org.w3c.dom.Text;

import java.util.ArrayList;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.adapter.DashGridAdapter;
import dev.adi.poc.rotarydemo.helper.Config;
import dev.adi.poc.rotarydemo.model.DashButtonModel;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

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
        Ason ason = new Ason(preferences.getString("user-data", "test-data"));

        getSupportActionBar().setTitle("Welcome " + ason.get("first_name").toString());
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navDrawer = (DrawerLayout) findViewById(R.id.drawer);
        navMenu = (NavigationView) findViewById(R.id.nav);
        navMenu.setNavigationItemSelectedListener(this);
        rvDashGrid = (RecyclerView) findViewById(R.id.rv_dash_grid);
        rvDashGrid.setLayoutManager(new GridLayoutManager(this, 3));

//        Toast.makeText(this, ""+navMenu.getHeaderCount(),Toast.LENGTH_SHORT).show();

        TextView tvNavUsername = (TextView) navMenu.getHeaderView(0).findViewById(R.id.tv_nav_username);
        tvNavUsername.setText(ason.get("username").toString());

        ArrayList<DashButtonModel> listButtons = new ArrayList<>();
        listButtons.add(new DashButtonModel(R.drawable.dash_icon_about, DummyActivity.class));
        listButtons.add(new DashButtonModel(R.drawable.dash_icon_avenue_service, DummyActivity.class));
        listButtons.add(new DashButtonModel(R.drawable.dash_icon_calender, DummyActivity.class));
        listButtons.add(new DashButtonModel(R.drawable.dash_icon_clue_meeting, DummyActivity.class));
        listButtons.add(new DashButtonModel(R.drawable.dash_icon_district_projects, DummyActivity.class));
        listButtons.add(new DashButtonModel(R.drawable.dash_icon_district_team, DummyActivity.class));
        listButtons.add(new DashButtonModel(R.drawable.dash_icon_home, DummyActivity.class));
        listButtons.add(new DashButtonModel(R.drawable.dash_icon_member_directory, MembersActivity.class));
        listButtons.add(new DashButtonModel(R.drawable.dash_icon_news_letter, NewsLetterActivity.class));
        listButtons.add(new DashButtonModel(R.drawable.dash_icon_search, DummyActivity.class));

        rvDashGrid.setAdapter(new DashGridAdapter(this, listButtons));
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
