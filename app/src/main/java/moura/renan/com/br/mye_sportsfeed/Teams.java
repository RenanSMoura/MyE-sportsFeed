package moura.renan.com.br.mye_sportsfeed;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Renan on 31/08/2017.
 */

public class Teams {

    private String mTeamId;
    private String mTeamName;
    private String mTeamCountry;


    public static  ArrayList<Teams> fetchTeamsData(JSONObject json){
        ArrayList<Teams> list = new ArrayList<>();
        try {

                Teams team = new Teams();
                team.setmTeamId(json.getString("id"));
                team.setmTeamName(json.getString("name"));
                team.setmTeamCountry(json.getString("country"));
                list.add(team);

        }catch (Exception e){

        }


        return list;
    }
    public String getmTeamId() {
        return mTeamId;
    }

    public void setmTeamId(String mTeamId) {
        this.mTeamId = mTeamId;
    }

    public String getmTeamName() {
        return mTeamName;
    }

    public void setmTeamName(String mTeamName) {
        this.mTeamName = mTeamName;
    }

    public String getmTeamCountry() {
        return mTeamCountry;
    }

    public void setmTeamCountry(String mTeamCountry) {
        this.mTeamCountry = mTeamCountry;
    }
}
