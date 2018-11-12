package com.example.pluscomputers.weatherapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pluscomputers.weatherapp.DetailActivity;
import com.example.pluscomputers.weatherapp.R;
import com.example.pluscomputers.weatherapp.model.Days;
import com.example.pluscomputers.weatherapp.utilities.WeatherUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder>{

    private List<Days> mDaysList;
    private Context ctx;

    public WeatherAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView date,weatherDesc,highTemperature,lowTemperature;
        ImageView weatherIcon;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            date = itemView.findViewById(R.id.date);
            weatherDesc = itemView.findViewById(R.id.weather_description);
            highTemperature = itemView.findViewById(R.id.high_temperature);
            lowTemperature = itemView.findViewById(R.id.low_temperature);

            weatherIcon = itemView.findViewById(R.id.weather_icon);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();

            Days day = mDaysList.get(position);

            Intent intent = new Intent(ctx, DetailActivity.class);

            intent.putExtra("date", day.getDate());
            intent.putExtra("icon", day.getWeatherIconHourlyUrl());
            intent.putExtra("maxTempC", day.getMaxTempC());
            intent.putExtra("minTempC",day.getMinTempC());
            intent.putExtra("weatherDesc",day.getWeatherHourlyDesc());
            intent.putExtra("humidity",day.getHumidity());
            intent.putExtra("pressure",day.getPressure());
            intent.putExtra("chanceOfRain",day.getChanceOfRain());

            ctx.startActivity(intent);
        }
    }

    @Override
    public WeatherAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_list_item, parent, false);

        return new WeatherAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WeatherAdapter.MyViewHolder holder, int position) {
        Days day = mDaysList.get(position);

        holder.date.setText(WeatherUtils.formatDateForAdapter(day.getDate()));
        holder.weatherDesc.setText(day.getWeatherHourlyDesc());
        holder.highTemperature.setText(day.getMaxTempC()  + "\u00b0");
        holder.lowTemperature.setText(day.getMinTempC() + "\u00b0");

        Picasso.get()
                .load(day.getWeatherIconHourlyUrl())
                .into(holder.weatherIcon);
    }

    public void setWeatherList(List<Days> lajmiList) {
        this.mDaysList = lajmiList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mDaysList == null)
            return 0;
        else {
        return mDaysList.size();
        }
    }
}
