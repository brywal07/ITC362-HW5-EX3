import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.brywal07.criminalintentbw.CrimeDetailFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyTestSuite {
    @Test fun testEventFragment() {
        val scenario = launchFragmentInContainer<CrimeDetailFragment>()
        scenario.onFragment { fragment ->
            fragment.crime.isSolved
        }
    }
}