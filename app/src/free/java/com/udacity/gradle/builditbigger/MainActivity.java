package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.buildbigger.libjokevisualizer.JokeVisualizerActivity;


public class MainActivity extends AppCompatActivity implements AsyncFragment.IRequestState {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        }else{
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

    }

    @Override
    public void onDoInBackground() {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onPostExecute(String result) {
        displayJoke(result);
    }
}
