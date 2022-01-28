package com.example.orderfoodappwithsqlite.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.orderfoodappwithsqlite.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBName = "mydatabase.db";
    final static int DBVersion = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(
                "create table orders " +
                        "( id integer primary key autoincrement," +
                        "name text," +
                        "phone text," +
                        "price int," +
                        "image int," +
                        "quantity," +
                        "description text," +
                        "foodName text)"

        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS orders");
        onCreate(sqLiteDatabase);

    }

    public boolean insertOrder(String name, String phone, int price, int image, String desc, String foodName, int quantity) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("quantity", quantity);
        values.put("description", desc);
        values.put("foodName", foodName);
        long id = database.insert("orders", null, values);
        if (id <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<OrdersModel> getOrders() {
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders", null);
        if (cursor.moveToNext()) {
            while (cursor.moveToNext()) {
                OrdersModel model = new OrdersModel();
                model.setOrderNumber(cursor.getInt(0) + "");
                model.setSoldItemName(cursor.getString(6));
                model.setOrderImage(cursor.getInt(4));
                model.setPrice(cursor.getInt(3) + "");
                orders.add(model);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where id = " + id, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

//        cursor.close();
//        database.close();
        return cursor;

    }

    public boolean updateOrder(String name, String phone, int price, int image, String desc, String foodName, int quantity, int id) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("quantity", quantity);
        values.put("description", desc);
        values.put("foodName", foodName);
        long row = database.update("orders", values, "id", null);
        if (row <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public int deletedOrder(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders", "id" + id, null);

    }
}
