package com.example.quizapp;

import java.util.LinkedList;
import java.util.List;
import android.app.Activity;
import android.app.Application;

public class DealActivity extends Application {
    private List<Activity> activityList = new LinkedList<Activity>();
    private static DealActivity instance;

    private DealActivity(){}

    public synchronized static DealActivity getInstance(){
        if (null == instance) {
            instance = new DealActivity();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity:activityList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
