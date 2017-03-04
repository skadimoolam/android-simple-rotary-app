package dev.adi.poc.rotarydemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import dev.adi.poc.rotarydemo.R;

public class DummyActivity extends AppCompatActivity {

    final String TAG = DummyActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        getSupportActionBar().setTitle("Dummy Page");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
