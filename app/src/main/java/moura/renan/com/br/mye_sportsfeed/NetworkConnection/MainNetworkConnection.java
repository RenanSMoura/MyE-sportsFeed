package moura.renan.com.br.mye_sportsfeed.NetworkConnection;

import android.content.Context;
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
 * Has the responsible to create all network requests
 */

public class MainNetworkConnection {

    private static final String LOG_TAG = MainNetworkConnection.class.getName().toString();


    public String getJsonData(Context context,String requestUrl,String method){
        String jsonResponse = null;
        URL url = createUrl(requestUrl);
        try {
            jsonResponse = makeHttpConnection(url,method);
        }catch (IOException e){
            /**
             * TODO: CRIAR TOAST
             */
        }
        return jsonResponse;
    }
    private String makeHttpConnection(URL url, String method) throws IOException{
        //variable response to handle json data
        String jsonResponse = "";
        //handle https connection
        HttpsURLConnection httpsURLConnection = null;
        //handle result from httpsUrlConnection
        InputStream inputStream = null;


        /**
         * TODO: Adicionar mensagem de toast
         */
        if(url == null  || url.toString().isEmpty()){
            return null;
        }

        try {
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setReadTimeout(10000);
            httpsURLConnection.setConnectTimeout(15000);
            httpsURLConnection.setRequestMethod(method);
            httpsURLConnection.connect();

            if(httpsURLConnection.getResponseCode() == 200){
                inputStream = httpsURLConnection.getInputStream();
                jsonResponse = readFromSteam(inputStream);
            }else{
                /**
                 * TODO: TOAST DE ERRO DE CONEXÂO
                 */
            }
        }catch (IOException e){
            /**
             * TODO: ARRANJAR UM MEIO DE VOLTAR PARA A VIEW PRINCIPAL
             */
        }finally {
            if(httpsURLConnection != null){
                httpsURLConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromSteam(InputStream inputStream) throws  IOException {
        StringBuilder output = new StringBuilder();
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
        URL url = null;
        try {
            url = new URL(requestUrl);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG,"Error on URL" + e);
            return null;
        }
        return url;
    }
}
