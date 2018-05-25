package id.teknologi.teknologiid.feature.thread;

import java.util.List;

import butterknife.BindView;
import id.teknologi.teknologiid.model.Thread;

public interface ThreadDetailView extends BindView{
    void onSuccessThreads(List<Thread> threadList);
}
