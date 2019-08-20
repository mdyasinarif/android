package com.yasin.sqlfinalcurd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDbAdapter {
    MyDbHelper helper;

    public MyDbAdapter(Context context) {
        this.helper = new MyDbHelper(context);
    }

    static class MyDbHelper extends SQLiteOpenHelper {


        // database information
        static final String DB_NAME = "PROD.DB";
        //database version
        static final int DB_VERSION = 1;

        public MyDbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        //Table Name
        public static final String TABLE_NAME = "PRODUCT";
        // Table Colums
        public static final String ID = "id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String QTY = "qty";

        //Creating table Quary
        private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + PRODUCT_NAME + " TEXT NOT NULL,"
                + QTY + " INTEGER NOT NULL)";


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
    // end of helper class

    // start method
    public long insertData(Product product) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(MyDbHelper.PRODUCT_NAME, product.getProductname());
        cv.put(MyDbHelper.QTY, product.getQuantity());
        long id = db.insert(MyDbHelper.TABLE_NAME, null, cv);
        return id;
    }

    public long updateData(Product product) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String selection = MyDbHelper.ID + " = " + product.getId();
        ContentValues cv = new ContentValues();
        cv.put(MyDbHelper.PRODUCT_NAME, product.getProductname());
        cv.put(MyDbHelper.QTY, product.getQuantity());
        long id = db.update(MyDbHelper.TABLE_NAME, cv,selection,null);
        return id;
    }

    public List<Product> getList() {
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {MyDbHelper.ID,
                MyDbHelper.PRODUCT_NAME, MyDbHelper.QTY};

        Cursor cursor = db.query(
                MyDbHelper.TABLE_NAME,// the table to query
                projection,         // the array if coloum to return pass null to get all
                null,       // the colums for the where clause
                null,
                null,
                null,
                null
        );
        List<Product> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Product product = new Product(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), Integer.parseInt(cursor.getString(2)));
            list.add(product);
        }
        cursor.close();
        return list;
    }
    public void deleteProduct(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM  " + MyDbHelper.TABLE_NAME + " WHERE " + MyDbHelper.ID + "='"+ id + "'");
        db.close();
    }

    public Product findProductById(int id){
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {MyDbHelper.ID,
                MyDbHelper.PRODUCT_NAME, MyDbHelper.QTY};

        String selection = MyDbHelper.ID + " = " + id;

        Cursor cursor = db.query(
                MyDbHelper.TABLE_NAME,// the table to query
                projection,         // the array if coloum to return pass null to get all
                selection,
                null,
                null,
                null,
                null);
        Product product = new Product();
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            product.setId(Integer.parseInt(cursor.getString(0)));
            product.setProductname(cursor.getString(1));
            product.setQuantity(Integer.parseInt(cursor.getString(2)));
        }else{
            product = null;
        }
        return product;
    }
}
