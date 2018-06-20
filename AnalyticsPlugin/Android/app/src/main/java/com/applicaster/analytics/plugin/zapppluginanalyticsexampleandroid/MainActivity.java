package com.applicaster.analytics.plugin.zapppluginanalyticsexampleandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.applicaster.analytics.AnalyticsAgentUtil;
import com.applicaster.analytics.plugin.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String PARAM_1 = "param 1";
    private static final String PARAM_2 = "param 2";

    private ListView actionList;

    private static final String CLICKING_BUTTON_1 = "Clicking button 1";
    private static final String CLICKING_BUTTON_2 = "Clicking button 2";
    private static final String OTHER_ACTION = "Other action";
    private static final String PLAY_VIDEO = "Play \"video\"";
    private static final String PAUSE_VIDEO = "Pause \"video\"";
    private static final String STOP_VIDEO = "Stop \"video\"";

    String[] actionsString = new String[] { CLICKING_BUTTON_1, CLICKING_BUTTON_2,
            OTHER_ACTION, PLAY_VIDEO, PAUSE_VIDEO, STOP_VIDEO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        actionList = findViewById(R.id.list_actions);
        List<String> actions = new ArrayList<>(Arrays.asList(actionsString));
        actionList.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1,
                actions));
        actionList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (((TextView)view).getText().toString()){
            case CLICKING_BUTTON_1:
                AnalyticsAgentUtil.logEvent(CLICKING_BUTTON_1);
                break;
            case CLICKING_BUTTON_2:
                AnalyticsAgentUtil.logEvent(CLICKING_BUTTON_2);
                break;
            case OTHER_ACTION:
                HashMap<String, String> params = new HashMap<>();
                params.put(PARAM_1, "param 1");
                params.put(PARAM_2, "param 2");
                AnalyticsAgentUtil.logEvent(OTHER_ACTION, params);
                break;
            case PLAY_VIDEO:
                AnalyticsAgentUtil.logPlayEvent(0);
                break;
            case PAUSE_VIDEO:
                AnalyticsAgentUtil.logPauseEvent(12);
                break;
            case STOP_VIDEO:
                AnalyticsAgentUtil.logStopEvent(24);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        AnalyticsAgentUtil.pauseTracking(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AnalyticsAgentUtil.resumeTracking(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        AnalyticsAgentUtil.flushEvents(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
