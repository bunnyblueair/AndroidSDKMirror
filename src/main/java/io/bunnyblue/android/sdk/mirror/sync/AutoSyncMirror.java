/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror.sync;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.expression.Dates;

/**
 * @author bunnyblue
 */
@Component
public class AutoSyncMirror {
    @Scheduled(cron="0 15 1 * * ?")
    public void cronJob(){
        System.out.println("AutoSyncMirror task is working....");
        SyncSDKInit syncSDKInit=new SyncSDKInit();
        syncSDKInit.doSyncAll();
        System.out.println("AutoSyncMirror task is sync end....");
    }

    @Scheduled(cron="0 15 3 * * ?")
    public void syncStudio(){
        System.out.println("StudioSync task is working....");
        StudioSync studioSync=new StudioSync();
        studioSync.syncStudio();
        System.out.println("StudioSync task is sync end....");
    }

}
