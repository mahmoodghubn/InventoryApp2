package com.example.mahmood.inventoryapp2.data;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.mahmood.inventoryapp2.data.InventoryContract.InventoryEntry;


public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inventories.db";

    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_INVENTORY_TABLE =  "CREATE TABLE " + InventoryEntry.TABLE_NAME + " ("
                + InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InventoryEntry.PRODUCT_NAME + " INTEGER NOT NULL, "
                + InventoryEntry.PRODUCT_PRICE + " INTEGER NOT NULL, "
                + InventoryEntry.PRODUCT_QUANTITY + " INTEGER NOT NULL, "
                + InventoryEntry.SUPPLIER_NAME + " TEXT NOT NULL, "
                + InventoryEntry.SUPPLIER_PHONE_NUMBER + " INTEGER NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}

