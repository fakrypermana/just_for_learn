package id.teknologi.teknologiid.network;

import id.teknologi.teknologiid.base.ResponseArray;
import id.teknologi.teknologiid.model.Pekerjaan;
import id.teknologi.teknologiid.base.ResponseObject;
import id.teknologi.teknologiid.model.CobaModel;
import id.teknologi.teknologiid.model.Thread;
import io.reactivex.Observable;

/**
 * Created by galihgasur on 10/1/17.
 */

public class DataManager {
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

    public Observable<ResponseArray<Pekerjaan>> getPekerjaan() {
        return apiService.getPekerjaan();
    }

}
