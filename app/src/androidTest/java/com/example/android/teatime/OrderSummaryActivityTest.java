package com.example.android.teatime;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

// TODO (1) Add annotation to specify AndroidJUnitRunner class as the default test runner
@RunWith(AndroidJUnit4.class)
public class OrderSummaryActivityTest {

    private static final String INTENT_EXTRA_SUBJECT = "Freshly Brewed from TeaTime";
    private static final String INTENT_EXTRA_MESSAGE = "I just ordered a delicious tea from TeaTime. Next time you are craving a tea, check them out!";

    // TODO (2) Add the rule that indicates we want to use Espresso-Intents APIs in functional UI tests
    @Rule
    public IntentsTestRule<OrderSummaryActivity> intentsTestRule = new IntentsTestRule<>(OrderSummaryActivity.class);

    // TODO (3) Finish this method which runs before each test and will stub all external
    // intents so all external intents will be blocked
    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }


    // TODO (4) Finish this method which verifies that the intent sent by clicking the send email
    // button matches the intent sent by the application
    @Test
    public void clickSendEmailButton_SendsEmail() {
        onView(withId(R.id.send_email_button))
                .perform(click());
        intended(allOf(
                hasAction(Intent.ACTION_SENDTO),
                hasExtra(Intent.EXTRA_SUBJECT, INTENT_EXTRA_SUBJECT),
                hasExtra(Intent.EXTRA_TEXT, INTENT_EXTRA_MESSAGE)
        ));
    }

}
