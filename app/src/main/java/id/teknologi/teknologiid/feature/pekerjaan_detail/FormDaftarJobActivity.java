package id.teknologi.teknologiid.feature.pekerjaan_detail;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nbsp.materialfilepicker.MaterialFilePicker;

import id.teknologi.teknologiid.Navigation;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.feature.pekerjaan.PekerjaanActivity;

public class FormDaftarJobActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_daftar_job);

        Button btnAddFileCV = findViewById(R.id.btn_upload_cv);
        Button btnClose = findViewById(R.id.btn_close_dialog);
        EditText inputNamaDaftar = findViewById(R.id.input_nama_daftar_job);

        EditText inputEmailDaftar = findViewById(R.id.input_email_daftar_job);

        EditText inputTeleponDaftar = findViewById(R.id.input_telepon_daftar_job);

        TextView tvFilePath = findViewById(R.id.tv_filepath);


        /**
         * Jika tombol diklik
         */
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormDaftarJobActivity.this, Navigation.class);
                startActivity(intent);
                finish();
            }
        });

        /**
         * permission access file
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1001);
        }

        btnAddFileCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MaterialFilePicker()
                        .withActivity(FormDaftarJobActivity.this)
                        .withRequestCode(1000)
                        .withHiddenFiles(true) // Show hidden files and folders
                        .start();
            }
        });
    }
}
