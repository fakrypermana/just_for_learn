package id.teknologi.teknologiid.feature.thread_new;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import id.teknologi.teknologiid.R;

public class ThreadNewActivity2 extends AppCompatActivity {

//    @BindView(R.id.tv_title)
//    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_new2);

        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvTopik =  findViewById(R.id.tv_topic);
        TextView tvCategory = findViewById(R.id.tv_category);
        tvTitle.setText(getIntent().getExtras().getString("judul"));
        tvTopik.setText(getIntent().getExtras().getString("topik"));
        tvCategory.setText(getIntent().getExtras().getString("kategori"));


//        ImageView ivPhoto = (ImageView) findViewById(R.id.iv_Photo);
//        ivPhoto.setImageResource(Integer.parseInt(getIntent().getExtras().getString("sampul")));
    }

}
