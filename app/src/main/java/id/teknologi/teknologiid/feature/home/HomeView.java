package id.teknologi.teknologiid.feature.home;

import java.util.List;

import id.teknologi.teknologiid.base.BaseView;
import id.teknologi.teknologiid.model.Thread;

public interface HomeView extends BaseView {
    void onSuccessThreads(List<Thread> threadList);
}
