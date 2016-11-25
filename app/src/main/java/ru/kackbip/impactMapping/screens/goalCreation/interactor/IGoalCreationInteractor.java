package ru.kackbip.impactMapping.screens.goalCreation.interactor;

import java.util.Date;
import java.util.UUID;

import rx.Observable;

/**
 * Created by ryashentsev on 13.10.2016.
 */

public interface IGoalCreationInteractor {
    Observable<Void> addGoal(UUID id, String title, Date date);
}
