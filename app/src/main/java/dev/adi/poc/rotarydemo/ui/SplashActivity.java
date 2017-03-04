package dev.adi.poc.rotarydemo.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import dev.adi.poc.rotarydemo.R;

public class SplashActivity extends AppCompatActivity {

    ImageView ivLogoMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivLogoMain = (ImageView) findViewById(R.id.img_logo_main);
    }

    public void gotoLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        ActivityOptions options = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<View, String>(ivLogoMain, "anim_logo"));
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    public void gotoSignup(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        ActivityOptions options = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<View, String>(ivLogoMain, "anim_logo"));
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }
}
