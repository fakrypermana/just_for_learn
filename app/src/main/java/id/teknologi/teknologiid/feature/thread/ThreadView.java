package id.teknologi.teknologiid.feature.thread;

import java.util.List;

import id.teknologi.teknologiid.base.BaseView;
import id.teknologi.teknologiid.model.Thread;

public interface ThreadView extends BaseView {
    void onSuccessThreads(List<Thread> threadList);
}
