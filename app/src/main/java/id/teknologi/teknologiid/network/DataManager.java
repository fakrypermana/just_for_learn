package id.teknologi.teknologiid.network;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by galihgasur on 10/1/17.
 */

public class DataManager {
    private final ApiService apiService;
    public DataManager(ApiService apiService){
        this.apiService = apiService;
    }

}
