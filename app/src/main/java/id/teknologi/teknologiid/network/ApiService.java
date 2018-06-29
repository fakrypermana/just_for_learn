package id.teknologi.teknologiid.network;


import id.teknologi.teknologiid.base.ResponseArray;
import id.teknologi.teknologiid.base.ResponseObject;
import id.teknologi.teknologiid.model.CobaModel;
import id.teknologi.teknologiid.model.Pekerjaan;
import id.teknologi.teknologiid.model.Profile;
import id.teknologi.teknologiid.model.Thread;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by galihgasur on 10/1/17.
 */

public interface ApiService {
    @GET("threads")
    Observable<ResponseArray<Thread>> getThreads();

    @GET("threads/{id}/{slug}")
    Observable<ResponseObject<CobaModel>> getThreadDetail(
            @Path("id") int id,
            @Path("slug") String slug
    );

    //Pekerjaan
    @GET("jobs")
    Observable<ResponseArray<Pekerjaan>> getPekerjaan();

    @GET("jobs/{id}/{slug}")
    Observable<ResponseObject<Pekerjaan>> getPekerjaanDetail(
            @Path("id") int id,
            @Path("slug") String slug
    );

    //Profile
    @GET("profile")
    Observable<ResponseObject<Profile>> getProfile();

    //login dan register
    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("tag[0]") String tag
    );

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );
}
