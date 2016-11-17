package ru.kackbip.impactMapping.screens.base.presenter;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import ru.kackbip.impactMapping.screens.goals.view.IGoalsView;
import rx.AsyncEmitter;
import rx.Observable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ryashentsev on 13.11.2016.
 */
public class BasePresenterTest {

    private IGoalsView view;
    private AsyncEmitter<Void> destroyEmitter;
    private BasePresenter<IGoalsView> basePresenter;
    private AtomicBoolean isDestroyMethodCalled = new AtomicBoolean(false);

    private Observable<Void> destroyObservable(){
        return Observable.fromEmitter(emitter -> this.destroyEmitter = emitter, AsyncEmitter.BackpressureMode.ERROR);
    }

    @Before
    public void init(){
        view = mock(IGoalsView.class);
        when(view.waitForDestroy()).thenReturn(destroyObservable());


        basePresenter = new BasePresenter<IGoalsView>() {
            @Override
            protected void onViewDestroy() {
                isDestroyMethodCalled.set(true);
            }
        };
    }

    @Test
    public void destroy(){
        basePresenter.setView(view);
        verify(view).waitForDestroy();
        destroyEmitter.onNext(null);
        assertTrue(isDestroyMethodCalled.get());

        isDestroyMethodCalled.set(false);
        destroyEmitter.onNext(null);
        assertFalse(isDestroyMethodCalled.get());
    }

}