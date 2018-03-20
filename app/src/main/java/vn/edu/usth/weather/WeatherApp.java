package vn.edu.usth.weather;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by hung son on 21-Nov-17.
 */

public class WeatherApp extends Application {
    private RequestQueue queue;

    @Override
    public void onCreate(){
        super.onCreate();
        queue = Volley.newRequestQueue(this);
    }

    public RequestQueue getQueue(){ return queue;}
}
