package dev.adi.poc.rotarydemo.helper;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.Toast;

import java.net.URISyntaxException;

public class FileHelper {

    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getPath2(Context context, Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(column_index);
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        cursor.close();
        return s;
    }

    public static String getPath3(Context context, Uri uri) {
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(column_index);
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        cursor.close();
        return s;
    }

    public static void dumpImageMetaData(Context context, Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null, null);

        try {
            if (cursor != null && cursor.moveToFirst()) {
//                String displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                Toast.makeText(context, displayName, Toast.LENGTH_SHORT).show();

                int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
                String size = null;

                if (!cursor.isNull(sizeIndex)) {
                    size = cursor.getString(sizeIndex);
                } else {
                    size = "Unknown";
                }

                Toast.makeText(context, size, Toast.LENGTH_SHORT).show();
            }
        } finally {
            cursor.close();
        }
    }
}
