package id.teknologi.teknologiid.network;

import id.teknologi.teknologiid.base.ResponseArray;
import id.teknologi.teknologiid.model.QuestionDetailModel;
import id.teknologi.teknologiid.model.QuestionListModel;
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
    public Observable<ResponseArray<QuestionListModel>> getQuestionList() {
        return apiService.getQuestionList();
    }

    public Observable<ResponseArray<QuestionDetailModel>> getQuestionDetail() {
        return apiService.getQuestionDetail(id,slug);
    }

//    public Observable<ResponseArray<Thread>> getThreadDetile(int id, String slug) {
//        return apiService.getThreadDetail(id,slug);
//    }
}
