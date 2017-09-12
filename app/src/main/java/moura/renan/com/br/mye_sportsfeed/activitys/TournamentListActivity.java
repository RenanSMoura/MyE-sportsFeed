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


import moura.renan.com.br.mye_sportsfeed.NetworkConnection.MainNetworkConnection;
import moura.renan.com.br.mye_sportsfeed.R;
import moura.renan.com.br.mye_sportsfeed.Tournament;
import moura.renan.com.br.mye_sportsfeed.adapters.TournamentArrayAdapter;
import moura.renan.com.br.mye_sportsfeed.Loaders.GenericLoader;
import moura.renan.com.br.mye_sportsfeed.utils.Utils;

public class TournamentListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Tournament>>{
    private static final String LOG_TAG = TournamentListActivity.class.getName().toString();
    private static final int TOURNAMENT_LOADER_ID = 2;
    private TournamentArrayAdapter mTournamentArrayAdapter;
    private ListView mTournamentListView;
    private TextView mTournamentEmptyListView;
    private ProgressBar mProgressBar;
    private String mGameId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_list);

        mGameId = getIntent().getStringExtra("idGame");

        loadUIComponents();

        if(MainNetworkConnection.checkNetworkConnection(TournamentListActivity.this)){
            getSupportLoaderManager().initLoader(TOURNAMENT_LOADER_ID,null,this);
        }else{
            mProgressBar.setVisibility(View.GONE);
            mTournamentEmptyListView.setText(R.string.network_fail_connection);
        }

         setUpClickListener();
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Uri baseUri = Uri.parse(Utils.MAIN_URL);
        Uri.Builder builder = baseUri.buildUpon();
        builder.appendEncodedPath("tournaments");
        builder.appendQueryParameter("discipline",mGameId);
        builder.appendQueryParameter("featured","1");
        builder.appendQueryParameter("api_key",Utils.API_KEY);

        return new GenericLoader(this,builder.toString(), Utils.HttpMethods.GET,new Tournament());
    }

    @Override
    public void onLoadFinished(Loader<List<Tournament>> loader, List<Tournament> data) {
        mProgressBar.setVisibility(View.GONE);
        if(data != null && !data.isEmpty()){
            mTournamentArrayAdapter.addAll(data);
        }else{
            mTournamentArrayAdapter.clear();
            mTournamentEmptyListView.setText(R.string.tournaments_no_data_found);
        }

    }

    @Override
    public void onLoaderReset(Loader loader) {
        mTournamentArrayAdapter.clear();
    }

    private void loadUIComponents(){
        mProgressBar             = (ProgressBar) findViewById(R.id.tournament_loading_spinner);
        mTournamentListView      = (ListView) findViewById(R.id.tournament_list);
        mTournamentEmptyListView = (TextView) findViewById(R.id.tournament_empty_view);

        mTournamentArrayAdapter = new TournamentArrayAdapter(TournamentListActivity.this,new ArrayList<Tournament>());

        mTournamentListView.setAdapter(mTournamentArrayAdapter);
        mTournamentListView.setEmptyView(mTournamentEmptyListView);
    }
    private void setUpClickListener(){
        mTournamentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tournament tournament = mTournamentArrayAdapter.getItem(i);
                Intent intent = new Intent(TournamentListActivity.this, MatchesActivity.class);
                intent.putExtra("idTournament",tournament.getId());
                startActivity(intent);
            }
        });
    }
}
