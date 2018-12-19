import io.binayak.util.DependencyInjection
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext
import org.koin.test.KoinTest

/**
 * Purpose: Testing
 */
class MainAppTest : KoinTest {
    @Before
    fun before() {
        StandAloneContext.startKoin(DependencyInjection.injectionModules)
    }

    @Test
    fun testSomething() {
        // TODO: write tests
    }

    @After
    fun after() {
        StandAloneContext.stopKoin()
    }
}