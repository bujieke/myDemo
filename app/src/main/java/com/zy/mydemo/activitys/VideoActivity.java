package com.zy.mydemo.activitys;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import com.zy.mydemo.R;
import com.zy.mydemo.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class VideoActivity extends BaseActivity {


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private Button mBtnGetvideo;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();


    }

    private void initView() {

        mText = (TextView) findViewById(R.id.text);
        mBtnGetvideo = (Button) findViewById(R.id.btn_getvideo);
        mBtnGetvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checkPermission
                checkPermission();

            }


        });

    }

    /**
     * 检查权限并且申请权限
     */
    private void checkPermission() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            getVideo();

        } else {
            ActivityCompat.requestPermissions(this,
                    PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    private void getVideo() {
        String[] projection = {MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.DATA};
        String orderBy = MediaStore.Video.Media.DISPLAY_NAME;
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        getContentProvider(uri, projection, orderBy);

    }

    private void getContentProvider(Uri uri, String[] projection, String orderBy) {
        List<HashMap<String, String>> listImage = new ArrayList<HashMap<String, String>>();
        Cursor cursor = getContentResolver().query(uri, projection, null,
                null, orderBy);
        if (null == cursor) {
            return;
        }
        while (cursor.moveToNext()) {
            HashMap<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < projection.length; i++) {
                map.put(projection[i], cursor.getString(i));
                System.out.println(projection[i] + ":" + cursor.getString(i));
            }
            listImage.add(map);
        }
        mText.setText(listImage.toString());


    }

    @Override
    public int getLayout() {
        return R.layout.activity_video;
    }
}
