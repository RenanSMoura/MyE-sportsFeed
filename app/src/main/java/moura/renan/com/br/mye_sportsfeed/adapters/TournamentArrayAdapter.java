package moura.renan.com.br.mye_sportsfeed.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import moura.renan.com.br.mye_sportsfeed.R;
import moura.renan.com.br.mye_sportsfeed.Tournament;

/**
 * Created by Renan on 28/08/2017.
 */

public class TournamentArrayAdapter extends ArrayAdapter<Tournament> {

    public TournamentArrayAdapter(@NonNull Context context, ArrayList<Tournament> tournaments) {
        super(context, 0, tournaments);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listViewItem = convertView;

        if(listViewItem == null){
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item_tournament,parent,false);
        }

        Tournament currentTournament = getItem(position);

        TextView nameTournament = (TextView) listViewItem.findViewById(R.id.tournament_name);
        nameTournament.setText(currentTournament.getName());

        TextView countryTournament = (TextView) listViewItem.findViewById(R.id.tournament_country);
        countryTournament.setText(currentTournament.getCountry());

        TextView fullNameTournament = (TextView) listViewItem.findViewById(R.id.tournament_full_name);
        if(currentTournament.getFullName()== ""){
            fullNameTournament.setVisibility(View.GONE);
        }else{
            fullNameTournament.setText(currentTournament.getFullName());
        }

        TextView sizeTeamTournament = (TextView) listViewItem.findViewById(R.id.tournament_team_number);
        sizeTeamTournament.setText(currentTournament.getNumberOfTeams());

        TextView dateTournament = (TextView) listViewItem.findViewById(R.id.tournament_date);
        dateTournament.setText(currentTournament.getStartDate());

        return listViewItem;
    }
}
