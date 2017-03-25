package ru.bus.raspisanie.shalk_off.raspisanie_bus;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.api.APIService;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.util.Const;

/**
 * Created by pkorl on 25.03.2017.
 */

public class App extends Application {

    private static Retrofit retrofitSchedule;
    private static APIService service;

    private static void initRetrofit() {
        retrofitSchedule = new retrofit2.Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(interceptorAndTimeOut())
                .build();
        service = retrofitSchedule.create(APIService.class);
    }

    private static OkHttpClient interceptorAndTimeOut() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    public static APIService getAPIService() {
        return service;
    }

    @Override
    public void onCreate() {
        initRetrofit(); // Инициализация Retrofit
    }
}
