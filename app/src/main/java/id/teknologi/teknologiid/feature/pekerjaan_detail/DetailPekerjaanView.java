package id.teknologi.teknologiid.feature.pekerjaan_detail;

import id.teknologi.teknologiid.base.BaseView;
import id.teknologi.teknologiid.model.Pekerjaan;

public interface DetailPekerjaanView extends BaseView {
    void onSuccessDetailPekerjaan(Pekerjaan pekerjaan);
}
