package id.teknologi.teknologiid.network;


import id.teknologi.teknologiid.base.ResponseArray;
import id.teknologi.teknologiid.base.ResponseObject;
import id.teknologi.teknologiid.model.CobaModel;
import id.teknologi.teknologiid.model.DetileThread;
import id.teknologi.teknologiid.model.QuestionAnsweredModel;
import id.teknologi.teknologiid.model.QuestionDetailModel;
import id.teknologi.teknologiid.model.QuestionListModel;
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
    @GET("forum/question")
    Observable<ResponseArray<QuestionListModel>> getQuestionList();


    @GET("question/{id}/{slug}")
    Observable<ResponseObject<QuestionDetailModel>> getQuestionDetail(
            @Path("id") int id,
            @Path("slug") String slug
    );
    @GET("question/{id}/{slug}")
    Observable<ResponseArray<QuestionAnsweredModel>> getAnswerList(
            @Path("id") int id,
            @Path("slug") String slug
    );

    @GET("threads/{id}/{slug}/{comments}")
    Observable<ResponseObject<DetileThread>> getThreadDetail(
            @Path("id") int id,
            @Path("slug") String slug,
            @Path("comments") String comments
    );
}
