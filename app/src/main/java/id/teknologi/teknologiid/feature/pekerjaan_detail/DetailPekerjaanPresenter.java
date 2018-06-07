package id.teknologi.teknologiid.feature.pekerjaan_detail;

import id.teknologi.teknologiid.base.BasePresenter;
import id.teknologi.teknologiid.utils.ErrorHandler;
import id.teknologi.teknologiid.utils.RxUtils;

public class DetailPekerjaanPresenter extends BasePresenter<DetailPekerjaanView> {
    public DetailPekerjaanPresenter(DetailPekerjaanView detailPekerjaanView) {
        super(detailPekerjaanView);
    }

    public void getDetailPekerjaan(int id, String name){
        getView().onLoading(true);
        dataManager.getPekerjaanDetail(id, name)
                .doOnTerminate(() -> getView().onLoading(false))
                .compose(RxUtils.applyScheduler())
                .compose(RxUtils.applyApiCall())
                .subscribe(response -> {
                    getView().onSuccessDetailPekerjaan(response.getData());
                }, throwable -> {
                    String message = ErrorHandler.handleError(throwable);
                    getView().onFailed(message);
                })
                .isDisposed();
    }
}
