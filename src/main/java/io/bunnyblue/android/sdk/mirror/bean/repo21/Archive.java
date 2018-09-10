
/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror.bean.repo21;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Archive {

    @Expose
    private Complete complete;
    @SerializedName("host-os")
    private String hostOs;
    @Expose
    private List<Patch> patches;

    public Complete getComplete() {
        return complete;
    }

    public void setComplete(Complete complete) {
        this.complete = complete;
    }

    public String getHostOs() {
        return hostOs;
    }

    public void setHostOs(String hostOs) {
        this.hostOs = hostOs;
    }

    public List<Patch> getPatches() {
        return patches;
    }

    public void setPatches(List<Patch> patches) {
        this.patches = patches;
    }

}
