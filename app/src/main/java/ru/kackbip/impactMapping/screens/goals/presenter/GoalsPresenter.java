package ru.kackbip.impactMapping.screens.goals.presenter;

import ru.kackbip.impactMapping.screens.base.presenter.BasePresenter;
import ru.kackbip.impactMapping.screens.goals.interactor.IGoalsInteractor;
import ru.kackbip.impactMapping.screens.goals.router.IGoalsRouter;
import ru.kackbip.impactMapping.screens.goals.view.IGoalsView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ryashentsev on 08.10.2016.
 */

public class GoalsPresenter extends BasePresenter<IGoalsView> {

    private IGoalsInteractor interactor;
    private IGoalsRouter router;

    private Subscription mClickSubscription;
    private Subscription mGoalsSubscription;

    public GoalsPresenter(IGoalsInteractor interactor, IGoalsRouter router){
        this.interactor = interactor;
        this.router = router;
    }

    public void setView(IGoalsView view){
        super.setView(view);
        subscribeForCreateClick();
        subscribeForGoals();
    }

    @Override
    protected void onViewDestroy() {
        mClickSubscription.unsubscribe();
        mGoalsSubscription.unsubscribe();
    }

    private void subscribeForCreateClick(){
        mClickSubscription = view.waitForCreateClick()
                .subscribe(this::showGoalCreationScreen);
    }

    private void subscribeForGoals(){
        mGoalsSubscription = interactor.waitForGoals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::showGoals, throwable -> view.showError());
    }

    private void showGoalCreationScreen(Void aVoid){
        router.showGoalCreationScreen();
    }
}
