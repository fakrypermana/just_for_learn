package id.teknologi.teknologiid.feature.pekerjaan;

import java.util.List;

import id.teknologi.teknologiid.base.BaseView;
import id.teknologi.teknologiid.model.Pekerjaan;

public interface PekerjaanView extends BaseView {
    void onSuccessPekerjaan(List<Pekerjaan> pekerjaanList);
}
