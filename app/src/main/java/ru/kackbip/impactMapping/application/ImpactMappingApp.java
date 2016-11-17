package ru.kackbip.impactMapping.application;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

import ru.kackbip.impactMapping.application.di.ApiModule;
import ru.kackbip.impactMapping.application.di.AppComponent;
import ru.kackbip.impactMapping.application.di.AppModule;
import ru.kackbip.impactMapping.application.di.AutoInjector;
import ru.kackbip.impactMapping.application.di.BaseComponent;
import ru.kackbip.impactMapping.application.di.DaggerAppComponent;
import ru.kackbip.impactMapping.application.di.GsonModule;
import ru.kackbip.impactMapping.screens.goalCreation.di.GoalCreationComponent;
import ru.kackbip.impactMapping.screens.goalCreation.di.GoalCreationModule;
import ru.kackbip.impactMapping.screens.goalCreation.view.GoalCreationView;
import ru.kackbip.impactMapping.screens.goals.di.GoalsComponent;
import ru.kackbip.impactMapping.screens.goals.di.GoalsModule;
import ru.kackbip.impactMapping.screens.goals.view.GoalsView;

/**
 * Created by ryashentsev on 08.10.2016.
 */

public class ImpactMappingApp extends Application {

    private AutoInjector autoInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences.Editor editor = getSharedPreferences("ImpactMappingSP", 0).edit();
        editor.clear();
        editor.commit();
        initAutoInjector();
    }

    private void initAutoInjector(){
        AppComponent appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .gsonModule(new GsonModule())
                .build();

        Map<Class, BaseComponent> components = new HashMap<>();
        GoalsComponent goalsComponent = appComponent.plusGoalsComponent(new GoalsModule());
        components.put(GoalsView.class, goalsComponent);
        GoalCreationComponent goalCreationComponent = appComponent.plusGoalCreationComponent(new GoalCreationModule());
        components.put(GoalCreationView.class, goalCreationComponent);

        autoInjector = new AutoInjector(this, components);
    }
}
