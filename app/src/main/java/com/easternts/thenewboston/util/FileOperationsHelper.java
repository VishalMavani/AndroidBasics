package com.easternts.thenewboston.util;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Bansi on 1/29/2017.
 */
public class FileOperationsHelper {
    private static FileOperationsHelper ourInstance;

//    Lazy Loading
    public static FileOperationsHelper getInstance() {
        if(ourInstance == null)
            ourInstance = new FileOperationsHelper();
        return ourInstance;
    }

    private FileOperationsHelper() {
    }

//    For Internal Storage
    public String readInternalFile(Context context, String fileName) throws IOException {
        FileInputStream fileInputStream = context.openFileInput(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
        StringBuffer buffer = new StringBuffer();
        String line;
        if ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    public void saveInternalFile(Context context, String fileName, String data) throws IOException {
        FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        fileOutputStream.write(data.getBytes());
        fileOutputStream.close();
    }

//    For External Storage
    public String readExternalFile(String fileName) throws IOException {
        if (isExternalStorageReadable()) {
            File file = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)), fileName);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
                StringBuffer buffer = new StringBuffer();
                String line;
                if ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                return buffer.toString();
            }
        }
        return null;
    }

    public void saveExternalFile(String fileName, String data) throws IOException {
        if (isExternalStorageWriteable()) {
            File file = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)), fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }
    }

    public Boolean deleteExternalFile(String fileName) {
        File file = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)), fileName);
        return file.delete();
    }
    private Boolean isExternalStorageReadable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())) {
            return true;
        }
        return false;
    }

    private Boolean isExternalStorageWriteable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        }
        return false;
    }
}
