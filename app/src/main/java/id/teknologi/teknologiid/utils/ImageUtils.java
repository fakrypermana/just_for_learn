package id.teknologi.teknologiid.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by galihgasur on 10/14/17.
 */

public class ImageUtils {
    public static Bitmap getScaledBitmap(String filePath, int maxSize) {
        try {
            //Decode image size
            BitmapFactory.Options option = new BitmapFactory.Options();
            option.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, option);

            //Find the correct scale value. It should be the power of 2.
            int scale=1;
            int w = option.outWidth;
            int h = option.outHeight;
            while (w/scale/2>= maxSize && h/scale/2> maxSize)
                scale*=2;

            //Decode with inSampleSize
            BitmapFactory.Options option2 = new BitmapFactory.Options();
            option2.inSampleSize = scale;

            option.inJustDecodeBounds = false;

            return BitmapFactory.decodeFile(filePath, option2);

        } catch (OutOfMemoryError e) {
            e.getMessage();
        }
        return null;
    }

    /**
     * Downloading push notification image before displaying it in
     * the notification tray
     */
    public static Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File saveFile(Bitmap bitmap) {
        FileOutputStream out = null;
        File filePath = getOutputMediaFile();

        try {
            if (filePath != null) {
                out = new FileOutputStream(filePath);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 95, out);
            }
            if (out != null) {
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator
                + "IMG_" + timeStamp + ".jpg");
    }

    public static String IMAGE_DIRECTORY_NAME = "Ottoma";
}
