package moura.renan.com.br.mye_sportsfeed.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Renan on 29/08/2017.
 */

public class Utils {
    public static final String MAIN_URL = "https://api.toornament.com/v1";
    public static final String API_KEY = "swy_jhBl4bSfy95anch4GvUvMt8mm4rPocFlxjIchw0";

    public static class HttpMethods{
        public static final String GET = "GET";
        public static final String PUT = "PUT";
        public static final String DELETE = "DELETE";
        public static final String POST = "POST";
    }

    public static class ShowToast{
        public static void  ShowToastMessage(Context context,String message) {
            Toast.makeText(context,message,Toast.LENGTH_LONG).show();
        }
    }



}
