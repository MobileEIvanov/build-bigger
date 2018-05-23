package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.buildbigger.libjokevisualizer.JokeVisualizerActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.databinding.FragmentMainBinding;

import static android.app.Activity.RESULT_OK;
import static android.widget.Toast.*;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    ITellJokeInteraction mCallbackTellJokeInteraction;
    FragmentMainBinding mBinding;

    private InterstitialAd mInterstitialAd;


    View.OnClickListener mListenerClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mCallbackTellJokeInteraction != null) {
                mCallbackTellJokeInteraction.onTellJokeEvent();
            }
        }
    };

    private final AdListener mAddListener = new AdListener() {
        @Override
        public void onAdClosed() {
            // Load the next interstitial.
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            // Display new joke
            mBinding.btnTellJoke.callOnClick();
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

        loadBannerView();
        initializeFullScreenAdd();
        return mBinding.getRoot();
    }

    private void loadBannerView() {
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mBinding.adBannerView.loadAd(adRequest);
    }

    /**
     * Full screen. Implementation references
     * https://developers.google.com/admob/android/interstitial
     */
    private void initializeFullScreenAdd() {
        MobileAds.initialize(getActivity(),
                getString(R.string.adds_initialize_id));

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.full_screen_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(mAddListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == JokeVisualizerActivity.REQUEST_JOKE_VISUALIZE && resultCode == RESULT_OK) {
            mInterstitialAd.show();
        }
    }

    public void displayJoke(String joke) {
        Intent intent = new Intent(getActivity(), JokeVisualizerActivity.class);
        intent.putExtra("joke", joke);

        startActivityForResult(intent, JokeVisualizerActivity.REQUEST_JOKE_VISUALIZE);

    }
}
