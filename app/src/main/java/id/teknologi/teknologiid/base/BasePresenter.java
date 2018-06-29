package id.teknologi.teknologiid.base;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;

import id.teknologi.teknologiid.BuildConfig;
import id.teknologi.teknologiid.network.ApiService;
import id.teknologi.teknologiid.network.DataManager;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by galihgasur on 10/1/17.
 */

public abstract class BasePresenter<MvpView extends BaseView> {
    private MvpView mvpView;
    protected MvpView getView(){
        return mvpView;
    };

    protected DataManager dataManager;
    public BasePresenter(MvpView mvpView){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Log.d("ottoma", message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(25, TimeUnit.SECONDS)
                .readTimeout(25, TimeUnit.SECONDS)
                .connectTimeout(25, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    try {
                        return chain.proceed(request);
                    } catch (Exception e){
                        return new Response.Builder()
                                .request(request)
                                .code(408)
                                .protocol(Protocol.HTTP_1_1)
                                .message("{\"status\":\"fail\",\"description\":\"Please check your connection\"}")
                                .body(ResponseBody.create(MediaType.parse("application/json"), "{\"status\":\"fail\",\"description\":\"Please check your connection\"}"))
                                .build();
                    }
                })
                .addNetworkInterceptor(chain -> {
                    Request request = chain.request();

                    Request newRequest = request.newBuilder()
                            .addHeader("Cache-Control", "no-cache")
                            .addHeader("Cache-Control", "no-store")
                            .build();
                    try {
                        return chain.proceed(newRequest);
                    } catch (SocketException e){
                        e.printStackTrace();
                        return chain.proceed(chain.request());
                    }
                }).build();
        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .serializeNulls()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        dataManager = new DataManager(retrofit.create(ApiService.class));
        this.mvpView = mvpView;
    }
}

