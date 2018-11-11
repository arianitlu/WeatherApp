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

            Days lajmi = mDaysList.get(position);

            Intent intent = new Intent(ctx, DetailActivity.class);
//
//            intent.putExtra("title", lajmi.getTitle());
//            intent.putExtra("category", lajmi.getCategory());
//            intent.putExtra("image", lajmi.getImage());
//            intent.putExtra("color",lajmi.getColor());
//            intent.putExtra("description",lajmi.getDescription());
//
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
        Days lajmi = mDaysList.get(position);

        holder.date.setText(lajmi.getDate());
        holder.weatherDesc.setText(lajmi.getWeatherHourlyDesc());
        holder.highTemperature.setText(lajmi.getMaxTempC());
        holder.lowTemperature.setText(lajmi.getMinTempC());

        holder.weatherIcon.setImageResource(R.drawable.art_clear);
//        Picasso.get()
//                .load(lajmi.getImage())
//                //.placeholder(R.drawable.news_photo1)
//                //.error(R.drawable.news_photo1)
//                .into(holder.mImage);
    }

    public void setLajmi(List<Days> lajmiList) {
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