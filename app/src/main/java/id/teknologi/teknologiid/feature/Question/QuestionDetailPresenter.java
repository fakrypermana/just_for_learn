package id.teknologi.teknologiid.feature.Question;

import id.teknologi.teknologiid.base.BasePresenter;
import id.teknologi.teknologiid.utils.ErrorHandler;
import id.teknologi.teknologiid.utils.RxUtils;

public class QuestionDetailPresenter extends BasePresenter<QuestionDetailView>{
    public QuestionDetailPresenter(QuestionDetailView questionDetailView) {
        super(questionDetailView);
    }
    void getQuestionDetail() {
        getView().onLoading(true);
        dataManager.getQuestionDetail()
                .doOnTerminate(() -> getView().onLoading(false))
                .compose(RxUtils.applyScheduler())
                .compose(RxUtils.applyApiCall())
                .subscribe(response -> {
                    getView().onSuccessQuestionDetail(response.getData());
                }, throwable -> {
                    String message = ErrorHandler.handleError(throwable);
                    getView().onFailed(message);
                });

    }
}
