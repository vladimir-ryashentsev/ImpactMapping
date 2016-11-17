package ru.kackbip.impactMapping.screens.goalCreation.router;

import ru.kackbip.impactMapping.application.CurrentActivityProvider;

/**
 * Created by ryashentsev on 15.11.2016.
 */

public class GoalCreationRouter implements IGoalCreationRouter {

    private CurrentActivityProvider currentActivityProvider;

    public GoalCreationRouter(CurrentActivityProvider currentActivityProvider){
        this.currentActivityProvider = currentActivityProvider;
    }

    @Override
    public void close() {
        currentActivityProvider.getCurrentActivity().finish();
    }
}
