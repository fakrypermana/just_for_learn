package id.teknologi.teknologiid.feature.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseActivity;

public class PrevLoginRegistActivity extends BaseActivity {

    @BindView(R.id.btn_login_facebook)
    Button btnLoginFacebook;
    @BindView(R.id.btn_login_google)
    Button btnLoginGoogle;
    @BindView(R.id.btn_to_login)
    Button btnToLogin;
    @BindView(R.id.btn_to_regist)
    Button btnToRegist;

    @Override
    protected int contentView() {
        return R.layout.activity_prev_registlogin;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrevLoginRegistActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnToRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrevLoginRegistActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void setupView() {

    }
}
