package id.teknologi.teknologiid.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import butterknife.ButterKnife;
import id.teknologi.teknologiid.R;

/**
 * Created by galihgasur on 10/1/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    public static final int RESULT_LOGOUT = -2;
    public static final int RESULT_WORKSHOP_EDITED = 3;
    public static final int RQ_REGIS = 4500;
    public static final int RQ_EDIT_PROFILE = 4501;
    public static final int RQ_PICK_PHOTO = 4502;
    public static final int RQ_EDIT_WORKSHOP = 4503;
    public static final int RQ_WORKSHOP_DETAIL = 4504;
    public static final int RQ_ADD_PHOTOS = 4505;
    public static final int RQ_FILTER = 4506;
    public static final int RQ_CHAT = 4506;
    private boolean isActive = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isActive = true;
        setContentView(contentView());
        ButterKnife.bind(this);
        setupData(savedInstanceState);
        setupView();
    }

    @Override
    protected void onPause() {
//        isActive = false;
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        isActive = true;
    }

    @Override
    protected void onDestroy() {
        isActive = false;
        super.onDestroy();
    }

    public boolean isActive() {
        return isActive;
    }

    protected abstract int contentView();
    protected abstract void setupData(Bundle savedInstanceState);
    protected abstract void setupView();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    protected void setupToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
    }
    protected void setupToolbar(String title){
        setupToolbar();
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
    }
    protected void setupToolbar(int title){
        setupToolbar();
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
    }
    protected Toolbar getToolbar(){
        return toolbar;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("RESREQ","REQ :"+requestCode+", RES :"+resultCode);
    }
}
