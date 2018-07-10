package id.teknologi.teknologiid.feature.thread_new;

import id.teknologi.teknologiid.base.BasePresenter;
import id.teknologi.teknologiid.feature.thread.ThreadView;
import id.teknologi.teknologiid.utils.ErrorHandler;
import id.teknologi.teknologiid.utils.RxUtils;

public class ThreadTopicPresenter extends BasePresenter<ThreadTopicView> {
    public ThreadTopicPresenter(ThreadTopicView threadTopicView) {
        super(threadTopicView);
    }

    void getTopicList(){
        getView().onLoading(true);
        dataManager.getThreads()
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
    void UploadPost(){ }

}
