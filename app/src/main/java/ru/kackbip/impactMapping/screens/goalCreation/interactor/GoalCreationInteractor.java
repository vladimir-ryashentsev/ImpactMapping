package ru.kackbip.impactMapping.screens.goalCreation.interactor;

import java.util.Date;
import java.util.UUID;

import ru.kackbip.impactMapping.api.IApi;
import ru.kackbip.impactMapping.api.commands.AddGoalCommand;
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
    public Observable<Void> addGoal(UUID id, String title, Date date) {
        return api.execute(new AddGoalCommand(id, title, date));
    }
}
