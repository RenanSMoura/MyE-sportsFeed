package moura.renan.com.br.mye_sportsfeed.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import moura.renan.com.br.mye_sportsfeed.Games;
import moura.renan.com.br.mye_sportsfeed.R;

/**
 * Created by Renan on 30/08/2017.
 */

public class GameArrayAdapter extends ArrayAdapter<Games> {


    public GameArrayAdapter(@NonNull Context context, ArrayList<Games> games) {
        super(context, 0,games);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listViewItem = convertView;
        if(listViewItem == null){
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item_game,parent,false);
        }

        Games currentGame = getItem(position);

        TextView nameGame = (TextView) listViewItem.findViewById(R.id.game_name);
        nameGame.setText(currentGame.getName());

        return listViewItem;
    }
}
