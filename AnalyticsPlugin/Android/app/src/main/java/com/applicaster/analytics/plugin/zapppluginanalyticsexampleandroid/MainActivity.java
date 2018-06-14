package com.applicaster.analytics.plugin.zapppluginanalyticsexampleandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.applicaster.analytics.AnalyticsAgentUtil;
import com.applicaster.analytics.BaseAnalyticsAgent;
import com.applicaster.analytics.plugin.R;
import com.applicaster.analytics.plugins.testanalyticsplugin_android.TestAnalyticsAgent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView actionList;

    String[] actionsString = new String[] { "Clicking button 1", "Clicking button 2",
            "Other action", "Play \"video\"", "Pause \"video\"", "Stop \"video\""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initAnalytics();

        AnalyticsAgentUtil.createTracking(this);
    }

    // Place here the params of your Analytics Plugin.
    // TestAnalyticsPlugin-Android is a GoogleAnalytics plugin for demo purposes
    private void initAnalytics() {
        TestAnalyticsAgent testAnalyticsAgent = new TestAnalyticsAgent();
        HashMap<String, String> params = new HashMap<>();
        params.put("mobile_app_account_id", "UA-120927512-1");
        params.put("do_not_set_client_id", "0");
        params.put("anonymize_user_ip", "0");
        params.put("screen_views", "1");
        testAnalyticsAgent.setParams(params);

        List<BaseAnalyticsAgent> list = new ArrayList<>();
        list.add(testAnalyticsAgent);
        AnalyticsAgentUtil.setAnalyticsAgentsList(list);
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

    }
}
