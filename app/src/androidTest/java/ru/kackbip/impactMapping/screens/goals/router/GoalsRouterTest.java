package ru.kackbip.impactMapping.screens.goals.router;

import android.content.Intent;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import ru.kackbip.impactMapping.application.CurrentActivityProvider;
import ru.kackbip.impactMapping.screens.goalCreation.view.GoalCreationView;
import ru.kackbip.impactMapping.screens.goals.view.GoalsView;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ryashentsev on 10.11.2016.
 */
@RunWith(AndroidJUnit4.class)
public class GoalsRouterTest {

    @Test
    public void showGoalCreationScreen() throws Exception {
        GoalsView activity = mock(GoalsView.class);

        CurrentActivityProvider currentActivityProvider = mock(CurrentActivityProvider.class);
        when(currentActivityProvider.getCurrentActivity()).thenReturn(activity);

        GoalsRouter router = new GoalsRouter(currentActivityProvider);
        router.showGoalCreationScreen();

        ArgumentCaptor<Intent> captor = ArgumentCaptor.forClass(Intent.class);
        verify(activity).startActivity(captor.capture());
        Intent intent = captor.getValue();
        assertEquals(GoalCreationView.class.getCanonicalName(), intent.getComponent().getClassName());
    }

}