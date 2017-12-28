package com.gokhanaliccii.flavorhunter.util;

import android.content.Context;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

/**
 * Created by gokhan on 28/12/17.
 */

public class AssetReader<T> {

    private String path;
    private Class<T> mapper;
    private WeakReference<Context> mContext;

    private AssetReader(Context context) {
        this.mContext = new WeakReference<>(context);
    }

    private AssetReader(Context context, Class<T> clazz) {
        this.mapper = clazz;
        this.mContext = new WeakReference<>(context);
    }

    public static AssetReader newReader(Context context) {
        return new AssetReader(context);
    }

    public static <T> AssetReader newReader(Context context, Class<T> clazz) {
        return new AssetReader(context, clazz);
    }


    public AssetReader path(String path) {
        this.path = path;
        return this;
    }

    public T map() {
        String file = read();
        Gson gson = new Gson();

        return gson.fromJson(file, mapper);
    }

    public String read() {
        Context context = mContext.get();
        if (context == null) {
            return null;
        }

        String file = null;
        InputStream is = null;
        ByteArrayOutputStream os = null;

        try {
            is = context.getAssets().open(path);
            os = new ByteArrayOutputStream();

            pipe(is, os);
            file = os.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStreams(is, os);
        }

        return file;
    }

    private void pipe(InputStream is, OutputStream os) throws IOException {
        byte[] line = new byte[1024];
        int len = 0;
        while ((len = is.read(line)) > 0) {
            os.write(line, 0, len);
        }
    }

    private void closeStreams(Closeable... streams) {
        if (streams != null) {
            for (Closeable stream : streams) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
