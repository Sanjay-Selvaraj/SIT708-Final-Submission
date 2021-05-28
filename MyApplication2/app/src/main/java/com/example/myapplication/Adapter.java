package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<String> titles,ids;
    LayoutInflater inflater;
    oncardlistner listen;
    public Adapter(Context ctx, List<String> titles,List<String> ids,oncardlistner listen){
        this.titles = titles;
        this.ids = ids;
        this.inflater = LayoutInflater.from(ctx);
        this.listen = listen;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category,parent,false);
        return new ViewHolder(view,listen);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.title.setText(titles.get(position));
    holder.sdesc.setText("Learn about "+titles.get(position));

    }


    @Override
    public int getItemCount() {
        System.out.println("size: "+titles.size());
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, sdesc;
        CardView one;
        oncardlistner listner;
        public ViewHolder(@NonNull View itemView,oncardlistner listner) {
            super(itemView);
            title = itemView.findViewById(R.id.tname);
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
