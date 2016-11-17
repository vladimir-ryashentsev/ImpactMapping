package ru.kackbip.impactMapping.screens.goalCreation.presenter;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import ru.kackbip.impactMapping.screens.goalCreation.interactor.IGoalCreationInteractor;
import ru.kackbip.impactMapping.screens.goalCreation.router.IGoalCreationRouter;
import ru.kackbip.impactMapping.screens.goalCreation.view.IGoalCreationView;
import rx.AsyncEmitter;
import rx.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ryashentsev on 15.11.2016.
 */
public class GoalCreationPresenterTest {

    private GoalCreationPresenter presenter;
    private IGoalCreationInteractor interactor;
    private IGoalCreationRouter router;
    private IGoalCreationView view;

    private AsyncEmitter<String> titleEmitter;
    private AsyncEmitter<Date> dateEmitter;
    private AsyncEmitter<Void> destroyEmitter;
    private AsyncEmitter<Void> createEmitter;

    private static final String TITLE = "Заголовок!";
    private static final Date DATE = new Date(40000);

    @Before
    public void init() {
        router = mock(IGoalCreationRouter.class);
        interactor = mock(IGoalCreationInteractor.class);
        presenter = new GoalCreationPresenter(interactor, router);
        view = mock(IGoalCreationView.class);
        when(view.waitForTitleChanged()).thenReturn(Observable.fromEmitter(emitter -> titleEmitter = emitter, AsyncEmitter.BackpressureMode.ERROR));
        when(view.waitForDateChanged()).thenReturn(Observable.fromEmitter(emitter -> dateEmitter = emitter, AsyncEmitter.BackpressureMode.ERROR));
        when(view.waitForDestroy()).thenReturn(Observable.fromEmitter(emitter -> destroyEmitter = emitter, AsyncEmitter.BackpressureMode.ERROR));
        when(view.waitForCreateClick()).thenReturn(Observable.fromEmitter(emitter -> createEmitter = emitter, AsyncEmitter.BackpressureMode.ERROR));
        when(interactor.addGoal(TITLE, DATE)).thenReturn(Observable.just(null));
    }

    @Test
    public void setView() {
        presenter.setView(view);
        verify(view).waitForCreateClick();
        verify(view).waitForDateChanged();
        verify(view).waitForTitleChanged();
        verify(view).waitForDestroy();
    }

    @Test
    public void createGoal() {
        presenter.setView(view);
        titleEmitter.onNext(TITLE);
        dateEmitter.onNext(DATE);
        createEmitter.onNext(null);
        verify(interactor).addGoal(TITLE, DATE);
        verify(router, timeout(50)).close();
    }

    @Test
    public void destroy(){
        presenter.setView(view);
        destroyEmitter.onNext(null);

        titleEmitter.onNext(TITLE);
        dateEmitter.onNext(DATE);
        createEmitter.onNext(null);
        verify(router, never()).close();
    }
}