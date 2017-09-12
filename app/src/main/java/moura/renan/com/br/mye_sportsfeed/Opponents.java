package moura.renan.com.br.mye_sportsfeed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Renan on 31/08/2017.
 */

public class Opponents {

    private ArrayList<Teams> mTeamList;
    private boolean mForfeit; // Se ele desistiu
    private int mNumber; //Número do oponente
    private int mResult; //Resultado do oponente
    private int mScore; // Pontuação do oponente

    public static ArrayList<Opponents> fetchOpponentsData(JSONArray json){
        ArrayList<Opponents> list = new ArrayList<>();

        try {
            for(int i = 0; i < json.length(); i++){
                JSONObject jsonObject = json.getJSONObject(i);
                Opponents opponent = new Opponents();
                opponent.setmForfeit(jsonObject.getBoolean("forfeit"));
                opponent.setNumber(jsonObject.getInt("number"));
                opponent.setResult(jsonObject.getInt("result"));
                opponent.setScore(jsonObject.getInt("score"));
                opponent.setmTeamList(Teams.fetchTeamsData(jsonObject.getJSONObject("participant")));
                list.add(opponent);
            }
        }catch (Exception e){

        }
        return list;
    }
    public ArrayList<Teams> getmTeamList() {
        return mTeamList;
    }

    public void setmTeamList(ArrayList<Teams> mTeamList) {
        this.mTeamList = mTeamList;
    }

    public boolean ismForfeit() {
        return mForfeit;
    }

    public void setmForfeit(boolean mForfeit) {
        this.mForfeit = mForfeit;
    }

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int number) {
        this.mNumber = number;
    }

    public int getResult() {
        return mResult;
    }

    public void setResult(int result) {
        this.mResult = result;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        this.mScore = score;
    }
}
