package moura.renan.com.br.mye_sportsfeed.activitys;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import moura.renan.com.br.mye_sportsfeed.NetworkConnection.MainNetworkConnection;
import moura.renan.com.br.mye_sportsfeed.R;
import moura.renan.com.br.mye_sportsfeed.Tournament;
import moura.renan.com.br.mye_sportsfeed.adapters.TournamentArrayAdapter;
import moura.renan.com.br.mye_sportsfeed.Loaders.TournamentLoader;
import moura.renan.com.br.mye_sportsfeed.utils.Utils;

public class TournamentListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Tournament>>{

    /**
     * Id do identificador único para carregar o loader
     */
    private static final int TOURNAMENT_LOADER_ID = 2;
    //ArrayAdaper costumizado
    private TournamentArrayAdapter mTournamentArrayAdapter;
    /**
     * Variáveis de controle da UI
     */
    private ListView mTournamentListView;
    private TextView mTournamentEmptyListView;
    private ProgressBar mProgressBar;

    //Intent
    private Intent intent;

    //Controle do loader
    private LoaderManager mTournamentListLoaderManager;

    //Identificador do jogo
    private String idGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_list);

        //Recupero o valor passado pela intent
        intent = getIntent();
        idGame = intent.getStringExtra("idGame");

        // instancio minhas variáveis de UI
        mProgressBar             = (ProgressBar) findViewById(R.id.tournament_loading_spinner);
        mTournamentListView      = (ListView) findViewById(R.id.tournament_list);
        mTournamentEmptyListView = (TextView) findViewById(R.id.tournament_empty_view);

        //Instancio meu array adapter
        mTournamentArrayAdapter = new TournamentArrayAdapter(TournamentListActivity.this,new ArrayList<Tournament>());

        //Seto o array adapter na minha view e seto minha view vazia
        mTournamentListView.setAdapter(mTournamentArrayAdapter);
        mTournamentListView.setEmptyView(mTournamentEmptyListView);

        //Faço validação da conexão
        if(MainNetworkConnection.checkNetworkConnection(TournamentListActivity.this)){
            mTournamentListLoaderManager = getSupportLoaderManager();
            mTournamentListLoaderManager.initLoader(TOURNAMENT_LOADER_ID,null,this);
        }else{
            mProgressBar.setVisibility(View.GONE);
            mTournamentEmptyListView.setText(R.string.network_fail_connection);
        }

    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Uri baseUri = Uri.parse(Utils.MAIN_URL);
        Uri.Builder builder = baseUri.buildUpon();
        builder.appendEncodedPath("tournaments");
        builder.appendQueryParameter("discipline",idGame);
        builder.appendQueryParameter("featured","1");
        builder.appendQueryParameter("api_key",Utils.API_KEY);


        return new TournamentLoader(this,builder.toString(), Utils.HttpMethods.GET);
    }

    @Override
    public void onLoadFinished(Loader<List<Tournament>> loader, List<Tournament> data) {

        mTournamentEmptyListView.setText("Vazio");
        mProgressBar.setVisibility(View.GONE);
        mTournamentArrayAdapter.clear();

        if(data != null && !data.isEmpty()){
            mTournamentArrayAdapter.addAll(data);
        }

    }


    @Override
    public void onLoaderReset(Loader loader) {
        mTournamentArrayAdapter.clear();
    }
}
