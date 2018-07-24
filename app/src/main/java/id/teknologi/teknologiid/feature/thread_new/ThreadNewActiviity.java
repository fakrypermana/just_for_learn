package id.teknologi.teknologiid.feature.thread_new;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.model.Thread;
import id.teknologi.teknologiid.model.Topic;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class ThreadNewActiviity extends BaseActivity implements ThreadTopicView, RecyclerInterface{

    ThreadTopicPresenter presenter;
    List<Topic> topicList = new ArrayList<>();
    MenuView.ItemView itemView;

    @BindView(R.id.btn_topik)
    Button btnTopik;
    @BindView(R.id.tvItemSelected)
    TextView mItemSelected;

    //MultiChoice
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    Topic topicss;

    @Override
    protected int contentView() {
        return R.layout.thread_new_activiity;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {

        presenter = new ThreadTopicPresenter(this);
        presenter.getTopicList();
    }

    @Override
    protected void setupView() {

    }

    @Override
    public void onLoading(boolean isLoading) {

    }

    @Override
    public void onFailed(String message) {

    }

    @Override
    public void onRecyclerItemClicked(int position) {

    }

    @Override
    public void onSuccessTopic(List<Thread> data) {

        Log.d("hmm", new Gson().toJson(topicList));
        listItems = new String[topicList.size()];
        int i = 0;
        for (Topic topic:
                topicList) {
            listItems[i] = topic.getName();
            i++;
        }

        btnTopik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ThreadNewActiviity.this);
                mBuilder.setTitle("Topik");
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!mUserItems.contains(position)) {
                                mUserItems.add(position);
                            }
                        }else if(mUserItems.contains(position)){
                            mUserItems.remove(position);
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("pilih", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String item = "";
                        for (int i = 0; i < mUserItems.size(); i++){
                            item = item + listItems[mUserItems.get(i)];
                            if(i != mUserItems.size() -1){
                                item = item + ", ";

                            }
                        }
                        mItemSelected.setText(item);
                    }
                });

                mBuilder.setNegativeButton("batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton("Hapus Pilihan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedItems.length; i++){
                            checkedItems[i]=false;
                            mUserItems.clear();
                            mItemSelected.setText("");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

    }
}
