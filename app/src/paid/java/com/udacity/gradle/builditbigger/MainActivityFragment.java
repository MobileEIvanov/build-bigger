package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.buildbigger.libjokevisualizer.JokeVisualizerActivity;
import com.udacity.gradle.builditbigger.databinding.FragmentMainBinding;

import static android.app.Activity.RESULT_OK;


public class MainActivityFragment extends Fragment {

    private ITellJokeInteraction mCallbackTellJokeInteraction;
    private FragmentMainBinding mBinding;


    private final View.OnClickListener mListenerClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mCallbackTellJokeInteraction != null) {
                mCallbackTellJokeInteraction.onTellJokeEvent();
            }
        }
    };

    public void setListener(ITellJokeInteraction mListener) {
        this.mCallbackTellJokeInteraction = mListener;
    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        mBinding.btnTellJoke.setOnClickListener(mListenerClick);
        return mBinding.getRoot();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == JokeVisualizerActivity.REQUEST_JOKE_VISUALIZE && resultCode == RESULT_OK) {
            mBinding.btnTellJoke.callOnClick();
        }
    }

    public void displayJoke(String joke) {
        Intent intent = new Intent(getActivity(), JokeVisualizerActivity.class);
        intent.putExtra("joke", joke);

        startActivityForResult(intent, JokeVisualizerActivity.REQUEST_JOKE_VISUALIZE);
    }


}
