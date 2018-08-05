package com.example.akash.therightstep;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class




iAdapter extends RecyclerView.Adapter<iAdapter.ViewHolder> {
    private List<input> list;
    private Context context;

    public iAdapter(List<input> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.ilist,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        input listItems =list.get(position);
        holder.head.setText(listItems.getiQuestion());
        holder.des.setText(listItems.getiRespose());
        holder.auth.setText("By:-"+listItems.getX());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView head,des,auth;

        public ViewHolder(View itemView) {
            super(itemView);
            head=(TextView)itemView.findViewById(R.id.iq);
            des=(TextView) itemView.findViewById(R.id.ir);
            auth=(TextView)itemView.findViewById(R.id.ia);
        }
    }
}