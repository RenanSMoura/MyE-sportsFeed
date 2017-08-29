package moura.renan.com.br.mye_sportsfeed.Loaders;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import moura.renan.com.br.mye_sportsfeed.NetworkConnection.MainNetworkConnection;
import moura.renan.com.br.mye_sportsfeed.Tournament;

/**
 * Created by Renan on 28/08/2017.
 *
 */

public class TournamentLoader extends AsyncTaskLoader<List<Tournament>> {
    //For log
    private static final String LOG_TAG = TournamentLoader.class.getName().toString();
    //Url string for connection
    private String mUrl;
    //Call the main network connection class
    private MainNetworkConnection mMainNetworkConnection;
    //
    private ArrayList<Tournament> mTournamentArrayList;

    private Tournament mTornamentFetch;

    private String mMethod;
    /**
     *
     * @param context context
     * @param url URL para fazer a requisição HTTPS
     */
    public TournamentLoader(Context context,String url,String method) {
        super(context);
        mUrl = url;
        mMethod = method;
    }

    @Override
    public List<Tournament> loadInBackground() {
        /*
        Verifico se a url não é null e nem vazia
         */
        if(mUrl == null || mUrl.isEmpty()){
            return null;
        }
        //Instancio meus objetos de conexão e a lista que será o retorno
        mMainNetworkConnection = new MainNetworkConnection();
        mTournamentArrayList = new ArrayList<>();

        //Envio os dados
        String jsonTournamentFeedResponse = mMainNetworkConnection.getJsonData(getContext(),mUrl,"GET");

        mTournamentArrayList = mTornamentFetch.fetchTornamentData(jsonTournamentFeedResponse);
        return mTournamentArrayList;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }




}
