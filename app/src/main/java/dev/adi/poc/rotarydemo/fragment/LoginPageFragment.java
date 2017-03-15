package dev.adi.poc.rotarydemo.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.ason.Ason;
import com.afollestad.bridge.Form;

import java.util.ArrayList;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.helper.Config;
import dev.adi.poc.rotarydemo.helper.HttpHelper;
import dev.adi.poc.rotarydemo.ui.DashboardActivity;
import dev.adi.poc.rotarydemo.ui.SplashActivity;

public class LoginPageFragment extends Fragment {

    final String TAG = LoginPageFragment.class.getSimpleName();
    SharedPreferences.Editor sharePrefEditor;
    SharedPreferences preferences;

    ProgressDialog progressDialog;
    EditText etUsername, etPassword;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        preferences = context.getSharedPreferences(Config.perf_name, context.MODE_PRIVATE);
        sharePrefEditor = preferences.edit();

        View view = inflater.inflate(R.layout.splash_page_3, container, false);

        etUsername = (EditText) view.findViewById(R.id.et_username);
        etPassword = (EditText) view.findViewById(R.id.et_password);

        Button btnSignin = (Button) view.findViewById(R.id.email_sign_in_button);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        return view;
    }

//    public void attemptLogin() {
//        startActivity(new Intent(context, DashboardActivity.class));
//    }

    public void attemptLogin() {
        progressDialog = ProgressDialog.show(context, "Loading...", "Please wait!", false);

        if (validateForm()) {
            if (HttpHelper.hasNetworkAccess(context)) {
                Form formData = new Form();
                formData.add("username", etUsername.getText().toString());
                formData.add("password", etPassword.getText().toString());

                HttpHelper.postData(Config.url_login, formData, null, new HttpHelper.OnRequestCompleteListener() {
                    @Override
                    public void OnSuccess(Ason data) {
                        if (data.get("code").equals("success")) {
                            sharePrefEditor.putString("user-data", data.get("data").toString());
                            sharePrefEditor.putString("user-id", data.get("data.id").toString());
                            sharePrefEditor.apply();

                            Intent intent = new Intent(context, DashboardActivity.class);
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
                progressDialog.dismiss();
            }
        } else {
            showToast("Fill out all the fields");
            progressDialog.dismiss();
        }
    }

    private boolean validateForm() {
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
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static LoginPageFragment newInstance() {
        return new LoginPageFragment();
    }
}