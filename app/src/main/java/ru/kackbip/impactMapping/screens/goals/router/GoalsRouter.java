package ru.kackbip.impactMapping.screens.goals.router;

import android.app.Activity;
import android.content.Intent;

import ru.kackbip.impactMapping.application.CurrentActivityProvider;
import ru.kackbip.impactMapping.screens.goalCreation.view.GoalCreationView;

/**
 * Created by ryashentsev on 13.10.2016.
 */

public class GoalsRouter implements IGoalsRouter {

    private CurrentActivityProvider currentActivityProvider;

    public GoalsRouter(CurrentActivityProvider currentActivityProvider){
        this.currentActivityProvider = currentActivityProvider;
    }

    @Override
    public void showGoalCreationScreen() {
        Activity activity = currentActivityProvider.getCurrentActivity();
        Intent intent = new Intent(activity, GoalCreationView.class);
        activity.startActivity(intent);
    }
}
