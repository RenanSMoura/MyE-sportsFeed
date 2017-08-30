package moura.renan.com.br.mye_sportsfeed.Loaders;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

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
        if(mUrl == null){
            return null;
        }
        //Instancio meus objetos de conexão e a lista que será o retorno
        mMainNetworkConnection = new MainNetworkConnection();
        mTournamentArrayList = new ArrayList<>();

        /**
         * Passo o contexto da activity, a url de conexão e o método requirido
         */
        String jsonTournamentFeedResponse = mMainNetworkConnection.getJsonData(getContext(),mUrl, mMethod);

        //Verifico se o retorno não está vazio
        if(jsonTournamentFeedResponse == null || jsonTournamentFeedResponse.isEmpty()){
            //Utils.ShowToast.ShowToastMessage(getContext(),"Não há dados disponíveis.");
            return  null;
        }
        /**
         * Extraio as informações que retornaram da requisião https
         */
        mTournamentArrayList = mTornamentFetch.fetchTornamentData(jsonTournamentFeedResponse);
        //retorno meu array de torneios
        return mTournamentArrayList;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }




}
