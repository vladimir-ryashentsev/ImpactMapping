package ru.kackbip.impactMapping.screens.goalCreation.interactor;

import java.util.Date;

import rx.Observable;

/**
 * Created by ryashentsev on 13.10.2016.
 */

public interface IGoalCreationInteractor {
    Observable<Void> addGoal(String title, Date date);
}
