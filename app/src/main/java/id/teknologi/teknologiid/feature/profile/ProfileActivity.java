package id.teknologi.teknologiid.feature.profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.feature.login_register.PrevLoginRegistActivity;

public class ProfileActivity extends BaseActivity {
    @BindView(R.id.btn_ngasaldulu)
    Button btnNgasal;

    @Override
    protected int contentView() {
        return R.layout.activity_profile;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        btnNgasal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, PrevLoginRegistActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void setupView() {

    }
}
