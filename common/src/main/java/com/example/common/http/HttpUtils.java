package com.example.common.http;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {
    private static final String TAG = "HttpUtils";
    private static HttpUtils instance;
    private HttpUtils(){}

    public static HttpUtils getInstance() {
        if (instance == null){
            instance = new HttpUtils();
        }
        return instance;
    }
    private Retrofit retrofit;

    public Retrofit getRetrofit() {
        if (retrofit == null){
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .readTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://iwenwiki.com/api/yoho/")
                    .client(httpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public class MySelfInter implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response proceed = chain.proceed(request);
            Log.e(TAG, "intercept: "+proceed.code());
            Log.e(TAG, "intercept: "+proceed.message());
            Log.e(TAG, "intercept: "+proceed.body().string());
            return proceed;
        }
    }
}
