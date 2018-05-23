package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by emil.ivanov on 5/23/18.
 * Headless fragment that contains the AsyncTask responsible for execution of network requests
 * Interested classes will receive callbacks for the state when they implement {@link IRequestState}
 */
public class AsyncFragment extends Fragment {

    public static final String TAG = "AsyncTag";
    private static IRequestState mListener;
    private EndpointsAsyncTask mAsyncTask;

    public static AsyncFragment newInstance() {
        Bundle args = new Bundle();

        AsyncFragment fragment = new AsyncFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStop() {
        mAsyncTask.cancel(true);
        super.onStop();
    }

    public void setRequestStateListener(IRequestState listener) {
        mListener = listener;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestJoke();
    }

    public void requestJoke() {
        if (mListener != null) {
            mAsyncTask = new EndpointsAsyncTask();
            mAsyncTask.execute();
        }
    }

    /**
     * Async task responsible for execution of network request.
     * Depends on instance of {@link IRequestState} to pass the current state
     */
    static class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
        private MyApi myApiService = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mListener.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            if (myApiService == null) {  // Only do this once
                mListener.onDoInBackground();
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - 0.0.0.0 for physical device testing
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }


            try {
                return myApiService.generateJoke().execute().getData();
            } catch (IOException e) {
                mListener.onCancel();
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            mListener.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            mListener.onCancel();
            super.onCancelled();

        }
    }


    /**
     * Helper interface to track the state of the async task execution
     */
    public interface IRequestState {
        void onPreExecute();

        void onDoInBackground();

        void onCancel();

        void onPostExecute(String result);

    }
}
