/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror.util;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author bunnyblue
 */
public class FileChecker {

    public static String calcSHA1(File file)  {
        if (!file.exists()){
            return "";
        }

        MessageDigest sha1 = null;
        try {
            sha1 = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try (InputStream input = new FileInputStream(file)) {

            byte[] buffer = new byte[1024*1024];
            int len = input.read(buffer);

            while (len != -1) {
                sha1.update(buffer, 0, len);
                len = input.read(buffer);
            }

            return new HexBinaryAdapter().marshal(sha1.digest());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
