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
import com.example.zubrein.gpstracker.Model.hospital_info;

import java.util.List;

public class CustomAdapter_hospital extends BaseAdapter {
    private Context c;

    List<hospital_info> search;

    public CustomAdapter_hospital(Context context, List<hospital_info>search) {
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

        CustomAdapter_hospital.ViewHolder holder = new CustomAdapter_hospital.ViewHolder();
        if (convertView == null) {
            LayoutInflater inflate = (LayoutInflater)
                    c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.hospital_list, parent, false);
            holder.hospital = convertView.findViewById(R.id.hospital);
            holder.address = convertView.findViewById(R.id.address);
            holder.contact = convertView.findViewById(R.id.contact);
            holder.location=convertView.findViewById(R.id.location);



            convertView.setTag(holder);
        }else{
            holder = (CustomAdapter_hospital.ViewHolder) convertView.getTag();
        }

        holder.hospital.setText(search.get(position).getHospital_name());
        holder.address.setText(search.get(position).getAddress());
        holder.contact.setText(search.get(position).getContact_no());
        holder.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c,MapsActivity.class);
                intent.putExtra("lat",search.get(position).getLat());
                intent.putExtra("lon",search.get(position).getLon());
                c.startActivity(intent);

            }
        });








        return convertView;
    }
    static class ViewHolder {

        TextView hospital,address,contact,location;


    }
}
