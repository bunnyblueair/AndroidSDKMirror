/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror.sync;

import okhttp3.OkHttpClient;

/**
 * @author bunnyblue
 */
public abstract class BaseSync<T> {
    String  host;

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    String middle;
    String xmlName;
    OkHttpClient okHttpClient;
    public BaseSync(String host, String middle, String xmlName) {
        this.host = host;
        this.middle = middle;
        this.xmlName = xmlName;
    }


    public abstract T syncXml2Pojo();
    public abstract void downloadFile();
    public abstract void cleanupOldFile();


}
