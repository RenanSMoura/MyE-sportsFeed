package moura.renan.com.br.mye_sportsfeed;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Renan on 28/08/2017.
 */

public class Tournament {
    private static final String LOG_TAG = Tournament.class.getName().toString();
    private int id;
    private String name;
    private String fullName;
    private String startDate;
    private String endDate;
    private boolean isOnline;
    private boolean isPublic;
    private String numberOfTeams;
    private String location;
    private String country;

    /**
     * Processa os dados vindos da requisção https
     * contendo todos os dados de torneios do jogo selecionado
     * @param tournamentJson String contendo objetos json
     * @return Um Array de Tournaments
     */
    public static ArrayList<Tournament> fetchTornamentData(String tournamentJson){
        ArrayList<Tournament> list = new ArrayList<>();

        try{
            JSONArray jsonArray = new JSONArray(tournamentJson);

            for(int i = 0; i< jsonArray.length(); i++){
                Tournament tournament = new Tournament();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                tournament.setId(jsonObject.getInt("id"));
                tournament.setName(jsonObject.getString("name"));
                if(jsonObject.getString("full_name") == "null" || jsonObject.getString("full_name").isEmpty()){
                    tournament.setFullName("");
                }else {
                    tournament.setFullName(jsonObject.getString("full_name"));
                }
                tournament.setStartDate(jsonObject.getString("date_start"));
                tournament.setEndDate(jsonObject.getString("date_end"));
                tournament.setOnline(jsonObject.getBoolean("online"));
                tournament.setPublic(jsonObject.getBoolean("public"));
                if(jsonObject.getString("country") == "null" || jsonObject.getString("country").isEmpty()){
                    tournament.setCountry("Online");
                }else {
                    tournament.setCountry(jsonObject.getString("country"));
                }

                tournament.setNumberOfTeams(jsonObject.getString("size"));

                list.add(tournament);

            }
        }catch (JSONException e){
            /**
             * TODO: CRIAR EXCEPTION
             */
        }

        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(String numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



}
