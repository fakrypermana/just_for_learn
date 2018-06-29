package id.teknologi.teknologiid.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
<<<<<<< HEAD
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl){
=======

    private static Retrofit retrofit = null;

    public static Retrofit getClient (String baseURL){
>>>>>>> putri
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
<<<<<<< HEAD
                    .baseUrl(baseUrl)
=======
                    .baseUrl(baseURL)
>>>>>>> putri
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
