package com.bawei.rikao1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private String path = "http://result.eolinker.com/iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=one";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = getData();
                Log.e("myData",""+data);
            }
        }).start();
    }

    private String getData() {
        try {
            URL url=new URL(path);
            //开启一个连接
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            //判断网络状态
            if(connection.getResponseCode()==200){
                InputStream stream = connection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                byte[] bytes=new byte[1024];
                int length=0;
                while ((length=stream.read(bytes))!=-1){
                    byteArrayOutputStream.write(bytes,0,length);
                }
                String s = byteArrayOutputStream.toString();
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
