package moura.renan.com.br.mye_sportsfeed.activitys;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import moura.renan.com.br.mye_sportsfeed.Games;
import moura.renan.com.br.mye_sportsfeed.Loaders.GamesLoader;
import moura.renan.com.br.mye_sportsfeed.NetworkConnection.MainNetworkConnection;
import moura.renan.com.br.mye_sportsfeed.R;
import moura.renan.com.br.mye_sportsfeed.adapters.GameArrayAdapter;
import moura.renan.com.br.mye_sportsfeed.utils.Utils;

public class GamesListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Games>> {
    //Id do identificador único para carregar o loader
    private static final int GAMES_LOADER_ID = 1;
    // Quem vai realizar o loader
    private LoaderManager mGamesListLoaderManager;
    //Array Adpter para controlar a lista de jogos
    private GameArrayAdapter mGameArrayAdapter;
    //List view de jogos
    private ListView mGameListView;
    private TextView mGameEmptyListView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);

        //instancio minhas variáveis da UI
        mGameListView       = (ListView) findViewById(R.id.game_list);
        mGameEmptyListView  = (TextView) findViewById(R.id.game_empty_view);
        mProgressBar        = (ProgressBar) findViewById(R.id.game_loading_spinner);

        //Instancio meu array adapter
        mGameArrayAdapter = new GameArrayAdapter(GamesListActivity.this,new ArrayList<Games>());
        mGameListView.setAdapter(mGameArrayAdapter);
        mGameListView.setEmptyView(mGameEmptyListView);

        //Verifico a conexão
        if(MainNetworkConnection.checkNetworkConnection(GamesListActivity.this)){
            //Instancio o loaderManager e o inicio
            mGamesListLoaderManager = getSupportLoaderManager();
            mGamesListLoaderManager.initLoader(GAMES_LOADER_ID,null,this);
        }else {
            //Caso n exista conexão, mostro essa mensagem na tela
            mProgressBar.setVisibility(View.GONE);
            mGameEmptyListView.setText(R.string.network_fail_connection);
        }


        /**
         * Crio um ClickListener para iniciar a Activity de Torneios, passango o id do game
         *
         */
        mGameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Seleciono o id do game que foi clicado
                Games game  = mGameArrayAdapter.getItem(i);
                //Instancio uma nova intent
                Intent intent = new Intent(GamesListActivity.this,TournamentListActivity.class);
                //Adiciono parâmetros para essa intent
                intent.putExtra("idGame",game.getId());
                //Dou start na Activity
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGameArrayAdapter.clear();
    }


    @Override
    public Loader<List<Games>> onCreateLoader(int id, Bundle args) {

        List<String> gamesList = new ArrayList<>();
        //Como só estou carregando dois games, estou criando um array
        // que contém hardedcode os games que eu quero acompanhar
        String[] gamesString = {"dota2","counterstrike_go"};

        //Percorro meu array hardedcode para criar as urls
        for(int i = 0; i < gamesString.length; i++){
            Uri baseUri = Uri.parse(Utils.MAIN_URL);
            Uri.Builder uriBuilder = baseUri.buildUpon();
            uriBuilder.appendEncodedPath("disciplines");
            uriBuilder.appendEncodedPath(gamesString[i]);
            uriBuilder.appendQueryParameter("api_key",Utils.API_KEY);
            gamesList.add(uriBuilder.toString());
        }

        return new GamesLoader(GamesListActivity.this,gamesList, Utils.HttpMethods.GET);
    }

    @Override
    public void onLoadFinished(Loader<List<Games>> loader, List<Games> data) {
        //Retiro minha barra de progresso
        mProgressBar.setVisibility(View.GONE);
        //check o retorno
        if(data != null && !data.isEmpty()){
            mGameArrayAdapter.addAll(data);
        }else{
            //caso n venha nada
            mGameArrayAdapter.clear();
            mGameEmptyListView.setText("Nenhum game encontrado");
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Games>> loader) {
        mGameArrayAdapter.clear();
    }
}
