package ru.kackbip.impactMapping.screens.goalCreation.presenter;

import java.util.Date;

import ru.kackbip.impactMapping.screens.base.presenter.BasePresenter;
import ru.kackbip.impactMapping.screens.goalCreation.interactor.IGoalCreationInteractor;
import ru.kackbip.impactMapping.screens.goalCreation.router.IGoalCreationRouter;
import ru.kackbip.impactMapping.screens.goalCreation.view.IGoalCreationView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ryashentsev on 13.10.2016.
 */

public class GoalCreationPresenter extends BasePresenter<IGoalCreationView> {

    private IGoalCreationInteractor interactor;
    private IGoalCreationRouter router;

    private Subscription createSubscription;
    private Subscription dateSubscription;
    private Subscription titleSubscription;

    private String title;
    private Date date;

    public GoalCreationPresenter(IGoalCreationInteractor interactor, IGoalCreationRouter router) {
        this.interactor = interactor;
        this.router = router;
    }

    public void setView(IGoalCreationView view) {
        super.setView(view);
        subscribeForViewEvents();
    }

    @Override
    protected void onViewDestroy() {
        createSubscription.unsubscribe();
        dateSubscription.unsubscribe();
        titleSubscription.unsubscribe();
        title = null;
        date = null;
    }

    private void subscribeForViewEvents() {
        createSubscription = view.waitForCreateClick().subscribe(aVoid -> createGoal());
        dateSubscription = view.waitForDateChanged().subscribe(date -> this.date = date);
        titleSubscription = view.waitForTitleChanged().subscribe(title -> this.title = title);
    }

    private void createGoal() {
        interactor.addGoal(title, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        aVoid -> {
                            router.close();
                        },
                        throwable -> view.showError()
                );
    }
}
