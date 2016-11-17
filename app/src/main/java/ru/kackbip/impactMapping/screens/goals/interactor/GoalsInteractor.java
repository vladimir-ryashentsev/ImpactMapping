package ru.kackbip.impactMapping.screens.goals.interactor;

import ru.kackbip.impactMapping.api.IApi;
import ru.kackbip.impactMapping.api.projections.Goals;
import rx.Observable;

/**
 * Created by ryashentsev on 08.10.2016.
 */

public class GoalsInteractor implements IGoalsInteractor {

    private IApi api;

    public GoalsInteractor(IApi api){
        this.api = api;
    }

    @Override
    public Observable<Goals> waitForGoals() {
        return api.observe(Goals.class);
    }
}
