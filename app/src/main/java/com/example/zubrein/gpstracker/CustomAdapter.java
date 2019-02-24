package com.example.zubrein.gpstracker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.zubrein.gpstracker.Model.search_place;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context c;
    RequestOptions myOptions = new RequestOptions()

           .centerCrop()
            .override(300, 300);
    String allData = "";
    List<search_place>search;

    public CustomAdapter(Context context, List<search_place>search) {
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

        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflate = (LayoutInflater)
                    c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.place_list2, parent, false);
            holder.card_image = convertView.findViewById(R.id.cardImage);
            holder.card_title=convertView.findViewById(R.id.cardTitle);




            convertView.setTag(holder);
        }else{
            holder = (CustomAdapter.ViewHolder) convertView.getTag();
        }

        holder.card_title.setText(search.get(position).getPlace_name());


          Glide.with(c).load("http://www.hotelhillviewbandarban.com/jayed/"+search.get(position).getPlace_image()).apply(myOptions).into(holder.card_image);



        return convertView;
    }
    static class ViewHolder {

        TextView item_name, item_description,receiver,transporter,status,card_title;
        ImageView card_image;
        Button track;


    }

}
