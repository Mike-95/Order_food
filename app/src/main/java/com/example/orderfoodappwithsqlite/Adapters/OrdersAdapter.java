package com.example.orderfoodappwithsqlite.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodappwithsqlite.Database.DBHelper;
import com.example.orderfoodappwithsqlite.DetailActivity;
import com.example.orderfoodappwithsqlite.Models.OrdersModel;
import com.example.orderfoodappwithsqlite.R;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.viewHolder> {
    Context mContext;
    ArrayList<OrdersModel> list;

    public OrdersAdapter(Context mContext, ArrayList<OrdersModel> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_sample, parent, false);
        return new viewHolder(view);
    }

    /*
    this part of code referred to my order page
     */
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final OrdersModel model = list.get(position);
        holder.orderImage.setImageResource(model.getOrderImage());
        holder.orderItemName.setText(model.getSoldItemName());
        holder.orderNumber.setText(model.getOrderNumber());
        holder.price.setText(model.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("id", Integer.parseInt(model.getOrderNumber()));
                intent.putExtra("type", 1);
                mContext.startActivity(intent);

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DBHelper dbHelper = new DBHelper(mContext);
                if (dbHelper.deletedOrder(model.getOrderNumber()) > 0){
                    Toast.makeText(mContext, "Deleted.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext, "Error.", Toast.LENGTH_SHORT).show();
                }
                    return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView orderImage;
        TextView orderItemName, orderNumber, price;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            orderImage = itemView.findViewById(R.id.orderImage);
            orderItemName = itemView.findViewById(R.id.orderItemName);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            price = itemView.findViewById(R.id.price);

        }
    }
}
