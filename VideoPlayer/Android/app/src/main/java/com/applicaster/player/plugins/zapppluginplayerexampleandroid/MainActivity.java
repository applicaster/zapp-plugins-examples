package com.applicaster.player.plugins.zapppluginplayerexampleandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.applicaster.model.APURLPlayable;
import com.applicaster.plugin_manager.playersmanager.PlayerContract;
import com.applicaster.plugin_manager.playersmanager.internal.PlayableType;
import com.applicaster.plugin_manager.playersmanager.internal.PlayersManager;
import com.applicaster.util.UrlSchemeUtil;

/**
 * This sample activity will create a player contract which will create an instance of your player
 * and pass it a sample playable item. From there you have options to launch full screen or attach inline.
 * This is for testing your implementation against the Zapp plugin system.
 * <p>
 * Note: You must have your player plugin module in this project and the appropriate plugin manifest
 * in the plugin_configurations.json
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout videoLayout;
    private boolean inlineAttached = false;
    private PlayerContract playerContract;
    Button fullScreenButton;
    Button inlineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoLayout = findViewById(R.id.video_layout);

        fullScreenButton = findViewById(R.id.fullscreen_button);
        inlineButton = findViewById(R.id.inline_button);

        fullScreenButton.setOnClickListener(this);
        inlineButton.setOnClickListener(this);

        // Mock playable item. Replace this with the playable item your player expects
        APURLPlayable playable = new APURLPlayable("https://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", "Buck Bunny", "Test Video");

        // Player type should be left as default (covers the standard player as well as all plugin players)
        playable.setType(PlayableType.Default);

        // Player contract will get the instance of your plugin player and pass it the playable item
        playerContract = PlayersManager.getInstance().createPlayer(playable, this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fullscreen_button: {
                launchFullscreen();
            }
            break;
            case R.id.inline_button: {
                toggleInline();
            }
            break;
        }
    }

    /**
     * Mimics the functionality of launching a fullscreen player in the Zapp platform
     * This should not be modified
     */
    private void launchFullscreen() {
        playerContract.playInFullscreen(null, UrlSchemeUtil.PLAYER_REQUEST_CODE, this);
    }

    /**
     * Mimics the functionality of adding or removing an inline player in the Zapp platform
     * This should not be modified
     */
    private void toggleInline() {
        if (!inlineAttached) {
            inlineButton.setText("Remove Inline");
            inlineAttached = true;
            playerContract.attachInline(videoLayout);
            playerContract.playInline(null);
        } else {
            inlineButton.setText("Play Inline");
            inlineAttached = false;
            playerContract.stopInline();
            playerContract.removeInline(videoLayout);
        }
    }
}
