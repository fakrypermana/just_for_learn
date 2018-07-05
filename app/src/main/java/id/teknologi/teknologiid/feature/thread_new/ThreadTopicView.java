package id.teknologi.teknologiid.feature.thread_new;

import java.util.List;

import id.teknologi.teknologiid.base.BaseView;
import id.teknologi.teknologiid.model.Profile;
import id.teknologi.teknologiid.model.Topic;

public interface ThreadTopicView extends BaseView {
    void onSuccessTopic(List<Topic> topicList);
}