package id.teknologi.teknologiid.network;


import id.teknologi.teknologiid.base.ResponseArray;
import id.teknologi.teknologiid.base.ResponseObject;
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
}
