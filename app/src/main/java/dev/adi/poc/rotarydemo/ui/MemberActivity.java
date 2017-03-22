package dev.adi.poc.rotarydemo.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.model.MemberModel;

public class MemberActivity extends AppCompatActivity {

    public static final String TAG = MemberActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        MemberModel memberData = getIntent().getExtras().getParcelable("member-detail");

        getSupportActionBar().setTitle(memberData.memName);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView ivMemPhoto = (ImageView) findViewById(R.id.iv_member_photo);
        final TextView tvMemPhone = (TextView) findViewById(R.id.tv_member_phone);
        final TextView tvMemEmail = (TextView) findViewById(R.id.tv_member_email);
        TextView tvMemDob = (TextView) findViewById(R.id.tv_member_dob);
        TextView tvMemDesig = (TextView) findViewById(R.id.tv_member_designation);

        tvMemPhone.setText(memberData.memPhone);
        tvMemEmail.setText(memberData.memEmail);
        tvMemDob.setText(memberData.memDob);
        tvMemDesig.setText(memberData.memDesignation);

        if (memberData.memPhoto.length() > 0) {
            Picasso.with(this).load(memberData.memPhoto).fit().into(ivMemPhoto);
        }

        findViewById(R.id.iv_btn_action_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + tvMemPhone.getText()));
                if (ActivityCompat.checkSelfPermission(MemberActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(i);
            }
        });

        findViewById(R.id.iv_btn_action_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{tvMemEmail.getText().toString()});
                i.putExtra(Intent.EXTRA_SUBJECT, "Hi There!");
                i.setType("message/rfc822");
                startActivity(Intent.createChooser(i, "Select an Email App"));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
