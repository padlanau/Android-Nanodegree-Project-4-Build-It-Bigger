/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package au.com.market24seven.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import au.com.market24seven.gradle.jokes.Joker;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.market24seven.com.au",
                ownerName = "backend.market24seven.com.au",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that tells a corny jokes
     */
    @ApiMethod(name = "tellJoke")
    public MyBean tellJoke() {
        MyBean response = new MyBean();
        Joker joker = new Joker();
        response.setData(joker.getJoke());

        return response;
    }

}
