package ru.kackbip.impactMapping.screens.goals.presenter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import ru.kackbip.impactMapping.api.projections.Goals;
import ru.kackbip.impactMapping.screens.goals.interactor.IGoalsInteractor;
import ru.kackbip.impactMapping.screens.goals.router.IGoalsRouter;
import ru.kackbip.impactMapping.screens.goals.view.IGoalsView;
import rx.AsyncEmitter;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ryashentsev on 13.11.2016.
 */

public class GoalsPresenterTest {

    private IGoalsInteractor interactor;
    private AsyncEmitter<Goals> goalsEmitter;
    private IGoalsRouter router;
    private GoalsPresenter presenter;
    private IGoalsView view;
    private AsyncEmitter<Void> clickEmitter;
    private AsyncEmitter<Void> destroyEmitter;

    private <T> Observable<T> prepareSyncObservable(Observable<T> observable){
        TestSubscriber<T> subscriber = new TestSubscriber<>();
        Observable<T> result = observable.cache();
        result.subscribe(subscriber);
        return result;
    }

    private Observable<Goals> goalsObservable(){
        return prepareSyncObservable(Observable.<Goals>fromEmitter(emitter -> this.goalsEmitter = emitter, AsyncEmitter.BackpressureMode.ERROR));
    }

    private Observable<Void> destroyObservable(){
        return prepareSyncObservable(Observable.<Void>fromEmitter(emitter -> this.destroyEmitter = emitter, AsyncEmitter.BackpressureMode.ERROR));
    }

    private Observable<Void> createObservable(){
        return prepareSyncObservable(Observable.<Void>fromEmitter(emitter -> this.clickEmitter = emitter, AsyncEmitter.BackpressureMode.ERROR));
    }

    @Before
    public void init() {
        interactor = mock(IGoalsInteractor.class);
        when(interactor.waitForGoals()).thenReturn(goalsObservable());

        router = mock(IGoalsRouter.class);

        presenter = new GoalsPresenter(interactor, router);

        view = mock(IGoalsView.class);
        when(view.waitForCreateClick()).thenReturn(createObservable());
        when(view.waitForDestroy()).thenReturn(destroyObservable());
    }

    @Test
    public void setView() {
        presenter.setView(view);
        verify(view).waitForCreateClick();
        verify(interactor).waitForGoals();
        verify(view).waitForDestroy();
    }

    @Test
    public void receiveGoals() {
        setView();
        Goals goals = new Goals(new ArrayList<>());
        goalsEmitter.onNext(goals);
        verify(view, timeout(50)).showGoals(goals);
    }

    @Test
    public void openGoalCreationScreen() {
        setView();
        clickEmitter.onNext(null);
        verify(router).showGoalCreationScreen();
    }

    @Test
    public void destroy(){
        setView();
        destroyEmitter.onNext(null);

        clickEmitter.onNext(null);
        verify(router, never()).showGoalCreationScreen();

        Goals goals = new Goals(new ArrayList<>());
        goalsEmitter.onNext(goals);
        verify(view, never()).showGoals(goals);
    }
}
