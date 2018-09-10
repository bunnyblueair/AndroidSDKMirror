
/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror.bean.repo21;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Repository2 {

    @SerializedName("__name")
    private String _Name;
    @SerializedName("_xmlns:common")
    private String _xmlnsCommon;
    @SerializedName("_xmlns:generic")
    private String _xmlnsGeneric;
    @SerializedName("_xmlns:sdk")
    private String _xmlnsSdk;
    @SerializedName("_xmlns:sdk-common")
    private String _xmlnsSdkCommon;
    @SerializedName("_xmlns:xsi")
    private String _xmlnsXsi;
    @Expose
    private List<Channel> channels;
    @Expose
    private List<License> license;
    @Expose
    private List<RemotePackage> remotePackage;

    public String get_Name() {
        return _Name;
    }

    public void set_Name(String _Name) {
        this._Name = _Name;
    }

    public String get_xmlnsCommon() {
        return _xmlnsCommon;
    }

    public void set_xmlnsCommon(String _xmlnsCommon) {
        this._xmlnsCommon = _xmlnsCommon;
    }

    public String get_xmlnsGeneric() {
        return _xmlnsGeneric;
    }

    public void set_xmlnsGeneric(String _xmlnsGeneric) {
        this._xmlnsGeneric = _xmlnsGeneric;
    }

    public String get_xmlnsSdk() {
        return _xmlnsSdk;
    }

    public void set_xmlnsSdk(String _xmlnsSdk) {
        this._xmlnsSdk = _xmlnsSdk;
    }

    public String get_xmlnsSdkCommon() {
        return _xmlnsSdkCommon;
    }

    public void set_xmlnsSdkCommon(String _xmlnsSdkCommon) {
        this._xmlnsSdkCommon = _xmlnsSdkCommon;
    }

    public String get_xmlnsXsi() {
        return _xmlnsXsi;
    }

    public void set_xmlnsXsi(String _xmlnsXsi) {
        this._xmlnsXsi = _xmlnsXsi;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channel) {
        this.channels = channel;
    }

    public List<License> getLicense() {
        return license;
    }

    public void setLicense(List<License> license) {
        this.license = license;
    }

    public List<RemotePackage> getRemotePackage() {
        return remotePackage;
    }

    public void setRemotePackage(List<RemotePackage> remotePackage) {
        this.remotePackage = remotePackage;
    }

}
