package CodeKing.i_am_thankful_2;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import CodeKing.i_am_thankful_2.View.InputActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class InputActivityTest {
    @Rule
    public ActivityTestRule<InputActivity> mActivityTestRule = new ActivityTestRule<>(InputActivity.class);
    private InputActivity mActivity = null;
    //Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(InputActivity.class.getName(),null, false);
    private String t1 = "We are in USA";
    private String t2 = "We still alive";
    private String t3 = "We are all healthy";
    private String t4 = "We are so smart";
    private String t5 = "We are blessed";
    private String t6 = "We have a good family";
    private String t7 = "We have enough money to use";
    private String t8 = "We have a life goal";
    private String t9 = "We are LDS";
    private String t10 = "The world is still running";



    /**
     * test the user input into the text
     */
    @Test
    public void testUserInputScenario() {
        onView(withId(R.id.entry1)).perform(typeText(t1));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.entry2)).perform(typeText(t2));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.entry3)).perform(typeText(t3));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.entry4)).perform(typeText(t4));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.entry5)).perform(typeText(t5));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.save_note)).perform(click());
    }

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void onCreateOptionsMenu() {
    }

    @Test
    public void onOptionsItemSelected() {
    }
}