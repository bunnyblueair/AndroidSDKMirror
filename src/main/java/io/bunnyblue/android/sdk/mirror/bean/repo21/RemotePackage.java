
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
public class RemotePackage {


    @Expose
    private String path;
    @Expose
    private boolean obsolete=false;
    @Expose
    private List<Archive> archives;
    @Expose
    private ChannelRef channelRef;
    @Expose
    private Dependencies dependencies;
    @SerializedName("display-name")
    private String displayName;
    @Expose
    private Revision revision;
    @SerializedName("type-details")
    private TypeDetails typeDetails;
    @SerializedName("uses-license")
    private UsesLicense usesLicense;

    public String getPath() {
        return path;
    }

    public void setPath(String _path) {
        this.path = path;
    }

    public List<Archive> getArchives() {
        return archives;
    }

    public void setArchives(List<Archive> archives) {
        this.archives = archives;
    }

    public ChannelRef getChannelRef() {
        return channelRef;
    }

    public void setChannelRef(ChannelRef channelRef) {
        this.channelRef = channelRef;
    }

    public Dependencies getDependencies() {
        return dependencies;
    }

    public void setDependencies(Dependencies dependencies) {
        this.dependencies = dependencies;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Revision getRevision() {
        return revision;
    }

    public void setRevision(Revision revision) {
        this.revision = revision;
    }

    public TypeDetails getTypeDetails() {
        return typeDetails;
    }

    public void setTypeDetails(TypeDetails typeDetails) {
        this.typeDetails = typeDetails;
    }

    public UsesLicense getUsesLicense() {
        return usesLicense;
    }

    public void setUsesLicense(UsesLicense usesLicense) {
        this.usesLicense = usesLicense;
    }
    public boolean isObsolete() {
        return obsolete;
    }

    public void setObsolete(boolean obsolete) {
        this.obsolete = obsolete;
    }

}
