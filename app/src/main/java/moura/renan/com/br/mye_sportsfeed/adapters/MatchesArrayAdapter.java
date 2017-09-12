package moura.renan.com.br.mye_sportsfeed.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import moura.renan.com.br.mye_sportsfeed.Matches;
import moura.renan.com.br.mye_sportsfeed.R;

/**
 * Created by Renan on 31/08/2017.
 */

public class MatchesArrayAdapter extends ArrayAdapter<Matches> {

    public MatchesArrayAdapter(@NonNull Context context, ArrayList<Matches> matches) {
        super(context, 0,matches);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;

        if(listViewItem == null){
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.activity_games_list,parent,false);
        }
        Matches currentMatche = getItem(position);


        return super.getView(position, convertView, parent);
    }
}
