package moura.renan.com.br.mye_sportsfeed.activitys;

import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import moura.renan.com.br.mye_sportsfeed.Loaders.TournamentLoader;
import moura.renan.com.br.mye_sportsfeed.R;
import moura.renan.com.br.mye_sportsfeed.Tournament;
import moura.renan.com.br.mye_sportsfeed.adapters.TournamentArrayAdapter;

public class TournamentListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Tournament>>{

    /**
     * Id do identificador único para carregar o loader
     */
    private static final int TOURNAMENT_LOADER_ID = 2;
    //ArrayAdaper costumizado
    private TournamentArrayAdapter mTournamentArrayAdapter;

    private ListView mTournamentListView;

    private LoaderManager mTournamentListLoaderManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_list);

        mTournamentListView = (ListView) findViewById(R.id.tournament_list);
        mTournamentArrayAdapter = new TournamentArrayAdapter(TournamentListActivity.this,new ArrayList<Tournament>());
        mTournamentListView.setAdapter(mTournamentArrayAdapter);

        mTournamentListLoaderManager = getSupportLoaderManager();
        mTournamentListLoaderManager.initLoader(2,null,this);

    }

    /**
     * TODO: CRIAR LÓGICA PARA ADD PARÂMETROS
     * @param id
     * @param args
     * @return
     */
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Uri baseUri = Uri.parse("https://api.toornament.com/v1/tournaments?discipline=counterstrike_go&api_key=swy_jhBl4bSfy95anch4GvUvMt8mm4rPocFlxjIchw0");

        Uri.Builder builder = baseUri.buildUpon();
        return new TournamentLoader(this,builder.toString(),"GET");
    }

    @Override
    public void onLoadFinished(Loader<List<Tournament>> loader, List<Tournament> data) {

        /**
         * TODO: Realizar validações
         */
        mTournamentArrayAdapter.addAll(data);
    }


    @Override
    public void onLoaderReset(Loader loader) {
        mTournamentArrayAdapter.clear();
    }
}
