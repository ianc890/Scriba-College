package com.example.scriba.scribacollege.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.scriba.scribacollege.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UploadActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void uploadActivityTest() {

        ViewInteraction appCompatEditText = onView(
                withId(R.id.editTextEmail));
        appCompatEditText.perform(scrollTo(), replaceText("t"), closeSoftKeyboard());

        ViewInteraction appCompatEditTextTwo = onView(
                withId(R.id.editTextPassword));
        appCompatEditTextTwo.perform(scrollTo(), replaceText("t"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonLogin), withText("Login")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.ivAttachment),
                        withParent(withId(R.id.content_main)),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.b_upload), withText("Upload"),
                        withParent(withId(R.id.content_main)),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv_file_name), withText("File Upload completed.?? You can see the uploaded file here: ??http://coderefer.com/extras/uploads/Showcase Poster Instructions.docx"),
                        childAtPosition(
                                allOf(withId(R.id.content_main),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.View.class),
                                                1)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("File Upload completed.")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
