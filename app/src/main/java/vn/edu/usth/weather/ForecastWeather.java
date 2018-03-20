package vn.edu.usth.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hung son on 17-Oct-17.
 */

public class ForecastWeather extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("Frag", "onCreate");
//        TextView day = new TextView(getActivity());
//        day.setText("Thursday");
//
//        ImageView weather = new ImageView(getActivity());
//        weather.setImageResource(R.drawable.ic_action_name);
//
//        View v = new View(getActivity());
//        v.setBackgroundColor(0x20FF0000);
//
//        LinearLayout rootViewGroup = new LinearLayout(getActivity());
//        rootViewGroup.setOrientation(LinearLayout.VERTICAL);
//        rootViewGroup.addView(day);
//        rootViewGroup.addView(weather);
//        rootViewGroup.addView(v);
        return inflater.inflate(R.layout.forecast_weather,container,false);

    }
}
