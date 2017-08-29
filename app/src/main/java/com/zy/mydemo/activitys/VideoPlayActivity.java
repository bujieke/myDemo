package com.zy.mydemo.activitys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zy.mydemo.R;
import com.zy.mydemo.base.BaseActivity;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoPlayActivity extends AppCompatActivity {
    private VideoView mVideoView;
    private MediaController mMediaController;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_video_play);
        Intent intent = getIntent();
        path = intent.getStringExtra("path");
        if (!TextUtils.isEmpty(path)) {
            initView();
        } else {
            finish();
        }
    }

    @Override
    protected void onResume() {

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    private void initView() {
        Log.e("path", path);
        if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
            return;
        mVideoView = (VideoView) findViewById(R.id.surface_view);
        mVideoView.setVideoPath(path);//设置播放地址
        mMediaController = new MediaController(this);//实例化控制器
        mVideoView.setBufferSize(10240);
//        mMediaController.show(5000);//控制器显示5s后自动隐藏
        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_MEDIUM);
        mVideoView.setMediaController(mMediaController);//绑定控制器
//        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//设置播放画质 高画质
        mVideoView.requestFocus();//取得焦点
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setPlaybackSpeed(1.0f);
            }
        });

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
              mVideoView.stopPlayback();
                finish();
            }
        });
    }


}
