package ru.kackbip.impactMapping.screens.goalCreation.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Date;
import java.util.UUID;

import ru.kackbip.impactMapping.api.IApi;
import ru.kackbip.impactMapping.api.commands.AddGoalCommand;
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
        when(api.execute(any(AddGoalCommand.class))).thenReturn(Observable.just(null));

        interactor = new GoalCreationInteractor(api);
    }

    @Test
    public void addGoal() throws Exception {
        String title = "Заголовок";
        Date date = new Date(3000);
        UUID id = UUID.randomUUID();

        TestSubscriber<Void> subscriber = new TestSubscriber<>();

        interactor.addGoal(id, title, date).subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertValue(null);
        subscriber.assertCompleted();

        ArgumentCaptor<AddGoalCommand> captor = ArgumentCaptor.forClass(AddGoalCommand.class);
        verify(api).execute(captor.capture());

        AddGoalCommand command = captor.getValue();
        assertEquals(id, command.getId());
        assertEquals(title, command.getTitle());
        assertEquals(date, command.getDate());
    }
}