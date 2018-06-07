package id.teknologi.teknologiid.network;


import id.teknologi.teknologiid.base.ResponseArray;
import id.teknologi.teknologiid.base.ResponseObject;
import id.teknologi.teknologiid.model.Pekerjaan;
import id.teknologi.teknologiid.model.CobaModel;
import id.teknologi.teknologiid.model.Thread;
import io.reactivex.Observable;
import retrofit2.http.GET;
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

    @GET("jobs/{id}/{name}")
    Observable<ResponseObject<Pekerjaan>> getPekerjaanDetail(
            @Path("id") int id,
            @Path("name") String name
    );
}
