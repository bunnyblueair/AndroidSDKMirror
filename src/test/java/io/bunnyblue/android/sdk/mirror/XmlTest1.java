/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.bunnyblue.android.sdk.mirror.bean.repo21.Repository2;
import io.bunnyblue.android.sdk.mirror.sync.Repository2Sync;
import io.bunnyblue.android.sdk.mirror.sync.SyncSDKInit;
import io.bunnyblue.android.sdk.mirror.sync.SysImg2Sync;
import io.bunnyblue.android.sdk.mirror.util.ArrayAdapterFactory;
import io.bunnyblue.android.sdk.mirror.util.Repo21JsonFixer;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;
import org.leibnizcenter.xml.NotImplemented;
import org.leibnizcenter.xml.TerseJson;
import org.leibnizcenter.xml.helpers.DomHelper;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author bunnyblue
 */

public class XmlTest1 {
    private static final TerseJson.WhitespaceBehaviour COMPACT_WHITE_SPACE = TerseJson.WhitespaceBehaviour.Compact;

    @Test
    public void xmlText() throws IOException, ParserConfigurationException, SAXException, NotImplemented {
//     String xml=   FileUtils.readFileToString(new File("/Users/bunnyblue/mywork/mirror/src/test/repository2-1.xml"));
//
//        XmlMapper xmlMapper = new XmlMapper();
//        JsonNode node = xmlMapper.readTree(xml.getBytes());
//
//        ObjectMapper jsonMapper = new ObjectMapper();
//        String json = jsonMapper.writeValueAsString(node);
//        JSONObject xmlJSONObj = XML.toJSONObject(xml,false);
//        Type collectionType = new TypeToken<Repository2>(){}.getType();
//        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).create();
//        JSONObject jsonObject=new JSONObject(xmlJSONObj.get("sdk:sdk-repository").toString());
//        Repo21JsonFixer.fixJson(jsonObject);
//        Repository2 repository2=  gson.fromJson(jsonObject.toString(), collectionType);
      //  System.err.println(repository2);
        SysImg2Sync repository2Sync=new SysImg2Sync("https://dl.google.com/android/repository/",null,"sys-img2-1.xml");
        Repository2  repository2 = repository2Sync.syncXml2Pojo();
        repository2Sync.downloadFile();

    }
    @Test
    public void  configTest(){
        SyncSDKInit syncSDKInit =new SyncSDKInit();
        syncSDKInit.doSyncAll();
    }
}
