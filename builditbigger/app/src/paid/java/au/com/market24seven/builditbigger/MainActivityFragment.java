package au.com.market24seven.builditbigger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import au.com.market24seven.jokedisplay.JokeDisplayActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements OnJokeRetrievedListener {

    //private String mJoke;
    // private InterstitialAd mInterstitialAd;   for free version only
    private ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button button = (Button) root.findViewById(R.id.button_joke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMeJokesNow();
            }
        });

        mProgressBar = (ProgressBar) root.findViewById(R.id.progressBar);

        return root;
    }

    @Override
    public void onReceived(String joke) {
        mProgressBar.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_INTENT_KEY, joke);
        startActivity(intent);
    }

    public void showMeJokesNow(){
        mProgressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute(this);
    }
}
