package id.teknologi.teknologiid.feature.pekerjaan;

import id.teknologi.teknologiid.base.BasePresenter;
import id.teknologi.teknologiid.utils.ErrorHandler;
import id.teknologi.teknologiid.utils.RxUtils;

public class PekerjaanPresenter extends BasePresenter<PekerjaanView> {
    public PekerjaanPresenter(PekerjaanView pekerjaanView) {
        super(pekerjaanView);
    }

    public void getPekerjaan(int page){
        getView().onLoading(true);
        dataManager.getPekerjaan(page)
                .doOnTerminate(() -> getView().onLoading(false))
                .compose(RxUtils.applyScheduler())
                .compose(RxUtils.applyApiCall())
                .subscribe(response -> {
                    getView().onSuccessPekerjaan(response.getData());
                }, throwable -> {
                    String message = ErrorHandler.handleError(throwable);
                    getView().onFailed(message);
                }).isDisposed();
    }


}
