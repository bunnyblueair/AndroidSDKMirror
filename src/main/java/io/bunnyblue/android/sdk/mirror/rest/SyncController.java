/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror.rest;

import io.bunnyblue.android.sdk.mirror.SyncRepoConfig;
import io.bunnyblue.android.sdk.mirror.sync.StudioSync;
import io.bunnyblue.android.sdk.mirror.sync.SyncSDKInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

import static io.bunnyblue.android.sdk.mirror.SyncRepoConfig.CONFIG_FILE;

/**
 * @author bunnyblue
 */
@RestController
@RequestMapping("/sync")
public class SyncController {
    @Autowired
    SyncSDKInit syncSDKInit;
    @Autowired
    StudioSync studioSync;
    @RequestMapping("/yes")
    public String syncDoing(){
        File repoFolder = new File(SyncRepoConfig.REPO_FOLDER);
        if (repoFolder.exists()&&repoFolder.isDirectory()){
            return String.format("当前已经存在配置，定时任务自动处理，请勿手动调用  root at %s", repoFolder.getAbsolutePath());
        }
        syncSDKInit.doSyncAll();;
        studioSync.syncStudio();

        return "正在处理中。。需要同步60G+的文件，请耐心等待，不要";

    }
    @RequestMapping("/config")
    public String updateSyncConfig(String  local){
        if (!local.startsWith("/")){
            return "请使用绝对路径";
        }
        File config = new File(CONFIG_FILE);
        if (config.exists()) {
            return String.format("plz remove config file at %s", config.getAbsolutePath());
        }
        try {
          return   SyncRepoConfig.updateConfig(local);
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }

//        File repoFolder = new File(SyncRepoConfig.REPO_FOLDER);
//        if (repoFolder.exists()&&repoFolder.isDirectory()){
//            return String.format("当前已经存在配置，定时任务自动处理，请勿手动调用  root at %s", repoFolder.getAbsolutePath());
//        }
//        syncSDKInit.doSyncAll();;
//        studioSync.syncStudio();

       // return "正在处理中。。需要同步60G+的文件，请耐心等待，不要";

    }
//    @RequestMapping("/studioSync")
//    public String syncStudio(){
//        File repoFolder = new File(SyncRepoConfig.STUDIO_FOLDER);
//        studioSync.syncStudio();
//        return "doing";
//
//    }
}
