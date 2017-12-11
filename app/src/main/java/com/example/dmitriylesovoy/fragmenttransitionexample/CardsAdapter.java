package com.example.dmitriylesovoy.fragmenttransitionexample;


import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;

public class CardsAdapter extends PagerAdapter {

    private List<Integer> animals;
    private AnimalClickListener clickListener;

    public void setAnimals(Integer... animalsId) {
        this.animals = Arrays.asList(animalsId);
        notifyDataSetChanged();
    }

    public void setClickListener(AnimalClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        Context context = container.getContext();

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_card, container, false);
        final ImageView itemBg = itemView.findViewById(R.id.iv_card_bg);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            itemBg.setTransitionName(context.getString(R.string.shared_card, animals.get(position).toString()));
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



    @Override
    public int getCount() {
        return animals == null ? 0 : animals.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            container.removeView((View) object);
        }
    }

    public int getAnimalPositionById(int selectedAnimalId) {
        return animals.indexOf(selectedAnimalId);
    }

    interface AnimalClickListener {
        void onAnimalClick(ImageView animalImage, int animalId);
    }

}
