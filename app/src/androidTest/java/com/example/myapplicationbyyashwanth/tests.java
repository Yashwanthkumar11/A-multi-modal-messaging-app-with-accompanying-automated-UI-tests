package com.example.myapplicationbyyashwanth;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class tests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test1() throws InterruptedException {
        onView(withId(R.id.message)).perform(typeText("Typing random text"));
        closeSoftKeyboard();
        onView(withId(R.id.sendButton)).perform(click());
        onView(withContentDescription("Navigate up")).perform(click());
        onView(withId(R.id.message)).check(matches(withText("Typing random text")));

        String word = "hello Android how are you";
        onView(withId(R.id.button2)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.message)).check(matches(withText(word)));
    }

    @Test
    public void test2(){
        onView(withId(R.id.message)).perform(typeText("Typing random text"));
        closeSoftKeyboard();
        onView(withId(R.id.sendButton)).perform(click());
        onView(withId(R.id.phone)).perform(typeText("123456"));
        closeSoftKeyboard();
        onView(withId(R.id.confirmButton)).perform(click());
        onView(withId(R.id.phone)).check(matches(withHint("Enter a valid phone number or email address.")));
        onView(withId(R.id.phone)).perform(typeText("abcd@xyz,com"));
        closeSoftKeyboard();
        onView(withId(R.id.confirmButton)).perform(click());
        onView(withId(R.id.phone)).check(matches(withHint("Enter a valid phone number or email address.")));
    }
}
