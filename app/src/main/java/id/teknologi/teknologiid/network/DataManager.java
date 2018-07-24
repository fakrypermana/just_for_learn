package id.teknologi.teknologiid.network;

import id.teknologi.teknologiid.base.ResponseArray;
import id.teknologi.teknologiid.model.Job;
import id.teknologi.teknologiid.model.Pekerjaan;
import id.teknologi.teknologiid.base.ResponseObject;
import id.teknologi.teknologiid.model.CobaModel;
import id.teknologi.teknologiid.model.Profile;
import id.teknologi.teknologiid.base.ResponseObject;
import id.teknologi.teknologiid.model.CobaModel;
import id.teknologi.teknologiid.model.DetileThread;
import id.teknologi.teknologiid.model.QuestionAnsweredModel;
import id.teknologi.teknologiid.model.QuestionDetailModel;
import id.teknologi.teknologiid.model.QuestionListModel;
import id.teknologi.teknologiid.model.Thread;
import id.teknologi.teknologiid.model.ThreadCommentModel;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by galihgasur on 10/1/17.
 */

public class DataManager {
    private final ApiService apiService;
    private static Retrofit Retrofit = null;
    //baseURL
    //private static String baseUrl = "https://dev.teknologi.id/";
    private static String testUrl = "https://dev.teknologi.id/api/";


    public DataManager(ApiService apiService){
        this.apiService = apiService;
    }

    public Observable<ResponseArray<Thread>> getThreads() {
        return apiService.getThreads();
    }
    public Observable<ResponseArray<QuestionListModel>> getQuestionList() {
        return apiService.getQuestionList();
    }


    public Observable<ResponseObject<QuestionDetailModel>> getQuestionDetail(int id, String slug) {
        return apiService.getQuestionDetail(id,slug);
    }



    public Observable<ResponseObject<CobaModel>> getThreadDetail(int id, String slug) {
        return apiService.getThreadDetail(id, slug);
    }

    public Observable<ResponseObject<ThreadCommentModel>> getThreadComment(int id, String slug){
        return apiService.getThreadComment(id,slug);
    }

    //Pekerjaan
    public Observable<ResponseArray<Pekerjaan>> getPekerjaan(int page) {
        return apiService.getPekerjaan(page);
    }

    //Job
    public Observable<ResponseArray<Job>> getJob() {
        return apiService.getJob();
    }

    public Observable<ResponseObject<Pekerjaan>> getPekerjaanDetail(int id, String slug) {
        return apiService.getPekerjaanDetail(id,slug);
    }

    //Profile
    public Observable<ResponseObject<Profile>> getProfile(String token) {
        return apiService.getProfile(token);
    }

    //login
    public static ApiService getUserManagerService(Converter.Factory converterFactory){
        // Create retrofit builder.
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();

        // Set base url. All the @POST @GET url is relative to this url.
        retrofitBuilder.baseUrl(testUrl);

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

    public static ApiService getApiService(){
        return RetrofitClient.getClient(testUrl).create(ApiService.class);
    }


    public Observable<ResponseObject<DetileThread>> getThreadDetail(int id, String slug, String comment) {
        return apiService.getThreadDetail(id, slug, comment);
    }

    public Observable<ResponseArray<QuestionAnsweredModel>>getAnswerList(int id, String slug) {
        return apiService.getAnswerList(id, slug);
    }
//    public Observable<ResponseArray<Thread>> getThreadDetile(int id, String slug) {
//        return apiService.getThreadDetail(id,slug);
//    }
}
