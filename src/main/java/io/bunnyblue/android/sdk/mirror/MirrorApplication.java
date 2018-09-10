/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror;

import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MirrorApplication {
    @Bean
    OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }


    public static void main(String[] args) {
        SpringApplication.run(MirrorApplication.class, args);
    }
}
