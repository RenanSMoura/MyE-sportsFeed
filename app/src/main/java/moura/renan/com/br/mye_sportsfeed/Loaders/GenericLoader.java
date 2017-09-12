package moura.renan.com.br.mye_sportsfeed.Loaders;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

import moura.renan.com.br.mye_sportsfeed.Matches;
import moura.renan.com.br.mye_sportsfeed.NetworkConnection.MainNetworkConnection;
import moura.renan.com.br.mye_sportsfeed.Tournament;

/**
 * Created by Renan on 28/08/2017.
 *
 */


public class GenericLoader extends AsyncTaskLoader<List<Object>> {
    //For log
    private static final String LOG_TAG = GenericLoader.class.getName().toString();
    //Url string for connection
    private String mUrl;
    //Call the main network connection class
    private MainNetworkConnection mMainNetworkConnection  = new MainNetworkConnection();

    private ArrayList<Object> mGenericArrayList = new ArrayList<>();

    private String mMethod;
    private Object mGenericClass;

    public GenericLoader(Context context, String url, String method,Object genericClass) {
        super(context);
        mUrl = url;
        mMethod = method;
        mGenericClass = genericClass;
    }

    @Override
    public List<Object> loadInBackground() {
        if(mUrl == null){
            return null;
        }
        String jsonFeedResponse = mMainNetworkConnection.getJsonData(mUrl, mMethod);

        if(jsonFeedResponse == null){
            return  null;
        }
        if(mGenericClass instanceof Tournament){
            mGenericArrayList = Tournament.fetchTornamentData(jsonFeedResponse);
        }
        if(mGenericClass instanceof Matches){
            mGenericArrayList = Matches.fetchData(jsonFeedResponse);
        }
        return mGenericArrayList;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }



}
