package dev.adi.poc.rotarydemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import dev.adi.poc.rotarydemo.R;

public class MembersActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        getSupportActionBar().setTitle("Members");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.mem_btn_admin).setOnClickListener(this);
        findViewById(R.id.mem_btn_president).setOnClickListener(this);
        findViewById(R.id.mem_btn_secratary).setOnClickListener(this);
        findViewById(R.id.mem_btn_vice).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String toastText = null;
        switch (view.getId()) {
            case R.id.mem_btn_admin:
                toastText = "Administrator";
                break;

            case R.id.mem_btn_president:
                toastText = "President";
                break;

            case R.id.mem_btn_secratary:
                toastText = "Secratary";
                break;

            case R.id.mem_btn_vice:
                toastText = "Vice-President";
                break;
        }

        if (toastText != null) {
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show();
        }
    }
}
