package com.example.dllo.mynewworks.untils.net;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by dllo on 16/4/27.
 */
public class OKhttpHelper {

    private static OkHttpClient okHttpClient;
    public static OKhttpHelper helper;


    private OKhttpHelper(Context context) {

        int cacheSize = 10 << 20;
        Cache cache = new Cache(context.getCacheDir(), cacheSize);


        okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(15,TimeUnit.SECONDS)
                .readTimeout(15,TimeUnit.SECONDS)
                .build();

    }

//    public static OkHttpClient getOkHttpSingLetonInstance() {
//        if (okHttpClient == null) {
//            synchronized (OkHttpClient.class) {
//                if (okHttpClient == null) {
//                    okHttpClient = new OkHttpClient();
//                }
//            }
//        }
//        return okHttpClient;
//    }


    public static OKhttpHelper getInstance(Context context) {
        if (helper == null) {
            synchronized (OKhttpHelper.class) {
                if (helper == null) {
                    helper = new OKhttpHelper(context);
                }
            }
        }
        return helper;
    }


    private static Request buildGetRequest(String url, Object tag) {
        Request.Builder buidler = new Request.Builder();
        buidler.url(url);
        if (tag != null) {
            buidler.tag(tag);
        }
        return buidler.build();
    }

    private static Response buildResponse(String url, Object tag) throws IOException {
        Request request = buildGetRequest(url, tag);
        Response response = okHttpClient.newCall(request).execute();
        return response;
    }

    private static ResponseBody buildResponseBody(String url, Object tag) throws IOException {
        Response response = buildResponse(url, tag);
        if (response.isSuccessful()) {
            return response.body();
        }
        return null;
    }

    /**
     * get同步网络请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String getStringFromUrl(String url, Object tag) throws IOException {
        ResponseBody responseBody = buildResponseBody(url, tag);
        if (responseBody != null) {
            return responseBody.string();
        }
        return null;
    }

    public static byte[] getByteFromUrl(String url, Object tag) throws IOException {
        ResponseBody responseBody = buildResponseBody(url, tag);
        if (responseBody != null) {
            return responseBody.bytes();
        }
        return null;
    }

    /**
     * get同步网络请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static InputStream getStreamFromUrl(String url, Object tag) throws IOException {
        ResponseBody responseBody = buildResponseBody(url, tag);
        if (responseBody != null) {
            return responseBody.byteStream();
        }
        return null;
    }

    /**
     * get异步网络请求
     *
     * @param url
     * @param callback
     */
    public static void getDataAsync(String url, Object tag, Callback callback) {
        Request request = buildGetRequest(url, tag);
        okHttpClient.newCall(request).enqueue(callback);
    }


    //    *************************下面是post部分******************************
    private static Request builePostRequest(String url, RequestBody requestBody, Object tag) {
        Request.Builder builder = new Request.Builder();
        if (tag != null) {
            builder.tag(tag);
        }
        builder.url(url).post(requestBody);
        return builder.build();
    }


    private static String postRequestBody(String url, RequestBody requestBody, Object tag) throws IOException {
        Request request = builePostRequest(url, requestBody, tag);
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        }
        return null;
    }

    private static RequestBody buildRequestBody(Map<String, String> map) {
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null && map.size() > 0) {
            Set<Map.Entry<String, String>> mapSet = map.entrySet();
            for (Map.Entry<String, String> entry : mapSet) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        return builder.build();
    }

    /**
     * post同步访问网络提交键值对
     *
     * @param url
     * @param map
     * @return
     * @throws IOException
     */
    public static String postKeyValuePair(String url, Map<String, String> map, Object tag) throws IOException {
        RequestBody requestBody = buildRequestBody(map);
        return postRequestBody(url, requestBody, tag);
    }

    //    ****************************post异步请求******************************
    private static void postRequestBodyAnsync(String url, RequestBody requestBody, Object tag, Callback callback) {
        Request request = builePostRequest(url, requestBody, tag);
        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * post异步网络请求
     *
     * @param url
     * @param map
     * @param callback
     */
    public static void postKeyValueAsync(String url, Map<String, String> map, Object tag, Callback callback) {
        RequestBody requestBody = buildRequestBody(map);
        postRequestBodyAnsync(url, requestBody, tag, callback);
    }
//************************上传文件****************************

    private static RequestBody buildRequestBody(Map<String, String> map, File[] files, String[] formFileName) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//     第一部分提交 :文件控件以外的其他input数据
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.addPart(Headers.of("Content-Disposition", "form-data;name=\"" + entry.getKey() + "\""), RequestBody.create(null, entry.getValue()));
            }
        }
//        二 上传文件的控件数据
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                String fileName = file.getName();
                RequestBody requestBody = RequestBody.create(MediaType.parse(getNameType(fileName)), file);
                builder.addPart(Headers.of("Content-Disposition",
                        "form-data;name=\"" + formFileName[i] + "\";filename=\"" + fileName + "\""), requestBody);
            }
        }
        return builder.build();
    }

    private static String getNameType(String filename) {
        FileNameMap filenameMap = URLConnection.getFileNameMap();
        String contentTypeFor = filenameMap.getContentTypeFor(filename);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    /**
     * post同步上传文件以及其他表单控件(分块提交请求)
     *
     * @param url          网址
     * @param map          提交给服务器的表单信息键值对
     * @param files        提交文件
     * @param formFileName 每个需要提交的文件对应的input的name
     * @return
     * @throws IOException
     */
    public static String postUrlLoadFiles(String url, Map<String, String> map, File[] files, String[] formFileName, Object tag) throws IOException {
        RequestBody requestBody = buildRequestBody(map, files, formFileName);
        return postRequestBody(url, requestBody, tag);
    }


    public void cancelTag(Object tag)
    {
        for (Call call : okHttpClient.dispatcher().queuedCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
        for (Call call : okHttpClient.dispatcher().runningCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
    }
}
