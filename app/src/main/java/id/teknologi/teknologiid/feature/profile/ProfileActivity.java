package id.teknologi.teknologiid.feature.profile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseActivity;

import id.teknologi.teknologiid.feature.login_register.PrevLoginRegistActivity;
import id.teknologi.teknologiid.feature.login_register.RegisterActivity;
import id.teknologi.teknologiid.feature.profile.fragment.AktivitasFragment;
import id.teknologi.teknologiid.feature.profile.fragment.JawabanFragment;
import id.teknologi.teknologiid.feature.profile.fragment.PertanyaanFragment;
import id.teknologi.teknologiid.feature.profile.fragment.ThreadFragment;
import id.teknologi.teknologiid.model.Profile;

public class ProfileActivity extends BaseActivity implements ProfileView{
    ProfilePresenter presenter;
    Profile profile;

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
    @BindView(R.id.pager_profile)
    ViewPager viewPager;
    @BindView(R.id.tab_profile)
    TabLayout tabLayout;

    @Override
    protected int contentView() {
        return R.layout.activity_profile;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        presenter = new ProfilePresenter(this);
        presenter.getProfile();

        setupViewPager(viewPager);
        
        tabLayout.setupWithViewPager(viewPager);

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
        tvNamaProfile.setText(profile.getName());
        tvEmailProfile.setText(profile.getEmail());
        tvKontakProfile.setText(profile.getPhone_number());
    }

    @Override
    public void onLoading(boolean isLoading) {

    }

    @Override
    public void onFailed(String message) {

    }
}
