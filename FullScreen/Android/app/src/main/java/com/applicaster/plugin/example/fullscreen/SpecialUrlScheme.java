package com.applicaster.plugin.example.fullscreen;

import android.net.Uri;
import android.util.Log;

import com.applicaster.util.UrlSchemeUtil;

import java.util.Map;

public class SpecialUrlScheme {

    private SpecialAction action;
    private Uri originalUri;
    public Map<String, String> data;

    public enum SpecialAction {
        plugin;

        public static SpecialAction fromName(String name) {
            SpecialAction result = null;
            if (plugin.name().equalsIgnoreCase(name)) {
                result = plugin;
            }
            return result;
        }
    }

    void setAction(String actionStr) {
        action = SpecialAction.fromName(actionStr);
    }

    public void setData(Map<String, String> splitQuery) {
        // TODO Auto-generated method stub
        data = splitQuery;
    }


    public static SpecialUrlScheme parseData(Uri data) {
        SpecialUrlScheme result = new SpecialUrlScheme();
        if (data != null) {
            result.originalUri = data;
            try {
                result.setAction(data.getHost());
                result.setData(UrlSchemeUtil.splitQuery(data));
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("UrlSchemeUtil", "Failded to parse data: " + data);
            }
        }
        return result;
    }
}