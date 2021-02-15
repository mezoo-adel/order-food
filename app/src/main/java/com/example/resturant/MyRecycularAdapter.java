package com.example.resturant;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecycularAdapter extends RecyclerView.Adapter {

    int[] price;
    int[] image;
    String[] name;
    int[] priceSize;

    public MyRecycularAdapter(int[] price, int[] image, String[] name,int[] priceSize) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.priceSize=priceSize;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(cardView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        final CardView cardView = myViewHolder.getCardView();

        ImageView imageView = cardView.findViewById(R.id.food_image);
        imageView.setImageResource(image[position]);
        final TextView textView = cardView.findViewById(R.id.food_name);
        textView.setText(name[position]);
        final TextView textView1 = cardView.findViewById(R.id.food_price);
        textView1.setText(price[position] + " EGP");

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToDescription = new Intent(cardView.getContext(), FoodDescription.class);
                goToDescription.putExtra("images", image[position]);
                goToDescription.putExtra("names", name[position]);
                goToDescription.putExtra("prices", price[position]);
                goToDescription.putExtra("id", position);
if (priceSize != null){goToDescription.putExtra("priceSize", priceSize[position]);}
                cardView.getContext().startActivity(goToDescription);
            }
        });

/*        Button plus = cardView.findViewById(R.id.plus);
        Button minus = cardView.findViewById(R.id.minus);
        final TextView food_quantity = cardView.findViewById(R.id.food_quantity);

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    food_quantity.setText(String.valueOf(quantity+1));
                    quantity =Integer.parseInt(food_quantity.getText().toString()) ;


                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        quantity = Integer.parseInt(food_quantity.getText().toString());
                    if (quantity > 0 ) { food_quantity.setText(String.valueOf(quantity - 1));}
                }
            });

            ImageButton addToCart = cardView.findViewById(R.id.food_add_to_cart);
            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  new Intent(cardView.getContext(),OrderActivity.class).putExtra("quantity",5);
                }
            });*/
    }


    @Override
    public int getItemCount() {
        return name.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public MyViewHolder(@NonNull CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }

        public CardView getCardView() {
            return cardView;
        }
    }
}
