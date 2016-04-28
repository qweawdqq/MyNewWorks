package com.example.dllo.mynewworks.untils.net;

import android.support.v4.widget.ViewDragHelper;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by dllo on 16/4/27.
 */
public class OKhttpHelper {
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    private static Request buildGetRequest(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return request;
    }

    private static Response buildResponse(String url) throws IOException {
        Request request = buildGetRequest(url);
        Response response = okHttpClient.newCall(request).execute();
        return response;
    }

    private static ResponseBody buildResponseBody(String url) throws IOException {
        Response response = buildResponse(url);
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
    public static String getStringFromUrl(String url) throws IOException {
        ResponseBody responseBody = buildResponseBody(url);
        if (responseBody != null) {
            return responseBody.string();
        }
        return null;
    }

    public static byte[] getByteFromUrl(String url) throws IOException {
        ResponseBody responseBody = buildResponseBody(url);
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
    public static InputStream getStreamFromUrl(String url) throws IOException {
        ResponseBody responseBody = buildResponseBody(url);
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
    public static void getDataAsync(String url, Callback callback) {
        Request request = buildGetRequest(url);
        okHttpClient.newCall(request).enqueue(callback);
    }


    //    *************************下面是post部分******************************
    private static Request builePostRequest(String url, RequestBody requestBody) {
        Request.Builder builder = new Request.Builder();
        builder.url(url).post(requestBody);
        return builder.build();
    }


    private static String postRequestBody(String url, RequestBody requestBody) throws IOException {
        Request request = builePostRequest(url, requestBody);
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
    public static String postKeyValuePair(String url, Map<String, String> map) throws IOException {
        RequestBody requestBody = buildRequestBody(map);
        return postRequestBody(url, requestBody);
    }

    //    ****************************post异步请求******************************
    private static void postRequestBodyAnsync(String url, RequestBody requestBody, Callback callback) {
        Request request = builePostRequest(url, requestBody);
        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * post异步网络请求
     *
     * @param url
     * @param map
     * @param callback
     */
    public static void postKeyValueAsync(String url, Map<String, String> map, Callback callback) {
        RequestBody requestBody = buildRequestBody(map);
        postRequestBodyAnsync(url, requestBody, callback);
    }
}
