package ru.kackbip.impactMapping.screens.goals.view;

import ru.kackbip.impactMapping.api.projections.Goals;
import ru.kackbip.impactMapping.screens.base.view.IBaseView;
import ru.kackbip.impactMapping.screens.goals.presenter.GoalsPresenter;
import rx.Observable;

/**
 * Created by ryashentsev on 08.10.2016.
 */

public interface IGoalsView extends IBaseView<GoalsPresenter> {
    void showGoals(Goals goals);
    Observable<Void> waitForCreateClick();
}
