package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseRecyclerAdapter;
import id.teknologi.teknologiid.base.BaseViewHolder;
import id.teknologi.teknologiid.model.Topic;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class TopicAdapter extends BaseRecyclerAdapter<Topic, TopicAdapter.TopicVH>{

    public TopicAdapter(Context context, List<Topic> topicList,
                        RecyclerInterface recyclerCallback){
        super(context,topicList,recyclerCallback);
    }

    @Override
    protected int getResLayout(int type){
        return R.layout.activity_thread_new3;
    }

    @Override
    public TopicVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopicVH(initView(viewType, parent), getRecyclerCallback());
    }

    class TopicVH extends BaseViewHolder<Topic>{
        @BindView(R.id.s_id_topic)
        Spinner sIdTopic;

        public TopicVH(View itemView, RecyclerInterface recyclerInterface) {
            super(itemView, recyclerInterface);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(Topic topic) {

        }
    }
}
