package com.udacity.gradle.builditbigger;

/**
 * Created by emil.ivanov on 5/23/18.
 *
 * Used by {@link MainActivityFragment} in order to pass events to interested listeners
 * Depending on the free or paid flavor the action will differ
 *
 */
public interface ITellJokeInteraction {
    void onTellJokeEvent();
}
