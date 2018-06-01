package com.applicaster.analytics.plugins.testanalyticsplugin_android;

import android.content.Context;

import com.applicaster.analytics.BaseAnalyticsAgent;
import com.applicaster.player.wrappers.PlayerViewWrapper;
import com.applicaster.plugin_manager.playersmanager.Playable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TestAnalyticsAgent extends BaseAnalyticsAgent {
    @Override
    public void initializeAnalyticsAgent(Context context) {
        super.initializeAnalyticsAgent(context);
    }

    @Override
    public void setParams(Map params) {
        super.setParams(params);
    }

    @Override
    public Map<String, String> getConfiguration() {
        return super.getConfiguration();
    }

    @Override
    public void startTrackingSession(Context context) {
        super.startTrackingSession(context);
    }

    @Override
    public void stopTrackingSession(Context context) {
        super.stopTrackingSession(context);
    }

    @Override
    public void analyticsSwitch(boolean enabled) {
        super.analyticsSwitch(enabled);
    }

    @Override
    public void logEvent(String eventName) {
        super.logEvent(eventName);
    }

    @Override
    public void logEvent(String eventName, TreeMap<String, String> params) {
        super.logEvent(eventName, params);
    }

    @Override
    public void startTimedEvent(String eventName) {
        super.startTimedEvent(eventName);
    }

    @Override
    public void startTimedEvent(String eventName, TreeMap<String, String> params) {
        super.startTimedEvent(eventName, params);
    }

    @Override
    public void endTimedEvent(String eventName) {
        super.endTimedEvent(eventName);
    }

    @Override
    public void sendUserID(String userId) {
        super.sendUserID(userId);
    }

    @Override
    public void sendStandardProperties(JSONObject params) {
        super.sendStandardProperties(params);
    }

    @Override
    public void sendUserProperties(JSONObject params) throws JSONException {
        super.sendUserProperties(params);
    }

    @Override
    public void initPlayerSession(Playable playable, PlayerViewWrapper playerWrapper, int duration) {
        super.initPlayerSession(playable, playerWrapper, duration);
    }

    @Override
    public void handlePlayerError(String message) {
        super.handlePlayerError(message);
    }

    @Override
    public void logPlayEvent(long currentPosition) {
        super.logPlayEvent(currentPosition);
    }

    @Override
    public void logPlayerEnterBackground() {
        super.logPlayerEnterBackground();
    }

    @Override
    public void logPauseButtonPressed() {
        super.logPauseButtonPressed();
    }

    @Override
    public void logResumeButtonPressed() {
        super.logResumeButtonPressed();
    }

    @Override
    public void logPauseEvent(long currentPosition) {
        super.logPauseEvent(currentPosition);
    }

    @Override
    public void logStopEvent(long currentPosition) {
        super.logStopEvent(currentPosition);
    }

    @Override
    public void logVideoEndEvent(long currentPosition) {
        super.logVideoEndEvent(currentPosition);
    }

    @Override
    public void logSeekStartEvent(long position) {
        super.logSeekStartEvent(position);
    }

    @Override
    public void logVideoEvent(String eventName, TreeMap<String, String> params) {
        super.logVideoEvent(eventName, params);
    }

    @Override
    public void logSeekEndEvent(int position) {
        super.logSeekEndEvent(position);
    }

    @Override
    public void logBufferingStartEvent() {
        super.logBufferingStartEvent();
    }

    @Override
    public void logBufferingEndEvent() {
        super.logBufferingEndEvent();
    }

    @Override
    public void generalPlayerInfoEvent(Map<String, String> params) {
        super.generalPlayerInfoEvent(params);
    }

    @Override
    public void logFullscreenPressed(int currentPosition) {
        super.logFullscreenPressed(currentPosition);
    }

    @Override
    public void createTracking(Context context) {
        super.createTracking(context);
    }

    @Override
    public void pauseTracking(Context context) {
        super.pauseTracking(context);
    }

    @Override
    public void resumeTracking(Context context) {
        super.resumeTracking(context);
    }

    @Override
    public void setScreenView(String screenView) {
        super.setScreenView(screenView);
    }

    @Override
    public void flushEvents(Context context) {
        super.flushEvents(context);
    }

    @Override
    public boolean isNotBlacklisted(String eventName) {
        return super.isNotBlacklisted(eventName);
    }

    @Override
    protected Map<String, String> getExtraParameters(Set<String> remoteConfigKeysForMatch) {
        return super.getExtraParameters(remoteConfigKeysForMatch);
    }

    @Override
    public void trackCampaignParamsFromUrl(String url) {
        super.trackCampaignParamsFromUrl(url);
    }
}