/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror.sync;

import io.bunnyblue.android.sdk.mirror.SyncRepoConfig;
import io.bunnyblue.android.sdk.mirror.util.FileChecker;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author bunnyblue
 */
@Component
public class StudioSync {
    public String studio=  "https://developer.android.google.cn/studio/";
    OkHttpClient okHttpClient;
    public void syncStudio(){
        if (okHttpClient == null) {
            okHttpClient=   new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(10, TimeUnit.MINUTES)
                    .build();
            //   okHttpClient = new OkHttpClient();
        }

        File foler=new File(SyncRepoConfig.STUDIO_FOLDER);

        foler.mkdirs();
        try {
        Document document= Jsoup.connect(studio).get();
            Elements downloads=    document.getElementsByClass("agreed");
            for (int i = 0; i < downloads.size(); i++) {
                Element element=downloads.get(i);
             String downloadUrl=   element.child(0).attr("href");
             if (downloadUrl.endsWith("dmg"))
             {

                 String fileName=downloadUrl.substring(downloadUrl.lastIndexOf("/")+1);
                 File dmg=new File(foler,fileName);
                 File last=new File(foler,"last.txt");
                 if (dmg.exists()){
                     return;
                 }
                 downloadFile(downloadUrl,dmg);
                 FileUtils.writeStringToFile(last, fileName,false);
                 System.out.println(downloadUrl);

             }
            }
            //.get(1).childNode(0).
     // Elements downloads= document.getElementsByClass("download");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void downloadFile(String url, File targetFile) {


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

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
