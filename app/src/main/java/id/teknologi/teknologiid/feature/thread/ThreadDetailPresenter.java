package id.teknologi.teknologiid.feature.thread;

import id.teknologi.teknologiid.base.BasePresenter;
import id.teknologi.teknologiid.utils.ErrorHandler;
import id.teknologi.teknologiid.utils.RxUtils;

public class ThreadDetailPresenter{
    public ThreadDetailPresenter(ThreadDetailView threadDetailView){
        //super(threadDetailView);
    }

    void getThreadDetilePresenter(){
//        getView().onLoading(true);
//        dataManager.getThreads()
//                .doOnTerminate(() -> getView().onLoading(false))
//                .compose(RxUtils.applyScheduler())
//                .compose(RxUtils.applyApiCall())
//                .subscribe(response -> {
//                    getView().onSuccessThreads(response.getData());
//                }, throwable -> {
//                    String message = ErrorHandler.handleError(throwable);
//                    getView().onFailed(message);
//                });
    }
}
