package id.teknologi.teknologiid.feature.profile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.feature.login_register.LoginActivity;
import id.teknologi.teknologiid.feature.login_register.PrevLoginRegistActivity;
import id.teknologi.teknologiid.feature.login_register.RegisterActivity;
import id.teknologi.teknologiid.model.Profile;

public class ProfileActivity extends BaseActivity implements ProfileView{
    ProfilePresenter presenter;
    Profile profile;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @BindView(R.id.tv_nama_user_profil)
    TextView tvNamaProfile;
    @BindView(R.id.tv_alamat_user_profil)
    TextView tvAlamatProfile;
    @BindView(R.id.tv_email_user_profil)
    TextView tvEmailProfile;
    @BindView(R.id.tv_nomor_user_profil)
    TextView tvKontakProfile;
    @BindView(R.id.tv_point_profil)
    TextView tvPointProfile;
    @BindView(R.id.tv_pertanyaan_profil)
    TextView tvPertanyaanProfile;
    @BindView(R.id.tv_jawaban_profil)
    TextView tvJawabanProfile;
    @BindView(R.id.tv_vote_profil)
    TextView tvVoteProfile;

    @Override
    protected int contentView() {
        return R.layout.activity_profile;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        presenter = new ProfilePresenter(this);
        presenter.getProfile();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(ProfileActivity.this, PrevLoginRegistActivity.class));
                    finish();
                }

            }
        };

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
        tvAlamatProfile.setText(profile.getAdress());
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
