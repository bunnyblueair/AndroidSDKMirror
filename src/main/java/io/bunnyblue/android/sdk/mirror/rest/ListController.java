/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror.rest;

import io.bunnyblue.android.sdk.mirror.SyncRepoConfig;
import io.bunnyblue.android.sdk.mirror.localFile.AndroidFile;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author bunnyblue
 */
@Controller
public class ListController {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    static int M = 1024 * 1024 * 8;

    @RequestMapping("/android/repository")
    public String listFile(Map<String, Object> model) {
        List<AndroidFile> files = new ArrayList<>();

        File file = new File(SyncRepoConfig.REPO_FOLDER);

        for (File file1 : FileUtils.listFiles(file, null, true)) {
            AndroidFile androidFile = new AndroidFile();
            androidFile.name = file1.getName();
            androidFile.date = simpleDateFormat.format(new Date(file1.lastModified()));
            androidFile.size = file1.length() / M + " MB";
            androidFile.url = "android/repository" + file1.getAbsolutePath().replaceAll(file.getAbsolutePath(), "");
            files.add(androidFile);

        }
        model.put("message", "hello");
        model.put("androidFiles", files);
        return "repository";

    }

}
