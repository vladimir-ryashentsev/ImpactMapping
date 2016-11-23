package ru.kackbip.impactMapping.goalsManagement;

import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.widget.DatePicker;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import ru.kackbip.impactMapping.R;
import ru.kackbip.impactMapping.screens.goals.view.GoalsView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by ryashentsev on 17.11.2016.
 */

public class CreatingGoal {

    @Rule
    public ActivityTestRule<GoalsView> rule = new ActivityTestRule<>(GoalsView.class);

    @Test
    public void goalCreation() {
        String title = "Цель!";
        Date date = new Date(3000);

        whenICreateAGoal(title, date);
        thenTheGoalAppearsInTheGoalsList(title, date);
    }

    private void whenICreateAGoal(String title, Date date) {
        //open goal creation screen
        onView(withId(R.id.fab)).perform(click());

        //enter data
        onView(withId(R.id.title)).perform(replaceText(title));
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

        onView(withId(R.id.date)).perform(click());
        // Change the date of the DatePicker. Don't use "withId" as at runtime Android shares the DatePicker id between several sub-elements
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE)));
        // Click on the "OK" button to confirm and close the dialog
        onView(withId(android.R.id.button1)).perform(click());

        //click create
        onView(withId(R.id.create)).perform(click());
    }

    private void thenTheGoalAppearsInTheGoalsList(String title, Date date) {
        //verify if view with targets title and date exists
        Matcher<String> matcher = new BaseMatcher<String>() {
            @Override
            public boolean matches(Object item) {
                if (item == null ||
                        !(item instanceof String)) return false;
                DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
                String string = (String) item;
                return string.contains(df.format(date));
            }

            @Override
            public void describeTo(Description description) {

            }
        };
        onView(withText(title)).check(matches(isDisplayed()));
        onView(withText(matcher)).check(matches(isDisplayed()));
    }
}
