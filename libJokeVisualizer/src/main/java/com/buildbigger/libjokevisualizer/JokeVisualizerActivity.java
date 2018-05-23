package com.buildbigger.libjokevisualizer;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.buildbigger.libjokevisualizer.databinding.ActivityJokeVisualizerBinding;


public class JokeVisualizerActivity extends AppCompatActivity {
    public static final int REQUEST_JOKE_VISUALIZE = 2412;
    private ActivityJokeVisualizerBinding mBinding;


    private final View.OnClickListener mListenerMore = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (getIntent() != null) {
                setResult(RESULT_OK, getIntent());
                finish();
            } else {
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(JokeVisualizerActivity.this, R.layout.activity_joke_visualizer);
        String joke = getIntent().getStringExtra("joke");

        mBinding.tvJokeText.setText(joke);
        mBinding.btnWantMore.setOnClickListener(mListenerMore);
    }
}
