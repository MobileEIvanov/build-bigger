package com.udacity.gradle.builditbigger.backend;

import com.buildbigger.libJoker.JokeCreator;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * Joke generator end point
     */
    @ApiMethod(name = "generateJoke")
    public MyBean generateJoke() {
        MyBean response = new MyBean();

        response.setData(JokeCreator.jokeGenerator());

        return response;
    }

}
