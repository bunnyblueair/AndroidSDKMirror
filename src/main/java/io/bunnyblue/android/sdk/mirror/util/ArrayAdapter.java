/*
 * Copyright (c) 2018.
 * AndroidSDKMirror
 */

package io.bunnyblue.android.sdk.mirror.util;

/**
 * @author bunnyblue
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ArrayAdapter<T> extends TypeAdapter<List<T>> {
    private Class<T> adapterclass;

    public ArrayAdapter(Class<T> adapterclass) {
        this.adapterclass = adapterclass;
    }

    public List<T> read(JsonReader reader) throws IOException {

        List<T> list = new ArrayList<T>();

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ArrayAdapterFactory())
                .create();

        if (reader.peek() == JsonToken.BEGIN_OBJECT) {
            T inning = gson.fromJson(reader, adapterclass);
            list.add(inning);

        } else if (reader.peek() == JsonToken.BEGIN_ARRAY) {

            reader.beginArray();
            while (reader.hasNext()) {
                //   reader.
                //     System.err.println(reader);
                System.out.println(adapterclass.getName());
                    T inning = gson.fromJson(reader, adapterclass);
                    //  System.err.println(inning.toString());
                    list.add(inning);

            }
            reader.endArray();

        }

        return list;
    }

    public void write(JsonWriter writer, List<T> value) throws IOException {

    }

}