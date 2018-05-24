package id.teknologi.teknologiid.feature.home;

import android.annotation.SuppressLint;

import id.teknologi.teknologiid.base.BasePresenter;
import id.teknologi.teknologiid.base.BaseView;
import id.teknologi.teknologiid.utils.ErrorHandler;
import id.teknologi.teknologiid.utils.RxUtils;

public class HomePresenter extends BasePresenter<HomeView> {
    public HomePresenter(HomeView homeView) {
        super(homeView);
    }

    void getThreads(){
        getView().onLoading(true);
        dataManager.getThreads()
                .doOnTerminate(() -> getView().onLoading(false))
                .compose(RxUtils.applyScheduler())
                .compose(RxUtils.applyApiCall())
                .subscribe(response -> {
                    getView().onSuccessThreads(response.getData());
                }, throwable -> {
                    String message = ErrorHandler.handleError(throwable);
                    getView().onFailed(message);
                });
    }
}
