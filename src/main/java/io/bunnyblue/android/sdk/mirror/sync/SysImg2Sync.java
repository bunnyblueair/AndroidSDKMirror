/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror.sync;

/**
 * @author bunnyblue
 */
public class SysImg2Sync extends Repository2Sync {
    public SysImg2Sync(String host, String middle, String xmlName) {
        super(host, "sys-img/android", xmlName);
    }
}
