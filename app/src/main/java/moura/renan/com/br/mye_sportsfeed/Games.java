package moura.renan.com.br.mye_sportsfeed;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Renan on 30/08/2017.
 */

public class Games {
    private static final String LOG_TAG = Games.class.getName().toString();
    private String id;
    private String name;
    private String shortName;
    private String fullName;
    private String copyRights;

    public static ArrayList<Games> fetchGamesData(String gamesJson){
        ArrayList<Games> list = new ArrayList<>();

        try{
            JSONObject jsonObject = new JSONObject(gamesJson);
            Games game = new Games();
            game.setId(jsonObject.getString("id"));
            game.setName(jsonObject.getString("name"));
            game.setShortName(jsonObject.getString("shortname"));
            game.setFullName(jsonObject.getString("fullname"));
            game.setCopyRights(jsonObject.getString("copyrights"));

            list.add(game);

        }catch (JSONException e) {
            Log.e(LOG_TAG,"Deu Erro" + e.toString());
        }
        return list;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String nullName) {
        this.fullName = nullName;
    }

    public String getCopyRights() {
        return copyRights;
    }

    public void setCopyRights(String copyRights) {
        this.copyRights = copyRights;
    }

}
