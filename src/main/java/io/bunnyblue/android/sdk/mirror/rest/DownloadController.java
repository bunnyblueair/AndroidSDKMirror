/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror.rest;

import io.bunnyblue.android.sdk.mirror.SyncRepoConfig;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author bunnyblue
 */
@RestController
public class DownloadController {

    @RequestMapping("/android/repository/**")
    public ResponseEntity<InputStreamResource> foo(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
        String urlTail = new AntPathMatcher()
                .extractPathWithinPattern("/android/repository/**", request.getRequestURI());
        File file = new File(SyncRepoConfig.REPO_FOLDER, urlTail.replace("android/repository/", ""));
        if (file.getName().endsWith("dmg")) {
            try {
                file = new File(SyncRepoConfig.STUDIO_FOLDER, FileUtils.readFileToString(new File(SyncRepoConfig.STUDIO_FOLDER, "last.txt")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        MediaType mediaType = MediaType.APPLICATION_XML;
        if (file.getName().endsWith("xml")) {
            System.err.println(file.getAbsolutePath());


        } else {
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }


        return ResponseEntity
                .ok()
                .headers(headers)

                .contentLength(file.length())
                .contentType(mediaType)
                .body(new InputStreamResource(new FileInputStream(file)));
    }
    // return new File(file,urlTail);

}
