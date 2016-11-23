package com.sergibc.tmdb.data.repository.datasource;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Custom OkHttp3 Client
 */
public class CustomOkHttpClient {

    public static OkHttpClient getOkHttpClient(Map<String, String> headers, Cache cache) {
        // Logging
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        // Create an ssl socket factory with our all-trusting manager
        TLSSocketFactory sslSocketFactory = TLSSocketFactory.getInstance();
        return new OkHttpClient.Builder()
                .cache(cache)
                .sslSocketFactory(sslSocketFactory)
                .addInterceptor(logging)
                .addNetworkInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    if (headers != null) {
                        Set<String> keys = headers.keySet();
                        for (String key : keys) {
                            String value = headers.get(key);
                            if (value != null) {
                                builder.addHeader(key, value);
                            }
                        }
                    }

                    Request request = builder.build();
                    return chain.proceed(request);
                })
                .connectTimeout(45, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(45, TimeUnit.SECONDS).build();

    }

    public static OkHttpClient getOkHttpClient(Map<String, String> headers) {
        return getOkHttpClient(headers, null);
    }
}
