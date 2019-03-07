package com.gs.keystoredemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.gs.keystore.Config;
import com.gs.keystore.keystore.KeyStoreHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button encode;
    private Button decode;

    public static String baseSource = "jdasldhasudhwqhjhauihdasijdnkjsahgduyasuh98w9equ8989eqwiwqye78wqmasndajhl";

    private List<String> resourceList;

    private List<String> enResourceList;
    private List<String> deResourceList;


    public static final int TIMES = 100;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        encode = findViewById(R.id.encode);
        decode = findViewById(R.id.decode);
        resourceList = new ArrayList<>();
        enResourceList = new ArrayList<>();
        deResourceList = new ArrayList<>();
        gson = new Gson();
        initData();
        initListener();
    }

    private void initListener() {
        encode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encodeList();
            }
        });

        decode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decodeList();
            }
        });
    }

    private void initData() {
        resourceList.clear();
        for (int i = 0; i < TIMES; i++) {
            resourceList.add(baseSource + i);
        }
    }

    private void encodeList() {

        enResourceList.clear();
        long start = System.currentTimeMillis();
        Log.d(Config.GLOBAL_TAG, "keystore加密数据开始：" + String.valueOf(start));
        for (String item : resourceList) {
            String encryptString = KeyStoreHelper.encryptString(item);
            enResourceList.add(encryptString);
        }
        long end = System.currentTimeMillis();
        Log.d(Config.GLOBAL_TAG, "keystore加密数据结束：" + String.valueOf(end));
        Log.d(Config.GLOBAL_TAG, "keystore加密数据耗时：" + String.valueOf(end - start));


//        Log.d(Config.GLOBAL_TAG, "加密后的数据" + gson.toJson(enResourceList));
    }

    private void decodeList() {
        deResourceList.clear();
        long start = System.currentTimeMillis();
        Log.d(Config.GLOBAL_TAG, "keystore解密数据开始：" + String.valueOf(start));
        for (String item : enResourceList) {
            String decryptString = KeyStoreHelper.decryptString(item);
            deResourceList.add(decryptString);
        }
        long end = System.currentTimeMillis();
        Log.d(Config.GLOBAL_TAG, "keystore解密数据结束：" + String.valueOf(end));
        Log.d(Config.GLOBAL_TAG, "keystore解密数据耗时：" + String.valueOf(end - start));
//        Log.d(Config.GLOBAL_TAG, "解密后的数据" + gson.toJson(enResourceList));


    }
}
