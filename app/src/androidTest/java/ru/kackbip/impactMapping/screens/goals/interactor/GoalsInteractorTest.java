package ru.kackbip.impactMapping.screens.goals.interactor;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import ru.kackbip.impactMapping.api.IApi;
import ru.kackbip.impactMapping.api.projections.Goals;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ryashentsev on 10.11.2016.
 */
@RunWith(AndroidJUnit4.class)
public class GoalsInteractorTest {

    private static final Goals.Goal GOAL1 = new Goals.Goal(UUID.randomUUID(), "Цель1", new Date(1000));
    private static final Goals.Goal GOAL2 = new Goals.Goal(UUID.randomUUID(), "Цель2", new Date(2000));
    private static final Goals GOALS = new Goals(Arrays.asList(GOAL1, GOAL2));

    private GoalsInteractor interactor;
    private IApi api;

    @Before
    public void init() {
        api = mock(IApi.class);
        when(api.observe(Goals.class)).thenReturn(Observable.create(subscriber -> subscriber.onNext(GOALS)));
        interactor = new GoalsInteractor(api);
    }

    @Test
    public void observeGoals() {
        TestSubscriber<Goals> subscriber = new TestSubscriber<>();
        interactor.waitForGoals().subscribe(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertNotCompleted();
        assertTrue(subscriber.getOnNextEvents().size() == 1);

        Goals goals = subscriber.getOnNextEvents().get(0);
        assertTrue(goals.getGoals().size() == 2);

        Goals.Goal goal1 = goals.getGoals().get(0);
        assertEquals(GOAL1.getTitle(), goal1.getTitle());
        assertEquals(GOAL1.getDate(), goal1.getDate());

        Goals.Goal goal2 = goals.getGoals().get(1);
        assertEquals(GOAL2.getTitle(), goal2.getTitle());
        assertEquals(GOAL2.getDate(), goal2.getDate());
    }

}