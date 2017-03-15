package dev.adi.poc.rotarydemo.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
    ProgressDialog progressDialog;
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivLogoMain = (ImageView) findViewById(R.id.img_logo_main);
        splashSlider = (ViewPager) findViewById(R.id.slider_splash);

        preferences = getSharedPreferences(Config.perf_name, MODE_PRIVATE);
        sharePrefEditor = preferences.edit();

        pageFragments = new ArrayList<>();
        pageFragments.add(SplashPageFragment.newInstance(R.drawable.splash_img_1));
        pageFragments.add(SplashPageFragment.newInstance(R.drawable.splash_img_2));
        pageFragments.add(LoginPageFragment.newInstance());

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), pageFragments);
        splashSlider.setAdapter(pagerAdapter);

        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
    }

    public void gotoLast(View view) {
        splashSlider.setCurrentItem(pageFragments.size());
    }

    public void gotoNext(View view) {
        splashSlider.setCurrentItem(splashSlider.getCurrentItem() + 1);
    }

    public void gotoReg(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

//    public void attemptLogin(View view) {
//        progressDialog = ProgressDialog.show(this, "Loading...", "Please wait!", false);
//
//        if (validateForm()) {
//            if (HttpHelper.hasNetworkAccess(this)) {
//                Form formData = new Form();
//                formData.add("username", etUsername.getText().toString());
//                formData.add("password", etPassword.getText().toString());
//
//                HttpHelper.postData(Config.url_login, formData, null, new HttpHelper.OnRequestCompleteListener() {
//                    @Override
//                    public void OnSuccess(Ason data) {
//                        if (data.get("code").equals("1")) {
//                            sharePrefEditor.putString("user-data", data.toString());
//                            sharePrefEditor.putString("user-id", data.get("data.id").toString());
//                            sharePrefEditor.apply();
//
//                            Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
//                            intent.putExtra("user_id", data.get("data.id").toString());
//                            startActivity(intent);
//                        } else {
//                            showToast(data.get("message").toString());
//                            Log.i(TAG, "user id: " + data.get("code"));
//                        }
//                        progressDialog.dismiss();
//                    }
//
//                    @Override
//                    public void OnError(String error) {
//                        showToast(error);
//                        Log.i(TAG, error);
//                        progressDialog.dismiss();
//                    }
//                });
//
//            } else {
//                showToast("Cannot connect to the Internet");
//            }
//        }
//    }
//
//    private boolean validateForm() {
//        if (etUsername.getText().length() == 0) {
//            etUsername.setError("Username cannot be blank");
//            return false;
//        }
//
//        if (etPassword.getText().length() == 0) {
//            etPassword.setError("Password cannot be blank");
//            return false;
//        }
//
//        return true;
//    }

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

//    public void gotoLogin(View view) {
//        Intent intent = new Intent(this, LoginActivity.class);
//        ActivityOptions options = null;
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            options = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<View, String>(ivLogoMain, "anim_logo"));
//            startActivity(intent, options.toBundle());
//        } else {
//            startActivity(intent);
//        }
//    }
//
//    public void gotoSignup(View view) {
//        Intent intent = new Intent(this, RegisterActivity.class);
//        ActivityOptions options = null;
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            options = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<View, String>(ivLogoMain, "anim_logo"));
//            startActivity(intent, options.toBundle());
//        } else {
//            startActivity(intent);
//        }
//    }
}
