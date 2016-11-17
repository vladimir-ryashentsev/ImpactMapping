package ru.kackbip.impactMapping.screens.goalCreation.router;

import org.junit.Test;

import ru.kackbip.impactMapping.application.CurrentActivityProvider;
import ru.kackbip.impactMapping.screens.goalCreation.view.GoalCreationView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ryashentsev on 15.11.2016.
 */
public class GoalCreationRouterTest {

    @Test
    public void close(){
        CurrentActivityProvider currentActivityProvider = mock(CurrentActivityProvider.class);
        GoalCreationView activity = mock(GoalCreationView.class);
        when(currentActivityProvider.getCurrentActivity()).thenReturn(activity);
        GoalCreationRouter router = new GoalCreationRouter(currentActivityProvider);

        router.close();
        verify(activity).finish();
    }

}