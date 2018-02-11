package com.applicaster.pluginplayerexample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.applicaster.player.defaultplayer.BasePlayer;
import com.applicaster.plugin_manager.playersmanager.Playable;
import com.applicaster.plugin_manager.playersmanager.PlayableConfiguration;
import java.util.List;
import java.util.Map;

/**
 * Created by eladbendavid on 20/06/2017.
 */


//
//  ZappPlayerAdapter.java
//  ZappPluginPlayerExample-iOS
//
//  Created by Elad Ben David on 19/06/2017.
//  Copyright 2017 Applicaster. All rights reserved.
//
public class ZappPlayerAdapter extends BasePlayer {

    // Properties
    VideoView videoView;
    MediaController mediaController;

    /**
     * Optional initialization for the PlayerContract - will be called in the App's onCreate
     */
    @Override
    public void init(@NonNull Context appContext) {
        super.init(appContext);
    }

    /**
     * initialization of the player instance with a playable item
     *
     * @param playable
     */
    @Override
    public void init(@NonNull Playable playable, @NonNull Context context) {
        super.init(playable, context);
        videoView = new VideoView(context);
        if (mediaController == null) {
            mediaController = new MediaController(context);
        }
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(getFirstPlayable().getContentVideoURL()));
    }

    /**
     * initialization of the player instance with  multiple playable items
     *
     * @param playableList
     */
    @Override
    public void init(@NonNull List<Playable> playableList, @NonNull Context context) {
        super.init(playableList, context);
    }

    /**
     * initialization of the player plugin configuration with a Map params.
     * the params taken from res/raw/plugin_configurations.json
     *
     * @param params
     */
    @Override
    public void setPluginConfigurationParams(Map params) {
        super.setPluginConfigurationParams(params);
    }

    /**
     * return the player type
     *
     * @return PlayerItem.Type type of the player
     */
    @Override
    public PlayerType getPlayerType() {
        return PlayerType.Default;
    }

    /**
     * Optional method - best to implement but in case you can't it will still just return false.
     *
     * @return the playing state of the player
     */
    @Override
    public boolean isPlayerPlaying() {
        return videoView.isPlaying();
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
     * @param configuration player configuration.
     * @param requestCode   request code if needed - if not send NO_REQUEST_CODE instead.
     * @param context
     */
    @Override
    public void playInFullscreen(PlayableConfiguration configuration, int requestCode, @NonNull Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW );
        intent.setDataAndType(Uri.parse(getFirstPlayable().getContentVideoURL()), "video/*");
        getContext().startActivity(intent);
    }


    //---------- Inline ----------//
    /**
     * Add the player into the given container.
     *
     * @param videoContainerView The container to add the player to.
     */
    @Override
    public void attachInline(@NonNull ViewGroup videoContainerView) {
        ViewGroup.LayoutParams playerContainerLayoutParams
                = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT);
        videoContainerView.addView(videoView, playerContainerLayoutParams);
    }

    /**
     * Remove the player from it's container.
     *
     * @param videoContainerView The container that contains the player.
     */
    @Override
    public void removeInline(@NonNull ViewGroup videoContainerView) {
        if(videoContainerView.indexOfChild(videoView) >= 0) {
            videoContainerView.removeView(videoView);
        }
    }


    /**
     * Starts playing the inline player.
     */
    @Override
    public void playInline() {
        videoView.start();
    }

    /**
     * Stops playing the inline player.
     */
    @Override
    public void stopInline() {
        videoView.stopPlayback();
    }

    /**
     * Pauses playing the inline player
     */
    @Override
    public void pauseInline() {
        videoView.pause();
    }
    /**
     * Resumes playing the inline player.
     */
    @Override
    public void resumeInline() {
        videoView.resume();
    }
}
