package com.example.resturant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CartFragment extends Fragment {
  public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
        final View view = getView();
        Button button = view.findViewById(R.id.continue_shopping);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHome = new Intent(getContext(),MainActivity.class);
                //يمسح ال stack  و ميقعدش يطلع ويرجع بين ال mainActivity , cartFragment
                backHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backHome);
            }
        });

    }
}