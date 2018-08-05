package com.example.akash.therightstep;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    private List<post> list;
    private Context context;

    public myAdapter(List<post> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.home,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        post listItems =list.get(position);
        holder.head.setText(listItems.getNewsHeading());
        holder.des.setText(listItems.getNewsDiscription());
        holder.auth.setText("By:-"+listItems.getUid());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView head,des,auth;

        public ViewHolder(View itemView) {
            super(itemView);
            head=(TextView)itemView.findViewById(R.id.h1);
            des=(TextView) itemView.findViewById(R.id.dis);
            auth=(TextView)itemView.findViewById(R.id.aut);
        }
    }
}