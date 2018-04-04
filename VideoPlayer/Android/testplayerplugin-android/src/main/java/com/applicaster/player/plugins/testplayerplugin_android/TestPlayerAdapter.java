package com.applicaster.player.plugins.testplayerplugin_android;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.applicaster.player.defaultplayer.BasePlayer;
import com.applicaster.plugin_manager.playersmanager.Playable;
import com.applicaster.plugin_manager.playersmanager.PlayableConfiguration;
import com.applicaster.util.OSUtil;
import com.applicaster.util.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * TestPlayerAdapter.java:
 * This adapter extends the BasePlayer class which implements the PlayerContract.
 * This class includes the various initialization methods as well as several playback methods.
 */
public class TestPlayerAdapter extends BasePlayer {

    static final String KEY_PLAYABLE = "playable";
    static final String ALLOW_PORTRAIT = "allow_portrait";
    private VideoView videoView;
    private MediaController mediaController;
    private boolean allowPortrait = false;


    /**
     * initialization of the player instance with a playable item
     *
     * @param playable
     */
    @Override
    public void init(Playable playable, Context context) {
        super.init(playable, context);
        videoView = new VideoView(context);
    }

    /**
     * initialization of the player instance with  multiple playable items
     *
     * @param playList
     */
    @Override
    public void init(List<Playable> playList, Context context) {
        super.init(playList, context);
        videoView = new VideoView(context);
    }

    /**
     * initialization of the player plugin configuration with a Map params.
     * the params taken from res/raw/plugin_configurations.json
     *
     * @param params configurations
     */
    @Override
    public void setPluginConfigurationParams(Map params) {
        super.setPluginConfigurationParams(params);

        // This is an example of how you can retrieve a configuration
        if (params.containsKey(ALLOW_PORTRAIT)) {
            allowPortrait = StringUtil.booleanValue(params.get(ALLOW_PORTRAIT).toString());
        }
    }

    /**
     * get the first playable attached to the player
     *
     * @return playable
     */
    @Override
    public Playable getFirstPlayable() {
        return super.getFirstPlayable();
    }

    /**
     * return the player plugin configuration
     *
     * @return Player configuration.
     */
    @Override
    public Map getPluginConfigurationParams() {
        return super.getPluginConfigurationParams();
    }

    @Override
    public List<Playable> getPlayableList() {
        return super.getPlayableList();
    }

    /**
     * start the player in fullscreen with configuration.
     *
     * @param playableConfiguration player configuration.
     * @param requestCode           request code if needed - if not send NO_REQUEST_CODE instead.
     * @param context
     */
    @Override
    public void playInFullscreen(PlayableConfiguration playableConfiguration, int requestCode, Context context) {
        super.playInFullscreen(playableConfiguration, requestCode, context);
        Intent intent = new Intent(context, TestPlayerActivity.class);
        if (getFirstPlayable() != null) {
            intent.putExtra(KEY_PLAYABLE, getFirstPlayable());
            intent.putExtra(ALLOW_PORTRAIT, allowPortrait);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent);
        }
    }

    /**
     * Add the player into the given container.
     *
     * @param viewGroup The container the player should be added to
     */
    @Override
    public void attachInline(ViewGroup viewGroup) {
        super.attachInline(viewGroup);
        if (videoView != null) {
            viewGroup.addView(videoView);
        }
    }

    /**
     * Remove the player from it's container.
     *
     * @param viewGroup The container that the player is attached to
     */
    @Override
    public void removeInline(ViewGroup viewGroup) {
        super.removeInline(viewGroup);
        if (videoView != null) {
            viewGroup.removeView(videoView);
        }
    }

    /**
     * start the player in inline with configuration.
     *
     * @param configuration player configuration.
     */
    @Override
    public void playInline(PlayableConfiguration configuration) {
        super.playInline(configuration);
        if (getFirstPlayable() != null) {
            videoView.setVideoURI(Uri.parse(getFirstPlayable().getContentVideoURL()));
            videoView.start();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                    mediaController = new MediaController(getContext());
                    videoView.setMediaController(mediaController);
                    mediaController.setAnchorView(videoView);
                }
            });
        }
    }

    /**
     * Stops playing the inline player.
     */
    @Override
    public void stopInline() {
        super.stopInline();
        if (videoView != null) {
            videoView.stopPlayback();
        }
    }

    /**
     * Pauses playing the inline player
     */
    @Override
    public void pauseInline() {
        super.pauseInline();
        if (videoView != null) {
            videoView.pause();
        }
    }

    /**
     * Resumes playing the inline player.
     */
    @Override
    public void resumeInline() {
        super.resumeInline();
        if (videoView != null) {
            videoView.start();
        }
    }
}
