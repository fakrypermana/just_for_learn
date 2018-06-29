package id.teknologi.teknologiid.feature.Question;

import id.teknologi.teknologiid.base.BasePresenter;
import id.teknologi.teknologiid.model.QuestionListModel;
import id.teknologi.teknologiid.utils.ErrorHandler;
import id.teknologi.teknologiid.utils.RxUtils;

public class QuestionListPresenter extends BasePresenter<QuestionView> {
    public QuestionListPresenter(QuestionView questionView) {
        super(questionView);
    }

    void getQuestionList() {
        getView().onLoading(true);
        dataManager.getQuestionList()
                .doOnTerminate(() -> getView().onLoading(false))
                .compose(RxUtils.applyScheduler())
                .compose(RxUtils.applyApiCall())
                .subscribe(response -> {
                    getView().onSuccessQuestion(response.getData());
                }, throwable -> {
                    String message = ErrorHandler.handleError(throwable);
                    getView().onFailed(message);
                });

    }


}
