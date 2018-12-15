package com.qakap.tunas.tunasqakap.client;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.qakap.tunas.tunasqakap.Config.BASE_URL;

public class RetroClient {
    private static Retrofit retro = null;
    private static final String base_url = BASE_URL;

    private static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        if (retro == null) {
            retro = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return retro;
    }

    public static ApiService getBerita() {
        return getClient().create(ApiService.class);
    }

    public static ApiService updateBerita() {
        return getClient().create(ApiService.class);
    }

    public static ApiService deleteBerita() {
        return getClient().create(ApiService.class);
    }

    public static ApiService postBerita() {
        return getClient().create(ApiService.class);
    }

    public static ApiService getBeritaFromID() {
        return getClient().create(ApiService.class);
    }
}
