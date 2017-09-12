package moura.renan.com.br.mye_sportsfeed.NetworkConnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Renan on 28/08/2017.
 * Responsável por criar todos os requests
 */

public class MainNetworkConnection {
    private static final String LOG_TAG = MainNetworkConnection.class.getName().toString();
    private HttpsURLConnection httpsURLConnection = null;
    private InputStream inputStream = null;
    private String mMethod;
    private String jsonResponse;
    private static StringBuilder output;


    public String getJsonData(String requestUrl, String method) {
        URL url = createUrl(requestUrl);
        return makeHttpConnection(url, method);
    }

    private String makeHttpConnection(URL url, String method)  {
        mMethod = method;
        jsonResponse = "";
        if (checkIfUrlIsValid(url)) {
            try {
                createHttpsConnection(url);
                if (httpsURLConnection.getResponseCode() == 200) {
                    inputStream = httpsURLConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                }

                closeHttpsConnection();
                closeInputStream();
            }catch (IOException e){
                Log.e(LOG_TAG,e.getMessage().toString());
            }
        }
        return jsonResponse;
    }



    private static String readFromStream(InputStream inputStream) throws  IOException {
        output = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null){
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }

    private static URL createUrl(String requestUrl) {
        try{
            return new URL(requestUrl);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG,e.getMessage().toString());
            return null;
        }

    }

    private boolean checkIfUrlIsValid(URL url){
        if(url != null ){
            return  true;
        }
        return  false;
    }

    private void createHttpsConnection(URL url){
        try{
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setReadTimeout(10000);
            httpsURLConnection.setConnectTimeout(15000);
            httpsURLConnection.setRequestMethod(mMethod);
            httpsURLConnection.connect();
        }catch (IOException e){
            Log.e(LOG_TAG,e.getMessage().toString());
        }

    }

    private void closeHttpsConnection(){
        if (httpsURLConnection != null) {
            httpsURLConnection.disconnect();
        }
    }

    private void closeInputStream() throws IOException{
        if (inputStream != null) {
            inputStream.close();
        }
    }

    public static boolean checkNetworkConnection(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}

