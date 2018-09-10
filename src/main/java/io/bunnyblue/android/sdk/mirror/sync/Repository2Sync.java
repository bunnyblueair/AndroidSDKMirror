/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror.sync;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.bunnyblue.android.sdk.mirror.SyncRepoConfig;
import io.bunnyblue.android.sdk.mirror.bean.repo21.Archive;
import io.bunnyblue.android.sdk.mirror.bean.repo21.Patch;
import io.bunnyblue.android.sdk.mirror.bean.repo21.RemotePackage;
import io.bunnyblue.android.sdk.mirror.bean.repo21.Repository2;
import io.bunnyblue.android.sdk.mirror.util.ArrayAdapterFactory;
import io.bunnyblue.android.sdk.mirror.util.FileChecker;
import io.bunnyblue.android.sdk.mirror.util.Repo21JsonFixer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

/**
 * @author bunnyblue
 */
public class Repository2Sync extends BaseSync<Repository2> {
    private Repository2 repository2 = null;
    File repoFolder = new File(SyncRepoConfig.REPO_FOLDER);

    public Repository2Sync(String host, String middle, String xmlName) {
        super(host, middle, xmlName);
    }

    public String fetchHtml(String url) {
        if (okHttpClient == null) {
            okHttpClient=   new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(10, TimeUnit.MINUTES)
                    .build();
         //   okHttpClient = new OkHttpClient();
        }

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }

    @Override
    public Repository2 syncXml2Pojo() {
        String xmlUrl = null;
        File configFile = null;
        if (middle == null) {
            xmlUrl = host + xmlName;
            configFile = new File(repoFolder, xmlName);
        } else {
            xmlUrl = host + middle + "/" + xmlName;
            configFile = new File(repoFolder, middle + "/" + xmlName);
        }
        //   configFile.mkdirs();
        //  repoFolder.mkdirs();
        String xmlData = fetchHtml(xmlUrl);
        try {
            FileUtils.write(configFile, xmlData, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //   String xml = null;
        //   xml = FileUtils.readFileToString(new File(xmlData));
       // System.err.println(xmlData);
        JSONObject xmlJSONObj = XML.toJSONObject(xmlData, false);

        Type collectionType = new TypeToken<Repository2>() {
        }.getType();
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).create();
        String objData = xmlJSONObj.length() == 1 ? xmlJSONObj.get(xmlJSONObj.keys().next()).toString() : xmlJSONObj.toString();
        JSONObject jsonObject = new JSONObject(objData);
        if (!Repo21JsonFixer.fixJson(jsonObject)){
            return null;
        }
        repository2 = gson.fromJson(jsonObject.toString(), collectionType);
        return repository2;


    }

    @Override
    public void downloadFile() {
        String dlHostPath = null;
        File configFile = null;
        if (middle == null) {
            dlHostPath = host;
            // configFile=new File(repoFolder,xmlName);
        } else {
            dlHostPath = host + middle + "/";
            // configFile=new File(repoFolder,middle + "/" + xmlName);
        }
        if (repository2 != null) {
            File root = new File(repoFolder.getAbsolutePath());
            if (middle != null) {
                root = new File(repoFolder, middle);
            }
            root.mkdirs();
            for (RemotePackage remotePackage : repository2.getRemotePackage()) {
                if (!remotePackage.getChannelRef().getRef().equalsIgnoreCase("channel-0")) {
                    //only sync stable
                    continue;
                }
                if (remotePackage.isObsolete()) {
                    continue;
                }

                for (Archive archive : remotePackage.getArchives()) {
                    if (archive.getPatches() != null) {
                        for (Patch patch : archive.getPatches()) {
                            File patchFile = new File(root, patch.getUrl());
                            downloadFile(dlHostPath + patch.getUrl(), patchFile, patch.getChecksum());
                        }
                    }
                    File archiveFile = new File(root, archive.getComplete().getUrl());
                    // if (!archive.getComplete().getChecksum().equalsIgnoreCase(FileChecker.calcSHA1(archiveFile))){
                    downloadFile(dlHostPath + archive.getComplete().getUrl(), archiveFile, archive.getComplete().getChecksum());
                    //  }

                    try {
                        archiveFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }

        } else {
            System.err.println("sync error=====");
        }

    }

    @Override
    public void cleanupOldFile() {

    }

    public boolean downloadFile(String url, File targetFile, String hash) {
        if (FileChecker.calcSHA1(targetFile).equalsIgnoreCase(hash)) {
            System.out.println("file is lasted "+targetFile.getAbsolutePath());
            return true;
        }
        if (Boolean.FALSE){
            try {
                targetFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }

        System.err.println(url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            FileUtils.writeByteArrayToFile(targetFile, response.body().bytes(), false);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;


    }
}
