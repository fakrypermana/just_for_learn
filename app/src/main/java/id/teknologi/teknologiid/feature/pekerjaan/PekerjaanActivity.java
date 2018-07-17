package id.teknologi.teknologiid.feature.pekerjaan;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.adapter.PekerjaanAdapter;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.feature.pekerjaan_detail.DetailPekerjaanActivity;
import id.teknologi.teknologiid.model.Pekerjaan;
import id.teknologi.teknologiid.utils.AppUtils;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class PekerjaanActivity extends BaseActivity implements PekerjaanView,RecyclerInterface{
    PekerjaanPresenter presenter;
    PekerjaanAdapter adapter;
    List<Pekerjaan> pekerjaanList = new ArrayList<>();
    ProgressDialog progressDialog;
    //private final static String PATH = "PATH";
    private final static int PAGE = 0;

    @BindView(R.id.rv_pekerjaan)
    RecyclerView rvPekerjaan;
    @BindView(R.id.btn_floating_job)
    FloatingActionButton fab;
    @BindView(R.id.toolbar_job)
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected int contentView() {
        return R.layout.activity_pekerjaan;
    }


    @Override
    protected void setupData(Bundle savedInstanceState) {
        presenter = new PekerjaanPresenter(this);
        presenter.getPekerjaan(PAGE);
        adapter = new PekerjaanAdapter(this, pekerjaanList, this);

    }

    @Override
    protected void setupView() {
        rvPekerjaan.setLayoutManager(AppUtils.defaultLinearLayoutManager(this));
        rvPekerjaan.setAdapter(adapter);

        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(PekerjaanActivity.this);
                dialog.setContentView(R.layout.activity_filter_job);
                dialog.setTitle("Filter");
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.show();
                dialog.getWindow().setAttributes(lp);

                LinearLayout linearLayout = findViewById(R.id.container_minat_filter_job);

                // Create Checkbox Dynamically
                CheckBox checkBox = new CheckBox(PekerjaanActivity.this);
                checkBox.setText("uhuhuy");
                checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    }
                });

                // Add Checkbox to LinearLayout
                if (linearLayout != null) {
                    linearLayout.addView(checkBox);
                }

            }
        });

    }

    @Override
    public void onSuccessPekerjaan(List<Pekerjaan> pekerjaanList) {
        //Log.d("Pekerjaan", new Gson().toJson(pekerjaanList));
        //showProgressDialog();
        adapter.insertAndNotify(pekerjaanList);
    }

    @Override
    public void onLoading(boolean isLoading) {
        //Log.d("Pekerjaan","LOADING "+isLoading);
        //showProgressDialog();
    }

    @Override
    public void onFailed(String message) {
        Log.d("Pekerjaan","ERROR");
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecyclerItemClicked(int position) {
        Pekerjaan pekerjaan = pekerjaanList.get(position);
        Toast.makeText(this,"Clicked"+pekerjaanList.get(position).getName(), Toast.LENGTH_SHORT).show();
        DetailPekerjaanActivity.start(this,pekerjaan.getId(),pekerjaan.getSlug());
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
        if (id == R.id.action_search) {
            Toast.makeText(PekerjaanActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.action_notification){
            Toast.makeText(PekerjaanActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    /* Show progress dialog. */
    /*private void showProgressDialog()
    {
        // Set progress dialog display message.
        progressDialog.setMessage("Please Wait");

        // The progress dialog can not be cancelled.
        progressDialog.setCancelable(true);

        // Show it.
        progressDialog.show();
    }

    *//* Hide progress dialog. *//*
    private void hideProgressDialog()
    {
        // Close it.
        progressDialog.hide();
    }*/


}
