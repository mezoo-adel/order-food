package com.example.resturant;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Array;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


public class OrderFragment extends Fragment {

    public OrderFragment() {
        // Required empty public constructor
    }

    TextView food_quantity;
    TextView textView0;
    TextView textView;
    ImageView imageView;
    TextView textView2;
    TextView date;
    int quantity = 1;
    int price;
    int total;

    Cursor cursor, totalPrice;
    OrdersDataBase dataBase;
    SQLiteDatabase db;

    ImageButton imageButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_order, container, false);

        dataBase = new OrdersDataBase(getContext());
        db = dataBase.getReadableDatabase();
        cursor = db.rawQuery(" SELECT * FROM ordersTable ;", null);
        totalPrice = db.rawQuery("SELECT SUM (totalPrice) FROM ordersTable;", null);

        ListView listView = view.findViewById(R.id.list_orders);
        MyCursorAdapter cursorAdapter = new MyCursorAdapter(getContext(), cursor);
        listView.setAdapter(cursorAdapter);

        date = view.findViewById(R.id.date);
        SimpleDateFormat sdf = new SimpleDateFormat("E, MMM dd 'at' h:mma");
        String currentDateandTime = sdf.format(new Date());
        date.setText(currentDateandTime);

        return view;
    }

    private class MyCursorAdapter extends CursorAdapter {

        public MyCursorAdapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {

            return LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_fragment_layout, parent, false);
        }


        @Override
        public void bindView(final View view, final Context context, final Cursor cursor) {
            final View convertView = view;
            textView = convertView.findViewById(R.id.order_name);
            textView.setText(cursor.getString(cursor.getColumnIndex("name")));
            food_quantity = convertView.findViewById(R.id.order_quantity);
            quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));
            food_quantity.setText(String.valueOf(quantity));
            price = cursor.getInt(cursor.getColumnIndex("price"));
            textView2 = convertView.findViewById(R.id.order_price);
            textView2.setText(String.valueOf(price));
            imageView = convertView.findViewById(R.id.order_image);
            imageView.setImageResource(cursor.getInt(cursor.getColumnIndex("image")));

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setTitle("حذف السلة").setMessage(R.string.empty_cart);
                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    alertDialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation-
                            db.execSQL("DELETE FROM ordersTable ;");
                            cursor.requery();
                            notifyDataSetChanged();
                            textView0.setText("Total Price " + 0 + " EGP");
                        }
                        /* A null listener allows the button to dismiss the dialog and take no further action. */
                    }).setNegativeButton(android.R.string.no, null).setIcon(android.R.drawable.ic_dialog_alert).show();
                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        textView0 = getView().findViewById(R.id.total_price);
        if (totalPrice.moveToFirst()) {
            total = totalPrice.getInt(0);
        }
        textView0.setText("Total Price "+total);

        imageButton = getView().findViewById(R.id.order_cancel);

        Button button = getView().findViewById(R.id.finish_order);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView myList = getView().findViewById(R.id.list_orders);
                int itemsCount = myList.getChildCount();
                String[] names = new String[itemsCount];
                String[] quantities = new String[itemsCount];
                int[] prices = new int[itemsCount];

                for (int i = 0; i < itemsCount; i++) {
                    View view = myList.getChildAt(i);
                    String itemQuantity = ((TextView) view.findViewById(R.id.order_quantity)).getText().toString();
                    String itemName = ((TextView) view.findViewById(R.id.order_name)).getText().toString();
                    int itemPrice = Integer.parseInt(((TextView) view.findViewById(R.id.order_price)).getText().toString());

                    names[i] = itemName;
                    prices[i] = itemPrice;
                    quantities[i] = itemQuantity;
                }

                ProfileActivity profileActivity = new ProfileActivity();
                profileActivity.preferences = getContext().getSharedPreferences(profileActivity.sharedPrefFile, MODE_PRIVATE);

                String totalPrice = textView0.getText().toString();
                String message = date.getText() + "\n\n" + "Name :" + profileActivity.preferences.getString("myName", null) + "\n Address :" + profileActivity.preferences.getString("myAddress", null) + "\n Phone :" + profileActivity.preferences.getString("myPhone", null) + "\n Order" + Arrays.toString(names) + "\n Order Price" + Arrays.toString(prices) + "\n Quantity" + Arrays.toString(quantities) + "\n " + totalPrice;
              //  String phoneNumberWithCountryCode = "+201033931552";
              //  Intent openWhatsap = new Intent(Intent.ACTION_VIEW);
               // openWhatsap.setData(Uri.parse(String.format("https://api.whatsapp.com/send?phone=%s&text=%s", phoneNumberWithCountryCode, message)));
               // startActivity(openWhatsap);
                // db.execSQL("DELETE FROM ordersTable ");
                Intent intent  = new Intent(getContext(),PayingActivity.class);
                intent.putExtra("orderFinal",message);
                intent.putExtra("total",total);
                startActivity(intent);

            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
    }

}