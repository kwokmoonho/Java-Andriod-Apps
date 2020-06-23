package CodeKing.i_am_thankful_2;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import CodeKing.i_am_thankful_2.View.HomeActivity;
import CodeKing.i_am_thankful_2.View.InputActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class HomeActivityTest {
    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    private HomeActivity mActivity = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(InputActivity.class.getName(),null, false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

    /**
     * test insert function and the input activity
     */
    @Test
    public void testInsertClick() {
        assertNotNull(mActivity.findViewById(R.id.button_add_items));
        onView(withId(R.id.button_add_items)).perform(click());
        Activity inputActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(inputActivity);
        inputActivity.finish();
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void onActivityResult() {
    }

    @Test
    public void onCreateOptionsMenu() {
    }

    @Test
    public void onOptionsItemSelected() {
    }

    @Test
    public void buildRecyclerView() {
    }

    @Test
    public void setDaysCounter() {
    }

    @Test
    public void removeItem() {
    }
}