package id.teknologi.teknologiid.feature.pekerjaan;

import java.util.List;

import id.teknologi.teknologiid.base.BaseView;

public interface PekerjaanView extends BaseView {
    void onSuccessThreads(List<Thread> threadList);
}
