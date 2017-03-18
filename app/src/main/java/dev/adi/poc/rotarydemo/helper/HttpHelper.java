package dev.adi.poc.rotarydemo.helper;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.util.Log;

import com.afollestad.ason.Ason;
import com.afollestad.ason.AsonArray;
import com.afollestad.bridge.Bridge;
import com.afollestad.bridge.BridgeException;
import com.afollestad.bridge.Callback;
import com.afollestad.bridge.Form;
import com.afollestad.bridge.Request;
import com.afollestad.bridge.RequestBuilder;
import com.afollestad.bridge.Response;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dev.adi.poc.rotarydemo.model.EventModel;

public class HttpHelper {

    static String TAG = HttpHelper.class.getSimpleName();

    public interface OnRequestCompleteListener {
        void OnSuccess(Ason data);
        void OnError(String error);
    }

    public static boolean hasNetworkAccess(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void postData(String url, Form formData, String[] header, final OnRequestCompleteListener listener) {
        RequestBuilder request = Bridge
                .post(url)
                .body(formData);

        if (header != null) {
            request.header(header[0], header[1]);
        }

        request
                .throwIfNotSuccess()
                .request(new Callback() {
                    @Override
                    public void response(@NotNull Request request, @Nullable Response response, @Nullable BridgeException e) {
                        if (e != null) {
                            e.printStackTrace();
                            listener.OnError(e.getMessage());
                        } else {
                            Ason data = new Ason(response.asString());
                            Log.i(TAG, response.asString());
                            if (data.get("code").equals("success") && data.get("code") != "") {
                                listener.OnSuccess(data);
                            } else {
                                listener.OnError(data.get("message").toString());
                            }
                        }
                    }
                });
    }

    public static void getData(String url, String[] header, final OnRequestCompleteListener listener) {
        RequestBuilder request = Bridge
            .get(url);

        if (header != null) {
            request.header(header[0], header[1]);
        }

        request
            .throwIfNotSuccess()
            .request(new Callback() {
                @Override
                public void response(@NotNull Request request, @Nullable Response response, @Nullable BridgeException e) {
                    if (e != null) {
                        e.printStackTrace();
                        listener.OnError(e.getMessage());
                    } else {
                        Ason data = new Ason(response.asString());
                        Log.i(TAG, response.asString());
                        if (data.get("code").equals("success") && data.get("code") != "") {
                            listener.OnSuccess(data);
                        } else {
                            listener.OnError(data.get("message").toString());
                        }
                    }
                }
            });
    }
}
