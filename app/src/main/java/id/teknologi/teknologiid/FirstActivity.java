package id.teknologi.teknologiid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.teknologi.teknologiid.base.BaseActivity;

public class FirstActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    @Override
    protected int contentView() {
        return 0;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {

    }

    @Override
    protected void setupView() {

    }
}
