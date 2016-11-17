package ru.kackbip.impactMapping.screens.goalCreation.view;

import java.util.Date;

import ru.kackbip.impactMapping.screens.base.view.IBaseView;
import ru.kackbip.impactMapping.screens.goalCreation.presenter.GoalCreationPresenter;
import rx.Observable;

/**
 * Created by ryashentsev on 13.10.2016.
 */

public interface IGoalCreationView extends IBaseView<GoalCreationPresenter> {
    Observable<Void> waitForCreateClick();
    Observable<String> waitForTitleChanged();
    Observable<Date> waitForDateChanged();
}
