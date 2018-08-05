package com.example.akash.therightstep;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class pAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<get> list;
    private Context context;


    public pAdapter(List<get> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.give_list,parent,false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final get listItems =list.get(position);
        holder.head.setText(listItems.getQuestion());
        holder.des.setText(listItems.getDiscription());
        holder.authe.setText("By:-"+listItems.getAuth());
        holder.setItemClickListener(new ItemClickListner() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
                Intent intent=new Intent(context,give1.class);
                intent.putExtra("heading",list.get(position).getQuestion());
                intent.putExtra("discription",list.get(position).getDiscription());
                intent.putExtra("author",list.get(position).getAuth());
                intent.putExtra("uid",list.get(position).getUid());
                context.startActivity(intent);

                }
                else{
                    Toast.makeText(context, "Please long press to give suggestion"+list.get(position).getDiscription(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }





    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView head,des,auth;

        public ViewHolder(View itemView) {
            super(itemView);
            head=(TextView)itemView.findViewById(R.id.g1);
            des=(TextView) itemView.findViewById(R.id.g2);
            auth=(TextView)itemView.findViewById(R.id.g3);
        }
    }

}
class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
    public TextView head,des,authe;
private  ItemClickListner itemClickListner;
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        head=(TextView)itemView.findViewById(R.id.g1);
        des=(TextView) itemView.findViewById(R.id.g2);
        authe=(TextView)itemView.findViewById(R.id.g3);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

    }
    public void setItemClickListener(ItemClickListner itemClickListener){
        this.itemClickListner=itemClickListener;
    }

    @Override
    public void onClick(View v) {
itemClickListner.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListner.onClick(v,getAdapterPosition(),true);
        return true;
    }
}