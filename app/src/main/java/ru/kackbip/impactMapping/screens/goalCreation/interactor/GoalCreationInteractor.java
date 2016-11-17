package ru.kackbip.impactMapping.screens.goalCreation.interactor;

import java.util.Date;

import ru.kackbip.impactMapping.api.IApi;
import ru.kackbip.impactMapping.api.commands.CreateGoalCommand;
import rx.Observable;

/**
 * Created by ryashentsev on 13.10.2016.
 */

public class GoalCreationInteractor implements IGoalCreationInteractor {

    private IApi api;

    public GoalCreationInteractor(IApi api){
        this.api = api;
    }

    @Override
    public Observable<Void> addGoal(String title, Date date) {
        return api.execute(new CreateGoalCommand(title, date));
    }
}
