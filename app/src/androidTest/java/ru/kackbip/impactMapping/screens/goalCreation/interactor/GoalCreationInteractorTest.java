package ru.kackbip.impactMapping.screens.goalCreation.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Date;

import ru.kackbip.impactMapping.api.IApi;
import ru.kackbip.impactMapping.api.commands.CreateGoalCommand;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ryashentsev on 15.11.2016.
 */
public class GoalCreationInteractorTest {

    private GoalCreationInteractor interactor;
    private IApi api;

    @Before
    public void init(){
        api = mock(IApi.class);
        when(api.execute(any(CreateGoalCommand.class))).thenReturn(Observable.just(null));

        interactor = new GoalCreationInteractor(api);
    }

    @Test
    public void addGoal() throws Exception {
        TestSubscriber<Void> subscriber = new TestSubscriber<>();
        interactor.addGoal("Заголовок", new Date(3000)).subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertValue(null);
        subscriber.assertCompleted();

        ArgumentCaptor<CreateGoalCommand> captor = ArgumentCaptor.forClass(CreateGoalCommand.class);
        verify(api).execute(captor.capture());

        CreateGoalCommand command = captor.getValue();
        assertEquals("Заголовок", command.getTitle());
        assertEquals(new Date(3000), command.getDate());
    }
}