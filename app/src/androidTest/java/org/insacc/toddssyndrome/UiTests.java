package org.insacc.toddssyndrome;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.insacc.toddssyndrome.PatientList.PatientListActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by can on 19.02.2017.
 */
@RunWith(AndroidJUnit4.class)
public class UiTests {

    @Rule
    public ActivityTestRule<PatientListActivity> mActivityRule =
            new ActivityTestRule<>(PatientListActivity.class);

    @Test
    public void ensurePatientListViewWorks() {

        onView(withId(R.id.add_new_test_fab)).check(matches(isDisplayed()));
        onView(withId(R.id.add_new_test_fab)).perform(click());

        onView(withId(R.id.patient_test_name)).check(matches(isDisplayed()));
        onView(withId(R.id.patient_test_first_name)).check(matches(isDisplayed()));
        onView(withId(R.id.patient_test_done_button)).check(matches(isDisplayed()));
        onView(withId(R.id.migraine_radio_group)).check(matches(isDisplayed()));
        onView(withId(R.id.drug_radio_group)).check(matches(isDisplayed()));
        onView(withId(R.id.gender_radio_group)).check(matches(isDisplayed()));

        onView(withId(R.id.patient_test_name)).perform(typeText("Can"));
        onView(withId(R.id.patient_test_first_name)).perform(typeText("Undeger"));
        onView(withId(R.id.patient_test_done_button)).perform(click());


    }
}
