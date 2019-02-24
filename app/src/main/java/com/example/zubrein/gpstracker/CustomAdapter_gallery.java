package com.example.zubrein.gpstracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.zubrein.gpstracker.Model.gallery_info;

import java.util.List;

public class CustomAdapter_gallery extends RecyclerView.Adapter<CustomAdapter_gallery.MyviewHolder>{


    Context context;
    List<gallery_info> gallery;
    RequestOptions myOptions = new RequestOptions()

            .centerCrop()
            .override(300, 300);

    public CustomAdapter_gallery(Context context, List<gallery_info> gallery) {
        this.context = context;
        this.gallery = gallery;
    }

    public void setgallery(List<gallery_info> gallery) {
        this.gallery = gallery;
        notifyDataSetChanged();
    }

    @Override
    public CustomAdapter_gallery.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_list,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter_gallery.MyviewHolder holder, int position) {

        Glide.with(context).load("http://www.hotelhillviewbandarban.com/jayed/"+gallery.get(position).getImage()).apply(myOptions).into(holder.image);

    }

    @Override
    public int getItemCount() {
        if(gallery != null){
            return gallery.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        ImageView image;

        public MyviewHolder(View itemView) {
            super(itemView);

            image = (ImageView)itemView.findViewById(R.id.gallery_info);
        }
    }
}
