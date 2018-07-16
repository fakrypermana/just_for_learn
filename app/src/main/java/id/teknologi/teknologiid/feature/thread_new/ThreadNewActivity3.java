package id.teknologi.teknologiid.feature.thread_new;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.teknologi.teknologiid.Manifest;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.model.Thread;
import id.teknologi.teknologiid.model.Topic;
import id.teknologi.teknologiid.network.ApiService;
import id.teknologi.teknologiid.network.DataManager;
import jp.wasabeef.richeditor.RichEditor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThreadNewActivity3 extends AppCompatActivity{

    public static final int REQUEST_CODE_CAMERA = 300;
    public static final int REQUEST_CODE_GALLERY = 200;
    public static final int PERMISSION_REQUEST = 100;

    private String [] items = {"Camera","Gallery"};
    private String pathPhoto;
    private String pathCamera;
    private Bitmap mphoto;
    private boolean checker;
    private File tempFile = null;
    private Uri uriCamera = null;
    //Editor
    private RichEditor mEditor;

    //MultiChoice
    TextView mItemSelected;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();

    //Inisiasi
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.btn_topik)
    Button btnTopik;
    @BindView(R.id.b_create)
    Button bCreate;
    @BindView(R.id.iv_browsePhoto)
    ImageView ivBrowsePhoto;
    @BindView(R.id.btn_take_image)
    Button btnLoadImage;
    @BindView(R.id.textview_image_path)
    TextView tvPath;
    @BindView(R.id.topik)
            TextView topik;


    ApiService mApiService;

    ProgressDialog progressDialog;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_new3);
        ButterKnife.bind(this);
        mContext = ThreadNewActivity3.this;
        mApiService = DataManager.getApiService();
        progressDialog = new ProgressDialog(ThreadNewActivity3.this);

        //Editor
        mEditor = (RichEditor) findViewById(R.id.editor);
        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(16);
        mEditor.setEditorFontColor(Color.BLACK);
        mEditor.setPadding(10, 10, 10, 10);
        mEditor.setPlaceholder("Tulis Konten...");

        //klik upload button
        btnLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String title = etTitle.getText().toString();
//                String post = etTitle.getText().toString();
                showProgressDialog();
                openImage();

            }
        });

        //membuat thread
        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String title = etTitle.getText().toString();
                String post = mEditor.getHtml().toString();
                Log.d("checker",""+checker);
                Log.d("cari title", title.toString());
                Log.d("cari post", post.toString());
                if(checker==true){
                    uploadImage(pathCamera,title,post,"1");
                }else if(checker==false){
                    uploadImage(pathPhoto,title,post,"1");
                }
            }
        });

        //klik fungsi editor
        findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.undo();
            }
        });

        findViewById(R.id.action_redo).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.redo();
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBold();
            }
        });

        findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setItalic();
            }
        });

        findViewById(R.id.action_subscript).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setSubscript();
            }
        });

        findViewById(R.id.action_superscript).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setSuperscript();
            }
        });

        findViewById(R.id.action_strikethrough).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setStrikeThrough();
            }
        });

        findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setUnderline();
            }
        });

        findViewById(R.id.action_heading1).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(1);
            }
        });

        findViewById(R.id.action_heading2).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(2);
            }
        });

        findViewById(R.id.action_heading3).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(3);
            }
        });

        findViewById(R.id.action_heading4).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(4);
            }
        });

        findViewById(R.id.action_heading5).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(5);
            }
        });

        findViewById(R.id.action_heading6).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(6);
            }
        });

        findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
            private boolean isChanged;

            @Override public void onClick(View v) {
                mEditor.setTextColor(isChanged ? Color.BLACK : Color.RED);
                isChanged = !isChanged;
            }
        });

        findViewById(R.id.action_bg_color).setOnClickListener(new View.OnClickListener() {
            private boolean isChanged;

            @Override public void onClick(View v) {
                mEditor.setTextBackgroundColor(isChanged ? Color.TRANSPARENT : Color.YELLOW);
                isChanged = !isChanged;
            }
        });

        findViewById(R.id.action_indent).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setIndent();
            }
        });

        findViewById(R.id.action_outdent).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setOutdent();
            }
        });

        findViewById(R.id.action_align_left).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignLeft();
            }
        });

        findViewById(R.id.action_align_center).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignCenter();
            }
        });

        findViewById(R.id.action_align_right).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignRight();
            }
        });

        findViewById(R.id.action_blockquote).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBlockquote();
            }
        });

        findViewById(R.id.action_insert_bullets).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBullets();
            }
        });

        findViewById(R.id.action_insert_numbers).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setNumbers();
            }
        });

        findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.insertImage("http://www.1honeywan.com/dachshund/image/7.21/7.21_3_thumb.JPG",
                        "dachshund");
            }
        });

        findViewById(R.id.action_insert_link).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.insertLink("https://github.com/wasabeef", "wasabeef");
            }
        });
        findViewById(R.id.action_insert_checkbox).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.insertTodo();
            }
        });



        //MultiChoice

        listItems = getResources().getStringArray(R.array.country_arrays);
        checkedItems = new boolean[listItems.length];

        btnTopik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ThreadNewActivity3.this);
                mBuilder.setTitle("Topik");
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                      if (isChecked) {
                          if (!mUserItems.contains(position)) {
                              mUserItems.add(position);
                          }
                      }else if(mUserItems.contains(position)){
                              mUserItems.remove(position);
                          }
                      }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("pilih", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String item = "";
                        for (int i = 0; i < mUserItems.size(); i++){
                            item = item + listItems[mUserItems.get(i)];
                            if(i != mUserItems.size() -1){
                                item = item + ", ";

                            }
                        }
                        mItemSelected.setText(item);
                    }
                });

                mBuilder.setNegativeButton("batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton("Hapus Pilihan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedItems.length; i++){
                            checkedItems[i]=false;
                            mUserItems.clear();
                            mItemSelected.setText("");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
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
                    checker=true;
                    openCamera();
                }else if(items[i].equals("Gallery")){
                    checker=false;
                    openGalery();
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void openGalery(){
        try {
            if (ContextCompat.checkSelfPermission(ThreadNewActivity3.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ThreadNewActivity3.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
            } else {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQUEST_CODE_GALLERY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void openCamera(){
        try {
            if (ContextCompat.checkSelfPermission(ThreadNewActivity3.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ThreadNewActivity3.this, new String[]{android.Manifest.permission.CAMERA}, PERMISSION_REQUEST);
            } else {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    try {

                        tempFile = createImageFile();
                        Log.i("Mayank",tempFile.getAbsolutePath());

                        // Continue only if the File was successfully created
                        if (tempFile != null) {
                            Uri photoURI = FileProvider.getUriForFile(this, "id.teknologi.teknologiid.fileprovider",tempFile);
                            Log.d("uri",photoURI.toString());
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            startActivityForResult(cameraIntent, REQUEST_CODE_CAMERA);
                        }
                    } catch (Exception ex) {
                        // Error occurred while creating the File
                        Toast.makeText(getBaseContext(),ex.getMessage().toString(),Toast.LENGTH_SHORT).show();
                        Log.e("camera error",ex.getMessage().toString());
                    }


                }else{
                    Toast.makeText(getBaseContext(),"NUll",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case REQUEST_CODE_GALLERY:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
                } else {
                    Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
            Bitmap myBitmap = BitmapFactory.decodeFile(pathCamera);
            Bitmap rescale = myBitmap.createScaledBitmap(myBitmap,400,320,true);
            ivBrowsePhoto.setImageBitmap(myBitmap);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            myBitmap.compress(Bitmap.CompressFormat.JPEG, 30 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();

            //write the bytes in file
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(tempFile.getAbsolutePath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fos.write(bitmapdata);
                fos.close();
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            galleryAddPic();
            Log.d("pathCamera",pathCamera);
            progressDialog.hide();
        }else if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            pathPhoto = cursor.getString(columnIndex);
            Log.d("pathPhoto",pathPhoto);
            Bitmap myBitmap = BitmapFactory.decodeFile(pathPhoto);
            ivBrowsePhoto.setImageBitmap(myBitmap);
            cursor.close();

            Glide.with(mContext)
                    .load(selectedImage)
                    .into(ivBrowsePhoto);
            progressDialog.hide();
        }
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }

    private void showProgressDialog(){
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    private File createImageFile() throws IOException {

        // Create an image file name
        String root = Environment.getExternalStorageDirectory().toString();
        File file = new File(root+"test");
        file.mkdir();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        pathCamera = image.getAbsolutePath();
        //Convert bitmap to byte array

        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(pathCamera);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    public void uploadImage(String path, String tittle, String post, String id_topic) {
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("title", createPartFromString(tittle));
        map.put("post", createPartFromString(post));
        map.put("id_topic[0]", createPartFromString(id_topic));

        File file = new File(path);
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("browsePhoto", file.getName(), reqFile);
        Call<ResponseBody> call = mApiService.postingTread(body,map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.hide();
                if(response.isSuccessful()){
                    try{
                        String returnBodyText = response.body().string();
                        JSONObject jsonRESULTS = new JSONObject(returnBodyText);
                        Log.d("response",jsonRESULTS.toString());
                        if (jsonRESULTS.getString("status").equals("success")){
                            Toast.makeText(mContext, "Upload Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, "upload Failure", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.hide();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(mContext,"Connection Error",Toast.LENGTH_SHORT).show();
                    progressDialog.hide();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.hide();
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }



}

