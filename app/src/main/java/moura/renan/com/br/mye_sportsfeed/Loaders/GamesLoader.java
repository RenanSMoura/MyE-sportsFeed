package moura.renan.com.br.mye_sportsfeed.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import moura.renan.com.br.mye_sportsfeed.Games;
import moura.renan.com.br.mye_sportsfeed.NetworkConnection.MainNetworkConnection;

/**
 * Created by Renan on 30/08/2017.
 */

public class GamesLoader extends AsyncTaskLoader<List<Games>> {

    private static final String LOG_TAG = GamesLoader.class.getName().toString();

    private List<String> mListUrl;

    private MainNetworkConnection mMainNetworkConnection  =  new MainNetworkConnection();

    private ArrayList<Games> mGamesArrayList = new ArrayList<>();

    private String mMethod;

    private Games mGamesFetch;
    public GamesLoader(Context context, List<String> url, String method) {
        super(context);
        this.mListUrl = url;
        this.mMethod = method;
    }

    @Override
    public List<Games> loadInBackground() {
        for (int i =0; i < mListUrl.size(); i++){
            if(mListUrl.get(i) != null) {
                String jsonGamesFeedResponse = mMainNetworkConnection.getJsonData(mListUrl.get(i), mMethod);

                if (jsonGamesFeedResponse == null) {
                    return null;
                }

                List<Games> returnedGame = mGamesFetch.fetchGamesData(jsonGamesFeedResponse);

                mGamesArrayList.add(returnedGame.get(0));
            }
        }
        return mGamesArrayList;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
