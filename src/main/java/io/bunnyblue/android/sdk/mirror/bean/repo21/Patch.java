
/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror.bean.repo21;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Patch {

//    @SerializedName("based-on")
//    private BasedOn basedOn;
   // @Expose
    private String checksum;
   // @Expose
    private String size;
 //   @Expose
    private String url;

//    public BasedOn getBasedOn() {
//        return basedOn;
//    }
//
//    public void setBasedOn(BasedOn basedOn) {
//        this.basedOn = basedOn;
//    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
