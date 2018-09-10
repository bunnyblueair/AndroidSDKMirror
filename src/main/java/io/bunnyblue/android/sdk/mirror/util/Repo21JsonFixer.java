/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror.util;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author bunnyblue
 */
public class Repo21JsonFixer {
    public static boolean fixJson(JSONObject jsonObject) {
        if (!jsonObject.has("remotePackage")) {
            return false;
        }
        Object channel = jsonObject.opt("channel");
        if (channel instanceof JSONArray) {
            jsonObject.remove("channel");
            jsonObject.put("channels", channel);
        }
        if (jsonObject.get("remotePackage") instanceof JSONObject) {
            upperArray(jsonObject, "remotePackage", "remotePackage");
        }
        JSONArray remotePackages = jsonObject.optJSONArray("remotePackage");
        for (int i = 0; i < remotePackages.length(); i++) {
            JSONObject pkg = remotePackages.optJSONObject(i);
            Object obj = pkg.opt("archives");
            if (obj instanceof JSONArray) {

            } else {
                Object archive = pkg.getJSONObject("archives").opt("archive");
                pkg.remove("archives");
                JSONArray archives = new JSONArray();
                if (archive instanceof JSONArray) {
                    archives = (JSONArray) archive;
                } else {
                    archives.put(archive);
                }

                for (int j = 0; j < archives.length(); j++) {
                    JSONObject archiveObj = archives.optJSONObject(j);
                    upperArray(archiveObj, "patches", "patch");

                }
                pkg.put("archives", archives);
                System.out.println(pkg.toString());
            }
        }
        return true;
    }

    public static void upperArray(JSONObject pkg, String keys, String key) {
        if (!pkg.has(keys)) {
            return;
        }
        Object archive = null;
        if (keys.equalsIgnoreCase(key)) {
            archive = pkg.getJSONObject(keys);
        } else {
            archive = pkg.getJSONObject(keys).opt(key);
        }
        pkg.remove(keys);
        JSONArray archives = new JSONArray();
        if (archive instanceof JSONArray) {
            archives = (JSONArray) archive;
        } else {
            archives.put(archive);
        }
        pkg.put(keys, archives);
    }
}
