/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror.sync;

import io.bunnyblue.android.sdk.mirror.SyncRepoConfig;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * @author bunnyblue
 */
@Component
public class SyncSDKInit {
    @Autowired
    OkHttpClient okHttpClient;
    public void doSyncAll() {
        Iterator<String> iterator = SyncRepoConfig.config.keySet().iterator();
        while (iterator.hasNext()) {

            String key = iterator.next();
            String value = SyncRepoConfig.config.get(key);
            System.out.println("sync " + key + " " + value);
            int lastIndex = value.lastIndexOf("/");
            String middle = null;
            if (SyncRepoConfig.HOST.length() >= lastIndex) {

            } else {
                middle = value.substring(SyncRepoConfig.HOST.length(), lastIndex);
            }
            String xmlName = value.substring(lastIndex+1);
            Repository2Sync repository2Sync=new Repository2Sync(SyncRepoConfig.HOST, middle,xmlName );
            repository2Sync.setOkHttpClient(okHttpClient);
            repository2Sync.syncXml2Pojo();
            repository2Sync.downloadFile();
            System.err.println(middle + " " + xmlName);

        }
    }
}
