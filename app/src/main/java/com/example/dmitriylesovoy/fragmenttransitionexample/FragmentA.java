package com.example.dmitriylesovoy.fragmenttransitionexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;


public class FragmentA extends BaseFragment implements AnimalsAdapter.AnimalClickListener {

    private ViewPager vpBigCards;
    private AnimalsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getRootLayout() {
        return R.layout.fragment_a;
    }

    @Override
    protected void parseParameters(Bundle parameters) {

    }

    @Override
    protected void initViews(View rootView) {
        Button btnAdd = rootView.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addAnimal();
            }
        });
        vpBigCards = rootView.findViewById(R.id.vp_big_cards);
        adapter = new AnimalsAdapter();
        adapter.setAnimals(0,1,2,3,4,5);
        adapter.setClickListener(this);
        vpBigCards.setAdapter(adapter);
    }

    @Override
    public void onAnimalClick(ImageView animalImage, int animalId) {
        FragmentB nextFragment = new FragmentB();
        Bundle parameters = new Bundle();
        parameters.putInt(ANIMAL_ID, animalId);
        parameters.putIntegerArrayList(ANIMAL_LIST, adapter.getAnimals());
        nextFragment.setArguments(parameters);
        getMainActivity().performTransition(this, nextFragment, animalImage);
    }
}
