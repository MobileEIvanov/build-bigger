import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.allOf;

/**
 * Instrumentation test for Free application flow
 */
@SuppressWarnings("CanBeFinal")
@RunWith(AndroidJUnit4.class)
public class PaidVersionTestFlow {
    public static final String INSTRUCTIONS = "Press the button for a delicious joke!";
    private static final String ACTION_TEXT = "Tell me A Joke";

    /**
     * The ActivityTestRule is a rule provided by Android used for functional testing of a single
     * activity. The activity that will be tested will be launched before each test that's annotated
     * with @Test and before methods annotated with @Before. The activity will be terminated after
     * the test and methods annotated with @After are complete. This rule allows you to directly
     * access the activity during the test.
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void check_isMain_Content_Displayed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.fragment)).check(matches(isDisplayed()));
    }

    @Test
    public void checkAll_Free_ComponentsAreDisplayed() {

        onView(allOf(instanceOf(TextView.class), withId(R.id.instructions_text_view)))
                .check(matches(withText(INSTRUCTIONS)));

        onView(CoreMatchers.<View>instanceOf(Button.class))
                .check(matches(withText(ACTION_TEXT)));

        onView(CoreMatchers.<View>instanceOf(Button.class))
                .perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.tv_joke_text))
                .check(matches(isDisplayed()));
    }
}
