package com.example.work10_monthly.until;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OKHttpUntil {
    /**
     * 1.单例
     */
    private static OKHttpUntil instance;
    private final OkHttpClient mClient;

    public static OKHttpUntil getInstance(){
        if (instance==null){
            instance=new OKHttpUntil();
        }
        return instance;
    }

    private OKHttpUntil(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mClient = new OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    /**
     * 2.定义接口
     */
    public interface CallBack{
        void fail(Exception e);
        void success(Object o);

    }
    /**
     * 3.post异步
     */
    private Handler mHandler=new Handler(Looper.getMainLooper());
    public void postEnque(String path, final Class clazz, Map<String,String>map, final CallBack callBack){
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String,String> entry:map.entrySet()) {
                builder.add(entry.getKey(),entry.getValue());
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder().url(path).post(body).build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.fail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String string = response.body().string();
                        Gson gson = new Gson();
                        final Object o = gson.fromJson(string, clazz);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.success(o);
                            }
                        });
                    }catch (Exception e){
                        e.printStackTrace();
                    }
            }
        });
    }
}
