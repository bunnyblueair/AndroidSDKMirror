/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror;

import org.apache.commons.io.input.AutoCloseInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * true	Android Repository	https://dl.google.com/android/repository/repository2-1.xml
 * true	Android System Images	https://dl.google.com/android/repository/sys-img/android/sys-img2-1.xml
 * true	Android TV System Images	https://dl.google.com/android/repository/sys-img/android-tv/sys-img2-1.xml
 * true	Android Wear System Images	https://dl.google.com/android/repository/sys-img/android-wear/sys-img2-1.xml
 * true	Android Wear for China System Images	https://dl.google.com/android/repository/sys-img/android-wear-cn/sys-img2-1.xml
 * true	Glass Development Kit, Google Inc.	https://dl.google.com/android/repository/glass/addon2-1.xml
 * true	Google API add-on System Images	https://dl.google.com/android/repository/sys-img/google_apis/sys-img2-1.xml
 * true	Google API with Playstore System Images	https://dl.google.com/android/repository/sys-img/google_apis_playstore/sys-img2-1.xml
 * true	Google Inc.	https://dl.google.com/android/repository/addon2-1.xml
 * true	Intel HAXM	https://dl.google.com/android/repository/extras/intel/addon2-1.xml
 *
 * @author bunnyblue
 */
public class SyncRepoConfig {
    public static final String CONFIG_FILE = System.getProperty("user.home")+File.separator+".android_sdk_sync_mirror";
    public static final String HOST = "https://dl.google.com/android/repository/";
    public static String REPO_FOLDER = "/data/sdkmirror/android/repository";
    public static String STUDIO_FOLDER = "/data/sdkmirror/android/studio";
    //  public static final String REPO_FOLDER = "/Users/bunnyblue/mywork/mirrsdk/android/repository";
    public static HashMap<String, String> config = new HashMap<>();

    static {
        //ys-img/google_apis_playstore/sys-img2-1.xml
        //ys-img/google_apis_playstore/sys-img2-1.xml

        config.put("Android Repository addons_list", "https://dl.google.com/android/repository/addons_list-1.xml");
        config.put("Android Repository", "https://dl.google.com/android/repository/repository2-1.xml");
        config.put("Android Repository addons 3", "https://dl.google.com/android/repository/addons_list-3.xml");

        config.put("Android System Images", "https://dl.google.com/android/repository/sys-img/android/sys-img2-1.xml");
        config.put("Android TV System Images", "https://dl.google.com/android/repository/sys-img/android-tv/sys-img2-1.xml");
        config.put("Android Wear System Images", "https://dl.google.com/android/repository/sys-img/android-wear/sys-img2-1.xml");
        config.put("Android Wear for China System Images", "https://dl.google.com/android/repository/sys-img/android-wear-cn/sys-img2-1.xml");
        config.put("Google API with Playstore System Images", "https://dl.google.com/android/repository/sys-img/google_apis_playstore/sys-img2-1.xml");
        config.put("Glass Development Kit, Google Inc.", "https://dl.google.com/android/repository/glass/addon2-1.xml");
        config.put("Google API add-on System Images", "https://dl.google.com/android/repository/sys-img/google_apis/sys-img2-1.xml");
        config.put("Google Inc.", "https://dl.google.com/android/repository/addon2-1.xml");
        config.put("Intel HAXM", "https://dl.google.com/android/repository/extras/intel/addon2-1.xml");

    }

    public static void init() {
        File config = new File(CONFIG_FILE);
        if (config.exists()) {
            Properties properties = new Properties();
            try {
                properties.load(new AutoCloseInputStream(new FileInputStream(config)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            REPO_FOLDER = properties.getProperty("REPO_FOLDER", "/data/sdkmirror/android/repository");
            STUDIO_FOLDER = properties.getProperty("STUDIO_FOLDER", "/data/sdkmirror/android/studio");

        } else {
            REPO_FOLDER = "/data/sdkmirror/android/repository";
            STUDIO_FOLDER = "/data/sdkmirror/android/studio";
        }
        System.out.println("load config success");

    }
/**
 * 请使用绝对路径
 */
    public static String updateConfig(String rootPath) throws IOException {
        File root = new File(rootPath);
        root.mkdirs();
        if (!root.canWrite()) {
            return rootPath + " can't write!";
        }
        File config = new File(CONFIG_FILE);
        if (!config.exists()) {
            config.createNewFile();
        }

        Properties properties = new Properties();
        try {
            properties.load(new AutoCloseInputStream(new FileInputStream(config)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.setProperty("REPO_FOLDER", new File(root, "android/repository").getAbsolutePath());
        properties.setProperty("STUDIO_FOLDER", new File(root, "android/studio").getAbsolutePath());
        properties.store(new FileOutputStream(config), "android sdk sync mirror");
        System.out.println("update config success");

        init();

        return "update success";
    }

    static {
        init();
    }
}
