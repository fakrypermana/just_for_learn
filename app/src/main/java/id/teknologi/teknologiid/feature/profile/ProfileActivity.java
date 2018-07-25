package id.teknologi.teknologiid.feature.profile;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseActivity;

import id.teknologi.teknologiid.feature.login_register.PrevLoginRegistActivity;
import id.teknologi.teknologiid.feature.login_register.RegisterActivity;
import id.teknologi.teknologiid.feature.profile.fragment.AktivitasFragment;
import id.teknologi.teknologiid.feature.profile.fragment.JawabanFragment;
import id.teknologi.teknologiid.feature.profile.fragment.PertanyaanFragment;
import id.teknologi.teknologiid.feature.profile.fragment.ThreadFragment;
import id.teknologi.teknologiid.model.LoginModel;
import id.teknologi.teknologiid.model.Profile;
import id.teknologi.teknologiid.network.TokenPreferences;

public class ProfileActivity extends BaseActivity implements ProfileView{
    ProfilePresenter presenter;
    Profile profile;
    ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @BindView(R.id.tv_nama_user_profil)
    TextView tvNamaProfile;
    @BindView(R.id.tv_email_user_profil)
    TextView tvEmailProfile;
    @BindView(R.id.tv_nomor_user_profil)
    TextView tvKontakProfile;
    @BindView(R.id.tv_comment_profil)
    TextView tvCommentProfile;
    @BindView(R.id.tv_pertanyaan_profil)
    TextView tvPertanyaanProfile;
    @BindView(R.id.tv_jawaban_profil)
    TextView tvJawabanProfile;
    @BindView(R.id.tv_vote_profil)
    TextView tvVoteProfile;
    @BindView(R.id.img_user_profil)
    ImageView ivUser;


    @BindView(R.id.pager_profile)
    ViewPager viewPager;
    @BindView(R.id.tab_profile)
    TabLayout tabLayout;
    @BindView(R.id.toolbar_profile)
    Toolbar toolbar;

    GoogleApiClient mGoogleApiClient;

    @Override
    protected int contentView() {
        return R.layout.activity_profile;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void setupData(Bundle savedInstanceState) {
        presenter = new ProfilePresenter(this);
        /*LoginModel loginModel = TokenPreferences.load(this);
        String token = loginModel.getToken();
        Log.d("tokewoy","isinya"+token);*/
        //Hawk.init(this).build();
        String token = Hawk.get("token");
        Log.d("tokon","token"+token);
        presenter.getProfile("$2y$10$imJWxlU/yhjazOnxMPlwoev97dO0XAZjb6tmtrdnj3WJ1kXpzHJGG");

        setSupportActionBar(toolbar);

        setupViewPager(viewPager);
        
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(ColorStateList.valueOf(R.color.colorDarkGray));
        tabLayout.setSelectedTabIndicatorColor(R.color.colorPrimary);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(ProfileActivity.this, PrevLoginRegistActivity.class));
                }

            }
        };

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AktivitasFragment(), "Aktivitas");
        adapter.addFragment(new ThreadFragment(), "Thread");
        adapter.addFragment(new PertanyaanFragment(), "Pertanyaan" );
        adapter.addFragment(new JawabanFragment(), "Jawaban" );
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void setupView() {

    }

    @Override
    public void onSuccessProfile(Profile profile) {
        setView(profile);
    }

    public void setView(Profile profile){
        Glide.with(this).load(profile.getUrl_photo()).into(ivUser);
        tvNamaProfile.setText(profile.getName());
        tvEmailProfile.setText(profile.getEmail());
        tvKontakProfile.setText(profile.getPhone_number());
        tvPertanyaanProfile.setText(String.valueOf(profile.getVoteQuetion()));
        tvJawabanProfile.setText(String.valueOf(profile.getVoteAnswer()));
        tvCommentProfile.setText(String.valueOf(profile.getVoteComent()));
        tvVoteProfile.setText(String.valueOf(profile.getVoteThread()));
    }

    @Override
    public void onLoading(boolean isLoading) {

    }

    @Override
    public void onFailed(String message) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_more_mert_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        mAuth = FirebaseAuth.getInstance();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_more_profil) {
            Toast.makeText(ProfileActivity.this, "more clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.option_edit) {
            Toast.makeText(ProfileActivity.this, "edit clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.option_logout) {
            //Toast.makeText(ProfileActivity.this, "logout clicked", Toast.LENGTH_LONG).show();
            mAuth.signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showProgressDialog(){
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }


    /*private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        Intent intent = new Intent(ProfileActivity.this,PrevLoginRegistActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }*/
}
