package com.example.ar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<String> titles,ids;
    List<String> images;
    LayoutInflater inflater;
    oncardlistner listen;
    Context ctx;

    // Creating constructor to initiate adapter
    public Adapter(Context ctx, List<String> titles,List<String> images,List<String> ids,oncardlistner listen){
        this.ctx = ctx;
        this.titles = titles;
        this.images = images;
        this.ids = ids;
        this.inflater = LayoutInflater.from(ctx);
        this.listen = listen;
    }


    //For setting Layouts
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category,parent,false);
        return new ViewHolder(view,listen);

    }

    //For setting field values in the layout
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.title.setText(titles.get(position));
    holder.sdesc.setText("Learn about "+titles.get(position));
    Glide.with(ctx).load(images.get(position)).into(holder.image);

    }

    //For repeating the layouts
    @Override
    public int getItemCount() {
        System.out.println("size: "+titles.size());
        return titles.size();
    }
    //For reffering each and every field inside the viewholder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, sdesc;
        ImageView image;
        CardView one;
        oncardlistner listner;
        public ViewHolder(@NonNull View itemView,oncardlistner listner) {
            super(itemView);
            title = itemView.findViewById(R.id.tname);
            image = itemView.findViewById(R.id.image);
            sdesc = itemView.findViewById(R.id.sname);
            one = itemView.findViewById(R.id.one);
            this.listner = listner;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listner.oncardclick(getAdapterPosition());
        }
    }
    public interface oncardlistner{
        void oncardclick(int position);
    }
}
