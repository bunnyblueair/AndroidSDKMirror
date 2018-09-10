/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror;

import io.bunnyblue.android.sdk.mirror.sync.StudioSync;
import org.junit.Test;

/**
 * @author bunnyblue
 */
public class Dmgtest {
    @Test
    public void  syncDmg(){
        StudioSync studioSync=new StudioSync();
        studioSync.syncStudio();
    }
}
