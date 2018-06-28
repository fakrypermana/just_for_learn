package id.teknologi.teknologiid.network;

import id.teknologi.teknologiid.base.ResponseArray;
import id.teknologi.teknologiid.base.ResponseObject;
import id.teknologi.teknologiid.model.CobaModel;
import id.teknologi.teknologiid.model.DetileThread;
import id.teknologi.teknologiid.model.PostNewThread;
import id.teknologi.teknologiid.model.Thread;
import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by galihgasur on 10/1/17.
 */

public class DataManager {

    private static String testUrl = "https://dev.teknologi.id/api/";
    private final ApiService apiService;
    public DataManager(ApiService apiService){
        this.apiService = apiService;
    }

    public Observable<ResponseArray<Thread>> getThreads() {
        return apiService.getThreads();
    }

    public Observable<ResponseObject<CobaModel>> getThreadDetail(int id, String slug) {
        return apiService.getThreadDetail(id, slug);
    }

    public static ApiService PostNewThread(){
        return RetrofitClient.getClient(testUrl).create(ApiService.class);
    }


//    public Observable<ResponseArray<Thread>> getThreadDetile(int id, String slug) {
//        return apiService.getThreadDetail(id,slug);
//    }
}
