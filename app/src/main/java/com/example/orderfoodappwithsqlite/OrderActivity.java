package com.example.orderfoodappwithsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.orderfoodappwithsqlite.Adapters.OrdersAdapter;
import com.example.orderfoodappwithsqlite.Database.DBHelper;
import com.example.orderfoodappwithsqlite.Models.OrdersModel;
import com.example.orderfoodappwithsqlite.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        DBHelper helper = new DBHelper(this);
        ArrayList<OrdersModel> list = helper.getOrders();
        OrdersAdapter adapter = new OrdersAdapter(this, list);
        binding.orderRecyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(linearLayoutManager);

    }
}