package dev.adi.poc.rotarydemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.ui.SplashActivity;

public class SplashPageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getArguments().getInt("layout-id", R.layout.activity_dashboard), container, false);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.fragment_slider_image);
        relativeLayout.setBackgroundResource(getArguments().getInt("image-id", R.drawable.dash_icon_about));

        return view;
    }

    public static SplashPageFragment newInstance(int imageId) {
        SplashPageFragment page = new SplashPageFragment();
        Bundle args = new Bundle();
        args.putInt("layout-id", R.layout.splash_page_1);
        args.putInt("image-id", imageId);
        page.setArguments(args);
        return page;
    }
}