package id.teknologi.teknologiid.network;


import java.util.Map;

import id.teknologi.teknologiid.base.ResponseArray;
import id.teknologi.teknologiid.base.ResponseObject;
import id.teknologi.teknologiid.model.CobaModel;
import id.teknologi.teknologiid.model.Job;
import id.teknologi.teknologiid.model.Pekerjaan;
import id.teknologi.teknologiid.model.Profile;
import id.teknologi.teknologiid.model.DetileThread;
import id.teknologi.teknologiid.model.QuestionAnsweredModel;
import id.teknologi.teknologiid.model.QuestionDetailModel;
import id.teknologi.teknologiid.model.QuestionListModel;
import id.teknologi.teknologiid.model.Thread;
import id.teknologi.teknologiid.model.ThreadCommentModel;
import id.teknologi.teknologiid.model.Topic;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by galihgasur on 10/1/17.
 */

public interface ApiService {

    //THREAD

    @GET("threads")
    Observable<ResponseArray<Thread>> getThreads();

    @GET("threads/{id}/{slug}")
    Observable<ResponseObject<CobaModel>> getThreadDetail(
            @Path("id") int id,
            @Path("slug") String slug
    );

    @GET("threads/{id}/{slug}")
    Observable<ResponseObject<ThreadCommentModel>> getThreadComment(
            @Path("id") int id,
            @Path("slug") String slug
    );

    @GET("init/topic")
    Observable<ResponseArray<Topic>> getTopic();

    @Multipart
    @POST("threads/post")
    Observable<ResponseBody> postNewThread(
            @Part("title") RequestBody title,
            @Part("post") RequestBody post,
            @Part("id_topik") RequestBody slug,
            @Part("browsePhoto") RequestBody browsePhoto
    );

    @Multipart
    @POST("threads/post")
    Call<ResponseBody> postingTread(
            @Part MultipartBody.Part photo,
            @PartMap Map<String, RequestBody> text
    );

    @Multipart
    @POST("forum/question/store")
    Call<ResponseBody> QuestionCreate(
//            @Part MultipartBody.Part photo,
            @PartMap Map<String,RequestBody> text
    );

    @GET("forum/question")
    Observable<ResponseArray<QuestionListModel>> getQuestionList();

    @GET("question/{id}/{slug}")
    Observable<ResponseArray<QuestionAnsweredModel>> getAnswerList(
            @Path("id") int id,
            @Path("slug") String slug
    );

    @GET("question/{id}/{slug}")
    Observable<ResponseObject<QuestionDetailModel>> getQuestionDetail(
            @Path("id") int id,
            @Path("slug") String slug
    );

    @GET("threads/{id}/{slug}/{comments}")
    Observable<ResponseObject<DetileThread>> getThreadDetail(
            @Path("id") int id,
            @Path("slug") String slug,
            @Path("comments") String comments
    );

    /**
     * Bagian Fakry Adi Permana
     */

    //Pekerjaan
    @GET("jobs/more")
    Observable<ResponseArray<Pekerjaan>> getPekerjaan(
            @Query("page") int page
    );

    //Detail Pekerjaan
    @GET("jobs/{id}/{slug}")
    Observable<ResponseObject<Pekerjaan>> getPekerjaanDetail(
            @Path("id") int id,
            @Path("slug") String slug
    );

    //Profile
    @GET("profile")
    Observable<ResponseObject<Profile>> getProfile(
            @Header("Token-Access") String token
    );


    //register
    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("tag[0]") String tag
    );

    //login
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login/socmed")
    Call<ResponseBody> postTokenFire(
            @Field("name") String name,
            @Field("email") String email,
            @Field("uid") String uid,
            @Field("access_token") String access_token
    );

    //bookmark pekerjaan
    @FormUrlEncoded
    @POST("jobs/{id}/{slug}/save")
    Call<ResponseBody> bookJob(
            @Path("id") int id,
            @Path("slug") String slug
    );

    @FormUrlEncoded
    @POST("profile/edit")
    Call<ResponseBody> postEditProfile(
            @Field("name") String name,
            @Field("email") String email,
            @Field("username") String username,
            @Field("adress") String adress,
            @Field("phone_number") String phone_number,
            @Field("short_bio") String short_bio,
            @Field("work") String work
    );

    @GET("init/job")
    Observable<ResponseArray<Job>> getJob();

}
