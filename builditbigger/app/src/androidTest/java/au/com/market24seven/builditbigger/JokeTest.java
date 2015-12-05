package au.com.market24seven.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by androidau on 11/11/2015.
 */
public class JokeTest extends ApplicationTestCase<Application>  implements OnJokeRetrievedListener {

    private final CountDownLatch mSignal = new CountDownLatch(1);
    String joke;

    public JokeTest() {
        super(Application.class);
    }

    public void testJoke() {
        try {
            new EndpointsAsyncTask().execute(this);
            boolean success = mSignal.await(5, TimeUnit.SECONDS);
            if (!success) {
                fail("Testing timed out, check server is active");
            }
            assertNotNull("joke is null", joke);
            assertFalse("joke is empty", joke.isEmpty());
        } catch (Exception ex) {
            fail();
        }
    }

    @Override
    public void onJokeRetrieved(String joke) {
        this.joke = joke;
        mSignal.countDown();
    }

}
