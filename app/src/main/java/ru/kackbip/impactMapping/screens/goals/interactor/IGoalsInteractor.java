package ru.kackbip.impactMapping.screens.goals.interactor;

import ru.kackbip.impactMapping.api.projections.Goals;
import rx.Observable;

/**
 * Created by ryashentsev on 08.10.2016.
 */

public interface IGoalsInteractor {
    Observable<Goals> waitForGoals();
}
