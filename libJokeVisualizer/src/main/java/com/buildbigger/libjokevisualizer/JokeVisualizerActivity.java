package com.buildbigger.libjokevisualizer;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.buildbigger.libjokevisualizer.databinding.ActivityJokeVisualizerBinding;

public class JokeVisualizerActivity extends AppCompatActivity {

    ActivityJokeVisualizerBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(JokeVisualizerActivity.this, R.layout.activity_joke_visualizer);
        String joke = getIntent().getStringExtra("joke");

        mBinding.tvJokeText.setText(joke);
    }
}
