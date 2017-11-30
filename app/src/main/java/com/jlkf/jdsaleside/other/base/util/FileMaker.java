package com.jlkf.jdsaleside.other.base.util;

import android.os.Environment;
import android.util.SparseArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import timber.log.Timber;

public class FileMaker {

    private static FileMaker instance;
    private String sdPath;
    private String mainPath = "/App/";
    private String autoCache = mainPath + "/autoCache/";

    public static final int TYPE_EXIST = 2;
    public static final int TYPE_FAILED = 3;
    public static final int TYPE_SUCCESS = 1;

    private SparseArray<String> paths;

    private FileMaker() {
        sdPath = getSdPath();
        mainPath = sdPath + mainPath;
        paths = new SparseArray<>();
    }

    public static FileMaker getInstance() {
        if (instance == null) {
            synchronized (FileMaker.class) {
                if (instance == null) {
                    instance = new FileMaker();
                }
            }
        }
        return instance;
    }

    public void setMainPath(String mainName) {
        this.mainPath = sdPath + "/" + mainName;
        this.autoCache = "cache";
        createFolder(10000, autoCache);
    }

    public String getCachePath() {
        return this.mainPath + "/" + autoCache + "/";
    }

    public int createFolder(int key, String name) {
        name = name.replaceAll("/", "");
        File file = new File(mainPath + "/" + name + "/");
        paths.append(key, file.getAbsolutePath());
        if (!file.exists()) {
            if (file.mkdirs()) {
                return TYPE_SUCCESS;
            } else {
                return TYPE_FAILED;
            }
        } else {
            return TYPE_EXIST;
        }
    }

    public String getPath(int key) {
        if (paths.indexOfKey(key) != -1) {
            return paths.get(key) + "/";
        }
        return null;
    }

    public boolean setCache(String key, String value) {
        File file = new File(getCachePath() + key + ".auto");
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    return false;
                }
            }
            FileOutputStream fs = new FileOutputStream(file);
            fs.write(value.getBytes());
            fs.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String getCache(String key) {
        String res = null;
        File file = new File(getCachePath() + key + ".auto");
        try {
            Timber.tag("sss");
            Timber.e("File->" + getCachePath() + key + ".auto");
            FileInputStream fin = new FileInputStream(file);
            int length = fin.available();
            byte[] buffer = new byte[length];
            fin.read(buffer);
            res = new String(buffer);
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public String getSdPath() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getPath();
        }
        return Environment.getDataDirectory().getPath();
    }
}
