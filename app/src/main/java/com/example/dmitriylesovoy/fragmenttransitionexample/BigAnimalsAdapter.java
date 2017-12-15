package com.example.dmitriylesovoy.fragmenttransitionexample;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BigAnimalsAdapter extends AnimalsAdapter {

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        Context context = container.getContext();

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_card, container, false);
        final ImageView itemBg = itemView.findViewById(R.id.iv_card_bg);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            itemView.setTransitionName(context.getString(R.string.shared_card, animals.get(position).toString()));
        }
        itemBg.setImageResource(animals.get(position) % 2 == 0 ? R.drawable.horse : R.drawable.parrot);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onAnimalClick(itemBg, animals.get(position));
                }
            }
        });
        container.addView(itemView);

        return itemView;
    }

}
