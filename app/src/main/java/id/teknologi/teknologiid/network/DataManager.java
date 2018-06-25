package id.teknologi.teknologiid.network;

import id.teknologi.teknologiid.base.ResponseArray;
import id.teknologi.teknologiid.model.Pekerjaan;
import id.teknologi.teknologiid.base.ResponseObject;
import id.teknologi.teknologiid.model.CobaModel;
import id.teknologi.teknologiid.model.Profile;
import id.teknologi.teknologiid.model.Thread;
import io.reactivex.Observable;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by galihgasur on 10/1/17.
 */

public class DataManager {
    private final ApiService apiService;

    //baseURL
    private static String baseUrl = "https://dev.teknologi.id/";


    public DataManager(ApiService apiService){
        this.apiService = apiService;
    }

    public Observable<ResponseArray<Thread>> getThreads() {
        return apiService.getThreads();
    }

    public Observable<ResponseObject<CobaModel>> getThreadDetail(int id, String slug) {
        return apiService.getThreadDetail(id, slug);
    }

    //Pekerjaan
    public Observable<ResponseArray<Pekerjaan>> getPekerjaan() {
        return apiService.getPekerjaan();
    }

    public Observable<ResponseObject<Pekerjaan>> getPekerjaanDetail(int id, String slug) {
        return apiService.getPekerjaanDetail(id,slug);
    }

    //Profile
    public Observable<ResponseObject<Profile>> getProfile() {
        return apiService.getProfile();
    }

    //login
    public static ApiService getUserManagerService(Converter.Factory converterFactory){
        // Create retrofit builder.
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();

        // Set base url. All the @POST @GET url is relative to this url.
        retrofitBuilder.baseUrl(baseUrl);

        /* The converter factory can be GsonConverterFactory( Convert response text to json object. ),
           if the value is null then convert response text okhttp3.ResponseBody. */
        if(converterFactory != null ) {
            retrofitBuilder.addConverterFactory(converterFactory);
        }

        // Build the retrofit object.
        Retrofit retrofit = retrofitBuilder.build();

        //Create instance for user manager interface and return it.
        ApiService userManagerService = retrofit.create(ApiService.class);
        return userManagerService;
    }
}
