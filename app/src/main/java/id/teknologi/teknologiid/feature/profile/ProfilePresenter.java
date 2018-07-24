package id.teknologi.teknologiid.feature.profile;

import id.teknologi.teknologiid.base.BasePresenter;
import id.teknologi.teknologiid.utils.ErrorHandler;
import id.teknologi.teknologiid.utils.RxUtils;

public class ProfilePresenter extends BasePresenter<ProfileView> {
    public ProfilePresenter(ProfileView profileView) {
        super(profileView);
    }

    void getProfile(String token){
        getView().onLoading(true);
        dataManager.getProfile(token)
                .doOnTerminate(() -> getView().onLoading(false))
                .compose(RxUtils.applyScheduler())
                .compose(RxUtils.applyApiCall())
                .subscribe(response -> {
                    getView().onSuccessProfile(response.getData());
                }, throwable -> {
                    String message = ErrorHandler.handleError(throwable);
                    getView().onFailed(message);
                }).isDisposed();
    }
}
