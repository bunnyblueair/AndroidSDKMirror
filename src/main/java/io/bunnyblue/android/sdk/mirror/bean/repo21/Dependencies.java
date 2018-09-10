
/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror.bean.repo21;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Dependencies {

    @Expose
    private List<Dependency> dependency;

    public List<Dependency> getDependency() {
        return dependency;
    }

    public void setDependency(List<Dependency> dependency) {
        this.dependency = dependency;
    }

}
