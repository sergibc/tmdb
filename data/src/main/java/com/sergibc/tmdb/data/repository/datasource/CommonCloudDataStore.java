package com.sergibc.tmdb.data.repository.datasource;

import com.sergibc.tmdb.data.net.ApiConstants;
import com.sergibc.tmdb.data.util.DeviceUtil;

import android.content.Context;

import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class with common methods for the cloud data source
 */
public abstract class CommonCloudDataStore {

    private static final String TAG = "CloudDataStore";

    protected Context context;

    public CommonCloudDataStore(Context context) {
        this.context = context;
    }

    /**
     * Build the Retrofit object for the rest services
     */
    protected Retrofit buildRetrofit() {
        // add interceptors
        OkHttpClient httpClient = CustomOkHttpClient.getOkHttpClient(getHeaders());

        return new Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    protected abstract Map<String, String> getHeaders();

    protected String getLanguage() {
        return DeviceUtil.getDeviceLanguage();
    }

}