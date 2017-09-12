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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import moura.renan.com.br.mye_sportsfeed.Loaders.GenericLoader;
import moura.renan.com.br.mye_sportsfeed.Matches;
import moura.renan.com.br.mye_sportsfeed.NetworkConnection.MainNetworkConnection;
import moura.renan.com.br.mye_sportsfeed.R;
import moura.renan.com.br.mye_sportsfeed.adapters.MatchesArrayAdapter;
import moura.renan.com.br.mye_sportsfeed.utils.Utils;

public class MatchesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Matches>>{
    private String mIdTournament;
    private ListView mMatchesListView;
    private TextView mEmptyListMatches;
    private ProgressBar mProgressBar;
    private LoaderManager mLoaderManager;
    private MatchesArrayAdapter mMatchesArrayAdapter;

    private static final int MATCHES_LOADER_ID = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        mMatchesListView  = (ListView) findViewById(R.id.matches_list);
        mProgressBar      = (ProgressBar) findViewById(R.id.matches_loading_spinner);
        mEmptyListMatches = (TextView) findViewById(R.id.matches_empty_view);

        mMatchesArrayAdapter = new MatchesArrayAdapter(MatchesActivity.this,new ArrayList<Matches>());

        mMatchesListView.setEmptyView(mEmptyListMatches);
        mMatchesListView.setAdapter(mMatchesArrayAdapter);

        mIdTournament = getIntent().getStringExtra("idTournament");

        if(MainNetworkConnection.checkNetworkConnection(MatchesActivity.this)){
            mLoaderManager = getSupportLoaderManager();
            mLoaderManager.initLoader(MATCHES_LOADER_ID,null,this);
        }else{
            mProgressBar.setVisibility(View.GONE);
            mEmptyListMatches.setText(R.string.network_fail_connection);
        }

        mMatchesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Uri baseUri = Uri.parse("https://api.toornament.com/v1/tournaments/618724800899661824/matches?sort=schedule&with_games=1&has_result=1&api_key=swy_jhBl4bSfy95anch4GvUvMt8mm4rPocFlxjIchw0");
        Uri.Builder builder = baseUri.buildUpon();
//        builder.appendEncodedPath("tournaments");
//        builder.appendEncodedPath(mIdTournament);
//        builder.appendEncodedPath("matches");

        return new GenericLoader(this,builder.toString(),Utils.HttpMethods.GET,new Matches());
    }


    @Override
    public void onLoadFinished(Loader<List<Matches>> loader, List<Matches> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<Matches>> loader) {

    }
}
