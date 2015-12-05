package au.com.market24seven.jokedisplay;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by androidau on 12/11/2015.
 */
public class JokeDisplayFragment  extends Fragment  {

        public JokeDisplayFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.joke_displayfragment, container, false);

            Intent intent = getActivity().getIntent();
            String joke = intent.getStringExtra(JokeDisplayActivity.JOKE_INTENT_KEY);

            TextView jokeTextView = (TextView) root.findViewById(R.id.textview_joke);
            if (joke != null && joke.length() != 0) {
                jokeTextView.setText(joke);
            }

            return root;
        }
    }