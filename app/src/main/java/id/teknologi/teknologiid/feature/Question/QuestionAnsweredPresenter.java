package id.teknologi.teknologiid.feature.Question;

import id.teknologi.teknologiid.base.BasePresenter;
import id.teknologi.teknologiid.base.BaseView;
import id.teknologi.teknologiid.utils.ErrorHandler;
import id.teknologi.teknologiid.utils.RxUtils;

public class QuestionAnsweredPresenter extends BasePresenter<QuestionAnsweredView> {


    public QuestionAnsweredPresenter(QuestionAnsweredView questionAnsweredView) {
        super(questionAnsweredView);}
    void getAnswerList(int id, String slug) {
        getView().onLoading(true);
        dataManager.getAnswerList(id, slug)
                .doOnTerminate(() -> getView().onLoading(false))
                .compose(RxUtils.applyScheduler())
                .compose(RxUtils.applyApiCall())
                .subscribe(response -> {
                    getView().onSuccessAnswer(response.getData());
                }, throwable -> {
                    String message = ErrorHandler.handleError(throwable);
                    getView().onFailed(message);
                });

    }
}
