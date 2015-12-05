package au.com.market24seven.builditbigger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import au.com.market24seven.jokedisplay.JokeDisplayActivity;

/**
 * A placeholder fragment containing a simple view.
 *
 * Error:(22, 8) error: duplicate class: au.com.market24seven.builditbigger.MainActivityFragment
 * Solution is to delete : main/java/au.com.market24seven.builditbigger.MainActivityFragment
 */
public class MainActivityFragment extends Fragment implements OnJokeRetrievedListener {

    private String mJoke;
    private InterstitialAd mInterstitialAd;
    private ProgressBar mProgressBar;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startDisplayingJoke();
            }
        });


        loadAnInterstitialAd();

        Button button = (Button) root.findViewById(R.id.button_joke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMeJokesNow();
            }
        });

        mProgressBar = (ProgressBar) root.findViewById(R.id.progressbar_joke);

        // https://developers.google.com/admob/android/quick-start
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;

    }

    /*  Make these two changes:
        Import the AdRequest and AdView classes.
        Add code to find your AdView in the layout, create an AdRequest, and then load an ad into the AdView with it.
        Do not use the AdRequest line shown above if you are testing. Refer to our Targeting page to learn more about using test devices and test device IDs.

        Target page:
        https://developers.google.com/admob/android/targeting#test_ads

        AdRequest request = new AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
            .addTestDevice("AC98C820A50B4AD8A2106EDE96FB87D4")  // My Galaxy Nexus test phone
            .build();

    */

    private void loadAnInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onJokeRetrieved(String joke) {
        mProgressBar.setVisibility(View.INVISIBLE);
        mJoke = joke;
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            startDisplayingJoke();
        }
    }

    private void startDisplayingJoke() {
        Intent intent = new Intent(getActivity(), JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.JOKE_INTENT_KEY, mJoke);
        startActivity(intent);
    }

    public void showMeJokesNow(){
        mProgressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute(this);
    }




}
