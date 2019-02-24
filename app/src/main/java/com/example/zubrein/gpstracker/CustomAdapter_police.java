package com.example.zubrein.gpstracker;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zubrein.gpstracker.Model.police_station_info;

import java.util.List;

public class CustomAdapter_police extends BaseAdapter {
    private Context c;

    List<police_station_info> search;

    public CustomAdapter_police(Context context, List<police_station_info>search) {
        this.search = search;
        this.c = context;

    }
    @Override
    public int getCount() {
        return search.size();
    }

    @Override
    public Object getItem(int position) {
        return search.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        CustomAdapter_police.ViewHolder holder = new CustomAdapter_police.ViewHolder();
        if (convertView == null) {
            LayoutInflater inflate = (LayoutInflater)
                    c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.police_station_list, parent, false);
            holder.police = convertView.findViewById(R.id.police);
            holder.address = convertView.findViewById(R.id.address);
            holder.contact = convertView.findViewById(R.id.contact);



            convertView.setTag(holder);
        }else{
            holder = (CustomAdapter_police.ViewHolder) convertView.getTag();
        }

        holder.police.setText(search.get(position).getPolice_station_name());
        holder.address.setText(search.get(position).getAddress());
        holder.contact.setText(search.get(position).getContact_no());







        return convertView;
    }
    static class ViewHolder {

        TextView police,address,contact;


    }
}
