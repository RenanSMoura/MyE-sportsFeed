package moura.renan.com.br.mye_sportsfeed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Renan on 30/08/2017.
 */

public class Matches {


    private String mMatchId;
    private String mMatchType;
    private String mMatchStatus;
    private Date   mMatchDate;
    private String mMatchFormat;
    private ArrayList<Opponents> mMatchOponents;
    private ArrayList<Jogos> mMatchJogos;


    public static ArrayList<Object> fetchData(String json){
        ArrayList<Object> list = new ArrayList<>();

        try{
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i < jsonArray.length();i++){
                Matches match = new Matches();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                match.setmMatchId(jsonObject.getString("id"));
                match.setmMatchStatus(jsonObject.getString("status"));
                match.setmMatchOponents(Opponents.fetchOpponentsData(jsonObject.getJSONArray("opponents")));

                list.add(match);

            }
        }catch (JSONException e){

        }
        return list;
    }

    public String getmMatchId() {
        return mMatchId;
    }

    public void setmMatchId(String mMatchId) {
        this.mMatchId = mMatchId;
    }

    public String getmMatchType() {
        return mMatchType;
    }

    public void setmMatchType(String mMatchType) {
        this.mMatchType = mMatchType;
    }

    public String getmMatchStatus() {
        return mMatchStatus;
    }

    public void setmMatchStatus(String mMatchStatus) {
        this.mMatchStatus = mMatchStatus;
    }

    public Date getmMatchDate() {
        return mMatchDate;
    }

    public void setmMatchDate(Date mMatchDate) {
        this.mMatchDate = mMatchDate;
    }

    public String getmMatchFormat() {
        return mMatchFormat;
    }

    public void setmMatchFormat(String mMatchFormat) {
        this.mMatchFormat = mMatchFormat;
    }

    public ArrayList<Opponents> getmMatchOponents() {
        return mMatchOponents;
    }

    public void setmMatchOponents(ArrayList<Opponents> mMatchOponents) {
        this.mMatchOponents = mMatchOponents;
    }

    public ArrayList<Jogos> getmMatchJogos() {
        return mMatchJogos;
    }

    public void setmMatchJogos(ArrayList<Jogos> mMatchJogos) {
        this.mMatchJogos = mMatchJogos;
    }
}
