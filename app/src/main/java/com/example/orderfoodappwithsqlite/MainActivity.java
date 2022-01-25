package com.example.orderfoodappwithsqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.orderfoodappwithsqlite.Adapters.MainAdapter;
import com.example.orderfoodappwithsqlite.Models.MainModel;
import com.example.orderfoodappwithsqlite.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.burger, "Burger", "5", "Chicken Burger with extra cheese"));
        list.add(new MainModel(R.drawable.burger, "Portebllo Mushhroom", "6", "Chicken Burger with extra cheese"));
        list.add(new MainModel(R.drawable.burger, "Burger2", "7", "Chicken Burger with extra cheese"));
        list.add(new MainModel(R.drawable.burger, "Burger3", "8", "Chicken Burger with extra cheese"));
        list.add(new MainModel(R.drawable.burger, "Burger4", "9", "Chicken Burger with extra cheese"));

        list.add(new MainModel(R.drawable.burger, "Burger", "5", "Chicken Burger with extra cheese"));
        list.add(new MainModel(R.drawable.burger, "Portebllo Mushhroom", "6", "Chicken Burger with extra cheese"));
        list.add(new MainModel(R.drawable.burger, "Burger2", "7", "Chicken Burger with extra cheese"));
        list.add(new MainModel(R.drawable.burger, "Burger3", "8", "Chicken Burger with extra cheese"));
        list.add(new MainModel(R.drawable.burger, "Burger4", "9", "Chicken Burger with extra cheese"));

        MainAdapter adapter = new MainAdapter(list, this);
        binding.mainRecyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.mainRecyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}