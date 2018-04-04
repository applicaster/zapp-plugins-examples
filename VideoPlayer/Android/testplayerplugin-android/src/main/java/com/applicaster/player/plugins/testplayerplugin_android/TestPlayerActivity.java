package com.applicaster.player.plugins.testplayerplugin_android;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.applicaster.plugin_manager.playersmanager.Playable;

import static com.applicaster.player.plugins.testplayerplugin_android.TestPlayerAdapter.ALLOW_PORTRAIT;
import static com.applicaster.player.plugins.testplayerplugin_android.TestPlayerAdapter.KEY_PLAYABLE;

public class TestPlayerActivity extends AppCompatActivity {

    private Playable playable;
    private VideoView videoView;
    private MediaController mediaController;
    private boolean allow_portrait = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (getIntent().getExtras() != null) {
            playable = (Playable) getIntent().getExtras()
                    .getSerializable(KEY_PLAYABLE);
            allow_portrait = getIntent().getBooleanExtra(ALLOW_PORTRAIT, false);
        }

        if (allow_portrait) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        }

        setContentView(R.layout.activity_test_player);
        videoView = findViewById(R.id.video_view);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (playable != null) {
            videoView.setVideoURI(Uri.parse(playable.getContentVideoURL()));
            videoView.start();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                    mediaController = new MediaController(TestPlayerActivity.this);
                    videoView.setMediaController(mediaController);
                    mediaController.setAnchorView(videoView);
                }
            });
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            this.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
