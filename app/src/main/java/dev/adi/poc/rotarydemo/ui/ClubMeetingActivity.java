package dev.adi.poc.rotarydemo.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.fragment.SplashPageFragment;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class ClubMeetingActivity extends AppCompatActivity {

    ViewPager vpImageSlider;
    TabLayout tlSliderIndicator;
    ArrayList<Fragment> pageFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_meeting);
        getSupportActionBar().setTitle("Club Meetings");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vpImageSlider = (ViewPager) findViewById(R.id.vp_img_slider);
        tlSliderIndicator = (TabLayout) findViewById(R.id.tl_slider_indicator);
        tlSliderIndicator.setupWithViewPager(vpImageSlider);

        pageFragments = new ArrayList<>();
        pageFragments.add(SplashPageFragment.newInstance(R.drawable.meeting_1));
        pageFragments.add(SplashPageFragment.newInstance(R.drawable.meeting_2));
        pageFragments.add(SplashPageFragment.newInstance(R.drawable.meeting_4));
        pageFragments.add(SplashPageFragment.newInstance(R.drawable.meeting_5));
        pageFragments.add(SplashPageFragment.newInstance(R.drawable.meeting_6));
        pageFragments.add(SplashPageFragment.newInstance(R.drawable.meeting_10));
        pageFragments.add(SplashPageFragment.newInstance(R.drawable.meeting_11));

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), pageFragments);
        vpImageSlider.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }

        return super.onOptionsItemSelected(item);
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
