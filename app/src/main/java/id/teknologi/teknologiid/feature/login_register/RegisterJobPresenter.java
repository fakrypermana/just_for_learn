package id.teknologi.teknologiid.feature.login_register;

import android.util.Log;

import id.teknologi.teknologiid.base.BasePresenter;
import id.teknologi.teknologiid.utils.ErrorHandler;
import id.teknologi.teknologiid.utils.RxUtils;

public class RegisterJobPresenter extends BasePresenter<RegisterJobView> {
    public RegisterJobPresenter(RegisterJobView registerJobView) {
        super(registerJobView);
    }

    public void getJob(){
        getView().onLoading(true);
        dataManager.getJob()
                .doOnTerminate(() -> getView().onLoading(false))
                .compose(RxUtils.applyScheduler())
                .compose(RxUtils.applyApiCall())
                .subscribe(response -> {
                    getView().onSuccessJob(response.getData());
                }, throwable -> {
                    String message = ErrorHandler.handleError(throwable);
                    getView().onFailed(message);
                }).isDisposed();
    }
}
