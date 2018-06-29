package id.teknologi.teknologiid.feature.thread_new;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;

import id.teknologi.teknologiid.R;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class ThreadNewActivity3 extends AppCompatActivity {

    public static final int REQUEST_CODE_CAMERA = 0012;
    public static final int REQUEST_CODE_GALLERY = 0013;

    private Button btnLoadImage;
    private ImageView ivImage;
    private TextView tvPath;
    private String [] items = {"Camera","Gallery"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_new3);

        btnLoadImage = (Button) findViewById(R.id.btn_take_image);
        ivImage = (ImageView) findViewById(R.id.image_view_image);
        tvPath = (TextView) findViewById(R.id.textview_image_path);

        btnLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });
    }

    /**
     * this method used to open image directory or open from camera
     */
    private void openImage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Options");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(items[i].equals("Camera")){
                    EasyImage.openCamera(ThreadNewActivity3.this,REQUEST_CODE_CAMERA);
                }else if(items[i].equals("Gallery")){
                    EasyImage.openGallery(ThreadNewActivity3.this, REQUEST_CODE_GALLERY);
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                switch (type){
                    case REQUEST_CODE_CAMERA:
                        Glide.with(ThreadNewActivity3.this)
                                .load(imageFile)
                                .into(ivImage);
                        tvPath.setText(imageFile.getAbsolutePath());
                        break;
                    case REQUEST_CODE_GALLERY:
                        Glide.with(ThreadNewActivity3.this)
                                .load(imageFile)
                                .into(ivImage);
                        tvPath.setText(imageFile.getAbsolutePath());
                        break;
                }
            }
        });
    }
}

