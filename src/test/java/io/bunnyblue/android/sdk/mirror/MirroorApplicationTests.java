/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror;

import io.bunnyblue.android.sdk.mirror.sync.SyncSDKInit;
import okhttp3.OkHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MirroorApplicationTests {
    @Bean
    OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
    @Test
    public void contextLoads() {
        SyncSDKInit syncSDKInit =new SyncSDKInit();
        syncSDKInit.doSyncAll();
    }

}
