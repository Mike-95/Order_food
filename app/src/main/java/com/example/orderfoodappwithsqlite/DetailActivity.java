package com.example.orderfoodappwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.orderfoodappwithsqlite.Database.DBHelper;
import com.example.orderfoodappwithsqlite.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper dbHelper = new DBHelper(this);

        if (getIntent().getIntExtra("type", 0) == 2) {
        /*
        this part coming from Main Adapter class
         */
            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            binding.detailImage.setImageResource(image);
            binding.foodName.setText(name);
            binding.priceDetail.setText(String.format("%d", price));
            binding.detailDescription.setText(description);


            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    boolean isInserted = dbHelper.insertOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            name,
                            description,
                            Integer.parseInt(binding.quantity.getText().toString())
                    );
                    if (isInserted)
                        Toast.makeText(DetailActivity.this, "Data Success..", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = dbHelper.getOrderById(id);
            int image = cursor.getInt(4);
            binding.detailImage.setImageResource(image);
            binding.foodName.setText(cursor.getString(6));
            binding.priceDetail.setText(String.format("%d", cursor.getInt(3)));
            binding.detailDescription.setText(cursor.getString(7));

            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.insertButton.setText("Update Now");
            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   boolean isUpdated = dbHelper.updateOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.priceDetail.getText().toString()),
                            image,
                            binding.detailDescription.getText().toString(),
                            binding.foodName.getText().toString(),
                            1,
                            id
                    );
                   if (isUpdated)
                       Toast.makeText(DetailActivity.this, "Updated.", Toast.LENGTH_SHORT).show();
                   else
                       Toast.makeText(DetailActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                } 
            });

        }

    }
}