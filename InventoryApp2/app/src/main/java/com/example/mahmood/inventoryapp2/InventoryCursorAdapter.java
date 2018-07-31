package com.example.mahmood.inventoryapp2;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.mahmood.inventoryapp2.data.DbHelper;
import com.example.mahmood.inventoryapp2.data.InventoryContract.InventoryEntry;

public class InventoryCursorAdapter extends CursorAdapter {


    /**
     * Constructs a new {@link InventoryCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public InventoryCursorAdapter(Context context, Cursor c ) {
        super(context, c, 0 /* flags */);

    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }


    /**
     * This method binds the product data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current product can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        // Find individual views that we want to modify in the list item layout
        TextView productTextView = (TextView) view.findViewById(R.id.product);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        final TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
        
        // Find the columns of product attributes that we're interested in
        int productColumnIndex = cursor.getColumnIndex(InventoryEntry.PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.PRODUCT_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.PRODUCT_QUANTITY);

        // Read the product attributes from the Cursor for the current product
        String product = cursor.getString(productColumnIndex);
        final String price = cursor.getString(priceColumnIndex);
        final String quantity = cursor.getString(quantityColumnIndex);

        // Update the TextViews with the attributes for the current product
        productTextView.setText(product);
        priceTextView.setText(price);
        quantityTextView.setText(quantity);

        //get the id of the current product
        int idColumnIndex = cursor.getColumnIndex(InventoryEntry._ID);
        final String id = cursor.getString(idColumnIndex);
        final int idInt = Integer.parseInt(id);

        Button sale = (Button) view.findViewById(R.id.sale_button);
        sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the quantity
                int intQuantity = Integer.parseInt(quantity);

                //update the quantity
                if(intQuantity>0)
                    intQuantity--;

                //get the uri of the current product
                Uri mCurrentInventoryUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, idInt);

                //update the quantity using the current product's id
                ContentValues values = new ContentValues();
                values.put(InventoryEntry.PRODUCT_QUANTITY, intQuantity);
                String[] idString = {id};
                int rowsAffected = context.getContentResolver().update(mCurrentInventoryUri, values, InventoryEntry._ID, idString);

            }
        });
    }
}
