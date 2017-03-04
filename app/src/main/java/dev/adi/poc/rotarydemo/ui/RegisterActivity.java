package dev.adi.poc.rotarydemo.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.ason.Ason;
import com.afollestad.bridge.Form;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.helper.HttpHelper;
import dev.adi.poc.rotarydemo.helper.UrlHelper;

public class RegisterActivity extends AppCompatActivity {

    public static final String PERF_NAME = "rotery_demo";
    final String TAG = RegisterActivity.class.getSimpleName();
    ProgressDialog progressDialog;
    EditText etUsername, etPassword;
    SharedPreferences.Editor sharePrefEditor;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        preferences = getSharedPreferences(PERF_NAME, MODE_PRIVATE);
        sharePrefEditor = preferences.edit();

        if (!TextUtils.isEmpty(preferences.getString("user-data", ""))) {
            Intent intent = new Intent(this, DashboardActivity.class);
            intent.putExtra("user_id", preferences.getString("user-id", ""));
            startActivity(intent);
        }
    }

    public void attemptLogin(View view) {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

    public void attemptLoginFinal(View view) {
        progressDialog = ProgressDialog.show(this, "Loading...", "Please wait!", false);

        if (validateForm()) {
            if (HttpHelper.hasNetworkAccess(this)) {
                Form formData = new Form();
                formData.add("username", etUsername.getText().toString());
                formData.add("password", etPassword.getText().toString());

                HttpHelper.postData(UrlHelper.url_login, formData, null, new HttpHelper.OnRequestCompleteListener() {
                    @Override
                    public void OnSuccess(Ason data) {
                        if (data.get("code").equals("1")) {
                            sharePrefEditor.putString("user-data", data.toString());
                            sharePrefEditor.putString("user-id", data.get("data.id").toString());
                            sharePrefEditor.apply();

                            Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
                            intent.putExtra("user_id", data.get("data.id").toString());
                            startActivity(intent);
                        } else {
                            showToast(data.get("message").toString());
                            Log.i(TAG, "user id: " + data.get("code"));
                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void OnError(String error) {
                        showToast(error);
                        Log.i(TAG, error);
                        progressDialog.dismiss();
                    }
                });
            } else {
                showToast("Cannot connect to the Internet");
            }
        }
    }

    private boolean validateForm() {
        if (etUsername.getText().length() == 0) {
            etUsername.setError("Username cannot be blank");
            return false;
        }

        if (etPassword.getText().length() == 0) {
            etPassword.setError("Password cannot be blank");
            return false;
        }

        return true;
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
