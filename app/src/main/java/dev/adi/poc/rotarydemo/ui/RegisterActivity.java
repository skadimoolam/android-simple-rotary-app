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
import dev.adi.poc.rotarydemo.helper.Config;
import dev.adi.poc.rotarydemo.helper.HttpHelper;
import dev.adi.poc.rotarydemo.helper.UrlHelper;

public class RegisterActivity extends AppCompatActivity {

    final String TAG = RegisterActivity.class.getSimpleName();
    ProgressDialog progressDialog;
    EditText etFirstname, etLastname, etEmail, etUsername, etPassword;
    SharedPreferences.Editor sharePrefEditor;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstname = (EditText) findViewById(R.id.et_first_name);
        etLastname = (EditText) findViewById(R.id.et_last_name);
        etEmail = (EditText) findViewById(R.id.et_email);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        preferences = getSharedPreferences(Config.perf_name, MODE_PRIVATE);
        sharePrefEditor = preferences.edit();
    }

    public void attemptReg(View view) {
        progressDialog = ProgressDialog.show(this, "Loading...", "Please wait!", false);

        if (validateForm()) {
            if (HttpHelper.hasNetworkAccess(this)) {
                Form formData = new Form();
                formData.add("first_name", etFirstname.getText().toString());
                formData.add("last_name", etLastname.getText().toString());
                formData.add("email", etEmail.getText().toString());
                formData.add("username", etUsername.getText().toString());
                formData.add("password", etPassword.getText().toString());
                formData.add("api", "insert");
                formData.add("group_id", "2");

                HttpHelper.postData(Config.url_register, formData, null, new HttpHelper.OnRequestCompleteListener() {
                    @Override
                    public void OnSuccess(Ason data) {
                        if (data.get("code").equals("success")) {
                            showToast(data.get("message").toString());

                            Intent i = new Intent(RegisterActivity.this, SplashActivity.class);
                            i.putExtra("fromReg", true);
                            startActivity(i);
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
                progressDialog.dismiss();
            }
        } else {
            showToast("Fill out all the fields");
            progressDialog.dismiss();
        }
    }

    private boolean validateForm() {
        if (etFirstname.getText().toString().length() == 0) {
            etFirstname.setError("First name cannot be blank");
            return false;
        }

        if (etEmail.getText().toString().length() == 0) {
            etEmail.setError("Email cannot be blank");
            return false;
        }

        if (etUsername.getText().toString().length() == 0) {
            etUsername.setError("Username cannot be blank");
            return false;
        }

        if (etPassword.getText().toString().length() == 0) {
            etPassword.setError("Password cannot be blank");
            return false;
        }

        return true;
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
