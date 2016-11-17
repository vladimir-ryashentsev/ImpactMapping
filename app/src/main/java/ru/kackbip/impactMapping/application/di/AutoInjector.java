package ru.kackbip.impactMapping.application.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.Map;

/**
 * Created by ryashentsev on 02.11.2016.
 */

public class AutoInjector implements Application.ActivityLifecycleCallbacks {

    private Map<Class, ? extends BaseComponent> components;

    public AutoInjector(Application application, Map<Class, BaseComponent> components){
        this.components = components;
        application.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        BaseComponent baseComponent = components.get(activity.getClass());
        if(baseComponent!=null) baseComponent.inject(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
