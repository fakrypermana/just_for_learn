package id.teknologi.teknologiid.feature.Question;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;
import de.hdodenhof.circleimageview.CircleImageView;

import com.google.gson.Gson;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.LayoutParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.adapter.QuestionListAdapter;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.feature.thread.ThreadActivity;
import id.teknologi.teknologiid.feature.thread_new.ThreadNewActivity3;
import id.teknologi.teknologiid.model.QuestionListModel;
import id.teknologi.teknologiid.utils.AppUtils;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class QuestionListActivity extends  BaseActivity implements QuestionView, RecyclerInterface {

    QuestionListPresenter questionListPresenter;
    QuestionListAdapter questionListAdapter;
    List<QuestionListModel> questionListModels = new ArrayList<>();

    private int trigger =0;

    @BindView(R.id.rv_QuestionList)
    RecyclerView rvQuestionList;

    @BindView(R.id.action_search_view)
    SearchView searchView;
    @BindView(R.id.black_layout)
    LinearLayout blackLayout;



    @Override
    protected int contentView() {
        return R.layout.activity_main_question;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        questionListPresenter = new QuestionListPresenter(this);
        questionListPresenter.getQuestionList();
        questionListAdapter = new QuestionListAdapter(this, questionListModels, this);


    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void setupView() {
        blackLayout.setVisibility(View.GONE);
        rvQuestionList.setLayoutManager(AppUtils.defaultLinearLayoutManager(this));
        rvQuestionList.setAdapter(questionListAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                questionListAdapter.getFilter().filter(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                questionListAdapter.getFilter().filter(newText);
                return true;
            }
        });

        CircleImageView icon = new CircleImageView(this);
        icon.setImageResource(R.drawable.menu);
        final FloatingActionButton floatingButton =
                new FloatingActionButton.Builder(this)
                .setContentView(icon).build();
//        floatingButton.setBackgroundResource(R.color.colorAccentYellow);
//        floatingButton.setBackground(getResources().getDrawable(R.color.colorAccentYellow));
        SubActionButton.Builder builder = new SubActionButton.Builder(this);
//        builder.setBackgroundDrawable(getResources().getDrawable(R.color.colorAccentYellow));


        CircleImageView createQuestionButton = new CircleImageView(this);
        createQuestionButton.setImageResource(R.drawable.pen_white_48);
        SubActionButton createButton =builder.setContentView(createQuestionButton)
                .build();

        ImageView filterQuestion = new ImageView(this);
        filterQuestion.setImageResource(R.drawable.filter);
        SubActionButton filterButton =builder.setContentView(filterQuestion).build();

        ImageView tagFilter = new ImageView(this);
        tagFilter.setImageResource(R.drawable.tag_label);
        SubActionButton tagFilterButton =builder.setContentView(tagFilter).build();

        LayoutParams params=new LayoutParams(200,200);
        createButton.setLayoutParams(params);
        filterButton.setLayoutParams(params);
        tagFilterButton.setLayoutParams(params);

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blackLayout.setVisibility(View.VISIBLE);
                final FloatingActionMenu floatingmenu = new FloatingActionMenu.Builder(QuestionListActivity.this)
                        .addSubActionView(createButton)
                        .addSubActionView(filterButton)
                        .addSubActionView(tagFilterButton)
                        .attachTo(floatingButton)
                        .setRadius(400)
                        .setStartAngle(270)
                        .setEndAngle(150)
                        .build();

                createButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(QuestionListActivity.this, QuestionCreateActivity.class);
                        startActivity(intent);
                        floatingmenu.close(true);
                    }
                });
                filterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(QuestionListActivity.this,"filter question",Toast.LENGTH_SHORT).show();
                        floatingmenu.close(true);
                    }
                });
                tagFilterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(QuestionListActivity.this,"filter tag question",Toast.LENGTH_SHORT).show();
                        floatingmenu.close(true);
                    }
                });
                blackLayout.setVisibility(View.GONE);

            }
        });
        Log.d("trigger", "trigger= "+trigger);

    }

    @Override
    public void onSuccessQuestion(List<QuestionListModel> questionListModels) {
        Log.d("QUESTION LIST", new Gson().toJson(questionListModels));
        questionListAdapter.insertAndNotify(questionListModels);


    }

    @Override
    public void onLoading(boolean isLoading) {
        Log.d("QUESTION LIST", "LOADING" + isLoading);

    }

    @Override
    public void onFailed(String message) {
        Log.d("QUESTION LIST", "ERROR");

    }

    @Override
    public void onRecyclerItemClicked(int position) {
        QuestionListModel question = questionListModels.get(position);
        Toast.makeText(this, "Clicked" + questionListModels.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        QuestionDetailActivity.start(this, question.getId(), question.getSlug());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_notif, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_post) {
//            String title = tvQuestionEditor.getText().toString();
//            String post = richEditor.getHtml().toString();
////            String topic = sIdTopic.getSelectedItem().toString();
////            Log.d("checker",""+checker);
//            Log.d("cari title", title.toString());
//            Log.d("cari post", post.toString());
//
//            uploadContent(title,post);
//
////            Log.d("cari topic", topic.toString());
////            if(checker==true){
////                uploadImage(pathCamera,title,post,"1");
////            }else if(checker==false){
////                uploadImage(pathPhoto,title,post,"1");
////            }
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }



}