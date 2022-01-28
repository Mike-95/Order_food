package com.example.orderfoodappwithsqlite.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodappwithsqlite.Database.DBHelper;
import com.example.orderfoodappwithsqlite.DetailActivity;
import com.example.orderfoodappwithsqlite.Models.MainModel;
import com.example.orderfoodappwithsqlite.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    Context mContext;
    ArrayList<MainModel> list;

    public MainAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.sample_mainfood, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final MainModel model = list.get(position);
        holder.foodImage.setImageResource(model.getImg());
        holder.name.setText(model.getName());
        holder.orderPrice.setText(model.getPrice());
        holder.description.setText(model.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("image", model.getImg());
                intent.putExtra("name", model.getName());
                intent.putExtra("price", model.getPrice());
                intent.putExtra("desc", model.getDescription());
                intent.putExtra("type", 2);
                mContext.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView foodImage;
        TextView name, orderPrice, description;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.foodImage);
            name = itemView.findViewById(R.id.name);
            orderPrice = itemView.findViewById(R.id.orderPrice);
            description = itemView.findViewById(R.id.description);


        }
    }
}
