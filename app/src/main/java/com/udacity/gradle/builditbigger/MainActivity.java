package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.buildbigger.libjokevisualizer.JokeVisualizerActivity;
import com.udacity.gradle.builditbigger.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements AsyncFragment.IRequestState {
    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }

    public void tellJoke(View view) {
        requestJokeFromApi();
    }

    private void requestJokeFromApi() {

        AsyncFragment asyncFragment = (AsyncFragment) getSupportFragmentManager().findFragmentByTag(AsyncFragment.TAG);
        if (asyncFragment == null) {
            asyncFragment = AsyncFragment.newInstance();
        }
        asyncFragment.setRequestStateListener(this);

        if (!asyncFragment.isAdded()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(asyncFragment, AsyncFragment.TAG)
                    .commitAllowingStateLoss();
        } else {
            asyncFragment.requestJoke();
        }

    }

    private void displayJoke(String joke) {
        Intent intent = new Intent(this, JokeVisualizerActivity.class);
        intent.putExtra("joke", joke);

        startActivity(intent);

    }


    @Override
    public void onPreExecute() {
        mBinding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDoInBackground() {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onPostExecute(String result) {
        mBinding.progressBar.setVisibility(View.GONE);
        displayJoke(result);
    }
}
