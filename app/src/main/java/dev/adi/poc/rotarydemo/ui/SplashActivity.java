package dev.adi.poc.rotarydemo.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.ason.Ason;
import com.afollestad.bridge.Form;

import java.util.ArrayList;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.fragment.LoginPageFragment;
import dev.adi.poc.rotarydemo.fragment.SplashPageFragment;
import dev.adi.poc.rotarydemo.helper.Config;
import dev.adi.poc.rotarydemo.helper.HttpHelper;

public class SplashActivity extends AppCompatActivity {

    final String TAG = SplashActivity.class.getSimpleName();
    SharedPreferences.Editor sharePrefEditor;
    SharedPreferences preferences;

    ImageView ivLogoMain;
    ViewPager splashSlider;
    ArrayList<Fragment> pageFragments;
    TabLayout sliderTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivLogoMain = (ImageView) findViewById(R.id.img_logo_main);
        splashSlider = (ViewPager) findViewById(R.id.slider_splash);

        preferences = getSharedPreferences(Config.perf_name, MODE_PRIVATE);
        sharePrefEditor = preferences.edit();

        if (!preferences.getString("user-data", "test-data").equals("test-data")) {
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        }

        pageFragments = new ArrayList<>();
        pageFragments.add(SplashPageFragment.newInstance(R.drawable.splash_img_1));
        pageFragments.add(SplashPageFragment.newInstance(R.drawable.splash_img_2));
        pageFragments.add(SplashPageFragment.newInstance(R.drawable.splash_img_3));
        pageFragments.add(LoginPageFragment.newInstance());

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), pageFragments);
        splashSlider.setAdapter(pagerAdapter);

        sliderTabs = (TabLayout) findViewById(R.id.tab_slider);
        sliderTabs.setupWithViewPager(splashSlider, true);

        if (getIntent().getBooleanExtra("fromReg", false)) {
            splashSlider.setCurrentItem(3, false);
        }
    }

    public void gotoReg(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> pageFragments;

        public PagerAdapter(FragmentManager fm, ArrayList<Fragment> pageList) {
            super(fm);
            pageFragments = pageList;
        }

        @Override
        public Fragment getItem(int i) {
            return pageFragments.get(i);
        }

        @Override
        public int getCount() {
            return pageFragments.size();
        }
    }
}
