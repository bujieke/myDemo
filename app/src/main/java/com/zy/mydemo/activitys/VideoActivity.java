package com.zy.mydemo.activitys;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zy.mydemo.R;
import com.zy.mydemo.adapter.QueryAdapter;
import com.zy.mydemo.adapter.VideoListAdapter;
import com.zy.mydemo.base.BaseActivity;
import com.zy.mydemo.base.BaseAdapter;
import com.zy.mydemo.ui.RecyclerViewTool;
import com.zy.mydemo.utils.PermissionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VideoActivity extends BaseActivity {
    String[] requestPermission = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private RecyclerView mRvVideoList;
    private VideoListAdapter adapter;
    private List<Map<String, String>> mlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getVideoList();

    }

    private void initView() {
        mRvVideoList = (RecyclerView) findViewById(R.id.rv_video_list);
        RecyclerViewTool recyclerViewTool = new RecyclerViewTool(mRvVideoList, mContext);
        recyclerViewTool.initRecyle(RecyclerViewTool.RVTYPE_GENERAL);
        mlist = new ArrayList<>();
        adapter = new VideoListAdapter(mlist, R.layout.item_videolist);

        mRvVideoList.setAdapter(adapter);


        adapter.setItemClickLitener(new BaseAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Map<String, String> stringStringMap = mlist.get(position);
                Intent intent = new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra("path", stringStringMap.get("_data"));
                opeanActivity(intent);
            }
        });
    }

    /**
     * 检查权限并且申请权限
     */
    private void checkPermission() {
        if (PermissionUtils.checkPermission(this, requestPermission)) {
            getVideo();
        }
    }

    private void getVideo() {
        String[] projection = {MediaStore.Video.Media._ID,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.DATA};
        String orderBy = MediaStore.Video.Media.DISPLAY_NAME;
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        getContentProvider(uri, projection, orderBy);

    }

    private void getContentProvider(Uri uri, String[] projection, String orderBy) {
        final List<HashMap<String, String>> listImage = new ArrayList<HashMap<String, String>>();
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
        Log.e("data", listImage.toString());
        if (listImage.size() > 0) {
            mlist.clear();
            mlist.addAll(listImage);
            adapter.notifyDataSetChanged();
        }


    }

    @Override
    public int getLayout() {
        return R.layout.activity_video;
    }

    /**
     * 权限请求返回结果
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, new PermissionUtils.OnPermissionHandleOverListener() {
            @Override
            public void onHandleOver(boolean isOkExactly, Map<String, Integer> result) {
                if (isOkExactly) {
                    getVideo();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_video, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_searchvideo) {
            getVideoList();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    /**
     * 获取播放列表
     */
    private void getVideoList() {
        checkPermission();
    }


}
