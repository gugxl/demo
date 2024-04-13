package com.gugu.demo.baduai;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

class Sample {

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    public static void main(String[] args) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "怎么学习java");
        Request request = new Request.Builder()
            .url("https://aip.baidubce.com/oauth/2.0/token?client_id=SPaT0Lb8OSbeLDGyHQeBeDtE" +
                "&client_secret=JEkSFGy4a0wezfYycSX494YOQvU3PqcE" +
                "&grant_type=client_credentials")
            .method("POST", body)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        System.out.println(response.body().string());

    }


}