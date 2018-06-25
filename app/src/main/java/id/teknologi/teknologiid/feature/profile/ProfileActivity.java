package id.teknologi.teknologiid.feature.profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    @BindView(R.id.tv_nama_profile)
    TextView tvNamaProfile;
    @BindView(R.id.tv_alamat_profile)
    TextView tvAlamatProfile;
    @BindView(R.id.tv_email_profile)
    TextView tvEmailProfile;
    @BindView(R.id.tv_kontak_profile)
    TextView tvKontakProfile;
    @BindView(R.id.tv_point_profile)
    TextView tvPointProfile;
    @BindView(R.id.tv_peringkat_profile)
    TextView tvPeringkatProfile;
    @BindView(R.id.btn_to_login_profile)
    Button btnToLogin;

    @Override
    protected int contentView() {
        return R.layout.activity_profile;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        presenter = new ProfilePresenter(this);
        presenter.getProfile();

        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void setupView() {
        /*tvNamaProfile.setText(profile.getName());
        tvAlamatProfile.setText(profile.getAdress());
        tvEmailProfile.setText(profile.getEmail());
        tvKontakProfile.setText(profile.getPhone_number());*/
    }

    @Override
    public void onSuccessProfile(Profile profileList) {

    }

    @Override
    public void onLoading(boolean isLoading) {

    }

    @Override
    public void onFailed(String message) {

    }
}
