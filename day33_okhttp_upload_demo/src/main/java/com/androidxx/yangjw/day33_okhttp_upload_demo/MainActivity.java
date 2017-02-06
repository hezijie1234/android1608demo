package com.androidxx.yangjw.day33_okhttp_upload_demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttp基本使用之上传功能
 * 步骤：
 * 1、导包
 * 2、创建OkHttpClient对象
 * 3、创建Request（传值，传递的文件？？？？？）
 * 4、执行Request
 */
public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    private static final String TAG = "android1608";
    private ImageView mShowImage;
    private byte[] bytes;
    private OkHttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowImage = (ImageView) findViewById(R.id.main_show_image);

        //创建OkHttpClient对象
        httpClient = new OkHttpClient.Builder().build();
    }

    //打开相册选择图片，采用的是Activity的隐式启动方式
    public void choosePic(View view) {
        //ACTION_GET_CONTENT表示选择内容
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CODE);
    }

    public void upload(View view) {

        //参数1：文件的类型
        //参数2：文件的内容
        MediaType mediaType = MediaType.parse("image/*");
        RequestBody requestBody = RequestBody.create(mediaType, bytes);
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM) //表示以Form表单数据格式进行传递
                /**
                 * 参数1：传递的文件的key
                 * 参数2：上传到服务器的文件的名称
                 * 参数3：requestBody对象
                 */
                .addFormDataPart("file", "abc.png", requestBody)
                .build();

        Request request = new Request.Builder()
                .url("http://192.168.53.18:8080/androidxx/upload.do")
                .post(multipartBody)
                .build();

        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: ----------" + response.body().string());
                int code = response.code();
                if (code == 200) {
                    Log.i(TAG, "onResponse: success");
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult: " + data.getData());
        InputStream inputStream = null;
        try {
            inputStream = getContentResolver().openInputStream(data.getData());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len=0;
            while((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer,0,len);
            }
            baos.flush();
            bytes = baos.toByteArray();
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            mShowImage.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
