package evan.chen.tutorial.tdd.robolectricsample

import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowAlertDialog

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    private lateinit var activity: MainActivity

    @Before
    fun setupActivity() {

        MockitoAnnotations.initMocks(this)

        activity = Robolectric.buildActivity(MainActivity::class.java).setup().get()

    }

    @Test
    fun registerSuccessShouldDirectToResult() {
        //Step 1. Create shadowActivity
        val shadowActivity = Shadows.shadowOf(activity)

        //Step 2. Type UserId and Password, then click send button.
        val userId = "A123456789"
        val userPassword = "a123456789"
        activity.loginId.setText(userId)
        activity.password.setText(userPassword)
        activity.send.performClick()

        //Step 3. Verify intent
        val nextIntent = shadowActivity.nextStartedActivity

        //To assert nextIntent's class should be ResultActivity
        assertEquals(nextIntent.component!!.className, ResultActivity::class.java.name)

        //To assert nextIntent's size is 1
        assertEquals(1, nextIntent.extras!!.size())

        //To assert nextIntent's extra include ID
        assertEquals(userId, nextIntent.extras!!.getString("ID"))
    }

    @Test
    fun registerFailShouldAlert() {

        //arrange
        val userId = "A1234"
        val userPassword = "a123456789"
        activity.loginId.setText(userId)
        activity.password.setText(userPassword)

        //act
        activity.send.performClick()

        //assert
        val dialog = ShadowAlertDialog.getLatestDialog()
        assertNotNull(dialog)
        assertTrue(dialog.isShowing)
    }
}