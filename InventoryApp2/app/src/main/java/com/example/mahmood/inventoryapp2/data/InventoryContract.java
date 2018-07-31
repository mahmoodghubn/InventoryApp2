package com.example.mahmood.inventoryapp2.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class InventoryContract {

    private InventoryContract(){

    }

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.example.mahmood.inventoryapp2";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.mahmood.inventoryapp2/INVENTORIES/ is a valid path for
     * looking at inventory data. content://com.example.mahmood.inventoryapp2/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_INVENTORIES = "INVENTORIES";


    public static final class InventoryEntry implements BaseColumns {
        /** The content URI to access the inventory data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORIES);

        public final static String TABLE_NAME = "Inventory";

        //columns of the table
        public final static String _ID = BaseColumns._ID;

        public final static String PRODUCT_NAME ="name";

        public final static String PRODUCT_PRICE = "price";

        public final static String PRODUCT_QUANTITY = "quantity";

        public final static String SUPPLIER_NAME = "supplier";

        public final static String SUPPLIER_PHONE_NUMBER = "phone";

    }
}