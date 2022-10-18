package com.example.myweather;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myweather.lei.hourAdapter;
import com.example.myweather.lei.timehour;
import com.example.myweather.net.Weather;
import com.example.myweather.net.dayweather;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private List<timehour> timehourList = new ArrayList<>();

    private Context context;

    private List<Weather> datas;

    private ScrollView weatherlayout;

    //这四个是从day中获得到的天气
    private TextView title_city;

    private TextView wendu;

    private TextView tianqi;

    private TextView sheshidu;

    private TextView airzhiliang;

    private LinearLayout forecastLayout;

    //detail
    private TextView rise_time;

    private TextView set_time;

    private TextView wind_speed;

    private TextView wind_direction;

    private  TextView air_humidity;

    private TextView feels_like;

    private TextView air_altimeter;

    private  TextView SHIDU;

    private TextView TIGAN;

    private TextView QIYA;

    private View view;

    public ViewPagerAdapter(Context context, List<Weather> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        view = View.inflate(context, R.layout.scrollview , null);
        Log.d("----------", "instantiateItem: " + datas.get(position));

        //这里填写每个东西的实例和输出的东西
        //        TextView tv = (TextView) view.findViewById(R.id.tv);
        //        tv.setText(mData.get(position));
        weatherlayout = (ScrollView) view.findViewById(R.id.weather_layout);
        title_city = (TextView) view.findViewById(R.id.title_city);
        wendu = (TextView) view.findViewById(R.id.wendu);
        tianqi = (TextView) view.findViewById(R.id.tianqi);
        sheshidu = (TextView) view.findViewById(R.id.sheshidu);
        airzhiliang = (TextView) view.findViewById(R.id.airzhiliang);

        forecastLayout = (LinearLayout) view.findViewById(R.id.forecast_layout);

        //detail
        rise_time = (TextView) view.findViewById(R.id.sunrise_time) ;
        set_time = (TextView) view.findViewById(R.id.sunset_time) ;
        wind_speed = (TextView) view.findViewById(R.id.WindSpeed) ;
        air_humidity = (TextView) view.findViewById(R.id.Humidity) ;
        feels_like = (TextView) view.findViewById(R.id.FeelsLike) ;
        air_altimeter = (TextView) view.findViewById(R.id.Altimeter) ;
        wind_direction = (TextView) view.findViewById(R.id.WindDirCompass) ;
        SHIDU = (TextView) view.findViewById(R.id.shidu);
        TIGAN = (TextView) view.findViewById(R.id.tigan);
        QIYA = (TextView) view.findViewById(R.id.qiya);

        inittimehours(datas.get(position));

        //recycleview的初始化
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.main_recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        hourAdapter hourAdapter = new hourAdapter(timehourList);
        recyclerView.setAdapter(hourAdapter);

        showdayweather(datas.get(position));

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    //主界面recycleview的更新
    private void inittimehours(Weather weather){
        for (com.example.myweather.net.todayhours todayhours : weather.todayhourslist){
            timehour timehour123 = new timehour(todayhours.hourtime,todayhours.hourwendu,
                    todayhours.hourtianqiimg,todayhours.hourair);
            timehourList.add(timehour123);
        }
    }

    //关于主界面的更新都是这个函数
    public void showdayweather(Weather weather) {
            String Temperatere = weather.today.wendu;
            String phrase = weather.today.tianqi;
            String air = weather.today.kongqi;
            String air_level = weather.today.kongqizhiliang;
            String request_cityname = weather.request_city;
            wendu.setText(Temperatere);
            tianqi.setText(phrase);
            sheshidu.setText("℃");
            airzhiliang.setText("空气 " + air_level +" " + air);
            title_city.setText(request_cityname);

            forecastLayout.removeAllViews();
            for(dayweather day123 : weather.dayslist){
                View view = View.inflate(context, R.layout.forecast_item , null);
                TextView datatext = (TextView) view.findViewById(R.id.date_text);
                TextView weathertext = (TextView) view.findViewById(R.id.weather_text);
                TextView temtext = (TextView) view.findViewById(R.id.tem_text);
                weathertext.setText(day123.daysday.daystianqi);
                temtext.setText(day123.daysday.dayswendu + "℃");
                datatext.setText(day123.daysriqi);
                forecastLayout.addView(view);
            }

            rise_time.setText("日出" + weather.dayslist.get(0).richu);
            set_time.setText("日落" + weather.dayslist.get(0).riluo);

            wind_speed.setText(weather.today.fengsu + "级");
            air_humidity.setText(weather.today.shidu + "%");
            feels_like.setText(weather.today.tiganwendu + "°");
            air_altimeter.setText(weather.today.qiya + "hpa");
            wind_direction.setText(weather.today.fengxiangbiao);
            SHIDU.setText("湿度");
            TIGAN.setText("体感");
            QIYA.setText("气压");
        }

}
