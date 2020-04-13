package com.example.android.participantsClient;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class OkHttpClientHelper {

    private static OkHttpClient client = new OkHttpClient();

    public static void get(String url,String name, String value, HttpCallback cb) {
        call( url,name,value, cb);
    }

    private static void call(String url, String name, String value, final HttpCallback cb) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader(name,value)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {

                cb.onSuccess(response);
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                cb.onFailure(null, e);
            }
        });
    }

    public interface HttpCallback  {
        void onFailure(Response response, IOException e);
        void onSuccess(Response response);
    }

}