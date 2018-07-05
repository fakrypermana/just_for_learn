package id.teknologi.teknologiid.feature.thread_new;

import id.teknologi.teknologiid.base.BasePresenter;
import id.teknologi.teknologiid.utils.ErrorHandler;
import id.teknologi.teknologiid.utils.RxUtils;

public class ThreadTopicPresenter extends BasePresenter<ThreadTopicView> {
    public ThreadTopicPresenter(ThreadTopicView topicView) {
        super(topicView);
    }

    void UploadPost(){ }

    void getTopic(){
        getView().onLoading(true);
        dataManager.getTopic()
                .doOnTerminate(() -> getView().onLoading(false))
                .compose(RxUtils.applyScheduler())
                .compose(RxUtils.applyApiCall())
                .subscribe(response -> {
                    getView().onSuccessTopic(response.getData());
                }, throwable -> {
                    String message = ErrorHandler.handleError(throwable);
                    getView().onFailed(message);
                })
                .isDisposed();
    }
}
