/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author bunnyblue
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(Map<String, Object> model, HttpServletRequest request) {

        try {
            File last = new File(SyncRepoConfig.STUDIO_FOLDER, "last.txt");
            String dmg = FileUtils.readFileToString(last);
            model.put("dmgUrl", "/android/repository/" + dmg);
            model.put("proxyUrl", "export SDK_TEST_BASE_URL=" + request.getRequestURL() + "android/repository/");
            model.put("standaloneUrl", getStandUrl(request));
        } catch (IOException e) {
            e.printStackTrace();
            model.put("proxyUrl", "export SDK_TEST_BASE_URL=" + request.getRequestURL() + "android/repository/");
            model.put("standaloneUrl", getStandUrl(request));
        }

        return "index";
    }

    public String getStandUrl(HttpServletRequest request) {
        String host = request.getRequestURL().toString();
        host = host.substring(host.indexOf(":") + 3, host.lastIndexOf(":"));
        // host=host.substring(host.lastIndexOf(":"));
        return String.format("tools/bin/sdkmanager --no_https --proxy=http --proxy_host=%s --proxy_port=80 \"patcher;v4\" \"extras;android;m2repository\" \"extras;google;m2repository\" emulator \"build-tools;25.0.3\" \"platforms;android-25\" platform-tools tools \"sources;android-25\"", host);
    }

}
