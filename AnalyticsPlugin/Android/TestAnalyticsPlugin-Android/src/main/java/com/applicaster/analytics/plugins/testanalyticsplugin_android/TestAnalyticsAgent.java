package com.applicaster.analytics.plugins.testanalyticsplugin_android;

import android.content.Context;
import android.text.TextUtils;

import com.applicaster.analytics.AnalyticsAgentUtil;
import com.applicaster.analytics.BaseAnalyticsAgent;
import com.applicaster.player.wrappers.PlayerViewWrapper;
import com.applicaster.plugin_manager.playersmanager.Playable;
import com.applicaster.util.APLogger;
import com.applicaster.util.StringUtil;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TestAnalyticsAgent extends BaseAnalyticsAgent {
    private static final String TAG = TestAnalyticsAgent.class.getSimpleName();

    /**
     * This variables are created for Google Analytics purposes.
     * You can delete all this variables when you doing your plugin.
     */
    // region vars
    private transient Tracker mTracker = null;
    private transient GoogleAnalytics googleAnalytics;
    private static final String MOBILE_APP_ACCOUNT_ID_IDENTIFIER = "mobile_app_account_id";
    private static final String ANONYMIZE_USER_IP_IDENTIFIER = "anonymize_user_ip";
    private static final String SCREEN_VIEWS_IDENTIFIER = "screen_views";
    private static final String DO_NOT_SET_CLIENT_ID = "do_not_set_client_id";
    private String mobileAppAccountId;
    private boolean anonymizeUserIp;
    private boolean screenViews;
    private boolean shouldSetClientId;

    // custom events
    private static final String PLAY_EVENT = "Play video";
    private static final String PAUSE_EVENT = "Pause video";
    private static final String STOP_EVENT = "Stop video";

    // endregion

    /**
     * Initialization of your Analytics provider.
     * @param context
     */
    @Override
    public void initializeAnalyticsAgent(Context context) {
        super.initializeAnalyticsAgent(context);
        googleAnalytics = GoogleAnalytics.getInstance(context);
        mTracker = googleAnalytics.newTracker(mobileAppAccountId);
        mTracker.enableAdvertisingIdCollection(true);
        if (StringUtil.isNotEmpty(storage.getID()) && shouldSetClientId) {
            mTracker.setClientId(storage.getID());
        }
        if(anonymizeUserIp){
            // Should turn on anonymize user ip.
            mTracker.setAnonymizeIp(true);
        }
    }

    /**
     * Get all the parameters from res/raw/plugin_configurations.json to use them
     * in the initialization.
     * @param params parameters from res/raw/plugin_configurations.json
     */
    @Override
    public void setParams(Map params) {
        super.setParams(params);
        mobileAppAccountId = getValue(params, MOBILE_APP_ACCOUNT_ID_IDENTIFIER);
        anonymizeUserIp = getValue(params, ANONYMIZE_USER_IP_IDENTIFIER).equals("1");
        screenViews = getValue(params, SCREEN_VIEWS_IDENTIFIER).equals("1");
        shouldSetClientId = !getValue(params, DO_NOT_SET_CLIENT_ID).equals("1");
    }

    /**
     * Get the value of the key present in plugin_configurations.json
     * @param params parameters
     * @param key key of the parameter
     * @return correspondent value of the parameter with key `key`
     */
    private String getValue(Map params, String key){
        String returnVal = "";
        if(params.get(key) != null){
            returnVal = params.get(key).toString();
        }
        return returnVal;
    }

    /**
     * It is a good practice to make the parameters of the plugin available with this method
     * @return a hash map of the configuration of the plugin
     */
    @Override
    public Map<String, String> getConfiguration() {
        Map<String, String> configuration = super.getConfiguration();
        if(mobileAppAccountId != null) {
            configuration.put(MOBILE_APP_ACCOUNT_ID_IDENTIFIER, mobileAppAccountId);
            configuration.put(ANONYMIZE_USER_IP_IDENTIFIER, Boolean.toString(anonymizeUserIp));
            configuration.put(SCREEN_VIEWS_IDENTIFIER, Boolean.toString(screenViews));
        }
        return configuration;
    }

    /**
     * This method is called when Zapp apps start tracking events (start tracking session)
     * @param context
     */
    @Override
    public void startTrackingSession(Context context) {
        super.startTrackingSession(context);
    }

    /**
     * This method is called when Zapp apps are closed
     * @param context
     */
    @Override
    public void stopTrackingSession(Context context) {
        super.stopTrackingSession(context);
        if (googleAnalytics != null) {
            APLogger.info(TAG, "Stop session without context");
            googleAnalytics = null;
            mTracker = null;
        }
    }


    @Override
    public void analyticsSwitch(boolean enabled) {
        super.analyticsSwitch(enabled);
    }

    /**
     * Log event
     * @param eventName name of the event logged
     */
    @Override
    public void logEvent(String eventName) {
        super.logEvent(eventName);
        logEvent(eventName, null);
    }

    /**
     * Log event with extra data
     * @param eventName name of the event logged
     * @param params extra data
     */
    @Override
    public void logEvent(String eventName, TreeMap<String, String> params) {
        super.logEvent(eventName, params);
        String label = getLabel(params);
        if (mTracker != null) {
            String[] partsColon = eventName.split(":");
            String[] parts = eventName.split("-");
            HitBuilders.EventBuilder eventBuilder = new HitBuilders.EventBuilder();
            if (partsColon.length >= 2) {
                logEvent(partsColon[0], partsColon[1], label, eventBuilder);
            } else if (parts.length >= 2) {
                logEvent(parts[0], parts[1], label, eventBuilder);
            } else {
                logEvent(eventName, eventName, label, eventBuilder);
            }
        }
    }

    // region discard these methods
    // custom method of log event used only for google analytics purposes (you can discard this method)
    private void logEvent(String category, String action, String label, HitBuilders.EventBuilder eventBuilder) {
        if (mTracker == null)
            return;
        eventBuilder.setCategory(category);
        if (!TextUtils.isEmpty(action)) {
            eventBuilder.setAction(action);
        }

        if (!TextUtils.isEmpty(label)) {
            eventBuilder.setLabel(label);
        }

        mTracker.send(eventBuilder.build());
        googleAnalytics.dispatchLocalHits();
    }

    private String getLabel(Map<String, String> map){
        String notAvailableString = "N/A";
        // Build the labels param.
        String labelsString = null;
        if(map != null) {
            StringBuilder labels = new StringBuilder();
            for (String key : map.keySet()) {
                String value = map.get(key);
                if (StringUtil.isEmpty(value)) {
                    value = notAvailableString;
                }
                String label = String.format("%s=%s;", key, value);
                labels.append(label);
            }

            if(labels.length() > 0){
                // If it's not empty, we need to remove the last ';'
                labels.setLength(labels.length() - 1);
            }

            labelsString = labels.toString();
        }

        return labelsString;
    }
    // endregion

    /**
     * Start tracking a single timed event with the given eventName
     *
     * @param eventName
     */
    @Override
    public void startTimedEvent(String eventName) {
        super.startTimedEvent(eventName);
    }

    /**
     * Start tracking a single timed event with the given eventName and params
     *
     * @param eventName
     * @param params
     */
    @Override
    public void startTimedEvent(String eventName, TreeMap<String, String> params) {
        super.startTimedEvent(eventName, params);
        logEvent(eventName, params);
    }

    /**
     * Stop tracking a single timed event with the given eventName
     *
     * @param eventName
     */
    @Override
    public void endTimedEvent(String eventName) {
        super.endTimedEvent(eventName);
    }

    /**
     * Set the User Id (UUID) on the Analytics Agent
     *
     * @param userId
     */
    @Override
    public void sendUserID(String userId) {
        super.sendUserID(userId);
    }

    /**
     * Send to agent Standard Properties.
     * This method should call only from AnalyticsStorage#setStandardProperties
     */
    @Override
    public void sendStandardProperties(JSONObject params) {
        super.sendStandardProperties(params);
    }

    /**
     * Send to agent the user properties.
     * This method should call only from AnalyticsStorage#setUserProperties
     */
    @Override
    public void sendUserProperties(JSONObject params) throws JSONException {
        super.sendUserProperties(params);
    }

    /**
     * Send to providers when the player session start.
     *
     * @param playable
     * @param playerWrapper
     * @param duration
     */
    @Override
    public void initPlayerSession(Playable playable, PlayerViewWrapper playerWrapper, int duration) {
        super.initPlayerSession(playable, playerWrapper, duration);
    }

    /**
     * Track a single player error.
     *
     * @param message
     */
    @Override
    public void handlePlayerError(String message) {
        super.handlePlayerError(message);
    }

    /**
     * Track a when the player starts play content.
     *
     * @param currentPosition
     */
    @Override
    public void logPlayEvent(long currentPosition) {
        super.logPlayEvent(currentPosition);
        logEvent(PLAY_EVENT);
    }

    /**
     * Track a when the player is not seen.
     */
    @Override
    public void logPlayerEnterBackground() {
        super.logPlayerEnterBackground();
    }

    /**
     * Track when pressed paused on player controller.
     */
    @Override
    public void logPauseButtonPressed() {
        super.logPauseButtonPressed();
    }

    /**
     * Track when pressed resume on player controller.
     */
    @Override
    public void logResumeButtonPressed() {
        super.logResumeButtonPressed();
    }

    /**
     * Track a when player paused.
     *
     * @param currentPosition
     */
    @Override
    public void logPauseEvent(long currentPosition) {
        super.logPauseEvent(currentPosition);
        logEvent(PAUSE_EVENT);
    }

    /**
     * Track when player stop.
     *
     * @param currentPosition
     */
    @Override
    public void logStopEvent(long currentPosition) {
        super.logStopEvent(currentPosition);
        logEvent(STOP_EVENT);
    }

    /**
     * Track a when player content end.
     *
     * @param currentPosition
     */
    @Override
    public void logVideoEndEvent(long currentPosition) {
        super.logVideoEndEvent(currentPosition);
    }

    /**
     * Track when user press on seek button.
     *
     * @param position
     */
    @Override
    public void logSeekStartEvent(long position) {
        super.logSeekStartEvent(position);
    }

    /**
     * Track a video event with the given eventName and params.
     * @param eventName
     * @param params
     */
    @Override
    public void logVideoEvent(String eventName, TreeMap<String, String> params) {
        super.logVideoEvent(eventName, params);
    }

    /**
     * Track a when user leave the seek button.
     * @param position
     */
    @Override
    public void logSeekEndEvent(int position) {
        super.logSeekEndEvent(position);
    }

    /**
     * Track a when player start buffering
     */
    @Override
    public void logBufferingStartEvent() {
        super.logBufferingStartEvent();
    }

    /**
     * Track a when player finish buffering
     */
    @Override
    public void logBufferingEndEvent() {
        super.logBufferingEndEvent();
    }

    /**
     * Track media info with params
     *
     * @param params
     */
    @Override
    public void generalPlayerInfoEvent(Map<String, String> params) {
        super.generalPlayerInfoEvent(params);
    }

    /**
     * Track when the video player goes into full-screen player
     * @param currentPosition
     */
    @Override
    public void logFullscreenPressed(int currentPosition) {
        super.logFullscreenPressed(currentPosition);
    }

    /**
     * Track when APBaseActivity finish OnCreate
     */
    @Override
    public void createTracking(Context context) {
        super.createTracking(context);
    }

    /**
     * Track when APBaseActivity finish OnPause
     */
    @Override
    public void pauseTracking(Context context) {
        super.pauseTracking(context);
    }

    /**
     * Track when APBaseActivity finish OnResume
     */
    @Override
    public void resumeTracking(Context context) {
        super.resumeTracking(context);
    }

    /**
     * Using this function when it good time to sent the event in the buffer.
     */
    @Override
    public void flushEvents(Context context) {
        super.flushEvents(context);
    }

    /**
     * Return false in case the event found in the black list events.
     */
    @Override
    public boolean isNotBlacklisted(String eventName) {
        return super.isNotBlacklisted(eventName);
    }

    /**
     *
     * @return a map containing key-value pairs of extra parameters
     */
    @Override
    protected Map<String, String> getExtraParameters(Set<String> remoteConfigKeysForMatch) {
        return super.getExtraParameters(remoteConfigKeysForMatch);
    }

    /**
     *  Analytics campaign measurements using urls that contain info parameters
     * @param url url, should contain campaign information
     */
    @Override
    public void trackCampaignParamsFromUrl(String url) {
        super.trackCampaignParamsFromUrl(url);
        if (mTracker != null) {
            mTracker.send(new HitBuilders.ScreenViewBuilder()
                    .setCampaignParamsFromUrl(url)
                    .build()
            );
        }
    }
}