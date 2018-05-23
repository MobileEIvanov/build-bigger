package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements ITellJokeInteraction, AsyncFragment.IRequestState {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        MainActivityFragment mainActivityFragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (mainActivityFragment != null) {
            mainActivityFragment.setListener(this);
        } else {
            Toast.makeText(this, R.string.error_loading_joke, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onTellJokeEvent() {
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

    @Override
    public void onPreExecute() {
        mBinding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCancel() {
        mBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPostExecute(String result) {
        mBinding.progressBar.setVisibility(View.GONE);
        MainActivityFragment mainActivityFragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (mainActivityFragment != null) {
            mainActivityFragment.displayJoke(result);
        } else {
            Toast.makeText(this, R.string.error_loading_joke, Toast.LENGTH_LONG).show();
        }
    }


}
