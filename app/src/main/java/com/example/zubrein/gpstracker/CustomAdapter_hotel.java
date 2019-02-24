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
import com.bumptech.glide.request.RequestOptions;
import com.example.zubrein.gpstracker.Model.hotel_info;

import java.util.List;

public class CustomAdapter_hotel extends BaseAdapter{

    private Context c;
    RequestOptions myOptions = new RequestOptions()

            .centerCrop()
            .override(300, 300);
    String allData = "";
    List<hotel_info> search;

    public CustomAdapter_hotel(Context context, List<hotel_info>search) {
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

        CustomAdapter_res.ViewHolder holder = new CustomAdapter_res.ViewHolder();
        if (convertView == null) {
            LayoutInflater inflate = (LayoutInflater)
                    c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.res_list, parent, false);
            holder.res_image = convertView.findViewById(R.id.res_image);
            holder.res_name=convertView.findViewById(R.id.res_name);
            holder.location=convertView.findViewById(R.id.location);




            convertView.setTag(holder);
        }else{
            holder = (CustomAdapter_res.ViewHolder) convertView.getTag();
        }

        holder.res_name.setText(search.get(position).getHotel_name());

        holder.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c,MapsActivity.class);
                intent.putExtra("lat",search.get(position).getHotel_lat());
                intent.putExtra("lon",search.get(position).getHotel_lon());
                c.startActivity(intent);

            }
        });

        Glide.with(c).load("http://www.hotelhillviewbandarban.com/jayed/"+search.get(position).getHotel_image()).apply(myOptions).into(holder.res_image);



        return convertView;
    }
    static class ViewHolder {

        TextView res_name,location, item_description,receiver,transporter,status,card_title;
        ImageView res_image;
        Button track;



    }
    
}
