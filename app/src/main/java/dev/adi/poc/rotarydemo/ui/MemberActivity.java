package dev.adi.poc.rotarydemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
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
        TextView tvMemPhone = (TextView) findViewById(R.id.tv_member_phone);
        TextView tvMemEmail = (TextView) findViewById(R.id.tv_member_email);
        TextView tvMemDob = (TextView) findViewById(R.id.tv_member_dob);
        TextView tvMemDesig = (TextView) findViewById(R.id.tv_member_designation);

        tvMemPhone.setText(memberData.memPhone);
        tvMemEmail.setText(memberData.memEmail);
        tvMemDob.setText(memberData.memDob);
        tvMemDesig.setText(memberData.memDesignation);

        if (memberData.memPhoto.length() > 0) {
            Picasso.with(this).load(memberData.memPhoto).fit().into(ivMemPhoto);
        }
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
