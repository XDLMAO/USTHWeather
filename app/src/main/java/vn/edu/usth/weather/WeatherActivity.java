package vn.edu.usth.weather;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "WeatherActivity";
    MediaPlayer player = new MediaPlayer();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        /*ForecastWeather f = new ForecastWeather();
        getSupportFragmentManager().beginTransaction().add(R.id.container,f).commit();
        WeatherFragment w = new WeatherFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container,w).commit();*/
        Log.i(TAG, "onCreate");

        PagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(4);
        pager.setAdapter(adapter);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);

//        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) pager.findViewById(R.id.action_refresh);
//        swipeRefreshLayout.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) this);

        InputStream is = getResources().openRawResource(R.raw.weather_tone);
        try {
            FileOutputStream fos = new FileOutputStream("/sdcard/weather_tone.mp3");
            byte buffer[] = new byte[1024];
            int numRead = 0;
            while ((numRead = is.read(buffer)) > 0) {
                fos.write(buffer, 0, numRead);
            }
            fos.close();
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            player.setDataSource("/sdcard/weather_tone.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
            Refresh("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
//                Refresh("http://ict.usth.edu.vn/wp-content/uploads/usth/usthlogo.png");
                // do something when search is pressed here
//                AsyncTask<String, Integer, Bitmap> task = new AsyncTask<String, Integer, Bitmap>() {
//                    @Override
//                    protected void onPreExecute() {
//                        // do some preparation here, if needed
//                        Toast.makeText(WeatherActivity.this, "Starting update", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    protected Bitmap doInBackground(String... strings) {
//
//                        URL url = null;
//                        try {
//                            url = new URL(strings[0]);
//                        } catch (MalformedURLException e) {
//                            e.printStackTrace();
//                        }
//                        // make a request to server
//                        HttpURLConnection connection = null;
//                        try {
//                            connection = (HttpURLConnection) url.openConnection();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            connection.setRequestMethod("GET");
//                        } catch (ProtocolException e) {
//                            e.printStackTrace();
//                        }
//                        connection.setDoInput(true);
//                        // allow reading response code and response dataconnection.
//                        try {
//                            connection.connect();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                        // Receive response
//                        int response = 0;
//                        try {
//                            response = connection.getResponseCode();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        Log.i("USTHWeather", "The response is: " + response);
//                        InputStream is = null;
//                        try {
//                            is = connection.getInputStream();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        Bitmap bitmap = BitmapFactory.decodeStream(is);
//                        connection.disconnect();
//                        return bitmap;
////                        try {
////                            Thread.sleep(5000);
////                        } catch (InterruptedException e) {
////                            e.printStackTrace();
////                        }
////                        return null;
//                    }
//                    @Override
//                    protected void onProgressUpdate(Integer... string){
//                        Toast.makeText(WeatherActivity.this, "Updating", Toast.LENGTH_LONG).show();
//                    }
//                    @Override
//                    protected void onPostExecute(Bitmap bitmap) {
//                        // This method is called in the main thread. After #doInBackground returns
//                        // the bitmap data, we simply set it to an ImageView using ImageView.setImageBitmap()
//
//                        // Process image response
//                        ImageView logo = (ImageView) findViewById(R.id.logo);
//                        logo.setImageBitmap(bitmap);
//                        Toast.makeText(WeatherActivity.this, "Done", Toast.LENGTH_LONG).show();
//                    }
//
//                };
//                task.execute("http://ict.usth.edu.vn/wp-content/uploads/usth/usthlogo.png");
//                return true;

                // once, should be performed once per app instance
//                String url = "http://ict.usth.edu.vn/wp-content/uploads/usth/usthlogo.png";
//                RequestQueue queue = ((WeatherApp)getApplication()).getQueue();
//                // a listener (kinda similar to onPostExecute())
//                Response.Listener<Bitmap> listener = new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap response) {
//                        ImageView iv = (ImageView) findViewById(R.id.logo);
//                        iv.setImageBitmap(response);
//                    }
//                };
//                // a simple request to the required image
//                ImageRequest imageRequest = new ImageRequest(url,
//                        listener, 0, 0, ImageView.ScaleType.CENTER,
//                        Bitmap.Config.ARGB_8888, null);
//                // go!
//                queue.add(imageRequest);
                return true;

            default:
            Intent i = new Intent(this, PhucActivity.class);
            startActivity(i);
        }
        return true;
    }

//    @Override
//    public void onRefresh(){
//        swipeRefreshLayout.setRefreshing(true);
//        Refresh("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
//    }

    @Override
    protected void onStart () {
        super.onStart();
        Log.i(TAG, "onStart");
    }
    @Override
    protected void  onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        player.pause();
    }
    @Override
    protected void onResume () {
        super.onResume();
        Log.i(TAG, "onResume");
        player.start();
    }
    @Override
    protected void onStop () {
        super.onStop();
        Log.i(TAG, "onStop");
    }
    @Override
    protected void onDestroy () {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
        private final int PAGE_COUNT = 4;
        private String titles[] = getResources().getStringArray(R.array.tab);

        public HomeFragmentPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public int getCount(){
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int page){
            switch (page){
                case 0: return new WeatherAndForecastFragment();
                case 1: return new WeatherAndForecastFragment();
                case 2: return new WeatherAndForecastFragment();
                case 3: return new WeatherAndForecastFragment();
            }
            return new EmptyFragment();
        }

        @Override
        public CharSequence getPageTitle(int position){
            return titles[position];
        }
    }

    public void Refresh(String... url) {
        RequestQueue queue = ((WeatherApp)getApplication()).getQueue();
        // once, should be performed once per app instance
        StringRequest request = new StringRequest(url[0],
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            TextView textView = (TextView)findViewById(R.id.status);
                            JSONObject obj = new JSONObject(response);
                            String string = obj.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONObject("condition").getString("text");
                            textView.setText(string);
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        Log.i("USTHWeather", "Json response " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        queue.add(request);
    }
}
