package ru.kackbip.impactMapping.screens.acceptance.goalsManagement;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;

import ru.kackbip.impactMapping.R;
import ru.kackbip.impactMapping.screens.goals.view.GoalsView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by ryashentsev on 17.11.2016.
 */

public class CreatingGoal {

    @Rule
    public ActivityTestRule<GoalsView> rule = new ActivityTestRule<>(GoalsView.class);

    @Test
    public void goalCreation(){
        String title = "Цель!";
        Date date = new Date(3000);

        whenICreateAGoal(title, date);
        thenTheGoalAppearsInTheGoalsList(title, date);
    }

    private void whenICreateAGoal(String title, Date date){
        //open goal creation screen
        onView(withId(R.id.fab)).perform(click());

        //enter data
        onView(withId(R.id.title)).perform(replaceText(title));
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        onView(withId(R.id.date)).perform(replaceText(df.format(date)));

        //click create
        onView(withId(R.id.create)).perform(click());
    }

    private void thenTheGoalAppearsInTheGoalsList(String title, Date date){
        //verify we on the currect activity
        onView(withText("Окончание:"));
        onView(withText(title));
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        onView(withText(df.format(date)));
    }
}
