package dev.adi.poc.rotarydemo.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.ason.Ason;
import com.afollestad.bridge.Bridge;
import com.afollestad.bridge.BridgeException;
import com.afollestad.bridge.Callback;
import com.afollestad.bridge.Form;
import com.afollestad.bridge.MultipartForm;
import com.afollestad.bridge.Request;
import com.afollestad.bridge.Response;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.helper.Config;
import dev.adi.poc.rotarydemo.helper.FileHelper;
import dev.adi.poc.rotarydemo.helper.HttpHelper;

public class ProfileActivity extends AppCompatActivity {

    final String TAG = ProfileActivity.class.getSimpleName();
    ProgressDialog progressDialog;
    EditText etFirstname, etLastname;
    TextView tvEmail, tvUsername;
    SharedPreferences.Editor sharePrefEditor;
    SharedPreferences preferences;
    Ason prefAson;

    int READ_RESULT_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etFirstname = (EditText) findViewById(R.id.et_first_name);
        etLastname = (EditText) findViewById(R.id.et_last_name);
        tvEmail = (TextView) findViewById(R.id.tv_profile_email);
        tvUsername = (TextView) findViewById(R.id.tv_profile_username);

        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        preferences = getSharedPreferences(Config.perf_name, MODE_PRIVATE);
        sharePrefEditor = preferences.edit();

        prefAson = new Ason(preferences.getString("user-data", ""));

        tvEmail.setText("Email : " + prefAson.get("email").toString());
        tvUsername.setText("Username : " + prefAson.get("username").toString());
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void uploadPhoto(View view) {
        new MaterialFilePicker()
            .withActivity(this)
            .withRequestCode(READ_RESULT_CODE)
            .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == READ_RESULT_CODE && resultCode == RESULT_OK) {
            MultipartForm form = new MultipartForm();
            form.add("api", "update");
            form.add("update_other_user_id", prefAson.get("id").toString());
            form.add("email", prefAson.get("email").toString());
            try {
                form.add("photo_name", new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (HttpHelper.hasNetworkAccess(this)) {

                Bridge
                    .post(Config.url_register)
                    .body(form)
                    .request(new Callback() {
                        @Override
                        public void response(@NotNull Request request, @Nullable Response response, @Nullable BridgeException e) {
                            Log.i("PROFILE_ACTIVITY", response.asString());
                            Ason ason = new Ason(response.asString());
                            if (ason.get("code").equals("success")) {
                                prefAson.put("profile_photo", ason.get("data.profile_photo").toString());
//                                prefAson.put("first_name", ason.get("data.first_name").toString());
//                                prefAson.put("last_name", ason.get("data.last_name").toString());
                                sharePrefEditor.putString("user-data", prefAson.toString());
                                sharePrefEditor.apply();
                            }
                            showToast(ason.get("message").toString());
                        }
                    });
            } else {
                showToast("Cannot connect to the Internet");
            }
       }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void attemptUpdate(View view) {
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
