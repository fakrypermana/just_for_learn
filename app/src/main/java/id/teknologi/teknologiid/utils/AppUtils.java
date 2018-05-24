package id.teknologi.teknologiid.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GoogleApiAvailability;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

import id.teknologi.teknologiid.R;

/**
 * Created by galihgasur on 10/1/17.
 */

public class AppUtils {
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public static LinearLayoutManager defaultLinearLayoutManager(Context context){
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }
    public static LinearLayoutManager horizontalLinearLayoutManager(Context context){
        return new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
    }
    public static boolean isEmailValid(String email){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.matches(emailPattern) && email.length() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public static String getRate(double aDouble){
        return new DecimalFormat("##.#").format(aDouble);
    }
    public static String getRealPath(Context context, Uri uri){
        String realPath;
        if (Build.VERSION.SDK_INT < 19)
            realPath = RealPathUtils.getPath(context, uri);
            // SDK > 19 (Android 4.4)
        else
            realPath = RealPathUtils.getPath(context, uri);
        return realPath;
    }
    public static boolean isNullOrEmpty(String string){
        if (string == null){
            return true;
        } else if (string.isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    public static void getFileData(Intent data, Context context, FileChooserInterface fileChooserInterface){
        Uri mediaUri = data.getData();
        if (mediaUri != null) {
            try{
                String realPath = AppUtils.getRealPath(context, mediaUri);
                File file;
                if (AppUtils.isNullOrEmpty(realPath)){
                    InputStream inputStream = context.getContentResolver().openInputStream(mediaUri);
                    byte[] buffer = new byte[inputStream.available()];
                    inputStream.read(buffer);
                    String filename=mediaUri.getPath().substring(mediaUri.getPath().lastIndexOf("/")+1);
                    file = new File(context.getCacheDir(),filename);
                    OutputStream outStream = new FileOutputStream(file);
                    outStream.write(buffer);
                } else {
                    file = new File(realPath);
                }
                Bitmap bitmap = ImageUtils.getScaledBitmap(file.getPath(), 720);
                fileChooserInterface.getFileSuccess(ImageUtils.saveFile(bitmap));
            } catch(IOException exception){
                exception.printStackTrace();
            }
        }else {
            Log.i("onActivityResult","no data parcelable uri");
            Toast.makeText(context, R.string.please_choose_other_image,Toast.LENGTH_SHORT).show();
        }
    }

    public static void performFileSearch(Activity activity, int reqCode) {
        if (checkReadWritePermission(activity)){
            Intent intent;
            if (Build.VERSION.SDK_INT >= 19){
                intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/*"});
                Log.d("build v","above kitkat");
            } else {
                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                Log.d("build v","below kitkat");
            }
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            Intent filechooser = Intent.createChooser(intent, activity.getResources().getString(R.string.choose_profile_photo));
            try{
                activity.startActivityForResult(filechooser, reqCode);
            } catch (ActivityNotFoundException e){
                // Potentially direct the user to the Market with a Dialog
                Toast.makeText(activity, "Please install a File Manager.",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }
    private static boolean checkReadWritePermission(Activity activity){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST);
            return false;
        } else {
            return true;
        }
    }
    public static boolean checkLocationPermission(Activity activity){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(activity,Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST);
            return false;
        } else {
            return true;
        }
    }
//    public static boolean checkPlayServices(Activity activity) {
//        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
//        int resultCode = apiAvailability.isGooglePlayServicesAvailable(activity);
//        if (resultCode != ConnectionResult.SUCCESS) {
//            if (apiAvailability.isUserResolvableError(resultCode)) {
//                apiAvailability.getErrorDialog(activity, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
//                        .show();
//            }
//            return false;
//        }
//        return true;
//    }
    public static String getMoneyFormat(Long money){
        DecimalFormat formatter = new DecimalFormat("#,###");
        if (money == 0){
            return "";
        }
        return "Rp " + formatter.format(money);
    }
    private static final int MY_PERMISSIONS_REQUEST = 500;
}
