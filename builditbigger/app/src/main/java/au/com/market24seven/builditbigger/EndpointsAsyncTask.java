package au.com.market24seven.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import au.com.market24seven.backend.myApi.MyApi;

/**
 * Created by androidau on 12/11/2015.
 */
public class EndpointsAsyncTask extends AsyncTask<OnJokeRetrievedListener, Void, String> {

    private static MyApi myApiService = null;
    private Context context;
    private OnJokeRetrievedListener listener;

    @Override
    protected String doInBackground(OnJokeRetrievedListener... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://build-it-bigger-1032.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }
        
        listener = params[0];
        //context = params[0].first;
        //String name = params[0].second;

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        listener.onJokeRetrieved(result);
       //  Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}