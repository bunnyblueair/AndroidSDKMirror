
/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror.bean.repo21;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class BasedOn {

    @Expose
    private String major;
    @Expose
    private String micro;
    @Expose
    private String minor;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMicro() {
        return micro;
    }

    public void setMicro(String micro) {
        this.micro = micro;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

}
