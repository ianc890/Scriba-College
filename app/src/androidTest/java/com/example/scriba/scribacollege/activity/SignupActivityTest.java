package com.example.scriba.scribacollege.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.scriba.scribacollege.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SignupActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void signupActivityTest() {
        pressBack();

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.linkSignup), withText("No account yet? Create one")));
        appCompatTextView.perform(scrollTo(), click());

        ViewInteraction appCompatEditText = onView(
                withId(R.id.editTextFirstname));
        appCompatEditText.perform(scrollTo(), replaceText("Lee"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                withId(R.id.editTextSurname));
        appCompatEditText2.perform(scrollTo(), replaceText("McCarthy"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                withId(R.id.editTextEmail));
        appCompatEditText3.perform(scrollTo(), replaceText("lmac@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                withId(R.id.editTextUsername));
        appCompatEditText4.perform(scrollTo(), replaceText("lmac"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                withId(R.id.editTextPassword));
        appCompatEditText5.perform(scrollTo(), replaceText("password"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.editTextPassword), withText("password")));
        appCompatEditText6.perform(pressImeActionButton());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonRegister), withText("Register"),
                        withParent(withId(R.id.content_main))));
        appCompatButton.perform(scrollTo(), click());

    }

}
