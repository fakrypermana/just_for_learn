package id.teknologi.teknologiid.feature.thread_detail;

import id.teknologi.teknologiid.base.BasePresenter;
import id.teknologi.teknologiid.utils.ErrorHandler;
import id.teknologi.teknologiid.utils.RxUtils;

public class ThreadDetailPresenter extends BasePresenter<ThreadDetailView> {
// auto generate
    public ThreadDetailPresenter(ThreadDetailView cobaView) {
        super(cobaView);
    }

    public void getThreadDetail(int id, String slug){
        getView().onLoading(true);
        dataManager.getThreadDetail(id, slug)
                .doOnTerminate(() -> getView().onLoading(false))
                .compose(RxUtils.applyScheduler())
                .compose(RxUtils.applyApiCall())
                .subscribe(response -> {
                    getView().onSuccessThreadDetail(response.getData());
                }, throwable -> {
                    String message = ErrorHandler.handleError(throwable);
                    getView().onFailed(message);
                })
                .isDisposed();
    }
}